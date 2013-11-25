package org.jmanderson.sonata;

import java.io.Serializable;

public class XMLBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2454236320559232552L;

	private String xml = "";

	public String getXml() {
		return xml;
	}

	public void setXml(String s) {
		xml = s;
	}

}
