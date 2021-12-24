package com.adventofcode2021.dec22;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.adventofcode2021.common.Point3d;

class ReactorCoreCuboid {

    private final State cubeState;
    private final Point3d minCoordinate;
    private final Point3d maxCoordinate;
    private final long numberOfCubes;
    private final Set<Point3d> cubesOppositeDefaultState;
    private final List<ReactorCoreCuboid> subCuboids;

    ReactorCoreCuboid( State cubeState, Point3d minCoordinate, Point3d maxCoordinate ) {
        this.cubeState = cubeState;
        this.minCoordinate = minCoordinate;
        this.maxCoordinate = maxCoordinate;
        this.numberOfCubes = (long) (maxCoordinate.x() - minCoordinate.x() + 1) * (maxCoordinate.y() - minCoordinate.y() + 1) * (maxCoordinate.z() - minCoordinate.z() + 1);
        this.cubesOppositeDefaultState = new HashSet<>();
        this.subCuboids = new ArrayList<>();
    }

    long numberOfCubesOff() {
        return numberOfCubes - numberOfCubesOn();
    }

    long numberOfCubesOn() {
        return cubeState.equals( State.OFF )
            ? subCuboids.stream().mapToLong( ReactorCoreCuboid::numberOfCubesOn ).sum()
            : numberOfCubes - subCuboids.stream().mapToLong( ReactorCoreCuboid::numberOfCubesOff ).sum();
    }

    boolean contains( ReactorCoreCuboid other ) {
        return minCoordinate.x() <= other.minCoordinate.x() && maxCoordinate.x() >= other.maxCoordinate.x()
            && minCoordinate.y() <= other.minCoordinate.y() && maxCoordinate.y() >= other.maxCoordinate.y()
            && minCoordinate.z() <= other.minCoordinate.z() && maxCoordinate.z() >= other.maxCoordinate.z();
    }

    void addCuboid( ReactorCoreCuboid subCuboid ) {
        if ( this.cubeState != subCuboid.cubeState ) {
            subCuboids.add( subCuboid );
        }
    }

    enum State {
        ON,
        OFF;

        static State parse( String state ) {
            switch ( state ) {
                case "on":
                    return ON;
                case "off":
                    return OFF;
                default:
                    throw new IllegalArgumentException( "Unrecognized state: " + state );
            }
        }
    }
}
