package com.KUAlchemists.backend.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class HelpScreenService {

    private String helpContent;

    public HelpScreenService(){
        setHelpContent();
    }

    private void setHelpContent(){
        // read the csv file and set the content
        this.helpContent = "";
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("helpContent.txt");
             BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = reader.readLine()) != null) {
                helpContent += line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String getHelpContent(){
        return this.helpContent;
    }


}
