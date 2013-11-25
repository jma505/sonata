package org.jmanderson.sonata;

import java.io.Serializable;

public final class ServiceBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4896304465474938125L;

	private int gas_id;

	private String gas_date;

	private int gas_mileage;

	private String gas_cost;

	private String longdesc;
	
	private String svc_routine_cost;
	
	private String svc_repair_cost;

	public ServiceBean() {
		gas_id = 0;
		gas_date = "";
		gas_cost = "0.00";
		svc_routine_cost = "0.00";
		svc_repair_cost = "0.00";
	}

	public int getGas_id() {
		return gas_id;
	}

	public String getGas_date() {
		return gas_date;
	}

	public int getGas_mileage() {
		return gas_mileage;
	}

	public String getGas_cost() {
		return gas_cost;
	}

	public void setGas_id(int i) {
		gas_id = i;
	}

	public void setGas_date(String s) {
		gas_date = s;
	}

	public void setGas_mileage(int i) {
		gas_mileage = i;
	}

	public void setGas_cost(String s) {
		gas_cost = s;
	}

	public String getLongdesc() {
		return longdesc;
	}

	public void setLongdesc(String longdesc) {
		this.longdesc = longdesc;
	}

	public String getSvc_routine_cost() {
		return svc_routine_cost;
	}

	public void setSvc_routine_cost(String svc_routine_cost) {
		this.svc_routine_cost = svc_routine_cost;
	}

	public String getSvc_repair_cost() {
		return svc_repair_cost;
	}

	public void setSvc_repair_cost(String svc_repair_cost) {
		this.svc_repair_cost = svc_repair_cost;
	}

}
