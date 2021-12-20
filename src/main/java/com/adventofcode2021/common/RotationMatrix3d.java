package com.adventofcode2021.common;

import java.util.Arrays;

public class RotationMatrix3d {

    private static final int DIMENSION_COUNT = 3;

    public static final RotationMatrix3d ROTATE_COUNTERCLOCKWISE_ABOUT_X = new RotationMatrix3d(
        new int[][] {
            {  1,  0,  0 },
            {  0,  0, -1 },
            {  0,  1,  0 }
        }
    );

    public static final RotationMatrix3d ROTATE_COUNTERCLOCKWISE_ABOUT_Y = new RotationMatrix3d(
        new int[][] {
            {  0,  0,  1 },
            {  0,  1,  0 },
            { -1,  0,  0 }
        }
    );

    public static final RotationMatrix3d ROTATE_COUNTERCLOCKWISE_ABOUT_Z = new RotationMatrix3d(
        new int[][] {
            {  0, -1,  0 },
            {  1,  0,  0 },
            {  0,  0,  1 }
        }
    );

    private final int[][] matrix;

    RotationMatrix3d( int[][] matrix ) {
        ensure3By3( matrix );
        this.matrix = copy( matrix );
    }

    private void ensure3By3( int[][] matrix ) {
        if ( matrix.length != DIMENSION_COUNT) {
            throw new IllegalArgumentException( "Matrix does not have " + DIMENSION_COUNT + " rows." );
        }
        for ( int y = 0; y < DIMENSION_COUNT; ++y ) {
            if ( matrix[y].length != DIMENSION_COUNT) {
                throw new IllegalArgumentException( "Matrix row " + y + " does not have " + DIMENSION_COUNT + " columns." );
            }
        }
    }

    private int[][] copy( int[][] matrix ) {
        int[][] copy = new int[DIMENSION_COUNT][];
        for ( int y = 0; y < DIMENSION_COUNT; ++y ) {
            copy[y] = new int[DIMENSION_COUNT];
            System.arraycopy( matrix[y], 0, copy[y], 0, DIMENSION_COUNT );
        }
        return copy;
    }

    public Point3d applyTo( Point3d point ) {
        return new Point3d(
            matrix[0][0] * point.x() + matrix[0][1] * point.y() + matrix[0][2] * point.z(),
            matrix[1][0] * point.x() + matrix[1][1] * point.y() + matrix[1][2] * point.z(),
            matrix[2][0] * point.x() + matrix[2][1] * point.y() + matrix[2][2] * point.z()

        );
    }

    public RotationMatrix3d multipliedBy( RotationMatrix3d other ) {
        int[][] multiplied = new int[DIMENSION_COUNT][DIMENSION_COUNT];

        multiplied[0][0] = matrix[0][0] * other.matrix[0][0] + matrix[0][1] * other.matrix[1][0] + matrix[0][2] * other.matrix[2][0];
        multiplied[0][1] = matrix[0][0] * other.matrix[0][1] + matrix[0][1] * other.matrix[1][1] + matrix[0][2] * other.matrix[2][1];
        multiplied[0][2] = matrix[0][0] * other.matrix[0][2] + matrix[0][1] * other.matrix[1][2] + matrix[0][2] * other.matrix[2][2];

        multiplied[1][0] = matrix[1][0] * other.matrix[0][0] + matrix[1][1] * other.matrix[1][0] + matrix[1][2] * other.matrix[2][0];
        multiplied[1][1] = matrix[1][0] * other.matrix[0][1] + matrix[1][1] * other.matrix[1][1] + matrix[1][2] * other.matrix[2][1];
        multiplied[1][2] = matrix[1][0] * other.matrix[0][2] + matrix[1][1] * other.matrix[1][2] + matrix[1][2] * other.matrix[2][2];

        multiplied[2][0] = matrix[2][0] * other.matrix[0][0] + matrix[2][1] * other.matrix[1][0] + matrix[2][2] * other.matrix[2][0];
        multiplied[2][1] = matrix[2][0] * other.matrix[0][1] + matrix[2][1] * other.matrix[1][1] + matrix[2][2] * other.matrix[2][1];
        multiplied[2][2] = matrix[2][0] * other.matrix[0][2] + matrix[2][1] * other.matrix[1][2] + matrix[2][2] * other.matrix[2][2];

        return new RotationMatrix3d( multiplied );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RotationMatrix3d)) return false;
        RotationMatrix3d that = (RotationMatrix3d) o;
        for ( int y = 0; y < DIMENSION_COUNT; ++y ) {
            for ( int x = 0; x < DIMENSION_COUNT; ++x ) {
                if ( matrix[y][x] != that.matrix[y][x] ) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(matrix);
    }
}
