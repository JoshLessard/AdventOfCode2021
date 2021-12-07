package com.adventofcode2021.dec06;

import java.util.stream.LongStream;

class LanternfishPopulation {

    private long[] lanternfishCountByCycleDaysLeft;

    LanternfishPopulation( long[] lanternfishCountByCycleDaysLeft ) {
        this.lanternfishCountByCycleDaysLeft = new long[lanternfishCountByCycleDaysLeft.length];
        System.arraycopy( lanternfishCountByCycleDaysLeft, 0, this.lanternfishCountByCycleDaysLeft, 0, lanternfishCountByCycleDaysLeft.length );
    }

    public void advanceDay() {
        long numberOfRespawningFish = lanternfishCountByCycleDaysLeft[0];
        for ( int i = 0; i < lanternfishCountByCycleDaysLeft.length - 1; ++i ) {
            lanternfishCountByCycleDaysLeft[i] = lanternfishCountByCycleDaysLeft[i + 1];
        }
        lanternfishCountByCycleDaysLeft[6] += numberOfRespawningFish;
        lanternfishCountByCycleDaysLeft[8] = numberOfRespawningFish;
    }

    long size() {
        return LongStream.of( lanternfishCountByCycleDaysLeft ).sum();
    }
}
