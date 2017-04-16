/**
 * 
 */
package com.barun.datamigration.boot;

import static org.easybatch.core.job.JobBuilder.aNewJob;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.easybatch.core.job.Job;
import org.easybatch.core.job.JobExecutor;
import org.easybatch.core.job.JobReport;
import org.easybatch.core.mapper.BeanIntrospectionException;
import org.easybatch.jdbc.JdbcRecordReader;
import org.easybatch.jdbc.JdbcRecordWriter;

import com.barun.datamigration.common.JdbcUtils;
import com.barun.datamigration.common.QueryBuilder;
import com.barun.datamigration.common.TablesMetadataGenerator;
import com.barun.datamigration.listener.CustomJobListener;
import com.barun.datamigration.mapper.CustomRecordMapper;
import com.barun.datamigration.model.Model;
import com.barun.datamigration.model.Table;

/**
 * @author bibhu
 *
 */
public class App {

	/**
	 * @param args
	 * @throws SQLException 
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws SQLException, FileNotFoundException{
		
		TablesMetadataGenerator tablesMetadataGenerator = new TablesMetadataGenerator();
		List<Table> tables = tablesMetadataGenerator.getAllTablesMetadata();
		DataSource oracleDS = JdbcUtils.getOracleDS();
		DataSource mysqlDS = JdbcUtils.getMySqlDS();
		for(Table table: tables){
			JdbcUtils.tableCleanUp(mysqlDS, table.getName());
			// Build a batch job
			Job job = buildJob(oracleDS, mysqlDS, table.getFields(),table.getName());
	        // Execute the job
	        JobExecutor jobExecutor = new JobExecutor();
	        JobReport jobReport = jobExecutor.execute(job);
	        jobExecutor.shutdown();
	        System.out.println(jobReport);
		}

	}

	/**
	 * @param oracleDS
	 * @param mysqlDS
	 * @param fields
	 * @return
	 * @throws BeanIntrospectionException
	 */
	private static Job buildJob(DataSource oracleDS, DataSource mysqlDS, String[] fields, final String tableName)
			throws BeanIntrospectionException {
		Job job = aNewJob()
				.named(tableName+"Job")
                .reader(new JdbcRecordReader(oracleDS, QueryBuilder.selectQueryBuilder(tableName, fields)))
                .mapper(new CustomRecordMapper<>(Model.class))
                .writer(new JdbcRecordWriter(mysqlDS, QueryBuilder.insertQueryBuilder(tableName, fields), new CustomPreparedStatementProvider(fields)))
                .enableJmx(true)
                .pipelineListener(new CustomJobListener())
                .jobListener(new CustomJobListener())
                .build();
		return job;
	}

}
