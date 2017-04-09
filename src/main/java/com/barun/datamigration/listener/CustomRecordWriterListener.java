/**
 * 
 */
package com.barun.datamigration.listener;

import org.easybatch.core.listener.RecordWriterListener;
import org.easybatch.core.record.Batch;

/**
 * @author bibhu
 *
 */
public class CustomRecordWriterListener implements RecordWriterListener{

	@Override
	public void beforeRecordWriting(Batch batch) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterRecordWriting(Batch batch) {
		System.out.println("not writting");
		
	}

	@Override
	public void onRecordWritingException(Batch batch, Throwable throwable) {
		System.out.println("Error");
		
	}

	
}
