package com.example.goyohome_app; // ← 현재 사용하는 패키지명과 일치해야 함!

public class Device {
    private String name;
    private String location;

    public Device(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }
}
