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
 * @struts.form name="gasForm"
 */
public class GasForm extends ActionForm {

	// --------------------------------------------------------- Instance Variables

	/**
	 * 
	 */
	private static final long serialVersionUID = -6967160785919202108L;

	/** gas_price property */
	private float gas_price;

	/** gas_mileage_prev property */
	private int gas_mileage_prev;

	/** gas_cost property */
	private float gas_cost;

	/** gas_dateD property */
	private String gas_dateD;

	/** gas_mpg property */
	private float gas_mpg;

	/** gas_gallons property */
	private float gas_gallons;

	/** gas_id property */
	private int gas_id;

	/** gas_mileage property */
	private int gas_mileage;

	/** gas_date property */
	private String gas_date;
	
	private String gas_desc_short;

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
				if (gas_date != null && gas_date.length() > 0) {
			try {
				String temp1 = DateUtils.parseToInput(gas_date);
				gas_date = temp1;
			}
			catch (Exception e) {
				errors.add("gas_date", new ActionError("error.gas_date.bad.format"));
			}
		}
		
		if (gas_date == null || gas_date.length() < 1) {
			gas_date = DateFormat.getDateInstance(DateFormat.SHORT).format(new Date());
		}
		
		if (gas_gallons <= 0.0) {
			errors.add("gas_gallons", new ActionError("error.gas_gallons.required"));
		}
		
		if (gas_cost <= 0.0) {
			errors.add("gas_cost", new ActionError("error.gas_cost.required"));
		}
		
		return errors;
	}

	/** 
	 * Returns the gas_price.
	 * @return float
	 */
	public float getGas_price() {
		return gas_price;
	}

	/** 
	 * Set the gas_price.
	 * @param gas_price The gas_price to set
	 */
	public void setGas_price(float gas_price) {
		this.gas_price = gas_price;
	}

	/** 
	 * Returns the gas_mileage_prev.
	 * @return int
	 */
	public int getGas_mileage_prev() {
		return gas_mileage_prev;
	}

	/** 
	 * Set the gas_mileage_prev.
	 * @param gas_mileage_prev The gas_mileage_prev to set
	 */
	public void setGas_mileage_prev(int gas_mileage_prev) {
		this.gas_mileage_prev = gas_mileage_prev;
	}

	/** 
	 * Returns the gas_cost.
	 * @return float
	 */
	public float getGas_cost() {
		return gas_cost;
	}

	/** 
	 * Set the gas_cost.
	 * @param gas_cost The gas_cost to set
	 */
	public void setGas_cost(float gas_cost) {
		this.gas_cost = gas_cost;
	}

	/** 
	 * Returns the gas_dateD.
	 * @return String
	 */
	public String getGas_dateD() {
		return gas_dateD;
	}

	/** 
	 * Set the gas_dateD.
	 * @param gas_dateD The gas_dateD to set
	 */
	public void setGas_dateD(String gas_dateD) {
		this.gas_dateD = gas_dateD;
	}

	/** 
	 * Returns the gas_mpg.
	 * @return float
	 */
	public float getGas_mpg() {
		return gas_mpg;
	}

	/** 
	 * Set the gas_mpg.
	 * @param gas_mpg The gas_mpg to set
	 */
	public void setGas_mpg(float gas_mpg) {
		this.gas_mpg = gas_mpg;
	}

	/** 
	 * Returns the gas_gallons.
	 * @return float
	 */
	public float getGas_gallons() {
		return gas_gallons;
	}

	/** 
	 * Set the gas_gallons.
	 * @param gas_gallons The gas_gallons to set
	 */
	public void setGas_gallons(float gas_gallons) {
		this.gas_gallons = gas_gallons;
	}

	/** 
	 * Returns the gas_id.
	 * @return int
	 */
	public int getGas_id() {
		return gas_id;
	}

	/** 
	 * Set the gas_id.
	 * @param gas_id The gas_id to set
	 */
	public void setGas_id(int gas_id) {
		this.gas_id = gas_id;
	}

	/** 
	 * Returns the gas_mileage.
	 * @return int
	 */
	public int getGas_mileage() {
		return gas_mileage;
	}

	/** 
	 * Set the gas_mileage.
	 * @param gas_mileage The gas_mileage to set
	 */
	public void setGas_mileage(int gas_mileage) {
		this.gas_mileage = gas_mileage;
	}

	/** 
	 * Returns the gas_date.
	 * @return String
	 */
	public String getGas_date() {
		return gas_date;
	}

	/** 
	 * Set the gas_date.
	 * @param gas_date The gas_date to set
	 */
	public void setGas_date(String gas_date) {
		this.gas_date = gas_date;
	}

	public String getGas_desc_short() {
		return gas_desc_short;
	}

	public void setGas_desc_short(String gas_desc_short) {
		this.gas_desc_short = gas_desc_short;
	}

}

