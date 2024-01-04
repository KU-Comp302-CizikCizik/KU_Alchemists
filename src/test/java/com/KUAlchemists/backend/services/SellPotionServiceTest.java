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


    @Test
    public void sellWhenEmptyPotionStorage(){
        Player player = new Player("testPlayer");
        Board.getInstance().createEmptyStoragesForPlayer(player);
        SellPotionService sellPotionService = new SellPotionService();
        sellPotionService.sellPotion(player, "HEALING", 100);
        assert (player.getGold() == 20 && Board.getInstance().getPotionStorage(player).getPotionsList().size() == 0);
    }


    //Question: Even though I throw IllegalArgumentException, the test still passes. Why?
    @Test
    public void negativePrice(){
        Player player = new Player("testPlayer");
        Board.getInstance().createEmptyStoragesForPlayer(player);
        Board.getInstance().addPotionToStorage(player, new Potion(PotionEffect.HEALING, null, null));
        SellPotionService sellPotionService = new SellPotionService();
        sellPotionService.sellPotion(player, "HEALING", -100);
        assertThrows(IllegalArgumentException.class, () -> {
            sellPotionService.sellPotion(player, "HEALING", -100);
        });
    }

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



    @Test
    public void testSellPoition(){
        Player player = new Player("testPlayer");
        Board.getInstance().createEmptyStoragesForPlayer(player);
        Board.getInstance().addPotionToStorage(player, new Potion(PotionEffect.HEALING, null, null));
        SellPotionService sellPotionService = new SellPotionService();
        sellPotionService.sellPotion(player, "HEALING", 100);
        assert(player.getGold() == 120 && Board.getInstance().getPotionStorage(player).getPotionsList().size() == 0);
    }

    @Test
    public void testEmptyPotionName(){
        Player player = new Player("testPlayer");
        Board.getInstance().createEmptyStoragesForPlayer(player);
        Board.getInstance().addPotionToStorage(player, new Potion(PotionEffect.HEALING, null, null));
        SellPotionService sellPotionService = new SellPotionService();
        sellPotionService.sellPotion(player, "", 100);
        assert(player.getGold() == 20 && Board.getInstance().getPotionStorage(player).getPotionsList().size() == 1);
    }


}