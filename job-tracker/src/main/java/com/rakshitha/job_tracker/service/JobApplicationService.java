package com.rakshitha.job_tracker.service;

import com.rakshitha.job_tracker.dto.JobApplicationRequest;
import com.rakshitha.job_tracker.dto.JobApplicationResponse;
import com.rakshitha.job_tracker.model.JobApplication;
import com.rakshitha.job_tracker.model.User;
import com.rakshitha.job_tracker.repository.JobApplicationRepository;
import com.rakshitha.job_tracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobApplicationService {

    private final JobApplicationRepository jobApplicationRepository;
    private final UserRepository userRepository;

    public JobApplicationResponse create(JobApplicationRequest request, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        JobApplication job = new JobApplication();
        job.setUser(user);
        job.setCompanyName(request.getCompanyName());
        job.setRole(request.getRole());
        job.setJobUrl(request.getJobUrl());
        job.setAppliedDate(request.getAppliedDate());
        job.setStatus(request.getStatus());
        job.setNotes(request.getNotes());

        jobApplicationRepository.save(job);
        return mapToResponse(job);
    }

    public List<JobApplicationResponse> getAll(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return jobApplicationRepository.findByUserId(user.getId())
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public JobApplicationResponse update(Long id, JobApplicationRequest request, String email) {
        JobApplication job = jobApplicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        if (!job.getUser().getEmail().equals(email)) {
            throw new RuntimeException("Unauthorized");
        }

        job.setCompanyName(request.getCompanyName());
        job.setRole(request.getRole());
        job.setJobUrl(request.getJobUrl());
        job.setAppliedDate(request.getAppliedDate());
        job.setStatus(request.getStatus());
        job.setNotes(request.getNotes());

        jobApplicationRepository.save(job);
        return mapToResponse(job);
    }

    public void delete(Long id, String email) {
        JobApplication job = jobApplicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        if (!job.getUser().getEmail().equals(email)) {
            throw new RuntimeException("Unauthorized");
        }

        jobApplicationRepository.deleteById(id);
    }

    private JobApplicationResponse mapToResponse(JobApplication job) {
        JobApplicationResponse response = new JobApplicationResponse();
        response.setId(job.getId());
        response.setCompanyName(job.getCompanyName());
        response.setRole(job.getRole());
        response.setJobUrl(job.getJobUrl());
        response.setAppliedDate(job.getAppliedDate());
        response.setStatus(job.getStatus());
        response.setNotes(job.getNotes());
        response.setCreatedAt(job.getCreatedAt());
        response.setUpdatedAt(job.getUpdatedAt());
        return response;
    }
}