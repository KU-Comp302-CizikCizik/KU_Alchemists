package com.KUAlchemists.backend.sound;

import com.KUAlchemists.backend.sound.SoundContrasts;
import com.KUAlchemists.backend.sound.SoundListener;
import com.KUAlchemists.ui.utils.UIConstants;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class SoundUI implements SoundListener {

    private void soundEffect(String effect) {
        try {
            File soundFile = new File(effect);

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onSoundEvent(String effect) {
        soundEffect(effect);
    }

}
