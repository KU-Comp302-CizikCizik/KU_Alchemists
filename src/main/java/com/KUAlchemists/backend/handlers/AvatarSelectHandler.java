package com.KUAlchemists.backend.handlers;

import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.services.AvatarSelectService;

/**
 * This class is responsible for handling avatar select requests.
 */
public class AvatarSelectHandler {
    /**
     * The singleton instance of AvatarSelectHandler.
     */
    private static AvatarSelectHandler INSTANCE;
    private AvatarSelectService avatarSelectService;

    /**
     * Gets the singleton instance of AvatarSelectHandler.
     *
     * @return The singleton instance of AvatarSelectHandler.
     */
    public static AvatarSelectHandler getInstance(){
        if(INSTANCE == null){
            INSTANCE = new AvatarSelectHandler();
        }
        return INSTANCE;
    }

    /**
     * Constructor for AvatarSelectHandler
     */
    private AvatarSelectHandler() {
        avatarSelectService = new AvatarSelectService();
    }

    /**
     * Selects an avatar.
     *
     * @param avatar The avatar to be selected.
     */
    public void handleSetAvatar(String avatar,int currentPlayerIndex) {
        avatarSelectService.setAvatar(GameEngine.getInstance().getPlayer(currentPlayerIndex), avatar);
        System.out.println("Player avatar" + GameEngine.getInstance().getPlayer(currentPlayerIndex).getAvatar());
        System.out.println("Avatar selected" + avatar);
        System.out.println("");
    }

}
