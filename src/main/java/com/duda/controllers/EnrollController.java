package com.duda.controllers;

import com.duda.services.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EnrollController implements BasicController {

    private EnrollmentService service;

    @Autowired
    public EnrollController(EnrollmentService enrollmentService){
        this.service = enrollmentService;
    }

    @PostMapping("/enroll/{pupilId}")
    public void enrollPupil(@PathVariable long pupilId){
        service.enroll(pupilId);
    }
}
