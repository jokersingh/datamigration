/**
 * 
 */
package com.barun.datamigration.model;

import java.util.List;

/**
 * @author bibhu
 *
 */
public class Table {
	
	private String name;
	private List<String> fields;
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
	public List<String> getFields() {
		return fields;
	}
	/**
	 * @param fields the fields to set
	 */
	public void setFields(List<String> fields) {
		this.fields = fields;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Table [name=" + name + ", fields=" + fields + "]";
	}

		
	
	
}
