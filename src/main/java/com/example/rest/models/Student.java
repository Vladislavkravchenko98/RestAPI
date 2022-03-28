package com.example.rest.models;

import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private Date dayOfNewStudy;

    @JsonView(View.Base.class)
    private String massage;

    private Timestamp visit;

    private boolean status;

   //Загрузка изображения
    @Column(nullable = true, length = 64)
    private String photos;

    public Student() {
    }

    public Student(Date dayOfNewStudy) {
        this.dayOfNewStudy = dayOfNewStudy;
    }

    public Student(String name, boolean status, String massage) {
        this.name = name;
        this.status = status;
        this.massage = massage;
    }

    public Student(String name, Date dayOfNewStudy, boolean status, String massage, Timestamp visit) {
        this.name = name;
        this.dayOfNewStudy = dayOfNewStudy;
        this.status = status;
        this.massage = massage;
        this.visit = visit;
    }

    public Student(String name, Date dayOfNewStudy, String massage, Timestamp visit) {
        this.name = name;
        this.dayOfNewStudy = dayOfNewStudy;
        this.massage = massage;
        this.visit = visit;
    }

    public Student(String name, boolean status, String massage, String photos) {
        this.name = name;
        this.status = status;
        this.massage = massage;
        this.photos = photos;
    }

    public Student(String name, Date dayOfNewStudy, boolean status, String massage, Timestamp visit, String photos) {
        this.name = name;
        this.dayOfNewStudy = dayOfNewStudy;
        this.status = status;
        this.massage = massage;
        this.visit = visit;
        this.photos = photos;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDayOfNewStudy() {
        return dayOfNewStudy;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }

    public Timestamp getVisit() {
        return visit;
    }

    public void setVisit(Timestamp visit) {
        this.visit = visit;
    }

    public String getPhotos() {
        return photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }
}
