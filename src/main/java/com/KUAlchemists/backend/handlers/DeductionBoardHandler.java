package com.KUAlchemists.backend.handlers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DeductionBoardHandler {


    public HashMap<String,String> getCircleList() {
        HashMap<String,String> circleList = new HashMap<>();
        circleList.put("green_minus","root_feather");
        circleList.put("blue_plus","frog_flower");
        circleList.put("red_minus","mushroom_plant");
        return circleList;
    }

    public List<String> getAlchemyList() {
        ArrayList<String> alchemyList = new ArrayList<>();
        alchemyList.add("alchemy_8_6");
        alchemyList.add("alchemy_8_7");
        alchemyList.add("alchemy_8_8");
        alchemyList.add("alchemy_6_1");
        alchemyList.add("alchemy_6_2");
        alchemyList.add("alchemy_6_3");
        alchemyList.add("alchemy_6_4");
        alchemyList.add("alchemy_6_5");
        alchemyList.add("alchemy_1_3");
        alchemyList.add("alchemy_1_4");
        alchemyList.add("alchemy_1_5");
        return alchemyList;
    }
}
