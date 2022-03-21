package com.ycjedu.javabean;

public class student {
    private String id;
    private String password;
    private String name;
    private String sex;
    private String school_date;
    private String major;
    private String email;

    public student(){}

    public student(String id, String password, String name, String sex, String school_date, String major, String email) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.sex = sex;
        this.school_date = school_date;
        this.major = major;
        this.email = email;
    }

    public student(String id, String password, String name, String sex, String school_date, String major) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.sex = sex;
        this.school_date = school_date;
        this.major = major;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public String getSchool_date() {
        return school_date;
    }

    public String getMajor() {
        return major;
    }

    public String getEmail() {
        return email;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setSchool_date(String school_date) {
        this.school_date = school_date;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
