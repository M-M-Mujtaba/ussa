package com.example.ussa.Util;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;



public class TimeTableReader {

    public void readCourseList() throws Exception {
        CSVReader reader1 = new CSVReader(new FileReader("TimeTable1.csv"));
        CSVReader reader2 = new CSVReader(new FileReader("TimeTable2.csv"));

        //Read CSV line by line and use the string array as you want
        String[] nextLine;
        int i = 2;

        //read course info
        while ((nextLine = reader1.readNext()) != null) {
            //skip 2 lines
            if (i > 0 ){
                i--;
            }
            else{

                //check if batch title instead of course
                if (!nextLine[0].isEmpty()){
                    //save to courseList
                    addCourseList(new Course( nextLine[0], nextLine[1], nextLine[2], Integer.parseInt(nextLine[3]), nextLine[4], nextLine[5], Integer.parseInt(nextLine[6]), nextLine[7]));
                }
            }
        }

        int classroom = 0;
        int day = 0;
        String search;
        int slots;

        //read time table
        while ((nextLine = reader2.readNext()) != null) {

            if (!nextLine[1].isEmpty() && !nextLine[1].equalsIgnoreCase("Room")){
                //check if day is changed
                if (!nextLine[0].isEmpty()){
                    if (nextLine[0].contains("Monday")){
                        day = 0;
                    }
                    else if (nextLine[0].contains("Tuesday")){
                        day = 1;
                    }
                    else if (nextLine[0].contains("Wednesday")){
                        day = 2;
                    }
                    else if (nextLine[0].contains("Thursday")){
                        day = 3;
                    }
                    else if (nextLine[0].contains("Friday")){
                        day = 4;
                    }
                    //day change, reset class
                    classroom = 0;
                }
                if (day == 0){
                    classRooms.add(nextLine[1]);
                }
                for (int j = 2; j < 77; j++ ){
                    if (!nextLine[j].isEmpty()){

                        search = nextLine[j];
                        search = search.replaceAll("\\(.*\\)", "");
                        search = search.trim();

                        slots = getCourseSlots(search);

                        for (int k = 0; k < slots; k++){
                            setSlot(day, classroom, j-2+k, nextLine[j]);
                        }
                    }

                }
                classroom++;
            }
        }
    }

    public int getCourseSlots(String courseTitle){
        int slots = 0;
        boolean lab = false;
        if (courseTitle.contains("Lab")) lab = true;

        for (Course c : courseList){
            if (lab){
                if (c.title.contains(courseTitle) && c.title.contains("Lab")){
                    slots = c.slots;
                }
            }
            else{
                if (c.title.contains(courseTitle) && !c.title.contains("Lab")){
                    slots = c.slots;
                }
            }
        }
        return slots;
    }

    public int getDayIndex(String day){
        int ret = -1;
        if (day.equalsIgnoreCase("Monday")){
            ret = 0;
        }
        else if (day.equalsIgnoreCase("Tuesday")){
            ret = 1;
        }
        else if (day.equalsIgnoreCase("Wednesday")){
            ret = 2;
        }
        else if (day.equalsIgnoreCase("Thursday")){
            ret = 3;
        }
        else if (day.equalsIgnoreCase("Friday")){
            ret = 4;
        }
        return ret;
    }

    public int getClassroomIndex(String classroom){
        int ret = -1;
        for (int i = 0; i < 31; i++){
            if (classRooms.get(i).equalsIgnoreCase(classroom)){
                ret = i;
            }
        }
        return ret;
    }

    public int getTimeSlotIndex(int hour24, int min60){
        int ret;
        hour24 = hour24 - 7;
        min60 = min60 - 20;
        min60 += hour24*60;
        int mod = min60%10;
        ret = min60/10 - 1;
        if (mod > 0) ret++;
        return ret;
    }



}
