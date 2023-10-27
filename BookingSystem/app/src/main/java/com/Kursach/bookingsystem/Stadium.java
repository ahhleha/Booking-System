package com.Kursach.bookingsystem;

public class Stadium {
    String name, info, url_image;

    public Stadium(String name, String info, String url_image) {
        this.name = name;
        this.info = info;
        this.url_image = url_image;
    }

    public Stadium() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getUrl_image() {
        return url_image;
    }

    public void setUrl_image(String url_image) {
        this.url_image = url_image;
    }
}
