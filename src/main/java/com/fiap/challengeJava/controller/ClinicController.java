package com.fiap.challengeJava.controller;

import com.fiap.challengeJava.dto.ClinicDTO;
import com.fiap.challengeJava.service.models.ClinicService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/clinics")
public class ClinicController {

    @Autowired
    private ClinicService clinicService;

    @PostMapping
    public ResponseEntity<ClinicDTO> insert(@RequestBody @Valid ClinicDTO clinicDTO) {
        ClinicDTO clinic = clinicService.insert(clinicDTO);
        return ResponseEntity.ok(clinic);
    }

    @GetMapping
    public ResponseEntity<List<ClinicDTO>> findAll() {
        List<ClinicDTO> clinics = clinicService.findAll();
        return ResponseEntity.ok(clinics);
    }

}
