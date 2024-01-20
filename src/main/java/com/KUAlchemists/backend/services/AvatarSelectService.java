package com.KUAlchemists.backend.services;

import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.models.Player;

/**
 * This class is responsible for handling avatar select requests.
 */
public class AvatarSelectService {
    /**
     * Constructor for AvatarSelectService
     */
    public AvatarSelectService() {
    }

    /**
     * Selects an avatar.
     *
     * @param avatar The avatar to be selected.
     */
    public void setAvatar(Player player, String avatar) {
        player.setAvatar(avatar);

    }
}
