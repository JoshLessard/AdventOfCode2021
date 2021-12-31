package com.adventofcode2021.dec22;

import static java.util.Collections.emptySet;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import com.adventofcode2021.common.Point3d;

class ReactorCoreCuboid {

    private final State cubeState;
    private final Point3d minCoordinate;
    private final Point3d maxCoordinate;
    private final long numberOfCubes;
    private final List<ReactorCoreCuboid> subCuboids;

    ReactorCoreCuboid( State cubeState, Point3d minCoordinate, Point3d maxCoordinate ) {
        this.cubeState = cubeState;
        this.minCoordinate = minCoordinate;
        this.maxCoordinate = maxCoordinate;
        this.numberOfCubes = (long) (maxCoordinate.x() - minCoordinate.x() + 1) * (maxCoordinate.y() - minCoordinate.y() + 1) * (maxCoordinate.z() - minCoordinate.z() + 1);
        this.subCuboids = new ArrayList<>();
    }

    long numberOfCubes() {
        return numberOfCubes;
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

    Set<ReactorCoreCuboid> withOverlapRemoved( ReactorCoreCuboid other ) {
        if ( other.contains( this ) ) {
            return emptySet();
        } else if ( this.contains( other ) ) {
            // Other cuboid is strictly a subcuboid of this one (if it were identical or containing, the first case would have caught it)
            return Set.of(
                new ReactorCoreCuboid( cubeState, minCoordinate, new Point3d( other.minCoordinate.x() - 1, maxCoordinate.y(), maxCoordinate.z() ) ),
                new ReactorCoreCuboid( cubeState, new Point3d( other.maxCoordinate.x() + 1, minCoordinate.y(), minCoordinate.z() ), maxCoordinate ),
                new ReactorCoreCuboid( cubeState, new Point3d( other.minCoordinate.x(), minCoordinate.y(), minCoordinate.z() ), new Point3d( other.maxCoordinate.x(), maxCoordinate.y(), other.minCoordinate.z() - 1 ) ),
                new ReactorCoreCuboid( cubeState, new Point3d( other.minCoordinate.x(), minCoordinate.y(), other.maxCoordinate.z() + 1 ), new Point3d( other.maxCoordinate.x(), maxCoordinate.y(), maxCoordinate.z() ) ),
                new ReactorCoreCuboid( cubeState, new Point3d( other.minCoordinate.x(), minCoordinate.y(), other.minCoordinate.z() ), new Point3d( other.maxCoordinate.x(), other.minCoordinate.y() - 1, other.maxCoordinate.z() ) ),
                new ReactorCoreCuboid( cubeState, new Point3d( other.minCoordinate.x(), other.maxCoordinate.y() + 1, other.minCoordinate.z() ), new Point3d( other.maxCoordinate.x(), maxCoordinate.y(), other.maxCoordinate.z() ) )
            );
        } else {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReactorCoreCuboid)) return false;
        ReactorCoreCuboid cuboid = (ReactorCoreCuboid) o;
        return cubeState == cuboid.cubeState && minCoordinate.equals(cuboid.minCoordinate) && maxCoordinate.equals(cuboid.maxCoordinate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cubeState, minCoordinate, maxCoordinate);
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
