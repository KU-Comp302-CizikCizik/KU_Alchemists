package com.KUAlchemists.backend.strategy;

import com.KUAlchemists.backend.enums.Aspect;

public interface AlchemicalNamingStrategy {
    String getAlchemicalName(Aspect red, Aspect green, Aspect blue);
}
