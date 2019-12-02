package com.example.ussa.Model;
import lombok.Setter;
import lombok.Getter;
import java.util.List;

public class TimeTable {

    //singleton class

        private static TimeTable instance = new TimeTable();

        private String [][][] timeSlots;
        private List<Course> courseList;
        private List<String> classRooms;


}
