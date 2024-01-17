package com.KUAlchemists.backend.handlers;

import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.enums.ApplicationMode;
import com.KUAlchemists.backend.enums.GameMode;
import com.KUAlchemists.backend.enums.UserType;
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
        if(GameEngine.getInstance().getApplicationMode() == ApplicationMode.ONLINE){
            avatarSelectService.setAvatar(GameEngine.getInstance().getCurrentPlayer(), avatar);
        }else{
            avatarSelectService.setAvatar(GameEngine.getInstance().getPlayer(currentPlayerIndex), avatar);
        }

    }

}
