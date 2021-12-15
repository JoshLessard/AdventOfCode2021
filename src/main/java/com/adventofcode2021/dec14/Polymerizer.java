package com.adventofcode2021.dec14;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;

class Polymerizer {

    private final Map<String, Character> pairInsertionMap;
    private final InsertionPairIndex insertionPairIndex;
    private final long[] quantityPerChar;
    private final long[] quantityPerInsertionPair;

    Polymerizer( Map<String, Character> pairInsertionMap, String polymerTemplate ) {
        this.pairInsertionMap = pairInsertionMap;
        this.insertionPairIndex = new InsertionPairIndex( pairInsertionMap.keySet() );
        this.quantityPerChar = countChars( polymerTemplate );
        this.quantityPerInsertionPair = countInsertionPairs( polymerTemplate, pairInsertionMap.keySet(), insertionPairIndex );
    }

    private long[] countChars( String polymerTemplate ) {
        long[] quantityPerChar = new long[26];
        for ( char i = 0; i < polymerTemplate.length(); ++i ) {
            ++quantityPerChar[polymerTemplate.charAt( i ) - 'A'];
        }
        return quantityPerChar;
    }

    private long[] countInsertionPairs( String polymerTemplate, Set<String> insertionPairs, InsertionPairIndex insertionPairIndex ) {
        long[] quantityPerInsertionPair = new long[insertionPairs.size()];
        for ( int i = 0; i < polymerTemplate.length() - 1; ++i ) {
            String pair = "" + polymerTemplate.charAt( i ) + polymerTemplate.charAt( i + 1 );
            if ( insertionPairs.contains( pair ) ) {
                int pairIndex = insertionPairIndex.indexOf( pair );
                ++quantityPerInsertionPair[pairIndex];
            }
        }
        return quantityPerInsertionPair;
    }

    void doPairInsertion() {
        long[] nextQuantityPerInsertionPair = new long[quantityPerInsertionPair.length];
        System.arraycopy( quantityPerInsertionPair, 0, nextQuantityPerInsertionPair, 0, quantityPerInsertionPair.length );
        for ( int i = 0; i < quantityPerInsertionPair.length; ++i ) {
            long insertionPairQuantity = quantityPerInsertionPair[i];
            if ( insertionPairQuantity > 0 ) {
                String insertionPair = insertionPairIndex.pairAtIndex( i );
                char characterToInsert = pairInsertionMap.get( insertionPair );
                quantityPerChar[characterToInsert - 'A'] += insertionPairQuantity;
                nextQuantityPerInsertionPair[i] -= insertionPairQuantity;
                String newPair1 = "" + insertionPair.charAt( 0 ) + characterToInsert;
                if ( pairInsertionMap.containsKey( newPair1 ) ) {
                    nextQuantityPerInsertionPair[insertionPairIndex.indexOf( newPair1 )] += insertionPairQuantity;
                }
                String newPair2 = "" + characterToInsert + insertionPair.charAt( 1 );
                if ( pairInsertionMap.containsKey( newPair2 ) ) {
                    nextQuantityPerInsertionPair[insertionPairIndex.indexOf( newPair2 )] += insertionPairQuantity;
                }
            }
        }
        System.arraycopy( nextQuantityPerInsertionPair, 0, quantityPerInsertionPair, 0, quantityPerInsertionPair.length );
    }

    long maxQuantity() {
        return Arrays.stream( quantityPerChar )
            .max()
            .getAsLong();
    }

    long minQuantity() {
        return Arrays.stream( quantityPerChar )
            .filter( q -> q > 0 )
            .min()
            .getAsLong();
    }
}
