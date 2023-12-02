package com.KUAlchemists.backend.handlers;

import java.util.ArrayList;
import java.util.List;

public class IngeridentStorageHandler {
    public void sellIngredient(String ingredientName) {
        //TO-DO: sell ingredient should be implemented
        System.out.println("Ingredient " + ingredientName + " is sold");
    }

    //this method should retur current ingredient names ile ["mushroom", "plant", "mushroom", "plant", "mushroom", "plant", "mushroom"]
    public List<String> getCards() {
        List<String> ls = new ArrayList<>();
        ls.add("mushroom");
        ls.add("plant");
        ls.add("mushroom");
        ls.add("frog");
        ls.add("mushroom");
        ls.add("frog");
        ls.add("mushroom");
        return ls;
    }
}
