package com.adventofcode2021.dec12;

import static java.util.Arrays.asList;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class PartBCaveVisitTracker implements CaveVisitTracker {

    private final Set<Cave> endpointCaves = new HashSet<>();
    private final Map<Cave, Integer> numberOfVisitsPerCave = new HashMap<>();
    private final Map<Cave, Integer> numberOfVisitsPerSmallCave = new HashMap<>();

    PartBCaveVisitTracker( Cave... endpointCaves ) {
        this.endpointCaves.addAll( asList( endpointCaves ) );
    }

    @Override
    public boolean mayBeVisited( Cave cave ) {
        return numberOfVisitsPerCave.getOrDefault( cave, 0 ) < numberOfVisitsAllowed( cave );
    }

    private int numberOfVisitsAllowed( Cave cave ) {
        if ( endpointCaves.contains( cave ) ) {
            return 1;
        } else if ( cave.isSmall() ) {
            return smallCaveHasBeenVisitedTwice() ? 1 : 2;
        } else {
            return Integer.MAX_VALUE;
        }
    }

    private boolean smallCaveHasBeenVisitedTwice() {
        return largestNumberOfSmallCaveVisits() >= 2;
    }

    private int largestNumberOfSmallCaveVisits() {
        return numberOfVisitsPerSmallCave.values().stream().max( Integer::compare ).orElse( 0 );
    }

    @Override
    public void addVisit( Cave cave ) {
        int currentNumberOfVisits = numberOfVisitsPerCave.getOrDefault( cave, 0 );
        numberOfVisitsPerCave.put( cave, currentNumberOfVisits + 1 );
        if ( cave.isSmall() ) {
            int currentNumberOfVisitsToSmallCave = numberOfVisitsPerSmallCave.getOrDefault( cave, 0 );
            numberOfVisitsPerSmallCave.put( cave, currentNumberOfVisitsToSmallCave + 1 );
        }
    }

    @Override
    public void removeVisit( Cave cave ) {
        int currentNumberOfVisits = numberOfVisitsPerCave.getOrDefault( cave, 1 );
        numberOfVisitsPerCave.put( cave, currentNumberOfVisits - 1 );
        if ( cave.isSmall() ) {
            int currentNumberOfVisitsToSmallCave = numberOfVisitsPerSmallCave.getOrDefault( cave, 1 );
            numberOfVisitsPerSmallCave.put( cave, currentNumberOfVisitsToSmallCave - 1 );
        }
    }
}
