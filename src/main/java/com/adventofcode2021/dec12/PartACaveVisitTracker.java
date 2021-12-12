package com.adventofcode2021.dec12;

import java.util.HashMap;
import java.util.Map;

class PartACaveVisitTracker implements CaveVisitTracker {

    private final Map<Cave, Integer> numberOfVisitsPerCave = new HashMap<>();

    @Override
    public boolean mayBeVisited( Cave cave ) {
        int numberOfVisitsAllowed = cave.isSmall() ? 1 : Integer.MAX_VALUE;
        return numberOfVisitsPerCave.getOrDefault( cave, 0 ) < numberOfVisitsAllowed;
    }

    @Override
    public void addVisit( Cave cave ) {
        int currentNumberOfVisits = numberOfVisitsPerCave.getOrDefault( cave, 0 );
        numberOfVisitsPerCave.put( cave, currentNumberOfVisits + 1 );
    }

    @Override
    public void removeVisit( Cave cave ) {
        int currentNumberOfVisits = numberOfVisitsPerCave.getOrDefault( cave, 1 );
        numberOfVisitsPerCave.put( cave, currentNumberOfVisits - 1 );
    }
}
