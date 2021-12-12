package com.adventofcode2021.dec12;

interface CaveVisitTracker {

    boolean mayBeVisited( Cave cave );
    void addVisit( Cave cave );
    void removeVisit( Cave cave );
}
