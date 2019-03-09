package com.duda.repositories;

import com.duda.model.Pupil;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PupilRepository extends CrudRepository<Pupil, Long> {
}
