package com.example.ussa.Model;

import java.util.ArrayList;
import java.util.List;

public class toManan {

    public static List<ClassRoom> classes = new ArrayList<>();

    public static void populate(){
        for (int i = 1; i <16 ; i++) {
            classes.add(new ClassRoom("CS" + i));
        }
        classes.add(new ClassRoom("Seminar Hall"));

    }


}
