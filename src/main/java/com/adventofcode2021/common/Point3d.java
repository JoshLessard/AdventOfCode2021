package com.adventofcode2021.common;

import java.util.Objects;

public class Point3d {

    public static final Point3d ORIGIN = new Point3d( 0, 0, 0 );

    private final int x;
    private final int y;
    private final int z;

    public Point3d( int x, int y, int z ) {
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

    public long squaredDistanceTo( Point3d other ) {
        return square( x - other.x ) + square( y - other.y ) + square( z - other.z );
    }

    private long square( long integer ) {
        return integer * integer;
    }

    public Delta3d deltaFrom( Point3d other ) {
        return new Delta3d( x - other.x, y - other.y, z - other.z );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point3d)) return false;
        Point3d point3d = (Point3d) o;
        return x == point3d.x && y == point3d.y && z == point3d.z;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }

    @Override
    public String toString() {
        return "Point3d{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }

    public Point3d translate( Delta3d delta ) {
        return new Point3d( x + delta.x(), y + delta.y(), z + delta.z() );
    }

    public int manhattanDistanceFrom( Point3d other ) {
        return Math.abs( x - other.x ) + Math.abs( y - other.y ) + Math.abs( z - other.z );
    }
}
