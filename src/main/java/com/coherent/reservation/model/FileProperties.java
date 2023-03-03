package com.coherent.reservation.model;

public enum FileProperties {
    ADD_TO_FILE_DB("src/main/resources/", "reservationDB.txt");

    public final String path;
    public final String name;

    FileProperties(String path, String name) {
        this.path = path;
        this.name = name;
    }

    public  String getPath(){
        return path;
    }

    public  String getName(){
        return name;
    }

    public  String getPathWithName(){
        return path + name;
    }


}
