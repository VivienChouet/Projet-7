package com.bibliotheque.batch.Service;

import com.bibliotheque.batch.DTO.WrapReservationDTO;
import com.bibliotheque.batch.Utility.LoggingController;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Configuration
@EnableBatchProcessing
public class BatchService {

    @Autowired
    private JobBuilderFactory jobs;

    @Autowired
    private StepBuilderFactory steps;

    @Autowired
    private JobLauncher launcher;

    @Autowired
    private JavaMailSender javaMailSender;

    Logger logger = LoggerFactory.getLogger(LoggingController.class);


@Bean
    public Job processJob() throws JsonProcessingException {
    return  jobs.get("processJob")
            .incrementer(new RunIdIncrementer())
            .listener(listener())
            .flow(step1())
            .end()
            .build();
}

@Bean
    Step step1 () throws JsonProcessingException {
    logger.info("Lauch step1");
    return steps.get("step1").<WrapReservationDTO, SimpleMailMessage>chunk(1)
            .reader(new Reader())
            .processor(new Processor())
            .writer(new Writer(this.javaMailSender))
            .build();
}

@Bean
    JobExecutionListener listener(){
    return  new JobExecutionListener() {
        @Override
        public void beforeJob(JobExecution jobExecution) {

        }

        @Override
        public void afterJob(JobExecution jobExecution) {
        System.out.println("All task done");
        }
    };
}

}
