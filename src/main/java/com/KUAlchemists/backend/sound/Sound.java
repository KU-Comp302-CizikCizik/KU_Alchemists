package com.KUAlchemists.backend.sound;

import java.util.ArrayList;
import java.util.List;

public class Sound {

    List<SoundListener> listeners = new ArrayList<>();

    public void setTime(String effect) {
        publishSoundEvent(effect);

    }


    public void addSoundListener(SoundListener lis) {
        listeners.add(lis);
    }


    public void publishSoundEvent(String effect) {
        for(SoundListener l: listeners)
            l.onSoundEvent(effect);
    }

}
