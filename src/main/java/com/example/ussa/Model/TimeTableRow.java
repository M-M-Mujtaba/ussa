package com.example.ussa.Model;

import java.util.ArrayList;
import lombok.Data;

@Data
public class TimeTableRow {


    ArrayList<TimeTableSlot> row;

    public void addTimeTableSlot(TimeTableSlot tts){
        row.add(tts);
    }



}