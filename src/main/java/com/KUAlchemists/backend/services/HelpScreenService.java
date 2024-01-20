package com.KUAlchemists.backend.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * This class is responsible for storing the help screen content.
 */
public class HelpScreenService {
    /**
     * The content of the help screen.
     */
    private String helpContent;
    /**
     * Constructor for HelpScreenService
     */
    public HelpScreenService(){
        setHelpContent();
    }

    /**
     * Sets the content of the help screen.
     */
    private void setHelpContent(){
        // read the txt file and set the content
        this.helpContent = "";
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("helpContent.txt");
             BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            String line;
            // read the txt file line by line
            while ((line = reader.readLine()) != null) {
                helpContent += "\n"+line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets the content of the help screen.
     *
     * @return The content of the help screen.
     */
    public String getHelpContent(){
        return this.helpContent;
    }


}
