/**
 * 
 */
package com.barun.datamigration.model;

import java.io.Serializable;

/**
 * @author bibhu
 *
 */
public class Test implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L; 
    private String ID;
    private String SUBJECT;
    private String BODY;
    private String CREATED_ON;
    public String getID(){
        return ID;
    }
    public void setID(String ID){        
        this.ID = ID;
    } 
    public String getSUBJECT(){
        return SUBJECT;
    }
    public void setSUBJECT(String SUBJECT){        
        this.SUBJECT = SUBJECT;
    } 
    public String getBODY(){
        return BODY;
    }
    public void setBODY(String BODY){        
        this.BODY = BODY;
    } 
    public String getCREATED_ON(){
        return CREATED_ON;
    }
    public void setCREATED_ON(String CREATED_ON){        
        this.CREATED_ON = CREATED_ON;
    } 
    @Override
    public String toString() {
    	String str = "Test : [";
    		str = str + "ID=" + ID +",";
    		str = str + "SUBJECT=" + SUBJECT +",";
    		str = str + "BODY=" + BODY +",";
    		str = str + "CREATED_ON=" + CREATED_ON +",";
        return str;
    }    
}