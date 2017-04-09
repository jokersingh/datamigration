/**
 * 
 */
package com.barun.datamigration.model;

import java.util.List;

/**
 * @author bibhu
 *
 */
public class Tables {

	private List<Table> tables;

	/**
	 * @return the tables
	 */
	public List<Table> getTables() {
		return tables;
	}

	/**
	 * @param tables the tables to set
	 */
	public void setTables(List<Table> tables) {
		this.tables = tables;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Tables [tables=" + tables + "]";
	}
	
	
	
}
