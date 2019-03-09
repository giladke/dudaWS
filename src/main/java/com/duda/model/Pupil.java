package com.duda.model;

import com.duda.util.Haversine;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@NoArgsConstructor
@EqualsAndHashCode
@Entity
public class Pupil {

    @Id
    @GeneratedValue
    private Long id;
    private double lat;
    private double lon;
    private int gpa;

    @OneToMany(targetEntity=Grade.class)
    private List<Grade> Grades;

    @OneToMany(targetEntity = Friendship.class, cascade = CascadeType.ALL)
    private Set<Friendship> friendsList;

    public Pupil(double lat, double lon, List<Grade> grades){
        this.lat = lat;
        this.lon = lon;
        this.Grades = grades;
        this.gpa = calculateGpa(grades);
        this.friendsList = new HashSet<>();
    }

    private int calculateGpa(List<Grade> grades) {
        if(grades == null || grades.isEmpty()){
            return 0;
        }
        int gradesTotal = grades.stream()
                .mapToInt(Grade::getGrade)
                .sum();
        return gradesTotal / grades.size();
    }

    public void addFriend(Pupil friend){
        this.friendsList.add(new Friendship(this.getId(), friend.getId()));
    }

    public double getDistanceFromSchoolInKm(School school){
        return Haversine.distance(this.lat, this.lon, school.getLat(), school.getLon());
    }
}