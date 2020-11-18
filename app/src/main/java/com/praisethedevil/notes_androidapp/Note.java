package com.praisethedevil.notes_androidapp;

import java.io.Serializable;

public class Note implements Serializable {
    private String text;
    private Integer id;

    public Note(String text) {
        this.text = text;
        id = globalId++;
    }

    private static Integer globalId = 0;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getId() {
        return id;
    }
}
