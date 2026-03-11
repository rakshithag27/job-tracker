package com.rakshitha.job_tracker.dto;

import com.rakshitha.job_tracker.model.ApplicationStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.time.LocalDate;

@Data
public class JobApplicationRequest {

    @NotBlank
    private String companyName;

    @NotBlank
    private String role;

    private String jobUrl;
    private LocalDate appliedDate;
    private ApplicationStatus status;
    private String notes;
}