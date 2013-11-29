package org.jmanderson.sonata.hibernate;
// default package

import java.util.Date;


/**
 * AbstractLog entity provides the base persistence definition of the Log entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractLog  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private Date date;
     private Integer mileage;
     private Float gallons;
     private Float cost;
     private Integer mileagePrev;
     private Float mpg;
     private Float price;
     private String shortdesc;
     private String longdesc;
     private Boolean service;
     private Float svcRepairCost;
     private Float svcRoutineCost;
     private Boolean formalService;


    // Constructors

    public Boolean getFormalService() {
		return formalService;
	}


	public void setFormalService(Boolean formalService) {
		this.formalService = formalService;
	}


	/** default constructor */
    public AbstractLog() {
    }

    
    /** full constructor */
    public AbstractLog(Date date, Integer mileage, Float gallons, Float cost, Integer mileagePrev, Float mpg, Float price, String shortdesc, String longdesc, Boolean service, Float svcRepairCost, Float svcRoutineCost) {
        this.date = date;
        this.mileage = mileage;
        this.gallons = gallons;
        this.cost = cost;
        this.mileagePrev = mileagePrev;
        this.mpg = mpg;
        this.price = price;
        this.shortdesc = shortdesc;
        this.longdesc = longdesc;
        this.service = service;
        this.svcRepairCost = svcRepairCost;
        this.svcRoutineCost = svcRoutineCost;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return this.date;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getMileage() {
        return this.mileage;
    }
    
    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public Float getGallons() {
        return this.gallons;
    }
    
    public void setGallons(Float gallons) {
        this.gallons = gallons;
    }

    public Float getCost() {
        return this.cost;
    }
    
    public void setCost(Float cost) {
        this.cost = cost;
    }

    public Integer getMileagePrev() {
        return this.mileagePrev;
    }
    
    public void setMileagePrev(Integer mileagePrev) {
        this.mileagePrev = mileagePrev;
    }

    public Float getMpg() {
        return this.mpg;
    }
    
    public void setMpg(Float mpg) {
        this.mpg = mpg;
    }

    public Float getPrice() {
        return this.price;
    }
    
    public void setPrice(Float price) {
        this.price = price;
    }

    public String getShortdesc() {
        return this.shortdesc;
    }
    
    public void setShortdesc(String shortdesc) {
        this.shortdesc = shortdesc;
    }

    public String getLongdesc() {
        return this.longdesc;
    }
    
    public void setLongdesc(String longdesc) {
        this.longdesc = longdesc;
    }

    public Boolean getService() {
        return this.service;
    }
    
    public void setService(Boolean service) {
        this.service = service;
    }

    public Float getSvcRepairCost() {
        return this.svcRepairCost;
    }
    
    public void setSvcRepairCost(Float svcRepairCost) {
        this.svcRepairCost = svcRepairCost;
    }

    public Float getSvcRoutineCost() {
        return this.svcRoutineCost;
    }
    
    public void setSvcRoutineCost(Float svcRoutineCost) {
        this.svcRoutineCost = svcRoutineCost;
    }
   








}