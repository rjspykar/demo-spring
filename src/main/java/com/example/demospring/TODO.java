package com.example.demospring;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.Data;

@Data
@JsonDeserialize
public class TODO {
    int id;
    String description;
    String dateTime;
    boolean isCompleted;

   //"id":13,"description":"ttest 12","isCompleted":false,"dateTime":"2023-06-25T17:28:55.848589"

    


    public TODO(int _id, String _desc, String _date, boolean _value){
        
        id=_id;
        description = _desc;
        dateTime = _date;
        isCompleted = _value;
    }


}