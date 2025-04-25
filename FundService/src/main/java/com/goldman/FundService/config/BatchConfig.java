package com.goldman.FundService.config;

import com.goldman.FundService.dto.FundDTO;
import com.goldman.FundService.repo.FundRepository;
import com.goldman.FundService.service.FundProcessor;
import com.goldman.FundService.service.FundReader;
import com.goldman.FundService.service.FundWriter;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final FundRepository fundRepository;

    public BatchConfig(JobRepository jobRepository,
            PlatformTransactionManager transactionManager,
            FundRepository fundRepository) {
        this.jobRepository = jobRepository;
        this.transactionManager = transactionManager;
        this.fundRepository = fundRepository;
    }

    @Bean
    public ItemReader<FundDTO> reader() {
        return new FundReader(fundRepository);
    }

    @Bean
    public ItemProcessor<FundDTO, FundDTO> processor() {
        return new FundProcessor();
    }

    @Bean
    public ItemWriter<FundDTO> writer() {
        return new FundWriter();
    }

    @Bean
    public Step step1() {
        return new StepBuilder("step1", jobRepository)
                .<FundDTO, FundDTO>chunk(10, transactionManager)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }

    @Bean
    public Job exportJsonJob() {
        return new JobBuilder("exportJsonJob", jobRepository)
                .start(step1())
                .build();
    }
}