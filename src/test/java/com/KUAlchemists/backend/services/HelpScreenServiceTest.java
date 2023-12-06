package com.KUAlchemists.backend.services;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HelpScreenServiceTest {

    @Test
    void getHelpContent_validFile_returnsContent() {
        HelpScreenService helpScreenService = new HelpScreenService();
        String content = helpScreenService.getHelpContent();

        assertNotNull(content, "Content should not be null");
        assertFalse(content.isEmpty(), "Content should not be empty");
        // You can add more assertions here to check specific content of the file
    }
}
