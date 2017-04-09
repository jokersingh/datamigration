/**
 * 
 */
package com.barun.datamigration.listener;

import org.easybatch.core.job.JobParameters;
import org.easybatch.core.job.JobReport;
import org.easybatch.core.listener.JobListener;
import org.easybatch.core.listener.PipelineListener;
import org.easybatch.core.record.Record;

/**
 * @author bibhu
 *
 */
public class CustomJobListener implements PipelineListener, JobListener{

	private long startTime;
    private long nbRecords;
    private long recordProcessingTimesSum;

    @Override
    public void beforeJobStart(JobParameters jobParameters) {

    }

    @Override
    public void afterJobEnd(JobReport jobReport) {
        jobReport.getMetrics().addMetric("Record processing time average (in ms)", (double)recordProcessingTimesSum / (double)nbRecords);
    }

    @SuppressWarnings("rawtypes")
	@Override
    public Record beforeRecordProcessing(Record record) {
        nbRecords++;
        startTime = System.currentTimeMillis();
        return record;
    }

    @SuppressWarnings("rawtypes")
	@Override
    public void afterRecordProcessing(Record input, Record output) {
        recordProcessingTimesSum +=  System.currentTimeMillis() - startTime;
        System.out.println("Record has been migrated Sucessfully !!!");
    }

    @SuppressWarnings("rawtypes")
    @Override
    public void onRecordProcessingException(Record record, Throwable throwable) {
        recordProcessingTimesSum += System.currentTimeMillis() - startTime;
    }
}
