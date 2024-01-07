package com.KUAlchemists.backend.services;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PublishTheoryServiceTest {
    @Test
    public void sealCheck(){
        PublishTheoryService pts = new PublishTheoryService();
        String content= pts.getPlayerSeal();
        assertNotNull(content, "Seal cannot be empty");
    }
    //glassbox



    @Test
    public void actionPointCheck(){
        PublishTheoryService pts = new PublishTheoryService();
        Number number=pts.getPlayerAction();

        assertNotEquals(number,0,"Not enough action point");

    }

}