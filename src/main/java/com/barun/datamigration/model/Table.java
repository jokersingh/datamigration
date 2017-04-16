/**
 * 
 */
package com.barun.datamigration.model;

import java.util.Arrays;

/**
 * @author bibhu
 *
 */
public class Table {
	
	private String name;
	private String[] fields;
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the fields
	 */
	public String[] getFields() {
		return fields;
	}
	/**
	 * @param fields the fields to set
	 */
	public void setFields(String[] fields) {
		this.fields = fields;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Table [name=" + name + ", fields=" + Arrays.toString(fields) + "]";
	}
	
	
}
