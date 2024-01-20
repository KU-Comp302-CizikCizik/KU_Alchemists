package com.KUAlchemists.backend.strategy;

import com.KUAlchemists.backend.enums.Aspect;
import com.KUAlchemists.backend.strategy.concrete.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AlchemicalNamingStrategyMap {
    private static final Map<List<Aspect>, AlchemicalNamingStrategy> strategyMap = new HashMap<>();

    static {
        // Add other aspect combinations and their corresponding strategies
        // Example: strategyMap.put(Arrays.asList(Aspect.POSITIVE_SMALL, Aspect.NEGATIVE_SMALL, Aspect.NEGATIVE_BIG), new Alchemy2Strategy());
        strategyMap.put(Arrays.asList(Aspect.POSITIVE_BIG, Aspect.POSITIVE_BIG, Aspect.POSITIVE_BIG), new Alchemy1Strategy());
        strategyMap.put(Arrays.asList(Aspect.POSITIVE_SMALL, Aspect.NEGATIVE_SMALL, Aspect.NEGATIVE_BIG), new Alchemy2Strategy());
        strategyMap.put(Arrays.asList(Aspect.POSITIVE_SMALL, Aspect.POSITIVE_BIG, Aspect.NEGATIVE_SMALL), new Alchemy3Strategy());
        strategyMap.put(Arrays.asList(Aspect.POSITIVE_BIG, Aspect.NEGATIVE_SMALL, Aspect.POSITIVE_SMALL), new Alchemy4Strategy());
        strategyMap.put(Arrays.asList(Aspect.NEGATIVE_SMALL, Aspect.POSITIVE_SMALL, Aspect.POSITIVE_BIG), new Alchemy5Strategy());
        strategyMap.put(Arrays.asList(Aspect.NEGATIVE_BIG, Aspect.NEGATIVE_BIG, Aspect.NEGATIVE_BIG), new Alchemy6Strategy());
        strategyMap.put(Arrays.asList(Aspect.NEGATIVE_SMALL, Aspect.NEGATIVE_BIG, Aspect.POSITIVE_SMALL), new Alchemy7Strategy());
        strategyMap.put(Arrays.asList(Aspect.NEGATIVE_BIG, Aspect.POSITIVE_SMALL, Aspect.NEGATIVE_SMALL), new Alchemy8Strategy());
    }

    public static AlchemicalNamingStrategy getStrategy(Aspect red, Aspect green, Aspect blue) {
        return strategyMap.getOrDefault(Arrays.asList(red, green, blue), new DefaultAlchemyStrategy());
    }
}
