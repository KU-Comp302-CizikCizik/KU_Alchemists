package com.KUAlchemists.backend.handlers;

import com.KUAlchemists.backend.sound.Sound;
import com.KUAlchemists.backend.sound.SoundListener;

public class SoundHandler {
    private static int volume;

        private Sound sound;

        public static int getVolume(){
            return volume;
        }
        public static void setVolume(int vol){
            volume = vol;
        }

        public SoundHandler() {
            sound = new Sound();
        }

        public void createSound(String effect) {
            sound.setTime(effect);
        }

        public void addListener(SoundListener lis) {
            sound.addSoundListener(lis);
        }




}
