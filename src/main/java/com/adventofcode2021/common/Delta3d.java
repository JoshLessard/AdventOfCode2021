package com.adventofcode2021.common;

import java.util.Objects;

public class Delta3d {

    private final int x;
    private final int y;
    private final int z;

    public Delta3d( int x, int y, int z ) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

    public int z() {
        return z;
    }

    public Delta3d inverse() {
        return new Delta3d( -x, -y, -z );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Delta3d)) return false;
        Delta3d delta3d = (Delta3d) o;
        return x == delta3d.x && y == delta3d.y && z == delta3d.z;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }
}
