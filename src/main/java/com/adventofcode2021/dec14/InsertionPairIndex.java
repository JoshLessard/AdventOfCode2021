package com.adventofcode2021.dec14;

import static java.util.stream.Collectors.toMap;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

class InsertionPairIndex {

    private final Map<String, Integer> indicesByInsertionPair;
    private final Map<Integer, String> insertionPairsByIndex;

    InsertionPairIndex( Collection<String> insertionPairs ) {
        this.indicesByInsertionPair = mapToIndices( insertionPairs );
        this.insertionPairsByIndex = reverseMap( this.indicesByInsertionPair );
    }

    private Map<String, Integer> mapToIndices( Collection<String> insertionPairs ) {
        Map<String, Integer> pairToIndexMap = new HashMap<>();
        int nextIndex = 0;
        for ( String pair : insertionPairs ) {
            pairToIndexMap.put( pair, nextIndex++ );
        }
        return pairToIndexMap;
    }

    private <K, V> Map<V, K> reverseMap( Map<K, V> map ) {
        return map.entrySet().stream()
            .collect( toMap( Map.Entry::getValue, Map.Entry::getKey ) );
    }

    int indexOf( String insertionPair ) {
        return Optional.ofNullable( indicesByInsertionPair.get( insertionPair ) )
            .orElseThrow( () -> new IllegalArgumentException( "Unrecognized insertion pair: " + insertionPair ) );
    }

    String pairAtIndex( int index ) {
        return Optional.ofNullable( insertionPairsByIndex.get( index ) )
            .orElseThrow( () -> new IllegalArgumentException( "Unrecognized insertion pair index: " + index ) );
    }
}
