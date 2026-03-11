package com.rakshitha.job_tracker.dto;

import com.rakshitha.job_tracker.model.ApplicationStatus;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class JobApplicationResponse {
    private Long id;
    private String companyName;
    private String role;
    private String jobUrl;
    private LocalDate appliedDate;
    private ApplicationStatus status;
    private String notes;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}