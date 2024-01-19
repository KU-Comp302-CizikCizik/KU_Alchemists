package com.KUAlchemists.backend.handlers;

import com.KUAlchemists.backend.sound.SoundUI;

public class SoundEffectHandler {
    private static SoundEffectHandler instance;

    public static SoundEffectHandler getInstance(){
        if (instance == null){
            instance = new SoundEffectHandler();
        }
        return instance;
    }

    public void handleSoundEffect(String effect){
        SoundUI beeper = new SoundUI();
        SoundHandler soundHandler = new SoundHandler();
        soundHandler.addListener(beeper);
        soundHandler.createSound(effect);
    }
}
