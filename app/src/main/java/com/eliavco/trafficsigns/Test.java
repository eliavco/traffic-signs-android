package com.eliavco.trafficsigns;

import java.util.Date;

public class Test implements Comparable<Test> {
    private long id;
    private long date;
    private String user;
    private long time;
    private int grade;

    public Test() {}

    public Test(long id, long date, String user, long time, int grade) {
        this.id = id;
        this.date = date;
        this.user = user;
        this.time = time;
        this.grade = grade;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        Date dt = new Date(this.date);
        return dt;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getUser() {
        return this.user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public long getTime() {
        return this.time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getGrade() {
        return this.grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    @Override
    public int compareTo(Test other) {
        if (other.getGrade() == this.grade) return (int)(this.time - other.getTime());
        return other.getGrade() - this.grade;
    }

    @Override
    public String toString() {
        return "Test{" +
                "id=" + this.id +
                ", date='" + this.getDate() +
                "', user='" + this.user + '\'' +
                ", time=" + this.time +
                ", grade=" + this.grade +
                '}';
    }
}
