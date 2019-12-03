package com.example.ussa.Model;
import lombok.Setter;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class TimeTable {

        private static final Logger logger = LoggerFactory.getLogger(TimeTable.class);

    //singleton class

        private static TimeTable instance = new TimeTable();
        @Getter
        @Setter
        ArrayList<TimeTableRow> timeRows = new ArrayList<>();


        private TimeTable(){
                for (int i = 0; i <30 ; i++) {
                        timeRows.add(new TimeTableRow());
                }
        }

        public static TimeTable getInstance() {
                return instance;
        }

        public  void addRow(TimeTableRow ttl){
                timeRows.add(ttl);
        }

        public TimeTableRow getRow(Day day, TimeSlot timeSlot){
                int index = (day.ordinal() * 6 )+  timeSlot.ordinal();
                System.out.println(" " + index + " " + day.ordinal() + " " +  timeSlot.ordinal());
                return timeRows.get(index);
        }

        public void setTimeTableSlot(Day day, TimeSlot timeSlot, TimeTableSlot tts){
                TimeTableRow row = getRow(day, timeSlot);
                row.addTimeTableSlot(tts);

        }

}
