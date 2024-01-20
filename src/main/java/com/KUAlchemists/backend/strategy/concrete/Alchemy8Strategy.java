package com.KUAlchemists.backend.strategy.concrete;

import com.KUAlchemists.backend.enums.Aspect;
import com.KUAlchemists.backend.strategy.AlchemicalNamingStrategy;

public class Alchemy8Strategy implements AlchemicalNamingStrategy {
    @Override
    public String getAlchemicalName(Aspect red, Aspect green, Aspect blue) {
        return "alchemy8.png";
    }
}
