package com.goldman.FundService.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/batch")
public class BatchJobController {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job exportJsonJob;

    @GetMapping("/run")
    public String runBatchJob() {
        try {
            JobParameters params = new JobParametersBuilder()
                    .addLong("time", System.currentTimeMillis()) // ensures uniqueness
                    .toJobParameters();

            jobLauncher.run(exportJsonJob, params);
            return "Batch job started successfully.";
        } catch (Exception e) {
            e.printStackTrace();
            return "Batch job failed: " + e.getMessage();
        }
    }
}
