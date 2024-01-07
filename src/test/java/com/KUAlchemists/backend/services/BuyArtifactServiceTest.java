package com.KUAlchemists.backend.services;

import com.KUAlchemists.backend.models.*;
import javafx.scene.layout.Pane;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class BuyArtifactServiceTest {

    private Deck deck;
    private ArtifactStorage artifactStorage;

    private BuyArtifactService service;
    private ArtifactShop artifactShop;

    private Player player;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        player = new Player("mahmut");

//        deck = mock(Deck.class);
        artifactStorage = new ArtifactStorage(); // Use real IngredientStorage instance
        artifactShop = new ArtifactShop();

        // Manually add the IngredientStorage to the Board's map for this player
        Board.getInstance().createEmptyStoragesForPlayer(player); // Ensure storage is created for the player
        Artifact artifact1 = artifactShop.getArtifact("elixir_of_insight");
        Artifact artifact2 = artifactShop.getArtifact("philosophers_compass");
        Board.getInstance().getArtifactStorage(player).addArtifact(artifact1); // Explicitly add the storage
        Board.getInstance().getArtifactStorage(player).addArtifact(artifact2);
        service = new BuyArtifactService();
    }


    //Test1
    @Test
    void getArtifactsNullTest(){
        ArrayList<String> artifacts = service.getArtifacts();

        //Test1
        for(String artifact: artifacts)
            assertNotNull(artifact, "Artifacts cannot be null");
    }

    //Test2
    @Test
    void getArtifactsLegalityTest(){
        ArrayList<String> artifacts = service.getArtifacts();

        //Test2
        List<String> actualArtifacts = Arrays.asList("elixir_of_insight", "philosophers_compass", "magic_mortar", "printing_press", "wisdom_idol");
        String errorMessage = " Artifacts must named as-> ";
        for(String artifact: actualArtifacts){
            errorMessage += artifact+", ";
        }
        for(String artifact: artifacts){
            boolean found = false;
            for(String actualArtifact: actualArtifacts){
                if(actualArtifact.equals(artifact) || "hard_bargain".equals(artifact))
                    found = true;
            }
            assertNotEquals(found, false, "Illegal name: "+"Artifact "+artifact+" not found."+errorMessage);
        }
    }


    //Test3
    @Test
    void getBoughtArtifactsNullTest(){
        service.buyArtifact(player, "elixir_of_insight");//buying artifact for testing
        ArrayList<String> boughtArtifacts = service.getBoughtArtifacts();

        //Test3
        for (String artifact: boughtArtifacts)
            assertNotNull(artifact, "Artifacts cannot be null");


    }

    //Test4
    @Test
    void getBoughtArtifactsLegalityTest() {
        service.buyArtifact(player, "elixir_of_insight");//buying artifact for testing
        ArrayList<String> boughtArtifacts = service.getBoughtArtifacts();

        //Test4
        List<String> actualArtifacts = Arrays.asList("elixir_of_insight", "philosophers_compass", "magic_mortar", "printing_press", "wisdom_idol");
        String errorMessage = " Artifacts must named as-> ";
        for(String artifact: actualArtifacts){
            errorMessage += artifact+", ";
        }
        for(String artifact: boughtArtifacts){
            boolean found = false;
            for(String actualArtifact: actualArtifacts){
                if(actualArtifact.equals(artifact))
                    found = true;
            }
            assertNotEquals(found, false, "Illegal name: "+"Artifact "+artifact+" not found."+errorMessage);
        }
    }

    //Test5
    @Test
    void buyArtifactProcessSuccessTest() {
        String artifactName = "elixir_of_insight";
        service.buyArtifact(player, artifactName);//buying artifact for testing
        ArrayList<String> boughtArtifacts = service.getBoughtArtifacts();

        //Test4
        boolean found = false;

        for (String artifact : boughtArtifacts) {
            if (artifactName.equals(artifact))
                found = true;
        }
        assertNotEquals(found, false, "Artifact buying failure: " + "Artifact " + artifactName + " not found.");
    }

}