package com.example.recyclerandcardviewapp;

import java.sql.Time;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Model {
    private String title;
    private String description;
    private int img;

    //Agregando atributo profesor
    private String professor;

    //Agregando atributo día
    private String day;

    //Agregando atributo hora
    private Time hour;

    //Agregando atributo fecha
    private Calendar date;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }


    //Agregando getter y setter de Professor
    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    //Agregando getter y setter de day
    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    //Agregando getter y setter de hour
    public Time getHour() {
        return hour;
    }

    public void setHour(Time hour) {
        this.hour = hour;
    }

    //Agregando getter y setter de date
    public Calendar getDate() {
        return date;
    }

    public void setDate(int year, int month, int day) {
        this.date = new GregorianCalendar(year,month,day);
    }
}
