package com.KUAlchemists.backend.services;

import com.KUAlchemists.backend.enums.PotionEffect;
import com.KUAlchemists.backend.models.Board;
import com.KUAlchemists.backend.models.Player;
import com.KUAlchemists.backend.models.Potion;
import com.KUAlchemists.backend.models.PotionStorage;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class SellPotionServiceTest {



    // Test for empty potion storage
    @Test
    public void sellWhenEmptyPotionStorage(){
        Player player = new Player("testPlayer");
        Board.getInstance().createEmptyStoragesForPlayer(player);
        SellPotionService sellPotionService = new SellPotionService();
        sellPotionService.sellPotion(player, "HEALING", 100);
        assert (player.getGold() == 20 && Board.getInstance().getPotionStorage(player).getPotionsList().size() == 0);
    }

    // Test for unpossed potion
    @Test
    public void testUnpossedPotion(){
        Player player = new Player("testPlayer");
        Board.getInstance().createEmptyStoragesForPlayer(player);
        Board.getInstance().addPotionToStorage(player, new Potion(PotionEffect.SPEED, null, null));
        SellPotionService sellPotionService = new SellPotionService();
        sellPotionService.sellPotion(player, "HEALING", 100);
        assert (player.getGold() == 20 && Board.getInstance().getPotionStorage(player).getPotionsList().size() == 1);
    }


    // Test for multiple same potion possed one potion should be sold
    @Test
    public void sellWhenMultipleSamePotionPossed(){
        Player player = new Player("testPlayer");
        Board.getInstance().createEmptyStoragesForPlayer(player);
        Board.getInstance().addPotionToStorage(player, new Potion(PotionEffect.HEALING, null, null));
        Board.getInstance().addPotionToStorage(player, new Potion(PotionEffect.HEALING, null, null));
        Board.getInstance().addPotionToStorage(player, new Potion(PotionEffect.SPEED, null, null));
        SellPotionService sellPotionService = new SellPotionService();
        sellPotionService.sellPotion(player, "HEALING", 100);
        assert (player.getGold() == 120 && Board.getInstance().getPotionStorage(player).getPotionsList().size() == 2);
    }


    //try to sell others potion
    @Test
    public void testSellPoition(){
        Player player = new Player("testPlayer1");
        Board.getInstance().createEmptyStoragesForPlayer(player);
        Player player2 = new Player("testPlayer2");
        Board.getInstance().createEmptyStoragesForPlayer(player2);
        Board.getInstance().addPotionToStorage(player, new Potion(PotionEffect.HEALING, null, null));
        SellPotionService sellPotionService = new SellPotionService();
        sellPotionService.sellPotion(player2, "HEALING", 100);
        assert(player.getGold() == 20 && Board.getInstance().getPotionStorage(player).getPotionsList().size() == 1);
    }


    // Test for empty potion name
    @Test
    public void testEmptyPotionName(){
        Player player = new Player("testPlayer");
        Board.getInstance().createEmptyStoragesForPlayer(player);
        Board.getInstance().addPotionToStorage(player, new Potion(PotionEffect.HEALING, null, null));
        SellPotionService sellPotionService = new SellPotionService();
        sellPotionService.sellPotion(player, "", 100);
        assert(player.getGold() == 20 && Board.getInstance().getPotionStorage(player).getPotionsList().size() == 1);
    }

    // Test for null potion name
    @Test
    public void nullPotionName(){
        Player player = new Player("testPlayer");
        Board.getInstance().createEmptyStoragesForPlayer(player);
        Board.getInstance().addPotionToStorage(player, new Potion(PotionEffect.HEALING, null, null));
        SellPotionService sellPotionService = new SellPotionService();
        sellPotionService.sellPotion(player, null, 100);
        assert(player.getGold() == 20 && Board.getInstance().getPotionStorage(player).getPotionsList().size() == 1);
    }
}