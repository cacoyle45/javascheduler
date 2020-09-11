package com.ciaran.obsidian.scheduler;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class DateOutputter {
    public DateOutputter(){

    }

    public  void RunDateOutput(){
        String date = GetDateTime();
        System.out.println("I'm scheduled and i last ran at "+ date);
    }
    private String GetDateTime(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        return dtf.format(now);
    }
}
