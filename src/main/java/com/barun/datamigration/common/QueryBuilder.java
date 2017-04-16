/**
 * 
 */
package com.barun.datamigration.common;

/**
 * @author bibhu
 *
 */
public final class QueryBuilder {

	public static String selectQueryBuilder(String tableName, String[] fields){
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append("SELECT ");
		for(String field: fields){
			queryBuilder.append(field + ",");
		}
		queryBuilder.append(" FROM ");
		queryBuilder.append(tableName);
		queryBuilder.delete(queryBuilder.lastIndexOf(","),queryBuilder.lastIndexOf(",")+1);
		return queryBuilder.toString();
	}
	
	public static String selectQueryBuilder(String tableName){
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append("SELECT * FROM ");
		queryBuilder.append(tableName);
		return queryBuilder.toString();
	}
	
	public static String insertQueryBuilder(String tableName, String[] fields){
		StringBuilder queryBuilder = new StringBuilder();	
		StringBuilder values = new StringBuilder();	
		values.append(" VALUES (");
		queryBuilder.append("INSERT INTO ");
		queryBuilder.append(tableName );
		queryBuilder.append(" (");
		for(String field: fields){
			queryBuilder.append(" "+field + ",");
			values.append("?,");
		}
		queryBuilder.delete(queryBuilder.lastIndexOf(","),queryBuilder.lastIndexOf(",")+1);
		queryBuilder.append(")");
		values.append(")");
		values.delete(values.lastIndexOf(","),values.lastIndexOf(",")+1);
		queryBuilder.append(values);
		return queryBuilder.toString();
	}
	
	public static void main(String[] args) {
		String[] fields = {"ID","SUBJECT","BODY", "CREATED_ON"};
		System.out.println(selectQueryBuilder("article",fields));
		System.out.println(insertQueryBuilder("article",fields));
	}
}
