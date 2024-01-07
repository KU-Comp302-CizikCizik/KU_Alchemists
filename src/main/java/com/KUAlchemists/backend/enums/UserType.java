package com.KUAlchemists.backend.enums;

public enum UserType {

    HOST("host"),
    CLIENT("client");


    private String type;
    UserType(String type) {
        this.type = type;
    }

    public String getTypeString() {
        return type;
    }
}
