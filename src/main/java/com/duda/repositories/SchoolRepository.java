package com.duda.repositories;

import com.duda.model.School;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface SchoolRepository extends CrudRepository<School, Long> {
    Collection<School> findByMinimumGpaGreaterThanEqual(int gpa);
    Collection<Long> findPupilListById(long id);
}
