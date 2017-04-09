/**
 * 
 */
package com.barun.datamigration.model;

/**
 * @author bibhu
 *
 */
public class Configuration {

	private Connection oracle;
	private Connection mySql;
	/**
	 * @return the oracle
	 */
	public Connection getOracle() {
		return oracle;
	}
	/**
	 * @param oracle the oracle to set
	 */
	public void setOracle(Connection oracle) {
		this.oracle = oracle;
	}
	/**
	 * @return the mySql
	 */
	public Connection getMySql() {
		return mySql;
	}
	/**
	 * @param mySql the mySql to set
	 */
	public void setMySql(Connection mySql) {
		this.mySql = mySql;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Configration [oracle=" + oracle + ", mySql=" + mySql + "]";
	}
	
	
	
}
