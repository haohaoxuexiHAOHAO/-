package com.ycjedu.javabean;

public class score {
    private String id;
    private String dat;
    private String android;
    private String jsp;

    public score(){}

    public score(String id, String dat, String android, String jsp) {
        this.id = id;
        this.dat = dat;
        this.android = android;
        this.jsp = jsp;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDat(String dat) {
        this.dat = dat;
    }

    public void setAndroid(String android) {
        this.android = android;
    }

    public void setJsp(String jsp) {
        this.jsp = jsp;
    }

    public String getId() {
        return id;
    }

    public String getDat() {
        return dat;
    }

    public String getAndroid() {
        return android;
    }

    public String getJsp() {
        return jsp;
    }
}
