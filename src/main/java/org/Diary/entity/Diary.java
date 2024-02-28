package org.Diary.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Diary implements Serializable {
    private static final long serialVersionUID = 987654321L;
    private String name;
    private LocalDateTime date;
    private ArrayList<Report> reports;

    public Diary(String name, LocalDateTime date, ArrayList<Report> reports) {
        this.name = name;
        this.date = date;
        this.reports = reports;
    }

    public Diary(String name) {
        this.date = LocalDateTime.now();
        this.reports = new ArrayList<>();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public ArrayList<Report> getReports() {
        return reports;
    }

    public void setReports(ArrayList<Report> reports) {
        this.reports = reports;
    }

    public void addReport(Report report) {
        getReports().add(report);
    }

    public void removeReport(Report report) {
        getReports().remove(report);
    }

    public Report retrieveReport(String name){
        return getReports().stream()
                .filter(r -> r.getName().equals(name))
                .findAny()
                .orElse(null);
    }

    @Override
    public String toString() {
        return "Diary: " +
                "name='" + name + '\'' +
                " - date: " + date +
                "\nreports=\n" + reports;
    }
}
