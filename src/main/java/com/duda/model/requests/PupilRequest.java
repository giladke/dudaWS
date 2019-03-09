package com.duda.model.requests;

import com.duda.model.Grade;
import lombok.Data;

import javax.persistence.Id;
import java.util.List;

@Data
public class PupilRequest {
    private double lat;
    private double lon;
    private List<Grade> Grades;

    public PupilRequest(double lat, double lon, List<Grade> grades){
        this.lat = lat;
        this.lon = lon;
        this.Grades = grades;
    }
}

