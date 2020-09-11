package com.ciaran.obsidian.scheduler;

import lombok.var;
import org.jobrunr.dashboard.JobRunrDashboardWebServer;
import org.jobrunr.jobs.mappers.JobMapper;
import org.jobrunr.scheduling.BackgroundJob;
import org.jobrunr.scheduling.JobScheduler;
import org.jobrunr.scheduling.cron.Cron;
import org.jobrunr.server.BackgroundJobServer;
import org.jobrunr.server.JobActivator;
import org.jobrunr.storage.StorageProvider;
import org.jobrunr.storage.sql.postgres.PostgresStorageProvider;
import org.jobrunr.storage.sql.sqlite.SqLiteStorageProvider;
import org.jobrunr.utils.mapper.JsonMapper;
import org.jobrunr.utils.mapper.jackson.JacksonJsonMapper;
import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

import javax.sql.DataSource;
import java.nio.file.Paths;

@SpringBootApplication
//@Import(JobRunrStorageConfiguration.class)
public class SchedulerApplication {

	public SchedulerApplication(){}
	public static void main(String[] args) {
		SpringApplication.run(SchedulerApplication.class, args);

		BackgroundJob.scheduleRecurringly("TestDate",() -> new DateOutputter().RunDateOutput(), Cron.minutely());
	}

	@Bean
	public BackgroundJobServer backgroundJobServer(StorageProvider storageProvider, JobActivator jobActivator) {
		final BackgroundJobServer backgroundJobServer = new BackgroundJobServer(storageProvider, jobActivator);
		backgroundJobServer.start();
		return backgroundJobServer;
	}

	@Bean
	public JobRunrDashboardWebServer dashboardWebServer(StorageProvider storageProvider, JsonMapper jsonMapper) {
		final JobRunrDashboardWebServer jobRunrDashboardWebServer = new JobRunrDashboardWebServer(storageProvider, jsonMapper);
		jobRunrDashboardWebServer.start();
		return jobRunrDashboardWebServer;
	}

	@Bean
	public JobActivator jobActivator(ApplicationContext applicationContext) {
		return applicationContext::getBean;
	}

	@Bean
	public JobScheduler jobScheduler(StorageProvider storageProvider) {
		JobScheduler jobScheduler = new JobScheduler(storageProvider);
		BackgroundJob.setJobScheduler(jobScheduler);
		return jobScheduler;
	}

	@Bean
	public StorageProvider storageProvider(DataSource dataSource, JobMapper jobMapper) {
		final PostgresStorageProvider postgresSqlStorageProvider = new PostgresStorageProvider(dataSource);
		postgresSqlStorageProvider.setJobMapper(jobMapper);
		return postgresSqlStorageProvider;
	}

	@Bean
	public PGSimpleDataSource dataSource() {
		final PGSimpleDataSource dataSource = new PGSimpleDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/jobrunr");
		dataSource.setUser("postgres");
		dataSource.setPassword("Password1!");
		return dataSource;
	}

	@Bean
	public JobMapper jobMapper(JsonMapper jsonMapper) {
		return new JobMapper(jsonMapper);
	}

	@Bean
	public JsonMapper jsonMapper() {
		return new JacksonJsonMapper();
	}
}
