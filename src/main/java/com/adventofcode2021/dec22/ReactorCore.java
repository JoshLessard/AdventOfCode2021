package com.adventofcode2021.dec22;

import java.util.HashSet;
import java.util.Set;

import com.adventofcode2021.common.Point3d;

class ReactorCore {

    private final Set<Point3d> cubesOn;

    ReactorCore() {
        this.cubesOn = new HashSet<>();
    }

    void turnOn( Point3d point ) {
        cubesOn.add( point );
    }

    void turnOff( Point3d point ) {
        cubesOn.remove( point );
    }

    int numberOfCubesOn() {
        return cubesOn.size();
    }
}
