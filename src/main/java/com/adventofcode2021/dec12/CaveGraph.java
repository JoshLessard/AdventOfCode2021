package com.adventofcode2021.dec12;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class CaveGraph {

    private final Map<Cave, Set<Cave>> connections = new HashMap<>();

    void connectCaves( Cave cave1, Cave cave2 ) {
        connections.computeIfAbsent( cave1, f -> new HashSet<>() ).add( cave2 );
        connections.computeIfAbsent( cave2, t -> new HashSet<>() ).add( cave1 );
    }

    public int numberOfPaths( Cave start, Cave end, CaveVisitTracker caveVisitTracker ) {
        return depthFirstPathCounter( start, end, caveVisitTracker );
    }

    private int depthFirstPathCounter( Cave current, Cave destination, CaveVisitTracker caveVisitTracker ) {
        if ( ! caveVisitTracker.mayBeVisited( current ) ) {
            return 0;
        }

        caveVisitTracker.addVisit( current );

        int numberOfPaths = current.equals( destination )
            ? 1
            : connections.get( current ).stream()
                .mapToInt( c -> depthFirstPathCounter( c, destination, caveVisitTracker ) )
                .sum();

        caveVisitTracker.removeVisit( current );

        return numberOfPaths;
    }
}
