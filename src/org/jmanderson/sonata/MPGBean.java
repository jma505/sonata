package org.jmanderson.sonata;

import java.io.Serializable;

public class MPGBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -100482230175616041L;

	private String overall_MPG;

	private String average_Price;

	private String nextServiceMileage;

	private String nextServiceDate;

	public String getOverall_MPG() {
		return overall_MPG;
	}

	public String getAverage_Price() {
		return average_Price;
	}

	public String getNextServiceMileage() {
		return nextServiceMileage;
	}

	public String getNextServiceDate() {
		return nextServiceDate;
	}

	public void setOverall_MPG(String s) {
		overall_MPG = s;
	}

	public void setAverage_Price(String s) {
		average_Price = s;
	}

	public void setNextServiceMileage(String s) {
		nextServiceMileage = s;
	}

	public void setNextServiceDate(String s) {
		nextServiceDate = s;
	}

}
