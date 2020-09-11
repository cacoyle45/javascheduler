package com.ciaran.obsidian.scheduler;

import org.jobrunr.jobs.mappers.JobMapper;
import org.jobrunr.storage.StorageProvider;
import org.jobrunr.storage.sql.postgres.PostgresStorageProvider;
import org.jobrunr.storage.sql.sqlite.SqLiteStorageProvider;
import org.jobrunr.utils.mapper.JsonMapper;
import org.jobrunr.utils.mapper.jackson.JacksonJsonMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.nio.file.Paths;

@Configuration
@ComponentScan(basePackageClasses = JobRunrStorageConfiguration.class)
public class JobRunrStorageConfiguration {

//    @Bean
//    public StorageProvider storageProvider(DataSource dataSource, JobMapper jobMapper) {
//        final SqLiteStorageProvider sqLiteStorageProvider = new SqLiteStorageProvider(dataSource);
//        sqLiteStorageProvider.setJobMapper(jobMapper);
//        return sqLiteStorageProvider;
//    }
//
//    @Bean
//    public PostgresStorageProvider dataSource() {
//        final SQLiteDataSource dataSource = new SQLiteDataSource();
//        dataSource.setUrl("jdbc:sqlite:" + Paths.get(System.getProperty("java.io.tmpdir"), "jobrunr.db"));
//        return dataSource;
//    }
//
//    @Bean
//    public JobMapper jobMapper(JsonMapper jsonMapper) {
//        return new JobMapper(jsonMapper);
//    }
//
//    @Bean
//    public JsonMapper jsonMapper() {
//        return new JacksonJsonMapper();
//    }

}
