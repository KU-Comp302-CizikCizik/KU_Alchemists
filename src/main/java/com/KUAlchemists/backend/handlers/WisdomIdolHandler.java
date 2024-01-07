package com.KUAlchemists.backend.handlers;

public class WisdomIdolHandler {

    private static WisdomIdolHandler instance = null;

    private String debunkIngredientName;

    private WisdomIdolHandler() {
        debunkIngredientName = "";
    }

    public static WisdomIdolHandler getInstance() {
        if (instance == null) {
            instance = new WisdomIdolHandler();
        }
        return instance;
    }

    public void setDebunkIngredientName(String ingredientName) {
        debunkIngredientName = ingredientName;
    }

    public String getDebunkIngredientName() {
        return debunkIngredientName;
    }

    public void useWisdomIdol() {
        System.out.println("Wisdom Idol used");
        //TO-DO: implement the useWisdomIdol method
        //he shouldn't lose reputation if he uses the wisdom idol
    }
}
