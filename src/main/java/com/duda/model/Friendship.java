package com.duda.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class Friendship {

    @Id
    @GeneratedValue
    private Long id;

    private long pupilId;
    private long otherPupilId;

    public Friendship(long pupilId, long otherPupilId){
        this.pupilId = pupilId;
        this.otherPupilId = otherPupilId;
    }
}
