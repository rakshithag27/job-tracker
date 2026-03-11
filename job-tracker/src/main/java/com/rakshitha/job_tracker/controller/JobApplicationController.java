package com.rakshitha.job_tracker.controller;

import com.rakshitha.job_tracker.dto.JobApplicationRequest;
import com.rakshitha.job_tracker.dto.JobApplicationResponse;
import com.rakshitha.job_tracker.service.JobApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
@RequiredArgsConstructor
public class JobApplicationController {

    private final JobApplicationService jobApplicationService;

    @PostMapping
    public ResponseEntity<JobApplicationResponse> create(
            @Valid @RequestBody JobApplicationRequest request,
            @AuthenticationPrincipal String email) {
        return ResponseEntity.ok(jobApplicationService.create(request, email));
    }

    @GetMapping
    public ResponseEntity<List<JobApplicationResponse>> getAll(
            @AuthenticationPrincipal String email) {
        return ResponseEntity.ok(jobApplicationService.getAll(email));
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobApplicationResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody JobApplicationRequest request,
            @AuthenticationPrincipal String email) {
        return ResponseEntity.ok(jobApplicationService.update(id, request, email));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id,
            @AuthenticationPrincipal String email) {
        jobApplicationService.delete(id, email);
        return ResponseEntity.noContent().build();
    }
}