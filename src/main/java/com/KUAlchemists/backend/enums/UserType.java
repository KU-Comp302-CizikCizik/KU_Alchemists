package com.KUAlchemists.backend.enums;

import java.io.Serializable;

public enum UserType implements Serializable {

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
