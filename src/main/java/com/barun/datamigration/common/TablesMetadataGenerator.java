/**
 * 
 */
package com.barun.datamigration.common;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import com.barun.datamigration.comparator.TableComparator;
import com.barun.datamigration.model.Table;

/**
 * @author bibhu
 *
 */
public final class TablesMetadataGenerator {

	public List<Table> getAllTablesMetadata() {
		List<Table> tables = new ArrayList<Table>();
		List<Map<String, Set<String>>> foreignKeys = new ArrayList<Map<String, Set<String>>>(); 
		Connection connection = null;
		Statement stmt = null;
		Statement stmt1 = null;
		ResultSet rs = null;
		try {
			DataSource oracle = JdbcUtils.getOracleDS();
			connection = oracle.getConnection();
			stmt = connection.createStatement();
			// only for Oracle
			rs = stmt.executeQuery("select object_name from user_objects where object_type = 'TABLE'");
			while (rs.next()) {
				Table table = new Table();
				String tableName = rs.getString(1);
				table.setName(tableName);
				stmt1 = connection.createStatement();
				ResultSet columnRS =  stmt1.executeQuery(QueryBuilder.selectQueryBuilder(tableName));
				int numberOfColumns = columnRS.getMetaData().getColumnCount();
				List<String> columns = new ArrayList<String>();
				for (int i = 0; i < numberOfColumns; i++) {
					columns.add(columnRS.getMetaData().getColumnLabel(i+1));
				}
				table.setFields(columns.toArray(new String[columns.size()]));
				tables.add(table);
				DatabaseMetaData metaDate = connection.getMetaData();
				ResultSet importedKey = metaDate.getImportedKeys(null, null, tableName);
				Map<String, Set<String>> keys = new HashMap<>();
				Set<String> key = new HashSet<>();
				while(importedKey.next()){
					key.add(importedKey.getString(3));
				}
				keys.put(tableName, key);
				foreignKeys.add(keys);
				
			}
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			
			try {
				stmt.close();
				stmt1.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Collections.sort(tables, new TableComparator(foreignKeys));
		Collections.reverse(tables);
		return tables;

	}
}
