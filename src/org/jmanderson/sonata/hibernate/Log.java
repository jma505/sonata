package org.jmanderson.sonata.hibernate;
// default package

import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Date;

import org.jmanderson.sonata.form.GasForm;
import org.jmanderson.sonata.form.ServiceForm;



/**
 * Log entity. @author MyEclipse Persistence Tools
 */
public class Log extends AbstractLog implements java.io.Serializable {

	private static NumberFormat nf1;
	private static NumberFormat nf2;
	private static NumberFormat nf3;
	
	static {
		nf1 = NumberFormat.getInstance();
		nf1.setMinimumFractionDigits(1);
		nf1.setMaximumFractionDigits(1);
		
		nf2 = NumberFormat.getInstance();
		nf2.setMinimumFractionDigits(2);
		nf2.setMaximumFractionDigits(2);
		
		nf3 = NumberFormat.getInstance();
		nf3.setMinimumFractionDigits(3);
		nf3.setMaximumFractionDigits(3);
	}

    // Constructors

    /** default constructor */
    public Log() {
    }

    
    /** full constructor */
    public Log(Date date, Integer mileage, Float gallons, Float cost, Integer mileagePrev, Float mpg, Float price, String shortdesc, String longdesc, Boolean service, Float svcRepairCost, Float svcRoutineCost) {
        super(date, mileage, gallons, cost, mileagePrev, mpg, price, shortdesc, longdesc, service, svcRepairCost, svcRoutineCost);        
    }
   
    public void loadFromGasForm(GasForm form) {
    	
    	if (form.getGas_id() > 0) {
    		setId(Integer.valueOf(form.getGas_id()));
    	}
    	setDate(new Date(form.getGas_date()));
    	setMileage(Integer.valueOf(form.getGas_mileage()));
    	setGallons(Float.valueOf(form.getGas_gallons()));
    	setCost(Float.valueOf(form.getGas_cost()));
    	setMileagePrev(Integer.valueOf(form.getGas_mileage_prev()));
    	setMpg(Float.valueOf(form.getGas_mpg()));
    	setPrice(Float.valueOf(form.getGas_price()));
    	setShortdesc(form.getGas_desc_short());
    	//
    	// The following needs to be changed once we implement Service tracking
    	//
    	setService(Boolean.FALSE);
    	setFormalService(Boolean.FALSE);
    	
    }
    
    public void loadFromServiceForm(ServiceForm form) {
    	if (form.getService_id() > 0) {
    		setId(Integer.valueOf(form.getService_id()));
    	}
    	setDate(new Date(form.getService_date()));
    	setMileage(Integer.valueOf(form.getService_mileage()));
    	setCost(Float.valueOf(form.getService_cost()));
    	setSvcRoutineCost(Float.valueOf(form.getService_routine_cost()));
    	setSvcRepairCost(Float.valueOf(form.getService_repair_cost()));
    	setLongdesc(form.getService_long_desc());
    	//
    	// Does this need to be changed???
    	//
    	setService(Boolean.TRUE);
    	setFormalService(form.getScheduledService());
    }
    
    public void fillGasForm(GasForm form) {
    	form.setGas_cost(this.getCost().floatValue());
    	form.setGas_gallons(this.getGallons().floatValue());
    	form.setGas_mileage(this.getMileage().intValue());
    	form.setGas_mileage_prev(this.getMileagePrev().intValue());
    	form.setGas_mpg(this.getMpg().floatValue());
    	form.setGas_price(this.getPrice().floatValue());
    	form.setGas_date(DateFormat.getDateInstance(3).format(this.getDate()));
    	form.setGas_dateD(DateFormat.getDateInstance(2).format(this.getDate()));
    	form.setGas_desc_short(this.getShortdesc());
    }
    
    public void fillServiceForm(ServiceForm form) {
    	form.setService_date(DateFormat.getDateInstance(3).format(this.getDate()));
    	form.setService_dateD(DateFormat.getDateInstance(2).format(this.getDate()));
    	form.setService_mileage(this.getMileage().intValue());
    	form.setService_cost(this.getCost().floatValue());
    	form.setService_routine_cost(this.getSvcRoutineCost().floatValue());
    	form.setService_repair_cost(this.getSvcRepairCost().floatValue());
    	form.setService_long_desc(this.getLongdesc());
    	form.setScheduledService(this.getFormalService());
    }
    
    private boolean isGasPurchase() {
    	if (getService().compareTo(Boolean.FALSE) == 0) {
    		return true;
    	}
    	return false;
    }
    
    public String getGasXML() {
    	StringBuffer sb = new StringBuffer();
    	if (isGasPurchase()) {
    		sb.append("<entry>");
    		sb.append("<id>").append(getId().intValue()).append("</id>");
    		sb.append("<date>").append(DateFormat.getDateInstance(2).format(this.getDate())).append("</date>");
    		sb.append("<mileage>").append(getMileage().intValue()).append("</mileage>");
    		sb.append("<gallons>").append(nf3.format(getGallons())).append("</gallons>");
    		sb.append("<cost>").append(nf2.format(getCost())).append("</cost>");
    		sb.append("<mpg>").append(nf1.format(getMpg())).append("</mpg>");
    		sb.append("<short_desc>").append(getShortdesc()).append("</short_desc>");
    		sb.append("</entry>");
    	}
    	else {
    		sb.append("<service>");
    		sb.append("<id>").append(getId().intValue()).append("</id>");
    		sb.append("<date>").append(DateFormat.getDateInstance(2).format(this.getDate())).append("</date>");
    		sb.append("<mileage>").append(getMileage().intValue()).append("</mileage>");
    		sb.append("<cost>").append(nf2.format(getCost())).append("</cost>");
    		sb.append("<routine_cost>").append(nf2.format(getSvcRoutineCost())).append("</routine_cost>");
    		sb.append("<repair_cost>").append(nf2.format(getSvcRepairCost())).append("</repair_cost>");
    		sb.append("<long_desc>").append(getLongdesc()).append("</long_desc>");
    		sb.append("</service>");
    	}
    	
    	return sb.toString();
    }
   
}
