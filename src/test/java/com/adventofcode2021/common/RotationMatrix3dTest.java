package com.adventofcode2021.common;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class RotationMatrix3dTest {

    @Test
    public void xRotationMatrixRotatesPoint90DegreesCounterClockwiseAboutXAxis() {
        Point3d pointAt0X = new Point3d( 0, 2, 1 );
        assertThat( RotationMatrix3d.ROTATE_COUNTERCLOCKWISE_ABOUT_X.applyTo( pointAt0X ) ).isEqualTo( new Point3d( 0, -1, 2 ) );

        Point3d pointWithXMagnitude = new Point3d( -3, -4, -7 );
        assertThat( RotationMatrix3d.ROTATE_COUNTERCLOCKWISE_ABOUT_X.applyTo( pointWithXMagnitude ) ).isEqualTo( new Point3d( -3, 7, -4 ) );
    }

    @Test
    public void yRotationMatrixRotatesPoint90DegreesCounterClockwiseAboutYAxis() {
        Point3d pointAt0Y = new Point3d( -12, 0, 16 );
        assertThat( RotationMatrix3d.ROTATE_COUNTERCLOCKWISE_ABOUT_Y.applyTo( pointAt0Y ) ).isEqualTo( new Point3d( 16, 0, 12 ) );

        Point3d pointWithYMagnitude = new Point3d( 28, 11, -59 );
        assertThat( RotationMatrix3d.ROTATE_COUNTERCLOCKWISE_ABOUT_Y.applyTo( pointWithYMagnitude ) ).isEqualTo( new Point3d( -59, 11, -28 ) );

        Point3d test = new Point3d( -18, -16, 41 );
        assertThat( RotationMatrix3d.ROTATE_COUNTERCLOCKWISE_ABOUT_Y.applyTo( test ) ).isEqualTo( new Point3d( 41, -16, 18 ) );
    }

    @Test
    public void zRotationMatrixRotatesPoint90DegreesCounterClockwiseAboutZAxis() {
        Point3d pointAt0Z = new Point3d( 33, -31, 0 );
        assertThat( RotationMatrix3d.ROTATE_COUNTERCLOCKWISE_ABOUT_Z.applyTo( pointAt0Z ) ).isEqualTo( new Point3d( 31, 33, 0 ) );

        Point3d pointWithZMagnitude = new Point3d( -79, 14, -27 );
        assertThat( RotationMatrix3d.ROTATE_COUNTERCLOCKWISE_ABOUT_Z.applyTo( pointWithZMagnitude ) ).isEqualTo( new Point3d( -14, -79, -27 ) );
    }

    @Test
    public void rotationMatricesCanBeMultiplied() {
        RotationMatrix3d matrix1 = new RotationMatrix3d(
            new int[][] {
                {  3, 18,  9 },
                { -6, 24, 82 },
                { 52,  7, -9 }
            }
        );
        RotationMatrix3d matrix2 = new RotationMatrix3d(
            new int[][] {
                { 14,  31, 79 },
                { 66, -39, 33 },
                { -23,  2,  4 }
            }
        );

        RotationMatrix3d expected = new RotationMatrix3d(
            new int[][] {
                { 1023, -591,  867 },
                { -386, -958,  646 },
                { 1397, 1321, 4303 }
            }
        );

        RotationMatrix3d actual = matrix1.multipliedBy( matrix2 );
        assertThat( actual ).isEqualTo( expected );
    }
}
