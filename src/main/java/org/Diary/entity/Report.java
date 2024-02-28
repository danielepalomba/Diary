package org.Diary.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Report implements Serializable{
    private static final long serialVersionUID = 123456789L;
    private String name;
    private LocalDateTime date;
    private String text;

    public Report(String name, String text) {
        setName(name);
        setDate(LocalDateTime.now());
        setText(text);
    }

    public LocalDateTime getDate() {
        return date;
    }

    private void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "\nReport: " +
                "name='" + name + '\'' +
                " - date=" + date +
                " - text='" + text + '\'' +
                '\n';
    }
}
