package com.example.rest.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nameOfSubject;

    private int active;

    public Subject(String nameOfsubject) {
        this.nameOfSubject = nameOfSubject;
    }

    public Subject() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNameOfsubject() {
        return nameOfSubject;
    }

    public void setNameOfSubject(String nameOfsubject) {
        this.nameOfSubject = nameOfsubject;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }
}
