/**
 * 
 */
package com.barun.datamigration.comparator;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.barun.datamigration.model.Table;

/**
 * @author bibhu
 *
 */
public class TableComparator implements Comparator<Table>{

	List<Map<String, Set<String>>> foreignKeys = null;;
	
	public TableComparator(List<Map<String, Set<String>>> foreignKeys) {
		this.foreignKeys = foreignKeys;
	}

	@Override
	public int compare(Table table1, Table table2) {
		Set<String> table1ForeginKey = null;
		Set<String> table2ForeginKey = null;
		for(Map<String, Set<String>> tableForeginKey : foreignKeys){
			if(null != tableForeginKey.get(table1)){
				table1ForeginKey = tableForeginKey.get(table1);
			}
			if(null != tableForeginKey.get(table2)){
				table2ForeginKey = tableForeginKey.get(table2);
			}
		}
		if(null == table1ForeginKey && null == table2ForeginKey){
			return 0;
		} else if(null == table1ForeginKey && null != table2ForeginKey){
			return -1;
		} else if(null != table1ForeginKey && null == table2ForeginKey){
			return 1;
		}
		else if(0 == table1ForeginKey.size() && 0 == table2ForeginKey.size()){
			return 0;
		} else if(0 == table1ForeginKey.size() && 0 != table2ForeginKey.size()){
			return 1;
		} else if(0 != table1ForeginKey.size() && 0 == table2ForeginKey.size()){
			return -1;
		} else if(0 != table1ForeginKey.size() && 0 != table2ForeginKey.size()){
			if(table1ForeginKey.contains(table2) && table2ForeginKey.contains(table1)){
				return 0;
			} 
		}
		return 0;
	}
	
	

}
