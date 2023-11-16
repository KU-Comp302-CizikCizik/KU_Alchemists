package com.KUAlchemists.backend;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;



public class AlchemistsGameTest {

    @Test
    public void FizzBuzzNormalNumbers() {

        AlchemistsGame alchemistsGame = new AlchemistsGame();
        Assertions.assertEquals("1", alchemistsGame.FizzBuzz(1));
    }

}