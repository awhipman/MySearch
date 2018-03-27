package com.html;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class MyJob implements Job{

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException { 
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS"); 
        try {
			HtmlJsoup.main(null);
			optionDB3.main(null);
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
    } 

}
