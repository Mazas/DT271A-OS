package com.company;

import java.util.ArrayList;

/**
 * Created by Mazas on 9/8/2017.
 */
public class ErrorList {
    private ArrayList<String> errLog = new ArrayList<>();

    public void addError(String err){
        errLog.add(err);
    }
    public void showErrorLog(){
        errLog.forEach(System.out::println);
    }
}
