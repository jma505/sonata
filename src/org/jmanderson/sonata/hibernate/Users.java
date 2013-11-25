package org.jmanderson.sonata.hibernate;

// default package



/**
 * Users entity. @author MyEclipse Persistence Tools
 */
public class Users extends AbstractUsers implements java.io.Serializable {

    // Constructors

    /** default constructor */
    public Users() {
    }

    
    /** full constructor */
    public Users(String userName, String userPassword, String userFirstName, String userFullName) {
        super(userName, userPassword, userFirstName, userFullName);        
    }
   
    private boolean validated = false;
    
    public void setValidated(boolean validated) {
    	this.validated = validated;
    }
    
    public boolean isValidated() {
    	return validated;
    }
   
}
