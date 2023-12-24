package com.KUAlchemists.backend.services;

import com.KUAlchemists.backend.enums.ApplicationMode;

public class ApplicationModeService {

    ApplicationMode applicationMode;

    public  ApplicationModeService() {
    }

    public Integer getApplicationMode() {
        if (applicationMode == ApplicationMode.OFFLINE) {
            return 0;
        } else if (applicationMode == ApplicationMode.ONLINE) {
            return 1;
        }
        return -1;
    }

    public void setApplicationMode(Integer mode) {
        if (mode == 0) {
            applicationMode = ApplicationMode.OFFLINE;
        } else if (mode == 1) {
            applicationMode = ApplicationMode.ONLINE;
        }
    }
}
