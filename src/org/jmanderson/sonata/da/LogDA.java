package org.jmanderson.sonata.da;

/**
 * New DAO for the log table.
 */

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StandardBasicTypes;
import org.jmanderson.sonata.CalcService;
import org.jmanderson.sonata.MPGBean;
import org.jmanderson.sonata.hibernate.Log;
import org.jmanderson.sonata.hibernate.SessionFactory;

public class LogDA {

	private LogDA() {
	}

	public static Log getLogById(int id) {

		Log log = null;
		try {
			Session session = SessionFactory.getSession();
			log = (Log) session.load(Log.class, Integer.valueOf(id));
		} catch (Exception e) {
			// TODO logging
			System.out.println(e);
		} finally {
//			SessionFactory.closeSession();
		}

		return log;
	}

	public static Log getLastEntry() {

		Log log = null;
		try {
			Session session = SessionFactory.getSession();
			Criteria criteria = session.createCriteria(Log.class);
			DetachedCriteria maxId = DetachedCriteria.forClass(Log.class)
					.setProjection(Property.forName("id").max());
			criteria.add(Property.forName("id").eq(maxId));
			log = (Log) criteria.uniqueResult();
		} catch (Exception e) {
			// TODO logging
			System.out.println(e);
		} finally {
//			SessionFactory.closeSession();
		}

		return log;
	}

	public static Log getFirstEntry() {

		Log log = null;
		try {
			Session session = SessionFactory.getSession();
			Criteria criteria = session.createCriteria(Log.class);
			DetachedCriteria minId = DetachedCriteria.forClass(Log.class)
					.setProjection(Property.forName("id").min());
			criteria.add(Property.forName("id").eq(minId));
			log = (Log) criteria.uniqueResult();
		} catch (Exception e) {
			// TODO logging
			System.out.println(e);
		} finally {
//			SessionFactory.closeSession();
		}

		return log;
	}

	public static List getLastFiveGasEntries() {

		List list = new ArrayList();
		try {
			Session session = SessionFactory.getSession();
			Criteria criteria = session.createCriteria(Log.class);
			criteria.add(Restrictions.gt("gallons", Float.valueOf("0.000")));
			criteria.addOrder(Order.desc("id"));
			criteria.setMaxResults(5);
			list = criteria.list();
		} catch (Exception e) {
			// TODO logging
			System.out.println(e);
		} finally {
//			SessionFactory.closeSession();
		}

		return list;
	}

	public static int getLastGasMileage() {

		int mileage = -1;

		try {
			Session session = SessionFactory.getSession();
			Query query = session
					.createSQLQuery(
							"select max(mileage) as mileage from log where gallons > 0.000")
					.addScalar("mileage", StandardBasicTypes.INTEGER);
			mileage = ((Integer) query.uniqueResult()).intValue();
		} catch (Exception e) {
			// TODO logging
			System.out.println(e);
		} finally {
//			SessionFactory.closeSession();
		}

		return mileage;
	}

	public static List getLastFiveServiceEntries() {

		List list = new ArrayList();
		try {
			Session session = SessionFactory.getSession();
			Criteria criteria = session.createCriteria(Log.class);
			criteria.add(Restrictions.eq("gallons", Float.valueOf("0.000")));
			criteria.addOrder(Order.desc("id"));
			criteria.setMaxResults(5);
			list = criteria.list();
		} catch (Exception e) {
			// TODO logging
			System.out.println(e);
		} finally {
//			SessionFactory.closeSession();
		}

		return list;
	}

	public static void addOrUpdate(Log log) {

		Transaction txn = null;
		try {
			Session session = SessionFactory.getSession();
			txn = session.beginTransaction();
			session.saveOrUpdate(log);
			txn.commit();
		} catch (Exception e) {
			// TODO logging
			System.out.println(e);
			txn.rollback();
		} finally {
//			SessionFactory.closeSession();
		}
	}

	public static List getAll() {

		List list = new ArrayList();
		try {
			Session session = SessionFactory.getSession();
			Criteria criteria = session.createCriteria(Log.class);
			criteria.addOrder(Order.asc("date")).addOrder(Order.asc("mileage"));
			list = criteria.list();
		} catch (Exception e) {
			// TODO logging
			System.out.println(e);
		} finally {
//			SessionFactory.closeSession();
		}

		return list;
	}

	public static float getTotalGallons(String id1, String id2) {
		float gallons = 0;

		try {
			Session session = SessionFactory.getSession();
			StringBuffer sb = new StringBuffer();
			sb
					.append(
							"select sum(x.gallons) as gsum from (select gallons from log where id > ")
					.append(id1).append(" and id <= ").append(id2).append(
							") as x");
			Query query = session.createSQLQuery(sb.toString()).addScalar(
					"gsum", StandardBasicTypes.FLOAT);
//			Object[] result = (Object[]) query.uniqueResult();
			gallons = ((Float) query.uniqueResult()).floatValue();
		} catch (Exception e) {
			// TODO logging
			System.out.println(e);
		} finally {
//			SessionFactory.closeSession();
		}

		return gallons;
	}

	public static MPGBean getMPGBean(NumberFormat nf1, NumberFormat cnf2) {
		MPGBean mpg = new MPGBean();

		try {
			Session session = SessionFactory.getSession();
			Query query = session
					.createSQLQuery(
							"select ((max(mileage)-min(mileage))/sum(gallons)) as mpg, (sum(cost)/sum(gallons)) as price from log")
					.addScalar("mpg", StandardBasicTypes.FLOAT).addScalar("price",
							StandardBasicTypes.FLOAT);
			Object[] result = (Object[]) query.uniqueResult();
			mpg.setOverall_MPG(nf1.format(((Float) result[0]).floatValue()));
			mpg.setAverage_Price(cnf2.format(((Float) result[1]).floatValue()));
			query = session
					.createSQLQuery(
							"select date, mileage, spgetnextservice() as serv from log where id = spgetprevservice()")
					.addScalar("date", StandardBasicTypes.DATE).addScalar("mileage",
							StandardBasicTypes.INTEGER).addScalar("serv",
							StandardBasicTypes.STRING);
			result = (Object[]) query.uniqueResult();
			Date lastServiceDate = (Date) result[0];
			int lastServiceMileage = ((Integer) result[1]).intValue();
			mpg.setNextServiceMileage((String) result[2]);
			int minId = ((Integer) session.createCriteria(Log.class)
					.setProjection(Property.forName("id").min()).uniqueResult())
					.intValue();
			query = session.createSQLQuery(
					"select date from log where id = :minId").addScalar("date",
					StandardBasicTypes.DATE).setInteger("minId", minId);
			Date beginDate = (Date) query.uniqueResult();
			int maxId = ((Integer) session.createCriteria(Log.class)
					.setProjection(Property.forName("id").max()).uniqueResult())
					.intValue();
			query = session.createSQLQuery(
					"select date, mileage from log where id = :maxId")
					.addScalar("date", StandardBasicTypes.DATE).addScalar("mileage",
							StandardBasicTypes.INTEGER).setInteger("maxId", maxId);
			result = (Object[]) query.uniqueResult();
			Date latestDate = (Date) result[0];
			int latestMileage = ((Integer) result[1]).intValue();
			mpg.setNextServiceDate(CalcService.calculateNextServiceDate(
					beginDate, lastServiceDate, latestDate, lastServiceMileage,
					latestMileage));
		} catch (Exception e) {
			// TODO logging
			System.out.println(e);
		} finally {
//			SessionFactory.closeSession();
		}

		return mpg;
	}
}
