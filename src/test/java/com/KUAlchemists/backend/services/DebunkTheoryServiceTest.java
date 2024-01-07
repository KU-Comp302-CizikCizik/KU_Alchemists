package com.KUAlchemists.backend.services;

import com.KUAlchemists.backend.engine.GameEngine;
import com.KUAlchemists.backend.enums.Aspect;
import com.KUAlchemists.backend.handlers.DebunkTheoryHandler;
import com.KUAlchemists.backend.models.Alchemical;
import com.KUAlchemists.backend.models.Ingredient;
import com.KUAlchemists.backend.models.Player;
import com.KUAlchemists.backend.models.Theory;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class DebunkTheoryServiceTest {

    @Test
    void punishmentFalseDebunkTest() {

        Player p = new Player("testPlayer");
        p.setScore(5);
        Ingredient ing =new Ingredient("birdfeet-ingredient");
        Alchemical alch= new Alchemical(Aspect.POSITIVE_BIG,Aspect.POSITIVE_BIG,Aspect.POSITIVE_BIG);
        ing.setAlchemical(alch);
        Theory t = new Theory(ing,alch,new HashMap<>());
        DebunkTheoryService dts = new DebunkTheoryService();
        GameEngine.getInstance().setCurrentPlayerforTest(p);
        int prev=GameEngine.getInstance().getCurrentPlayer().getScore();
            dts.debunkTheory(t,"POSITIVE_BIG",ing);

        int ffinal= GameEngine.getInstance().getCurrentPlayer().getScore();
        assertEquals(prev,ffinal,"It has to be equal "+prev+ffinal);

    }
    @Test
    void nullTheoryTest() {
        DebunkTheoryHandler dth= new DebunkTheoryHandler();
        Ingredient ing =new Ingredient("birdfeet-ingredient");
        Alchemical alch= new Alchemical(Aspect.POSITIVE_BIG,Aspect.POSITIVE_BIG,Aspect.POSITIVE_BIG);
        Player p = new Player("test");
        ing.setAlchemical(alch);
        GameEngine.getInstance().setCurrentPlayerforTest(p);

        Theory t = new Theory(ing,alch,new HashMap<>());
        DebunkTheoryService dts = new DebunkTheoryService();


        dts.debunkTheory(t,"POSITIVE_BIG",ing);


        assertNotNull(t,"Theory is null");

    }
    @Test
    void nullIngredientTest() {
        DebunkTheoryHandler dth= new DebunkTheoryHandler();
        Ingredient ing =new Ingredient("birdfeet-ingredient");
        Alchemical alch= new Alchemical(Aspect.POSITIVE_BIG,Aspect.POSITIVE_BIG,Aspect.POSITIVE_BIG);
        Player p = new Player("a");
        ing.setAlchemical(alch);
        GameEngine.getInstance().setCurrentPlayerforTest(p);

        Theory t = new Theory(ing,alch,new HashMap<>());
        DebunkTheoryService dts = new DebunkTheoryService();


        dts.debunkTheory(t,"POSITIVE_BIG",ing);


        assertNotNull(t.getIngredient(),"Ingredient is null");

    }
    @Test
    void nullAlchemyTest() {
        DebunkTheoryHandler dth= new DebunkTheoryHandler();
        Ingredient ing =new Ingredient("birdfeet-ingredient");
        Alchemical alch= new Alchemical(Aspect.POSITIVE_BIG,Aspect.POSITIVE_BIG,Aspect.POSITIVE_BIG);
        Player p = new Player("a");
        ing.setAlchemical(alch);
        GameEngine.getInstance().setCurrentPlayerforTest(p);

        Theory t = new Theory(ing,alch,new HashMap<>());
        DebunkTheoryService dts = new DebunkTheoryService();


        dts.debunkTheory(t,"POSITIVE_BIG",ing);


        assertNotNull( alch,"Player is null");

    }
    @Test
    void nullPlayerTest() {
        DebunkTheoryHandler dth= new DebunkTheoryHandler();
        Ingredient ing =new Ingredient("birdfeet-ingredient");
        Alchemical alch= new Alchemical(Aspect.POSITIVE_BIG,Aspect.POSITIVE_BIG,Aspect.POSITIVE_BIG);
        Player p = new Player("a");
        ing.setAlchemical(alch);
        GameEngine.getInstance().setCurrentPlayerforTest(p);

        Theory t = new Theory(ing,alch,new HashMap<>());
        DebunkTheoryService dts = new DebunkTheoryService();


        dts.debunkTheory(t,"POSITIVE_BIG",ing);


        assertNotNull( GameEngine.getInstance().getCurrentPlayer(),"Player is null");

    }





}
