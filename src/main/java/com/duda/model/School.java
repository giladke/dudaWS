package com.duda.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Data
@Entity
public class School {

    @Id
    @GeneratedValue
    private Long id;

    private double lat;
    private double lon;
    private int minimumGpa;
    private int maxNumberOfPupils;
    private int numOfPupils;

    @ElementCollection
    private List<Long> pupilList;

    public School(double lat, double lon, int minimumGpa, int maxNumberOfPupils, List<Long> pupilList){
        this.lat = lat;
        this.lon = lon;
        this.minimumGpa = minimumGpa;
        this.maxNumberOfPupils = maxNumberOfPupils;
        this.numOfPupils = 0;
        this.pupilList = pupilList;
    }

    public void addPupil(Pupil pupil){
        this.pupilList.add(pupil.getId());
        this.numOfPupils++;
    }

    public boolean hasRoomForEnrollment(){
        return numOfPupils < maxNumberOfPupils;
    }
}
