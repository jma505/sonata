package org.jmanderson.sonata.da;

/**
 * New DAO for the log table.
 */

// 
//
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StandardBasicTypes;
import org.jmanderson.sonata.CalcService;
import org.jmanderson.sonata.MPGBean;
import org.jmanderson.sonata.hibernate.Log;
import org.hibernate.SessionFactory;


public class LogDA {

	private static final SessionFactory factory = new Configuration().configure().buildSessionFactory();
	
	private LogDA() {
	}

	public static Log getLogById(int id) {

		Log log = null;
		try {
			Session session = factory.getCurrentSession();
			session.beginTransaction();
			log = (Log) session.load(Log.class, Integer.valueOf(id));
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO logging
			System.out.println(e);
			factory.getCurrentSession().getTransaction().rollback();
		} finally {
			// SessionFactory.closeSession();
			factory.getCurrentSession().close();
		}

		return log;
	}

	public static void deleteLogById(int id) {
		Log log = new Log();
		log.setId(Integer.valueOf(id));
		try {
			Session session = factory.getCurrentSession();
			session.beginTransaction();
			session.delete(log);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			// TODO logging
			System.out.println(e);
			factory.getCurrentSession().getTransaction().rollback();
		} finally {
			factory.getCurrentSession().close();
		}
	}
	
	public static Log getLastEntry() {

		Log log = null;
		try {
			Session session = factory.getCurrentSession();
			session.beginTransaction();
			Criteria criteria = session.createCriteria(Log.class);
			DetachedCriteria maxId = DetachedCriteria.forClass(Log.class)
					.setProjection(Property.forName("id").max());
			criteria.add(Property.forName("id").eq(maxId));
			log = (Log) criteria.uniqueResult();
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO logging
			System.out.println(e);
			factory.getCurrentSession().getTransaction().rollback();
		} finally {
			// SessionFactory.closeSession();
			factory.getCurrentSession().close();
		}

		return log;
	}

	public static Log getFirstEntry() {

		Log log = null;
		try {
			Session session = factory.getCurrentSession();
			session.beginTransaction();
			Criteria criteria = session.createCriteria(Log.class);
			DetachedCriteria minId = DetachedCriteria.forClass(Log.class)
					.setProjection(Property.forName("id").min());
			criteria.add(Property.forName("id").eq(minId));
			log = (Log) criteria.uniqueResult();
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO logging
			System.out.println(e);
			factory.getCurrentSession().getTransaction().rollback();
		} finally {
			// SessionFactory.closeSession();
			factory.getCurrentSession().close();
		}

		return log;
	}

	public static List getLastFiveGasEntries() {

		List list = new ArrayList();
		try {
			Session session = factory.getCurrentSession();
			session.beginTransaction();
			Criteria criteria = session.createCriteria(Log.class);
			criteria.add(Restrictions.eq("service", Boolean.valueOf(false)));
			criteria.addOrder(Order.desc("mileage"));
			criteria.setMaxResults(5);
			list = criteria.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO logging
			System.out.println(e);
			factory.getCurrentSession().getTransaction().rollback();
		} finally {
			// SessionFactory.closeSession();
			factory.getCurrentSession().close();
		}

		return list;
	}

	public static int getLastGasMileage() {

		int mileage = -1;

		try {
			Session session = factory.getCurrentSession();
			session.beginTransaction();
			Query query = session
					.createSQLQuery(
							"select max(mileage) as mileage from log where gallons > 0.000")
					.addScalar("mileage", StandardBasicTypes.INTEGER);
			mileage = ((Integer) query.uniqueResult()).intValue();
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO logging
			System.out.println(e);
			factory.getCurrentSession().getTransaction().rollback();
		} finally {
			// SessionFactory.closeSession();
			factory.getCurrentSession().close();
		}

		return mileage;
	}

	public static List getLastFiveServiceEntries() {

		List list = new ArrayList();
		try {
			Session session = factory.getCurrentSession();
			session.beginTransaction();
			Criteria criteria = session.createCriteria(Log.class);
			criteria.add(Restrictions.eq("service", Boolean.valueOf(true)));
			criteria.addOrder(Order.desc("mileage"));
			criteria.setMaxResults(5);
			list = criteria.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO logging
			System.out.println(e);
			factory.getCurrentSession().getTransaction().rollback();
		} finally {
			// SessionFactory.closeSession();
			factory.getCurrentSession().close();
		}

		return list;
	}

	public static void addOrUpdate(Log log) {

		try {
			Session session = factory.getCurrentSession();
			session.beginTransaction();
			session.saveOrUpdate(log);
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO logging
			System.out.println(e);
			factory.getCurrentSession().getTransaction().rollback();
		} finally {
			// SessionFactory.closeSession();
			factory.getCurrentSession().close();
		}
	}

	public static List getAll() {

		List list = new ArrayList();
		try {
			Session session = factory.getCurrentSession();
			session.beginTransaction();
			Criteria criteria = session.createCriteria(Log.class);
			criteria.addOrder(Order.asc("date")).addOrder(Order.asc("mileage"));
			list = criteria.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO logging
			System.out.println(e);
			factory.getCurrentSession().getTransaction().rollback();
		} finally {
			// SessionFactory.closeSession();
			factory.getCurrentSession().close();
		}

		return list;
	}

	public static float getTotalGallons(String id1, String id2) {
		float gallons = 0;

		try {
			Session session = factory.getCurrentSession();
			session.beginTransaction();
			StringBuffer sb = new StringBuffer();
			sb.append(
					"select sum(x.gallons) as gsum from (select gallons from log where id > ")
					.append(id1).append(" and id <= ").append(id2)
					.append(") as x");
			Query query = session.createSQLQuery(sb.toString()).addScalar(
					"gsum", StandardBasicTypes.FLOAT);
			// Object[] result = (Object[]) query.uniqueResult();
			gallons = ((Float) query.uniqueResult()).floatValue();
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO logging
			System.out.println(e);
			factory.getCurrentSession().getTransaction().rollback();
		} finally {
			// SessionFactory.closeSession();
			factory.getCurrentSession().close();
		}

		return gallons;
	}

	public static MPGBean getMPGBean(NumberFormat nf1, NumberFormat cnf2) {
		MPGBean mpg = new MPGBean();

		try {
			Session session = factory.getCurrentSession();
			session.beginTransaction();
			Query query = session
					.createSQLQuery(
							"select ((max(mileage)-min(mileage))/sum(gallons)) as mpg, (sum(cost)/sum(gallons)) as price from log")
					.addScalar("mpg", StandardBasicTypes.FLOAT)
					.addScalar("price", StandardBasicTypes.FLOAT);
			Object[] result = (Object[]) query.uniqueResult();
			mpg.setOverall_MPG(nf1.format(((Float) result[0]).floatValue()));
			mpg.setAverage_Price(cnf2.format(((Float) result[1]).floatValue()));
			query = session
					.createSQLQuery(
							"select date, mileage, spgetnextservice() as serv from log where id = spgetprevservice()")
					.addScalar("date", StandardBasicTypes.DATE)
					.addScalar("mileage", StandardBasicTypes.INTEGER)
					.addScalar("serv", StandardBasicTypes.STRING);
			result = (Object[]) query.uniqueResult();
			Date lastServiceDate = (Date) result[0];
			int lastServiceMileage = ((Integer) result[1]).intValue();
			mpg.setNextServiceMileage((String) result[2]);
			int minMileage = ((Integer) session.createCriteria(Log.class)
					.setProjection(Property.forName("mileage").min())
					.uniqueResult()).intValue();
			query = session
					.createSQLQuery(
							"select date from log where mileage = :minMileage")
					.addScalar("date", StandardBasicTypes.DATE)
					.setInteger("minMileage", minMileage);
			Date beginDate = (Date) query.uniqueResult();
			int maxMileage = ((Integer) session.createCriteria(Log.class)
					.setProjection(Property.forName("mileage").max())
					.uniqueResult()).intValue();
			query = session
					.createSQLQuery(
							"select date from log where mileage = :maxMileage")
					.addScalar("date", StandardBasicTypes.DATE)
					.setInteger("maxMileage", maxMileage);
			// result = (Object[]) query.uniqueResult();
			// Date latestDate = (Date) result[0];
			Date latestDate = (Date) query.uniqueResult();
			// int latestMileage = ((Integer) result[1]).intValue();
			int latestMileage = maxMileage;
			query = session
					.createSQLQuery(
							"select count(id) from log where formal_service = :svcTrue")
					.addScalar("count", StandardBasicTypes.INTEGER)
					.setBoolean("svcTrue", true);
			// adjust number of services to eliminate the first one, which was when the vehicle was delivered
			int numberOfServices = ((Integer) query.uniqueResult()).intValue() - 1;
			mpg.setNextServiceDate(CalcService.calculateNextServiceDate(
					beginDate, lastServiceDate, latestDate, lastServiceMileage,
					latestMileage, numberOfServices));
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO logging
			System.out.println(e);
			factory.getCurrentSession().getTransaction().rollback();
		} finally {
			// SessionFactory.closeSession();
			factory.getCurrentSession().close();
		}

		return mpg;
	}
}
