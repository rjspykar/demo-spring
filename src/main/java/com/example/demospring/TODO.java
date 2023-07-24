package com.example.demospring;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.Data;
import lombok.Setter;

@Data
@Setter
@JsonDeserialize
public class TODO {
    int id;
    String description;
    String dateTime;
    boolean isCompleted;

    public TODO(int _id, String _desc, String _date, boolean _value){
        id=_id;
        description = _desc;
        dateTime = _date;
        isCompleted = _value;

    }
}