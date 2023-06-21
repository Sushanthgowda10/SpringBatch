package com.nt.config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

import com.nt.listener.JobMonitoringListener;
import com.nt.model.ExamResult;
import com.nt.processor.ExamResultItemProcessor;



@Configuration
@EnableBatchProcessing
public class BatchConfig {
	private JobBuilderFactory jobBuildeFactory;
	
	@Autowired
	private JobBuilderFactory jobfactory;
	
	@Autowired
	private StepBuilderFactory stepfactory;
	
	@Autowired
	private ExamResultItemProcessor processor;
	@Autowired
	private JobMonitoringListener listener;
	
	@Autowired
	private DataSource ds;
	
	@Bean(name="ffiReader")
	public JdbcCursorItemReader<ExamResult> createReader() {
		return new JdbcCursorItemReaderBuilder<ExamResult>()
		.name("jdbc-reader")
		.dataSource (ds)
		.sql(" select id,dob,percentage,semester from exam_result")
		.beanRowMapper(ExamResult.class)
		.build();
	}
	
	@Bean(name ="jbiw")
	public FlatFileItemWriter<ExamResult> createWriter() {
		return new FlatFileItemWriterBuilder<ExamResult>()
				.name("writer")
				.resource(new FileSystemResource("C:\\Users\\Sushant.gowda\\TopBrains.csv"))
				.lineSeparator("\r\n")
				.delimited().delimiter(",")
				.names("id","dob","percentage","semester")
				.build();
	}
	@Bean(name ="step1")
	public Step CreateStep1() {
		return stepfactory.get("step1")
				.<ExamResult,ExamResult>chunk(5)
				.reader(createReader())
				.processor(processor)
				.writer(createWriter())
				.build();
	}

	@Bean(name ="job1")
	public Job CreateJob1() {
		return jobfactory.get("job1")
				.listener(listener)
			     .incrementer(new RunIdIncrementer())
			     .start(CreateStep1())
				.build();
	}
}

