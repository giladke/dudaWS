package com.duda.controllers;

import com.duda.model.Pupil;
import com.duda.model.requests.PupilRequest;
import com.duda.repositories.PupilRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PupilController implements BasicController {

    private PupilRepository repository;

    public PupilController(PupilRepository repository){
        this.repository = repository;
    }

    @PostMapping("/pupil")
    public long createPupil(@RequestBody PupilRequest payload){
        Pupil pupil = convertToPupil(payload);
        return repository.save(pupil).getId();
    }

    private Pupil convertToPupil(PupilRequest payload) {
        return new Pupil(payload.getLat(), payload.getLon(), payload.getGrades());
    }
}
