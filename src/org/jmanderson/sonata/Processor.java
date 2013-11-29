package org.jmanderson.sonata;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.struts.action.ActionForm;
import org.jmanderson.sonata.da.LogDA;
import org.jmanderson.sonata.da.UsersDA;
import org.jmanderson.sonata.form.GasForm;
import org.jmanderson.sonata.form.LoginForm;
import org.jmanderson.sonata.form.ServiceForm;
import org.jmanderson.sonata.hibernate.Log;
import org.jmanderson.sonata.hibernate.Users;

public class Processor {

	private static NumberFormat nf1 = NumberFormat.getInstance();
	private static NumberFormat nf2 = NumberFormat.getInstance();
	private static NumberFormat nf3 = NumberFormat.getInstance();
	private static NumberFormat cnf2 = NumberFormat.getCurrencyInstance();
	
	static {
		nf1.setMinimumFractionDigits(1);
		nf1.setMaximumFractionDigits(1);
		nf2.setMinimumFractionDigits(2);
		nf2.setMaximumFractionDigits(2);
		nf3.setMinimumFractionDigits(3);
		nf3.setMaximumFractionDigits(3);
		cnf2.setMinimumFractionDigits(2);
		cnf2.setMaximumFractionDigits(2);
	}
	
	/**
	 * Authenticates a username and password from the LoginForm.
	 * If the user is valid, returns the Users object with the
	 * password blanked out.
	 * If the authentication fails, returns null.
	 * @param form LoginForm
	 * @return Users object or null
	 */
	public static Users authenticate(LoginForm form) {
		Users user = null;
		
		String username = form.getUsername();
		if (username != null) {
			user = UsersDA.getUser(username);
			if (user != null && form.getPassword() != null) {
				if (user.getUserPassword().equals(form.getPassword())) {
					user.setUserPassword("");
					user.setValidated(true);
				}
				else {
					user = null;
				}
			}
		}
		
		return user;
	}
	
	public static void save(GasForm form) {
		Log log = new Log();
		log.loadFromGasForm(form);
		LogDA.addOrUpdate(log);
	}
	
	public static void save(ServiceForm form) {
		Log log = new Log();
		log.loadFromServiceForm(form);
		LogDA.addOrUpdate(log);
	}
	
	public static void delete(GasForm form) {
		LogDA.deleteLogById(form.getGas_id());
	}
	
	public static void delete(ServiceForm form) {
		LogDA.deleteLogById(form.getService_id());
	}
	
	public static List getLastFiveGasEntries() {
		List dbGas = LogDA.getLastFiveGasEntries();
		List gasBeans = new ArrayList();
		
		Iterator iter = dbGas.iterator();
		Log log;
		GasBean gasBean;
		while (iter.hasNext()) {
			log = (Log) iter.next();
			gasBean = new GasBean();
			gasBean.setGas_id(log.getId().intValue());
			gasBean.setGas_date(DateFormat.getDateInstance(2).format(log.getDate()));
			gasBean.setGas_mileage(log.getMileage().intValue());
			gasBean.setGas_gallons(nf3.format(log.getGallons().floatValue()));
			gasBean.setGas_cost(cnf2.format(log.getCost().floatValue()));
			gasBean.setGas_mileage_prev(log.getMileagePrev().intValue());
			gasBean.setGas_mpg(nf1.format(log.getMpg().floatValue()));
			gasBean.setGas_price(cnf2.format(log.getPrice().floatValue()));
			gasBean.setGas_desc_short(log.getShortdesc());
			gasBeans.add(gasBean);
		}
		
		return gasBeans;
	}
	
	public static List getLastFiveServiceEntries() {
		List dbService = LogDA.getLastFiveServiceEntries();
		List serviceBeans = new ArrayList();
		
		Iterator iter = dbService.iterator();
		Log log;
		ServiceBean serviceBean;
		while (iter.hasNext()) {
			log = (Log) iter.next();
			serviceBean = new ServiceBean();
			serviceBean.setService_id(log.getId().intValue());
			serviceBean.setService_date(DateFormat.getDateInstance(2).format(log.getDate()));
			serviceBean.setService_mileage(log.getMileage().intValue());
			serviceBean.setLongdesc(log.getLongdesc());
			serviceBean.setService_cost(cnf2.format(log.getCost().floatValue()));
			serviceBean.setService_repair_cost(cnf2.format(log.getSvcRepairCost().floatValue()));
			serviceBean.setService_routine_cost(cnf2.format(log.getSvcRoutineCost().floatValue()));
			serviceBeans.add(serviceBean);
		}
		
		return serviceBeans;
	}
	
	public static MPGBean getMPGBean() {
		MPGBean mpg = LogDA.getMPGBean(nf1, cnf2);
		
		return mpg;
	}
	
	public static void getLogEntry(GasForm form) {
		Log log = LogDA.getLogById(form.getGas_id());
		log.fillGasForm(form);
	}
	
	public static void getLogEntry(ServiceForm form) {
		Log log = LogDA.getLogById(form.getService_id());
		log.fillServiceForm(form);
	}
	
	public static void getLastGasMileage(GasForm form) {
		int mileage = LogDA.getLastGasMileage();
		form.setGas_mileage_prev(mileage);
	}
	
	public static String getMPG(String id1, String id2) {
		StringBuffer sb = new StringBuffer();
		
		float gallons = LogDA.getTotalGallons(id1, id2);
		int mileage1 = ((Log) LogDA.getLogById(Integer.parseInt(id1))).getMileage().intValue();
		int mileage2 = ((Log) LogDA.getLogById(Integer.parseInt(id2))).getMileage().intValue();
		int totalMileage = mileage2 - mileage1;
		sb.append(totalMileage).append(" miles, ");
		float mpg = totalMileage/gallons;
		System.out.println("MPG = " + mpg);
		sb.append(nf1.format(mpg)).append(" MPG");
		
		return sb.toString();
	}
	
	public static XMLBean getAllEntries() {
		XMLBean xmlBean = new XMLBean();
		StringBuffer sb = new StringBuffer();
		sb.append("<gaslist>");
		List logs = LogDA.getAll();
		Log log;
		Iterator iter = logs.iterator();
		while (iter.hasNext()) {
			log = (Log) iter.next();
			sb.append(log.getGasXML());
		}
		sb.append("</gaslist>");
		xmlBean.setXml(sb.toString());
		
		return xmlBean;
	}
}
