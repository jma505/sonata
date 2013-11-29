package org.jmanderson.sonata;

import java.io.Serializable;

public final class ServiceBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4896304465474938125L;

	private int service_id;

	private String service_date;

	private int service_mileage;

	private String service_cost;

	private String longdesc;
	
	private String service_routine_cost;
	
	private String service_repair_cost;

	public ServiceBean() {
		service_id = 0;
		service_date = "";
		service_cost = "0.00";
		service_routine_cost = "0.00";
		service_repair_cost = "0.00";
	}

	public int getService_id() {
		return service_id;
	}

	public String getService_date() {
		return service_date;
	}

	public int getService_mileage() {
		return service_mileage;
	}

	public String getService_cost() {
		return service_cost;
	}

	public void setService_id(int i) {
		service_id = i;
	}

	public void setService_date(String s) {
		service_date = s;
	}

	public void setService_mileage(int i) {
		service_mileage = i;
	}

	public void setService_cost(String s) {
		service_cost = s;
	}

	public String getLongdesc() {
		return longdesc;
	}

	public void setLongdesc(String longdesc) {
		this.longdesc = longdesc;
	}

	public String getService_routine_cost() {
		return service_routine_cost;
	}

	public void setService_routine_cost(String svc_routine_cost) {
		this.service_routine_cost = svc_routine_cost;
	}

	public String getService_repair_cost() {
		return service_repair_cost;
	}

	public void setService_repair_cost(String svc_repair_cost) {
		this.service_repair_cost = svc_repair_cost;
	}

}
