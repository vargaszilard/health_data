package com.econsult.health.controller;

import com.econsult.health.dto.ExaminationDto;
import com.econsult.health.service.ExaminationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/examinations")
public class ExaminationController {

    private final ExaminationService examinationService;

    @GetMapping
    public ResponseEntity<List<ExaminationDto>> getAllExaminations() {
        return ResponseEntity.ok(examinationService.getAllExaminations());
    }

}
