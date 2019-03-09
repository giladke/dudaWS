package com.duda.controllers;

import com.duda.model.School;
import com.duda.model.requests.SchoolRequest;
import com.duda.repositories.SchoolRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class SchoolController implements BasicController {

    private SchoolRepository schoolRepository;

    public SchoolController(SchoolRepository schoolRepository){
        this.schoolRepository = schoolRepository;
    }

    @PostMapping("/school")
    public long createSchool(@RequestBody SchoolRequest payload){
        School school = convertToSchoolObject(payload);
        return schoolRepository.save(school).getId();
    }

    private School convertToSchoolObject(SchoolRequest payload) {
        return new School(
                payload.getLat(),
                payload.getLon(),
                payload.getMaxNumberOfPupils(),
                payload.getMinimumGpa(),
                new ArrayList<>()
        );
    }
}
