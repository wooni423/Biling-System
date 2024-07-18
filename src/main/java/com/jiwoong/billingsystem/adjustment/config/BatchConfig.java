package com.jiwoong.billingsystem.adjustment.config;

import com.jiwoong.billingsystem.adjustment.tasklet.VideoStatTasklet;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;


@Configuration
@RequiredArgsConstructor
public class BatchConfig  {

    // 영상 통계 데이터 조회 및 생성 Job
    private final VideoStatTasklet videoStatTasklet;


    // 동영상 통계 job
    @Bean
    public Job videoStatJob(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager){
        System.out.println("videoStatJob start...");
        return new JobBuilder("videoStatJob",jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(videoStatStep(jobRepository,platformTransactionManager))
                .build();// Job 빌더를 사용하여 Job 생성

    }

    // 동영상 통계 step
    @Bean
    public Step videoStatStep(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager){
        System.out.println("videoStatStep start...");
        return new StepBuilder("videoStatStep",jobRepository)
                .tasklet(videoStatTasklet,platformTransactionManager)
                .build();
    }


    // 동영상 정산 step
}
