package com.duda.services.impl;

import com.duda.model.Pupil;
import com.duda.model.School;
import com.duda.repositories.PupilRepository;
import com.duda.repositories.SchoolRepository;
import com.duda.services.EnrollmentService;
import com.duda.services.FriendshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

    private static final Comparator<Map.Entry<Double, List<School>>> SCHOOL_GRADE_COMPARATOR = Comparator.comparingDouble(Map.Entry::getKey);

    private SchoolRepository schoolRepository;
    private PupilRepository pupilRepository;
    private FriendshipService friendshipService;

    @Autowired
    public EnrollmentServiceImpl(SchoolRepository schoolRepository, PupilRepository pupilRepository, FriendshipService friendshipService){
        this.schoolRepository = schoolRepository;
        this.pupilRepository = pupilRepository;
        this.friendshipService = friendshipService;
    }

    @Override
    public void enroll(long pupilId){
        Pupil pupil = getPupil(pupilId);

        Collection<School> schoolsEligibleForEnrolment = getEligibleSchools(pupil);
        if(schoolsEligibleForEnrolment.isEmpty()){
            System.out.println("no schools on the system");
            return;
        }

        School schoolToEnroll = findSchoolWithTheHighestRating(pupil, schoolsEligibleForEnrolment);
        System.out.println(String.format("selected school with id: %s", schoolToEnroll.getId()));

        enroll(pupil, schoolToEnroll);
        System.out.println(String.format("pupil id: %s was enrolled to school id : %s", pupil.getId(), schoolToEnroll.getId()));
    }

    private void enroll(Pupil pupil, School schoolToEnroll) {
        schoolToEnroll.addPupil(pupil);
        schoolRepository.save(schoolToEnroll);
    }

    private School findSchoolWithTheHighestRating(Pupil pupil, Collection<School> schoolsEligleForEnrolment) {
        Map<Double, List<School>> scoreToSchoolMapping = schoolsEligleForEnrolment.stream()
                .collect(Collectors.groupingBy(school -> calculateEnrollmentScore(school, pupil)));

        Optional<Map.Entry<Double, List<School>>> topSchools = scoreToSchoolMapping.entrySet().stream()
                .max(SCHOOL_GRADE_COMPARATOR);

        if (! topSchools.isPresent() || topSchools.get().getValue().isEmpty()) {
            throw new NoSuchElementException("no school found");
        }
        return topSchools.get().getValue().iterator().next();
    }

    private Pupil getPupil(long pupilId) {
        Optional<Pupil> pupil = pupilRepository.findById(pupilId);
        if(! pupil.isPresent()){
            throw new NoSuchElementException("no pupil with the given id");
        }
        return pupil.get();
    }
    private double calculateEnrollmentScore(School school, Pupil pupil){
        double distanceFromSchoolInKm = pupil.getDistanceFromSchoolInKm(school);
        if(distanceFromSchoolInKm == 0){
            return 0;
        }
        return getFriendsCount(pupil, school) / distanceFromSchoolInKm;
    }

    private Set<School> getEligibleSchools(Pupil pupil) {
        return schoolRepository.findByMinimumGpaGreaterThanEqual(pupil.getGpa()).stream()
                .filter(School::hasRoomForEnrollment)
                .collect(Collectors.toSet());
    }

    private long getFriendsCount(Pupil pupil, School school){
        return friendshipService.getFriendsIds(pupil.getId()).stream()
                .filter(isInSchool(school.getId()))
                .count();
    }

    private Predicate<Long> isInSchool(long schoolId){
        return pupilId-> schoolRepository.findPupilListById(schoolId).contains(pupilId);
    }



}
