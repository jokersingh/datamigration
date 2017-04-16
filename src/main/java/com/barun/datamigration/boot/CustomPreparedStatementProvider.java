/**
 * 
 */
package com.barun.datamigration.boot;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.easybatch.core.mapper.BeanIntrospectionException;
import org.easybatch.jdbc.BeanPropertiesPreparedStatementProvider;
import org.easybatch.jdbc.PreparedStatementProvider;

/**
 * @author bibhu
 *
 */
public class CustomPreparedStatementProvider implements PreparedStatementProvider{

	private String[] properties;

    /**
     * Create a new {@link BeanPropertiesPreparedStatementProvider}.
     *
     * @param type the record type
     * @param properties the properties to set in the statement in that order.
     * @throws BeanIntrospectionException when unable to introspect the record type
     */
    public CustomPreparedStatementProvider(String... properties) {
        this.properties = properties;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
    public void prepareStatement(PreparedStatement preparedStatement, Object record) throws SQLException {
        int index = 1;
        Map<String,Object> data = (HashMap) record;
        for(String property : properties){
        	preparedStatement.setObject(index++, data.get(property));
        }
        
    }

}
