/**
 * 
 */
package com.barun.datamigration.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.easybatch.core.mapper.AbstractRecordMapper;
import org.easybatch.core.mapper.RecordMapper;
import org.easybatch.core.record.GenericRecord;
import org.easybatch.core.record.Record;
import org.easybatch.jdbc.JdbcRecord;
import org.easybatch.jdbc.JdbcRecordMapper;

/**
 * @author bibhu
 *
 */
public class CustomRecordMapper<P> extends AbstractRecordMapper<P> implements RecordMapper<JdbcRecord, Record<P>>{


	/**
     * Field names used for custom column mapping.
     */
    private String[] fields;
    
    private Map<String,String> columType = new HashMap<>();

    /**
     * Create a new {@link JdbcRecordMapper}. Column names will be fetched from the jdbc result set meta data
     * and set to fields with the same name of the target object.
     *
     * @param recordClass the target domain object class
     */
    public CustomRecordMapper(final Class<P> recordClass) {
        super(recordClass);
    }

    /**
     * Create a new {@link JdbcRecordMapper}. The supplied field names will be used to map columns to the target object fields.
     *
     * @param recordClass the target domain object class
     * @param fields      the list of fields names
     */
    public CustomRecordMapper(final Class<P> recordClass, final String... fields) {
        this(recordClass);
        this.fields = fields;
    }
    
    @Override
    public Record<P> processRecord(final JdbcRecord record) throws Exception {
        ResultSet resultSet = record.getPayload();
        initFieldNames(resultSet);
        Map<String, String> values = new HashMap<>();
        for (int i = 0; i < fields.length; i++) {
        	if("TIMESTAMP".equals(columType.get(fields[i]))){
        		values.put(fields[i], resultSet.getTimestamp(i + 1).toString());
        	}else {
        		values.put(fields[i], resultSet.getString(i + 1));
        	}
        }
        return new GenericRecord<>(record.getHeader(), objectMapper.mapObject(values));
    }

    private void initFieldNames(final ResultSet resultSet) throws SQLException {
        int columnCount = resultSet.getMetaData().getColumnCount();
        if (fields == null) {
            fields = new String[columnCount];
            for (int i = 1; i < columnCount + 1; i++) {
            	String label = resultSet.getMetaData().getColumnLabel(i);
            	String type = resultSet.getMetaData().getColumnTypeName(i);
                fields[i - 1] = label;
                columType.put(label, type);
            }
        }
    }

}
