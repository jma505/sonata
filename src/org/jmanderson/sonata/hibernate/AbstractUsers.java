package org.jmanderson.sonata.hibernate;
// default package



/**
 * AbstractUsers entity provides the base persistence definition of the Users entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractUsers  implements java.io.Serializable {


    // Fields    

     private String userName;
     private String userPassword;
     private String userFirstName;
     private String userFullName;


    // Constructors

    /** default constructor */
    public AbstractUsers() {
    }

    
    /** full constructor */
    public AbstractUsers(String userName, String userPassword, String userFirstName, String userFullName) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userFirstName = userFirstName;
        this.userFullName = userFullName;
    }

   
    // Property accessors

    public String getUserName() {
        return this.userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return this.userPassword;
    }
    
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserFirstName() {
        return this.userFirstName;
    }
    
    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserFullName() {
        return this.userFullName;
    }
    
    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }
   








}