package org.jmanderson.sonata;

import java.io.Serializable;

public final class GasBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4896304465474938125L;

	private int gas_id;

	private String gas_date;

	private int gas_mileage;

	private String gas_gallons;

	private String gas_cost;

	private int gas_mileage_prev;

	private String gas_mpg;

	private String gas_price;
	
	private String gas_desc_short;

	public GasBean() {
		gas_id = 0;
		gas_date = "";
		gas_mileage_prev = 0;
		gas_mpg = "0.0";
		gas_price = "0.00";
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

	public String getGas_gallons() {
		return gas_gallons;
	}

	public String getGas_cost() {
		return gas_cost;
	}

	public int getGas_mileage_prev() {
		return gas_mileage_prev;
	}

	public String getGas_mpg() {
		return gas_mpg;
	}

	public String getGas_price() {
		return gas_price;
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

	public void setGas_gallons(String s) {
		gas_gallons = s;
	}

	public void setGas_cost(String s) {
		gas_cost = s;
	}

	public void setGas_mileage_prev(int i) {
		gas_mileage_prev = i;
	}

	public void setGas_mpg(String s) {
		gas_mpg = s;
	}

	public void setGas_price(String s) {
		gas_price = s;
	}

	public String getGas_desc_short() {
		return gas_desc_short;
	}

	public void setGas_desc_short(String gas_desc_short) {
		this.gas_desc_short = gas_desc_short;
	}

}
