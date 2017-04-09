/**
 * 
 */
package com.barun.datamigration.model;

import java.io.Serializable;

/**
 * @author bibhu
 *
 */
public class Article implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long ID;
	private String SUBJECT;
	private String BODY;
	private String CREATED_ON;
	/**
	 * @return the iD
	 */
	public long getID() {
		return ID;
	}
	/**
	 * @param iD the iD to set
	 */
	public void setID(long iD) {
		ID = iD;
	}
	/**
	 * @return the sUBJECT
	 */
	public String getSUBJECT() {
		return SUBJECT;
	}
	/**
	 * @param sUBJECT the sUBJECT to set
	 */
	public void setSUBJECT(String sUBJECT) {
		SUBJECT = sUBJECT;
	}
	/**
	 * @return the bODY
	 */
	public String getBODY() {
		return BODY;
	}
	/**
	 * @param bODY the bODY to set
	 */
	public void setBODY(String bODY) {
		BODY = bODY;
	}
	/**
	 * @return the cREATED_ON
	 * 
	 */
	public String getCREATED_ON(){
		return CREATED_ON;
	}
	/**
	 * @param cREATED_ON the cREATED_ON to set
	 */
	public void setCREATED_ON(String cREATED_ON) {
		CREATED_ON = cREATED_ON;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Article [ID=" + ID + ", SUBJECT=" + SUBJECT + ", BODY=" + BODY + ", CREATED_ON="
				+ CREATED_ON + "]";
	}
	
	
}
