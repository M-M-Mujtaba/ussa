package com.example.ussa.Model;
import lombok.Setter;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class TimeTable {

    //singleton class

        private static TimeTable instance = new TimeTable();
        @Getter
        @Setter
        ArrayList<TimeTableRow> timeRows = new ArrayList<>();


        private TimeTable(){

        }

        public static TimeTable getInstance() {
                return instance;
        }

        public  void addRow(TimeTableRow ttl){
                timeRows.add(ttl);
        }

        public TimeTableRow getRow(Day day, TimeSlot timeSlot){
                int index = day.ordinal() * timeSlot.ordinal();
                return timeRows.get(index);
        }

        public void setTimeTableSlot(Day day, TimeSlot timeSlot, TimeTableSlot tts){
                TimeTableRow row = getRow(day, timeSlot);
                row.addTimeTableSlot(tts);

        }

}
