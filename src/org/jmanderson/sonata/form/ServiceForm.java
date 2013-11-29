//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.1/xslt/JavaClass.xsl

package org.jmanderson.sonata.form;

import java.text.DateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.jmanderson.sonata.utils.DateUtils;

/** 
 * MyEclipse Struts
 * Creation date: 06-15-2006
 * 
 * XDoclet definition:
 * @struts.form name="serviceForm"
 */
public class ServiceForm extends ActionForm {

	// --------------------------------------------------------- Instance Variables

	/**
	 * 
	 */
	private static final long serialVersionUID = -6967160785919202108L;

	private int service_id;
	private String service_date;
	private int service_mileage;
	private float service_cost;
	private float service_routine_cost;
	private float service_repair_cost;
	private String service_long_desc;
	private String service_dateD;
	private boolean formal_service;
	

	// --------------------------------------------------------- Methods

	/** 
	 * Method validate
	 * @param mapping
	 * @param request
	 * @return ActionErrors
	 */
	public ActionErrors validate(
		ActionMapping mapping,
		HttpServletRequest request) {

		ActionErrors errors = new ActionErrors();
				if (service_date != null && service_date.length() > 0) {
			try {
				String temp1 = DateUtils.parseToInput(service_date);
				service_date = temp1;
			}
			catch (Exception e) {
				errors.add("service_date", new ActionError("error.gas_date.bad.format"));
			}
		}
		
		if (service_date == null || service_date.length() < 1) {
			service_date = DateFormat.getDateInstance(DateFormat.SHORT).format(new Date());
		}
		
		return errors;
	}


	public int getService_id() {
		return service_id;
	}


	public void setService_id(int service_id) {
		this.service_id = service_id;
	}


	public String getService_date() {
		return service_date;
	}


	public void setService_date(String service_date) {
		this.service_date = service_date;
	}


	public int getService_mileage() {
		return service_mileage;
	}


	public void setService_mileage(int service_mileage) {
		this.service_mileage = service_mileage;
	}


	public float getService_cost() {
		return service_cost;
	}


	public void setService_cost(float service_cost) {
		this.service_cost = service_cost;
	}


	public float getService_routine_cost() {
		return service_routine_cost;
	}


	public void setService_routine_cost(float service_routine_cost) {
		this.service_routine_cost = service_routine_cost;
	}


	public float getService_repair_cost() {
		return service_repair_cost;
	}


	public void setService_repair_cost(float service_repair_cost) {
		this.service_repair_cost = service_repair_cost;
	}


	public String getService_long_desc() {
		return service_long_desc;
	}


	public void setService_long_desc(String service_long_desc) {
		this.service_long_desc = service_long_desc;
	}


	public String getService_dateD() {
		return service_dateD;
	}


	public void setService_dateD(String service_dateD) {
		this.service_dateD = service_dateD;
	}
	
	public boolean getFormalService() {
		return formal_service;
	}
	
	public void setFormalService(boolean formal_service) {
		this.formal_service = formal_service;
	}

}

