package com.adventofcode2021.dec17;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.adventofcode2021.common.Point;

public class TargetAreaTest {

    private static final int DUMMY_COORDINATE = 0;

    @Test
    public void pointAtTopLeftOfTargetAreaIsWithinTargetArea() {
        TargetArea targetArea = new TargetArea( -15, 20, -10, 33 );

        Point topLeft = new Point( -15, 33 );

        assertThat( targetArea.contains( topLeft ) ).isTrue();
    }

    @Test
    public void pointAtTopRightOfTargetAreaIsWithinTargetArea() {
        TargetArea targetArea = new TargetArea( 23, 68, 2, 98 );

        Point topRight = new Point( 68, 98 );

        assertThat( targetArea.contains( topRight ) ).isTrue();
    }

    @Test
    public void pointAtBottomLeftOfTargetAreaIsWithinTargetArea() {
        TargetArea targetArea = new TargetArea( -79, -50, -150, -101 );

        Point bottomLeft = new Point( -79, -150 );

        assertThat( targetArea.contains( bottomLeft ) ).isTrue();
    }

    @Test
    public void pointAtBottomRightOfTargetAreaIsWithinTargetArea() {
        TargetArea targetArea = new TargetArea( -21, 0, 0, 15 );

        Point bottomRight = new Point( 0, 0 );

        assertThat( targetArea.contains( bottomRight ) ).isTrue();
    }

    @Test
    public void pointInsideTargetAreaIsWithinTargetArea() {
        TargetArea targetArea = new TargetArea( 16, 48, -2, 29 );

        Point pointWithinTargetArea = new Point( 31, 14 );

        assertThat( targetArea.contains( pointWithinTargetArea ) ).isTrue();
    }

    @Test
    public void pointOutsideTargetAreaIsNotWithinTargetArea() {
        TargetArea targetArea = new TargetArea( -33, 31, -39, 1 );

        Point pointOutsideTargetArea = new Point( -34, 2 );

        assertThat( targetArea.contains( pointOutsideTargetArea ) ).isFalse();
    }

    @Test
    public void xCoordinateAtFarLeftOfTargetAreaIsWithinTargetArea() {
        TargetArea targetArea = new TargetArea( -25, -2, DUMMY_COORDINATE, DUMMY_COORDINATE );

        assertThat( targetArea.containsX( -25 ) ).isTrue();
    }

    @Test
    public void xCoordinateAtFarRightOfTargetAreaIsWithinTargetArea() {
        TargetArea targetArea = new TargetArea( 16, 92, DUMMY_COORDINATE, DUMMY_COORDINATE );

        assertThat( targetArea.containsX( 92 ) ).isTrue();
    }

    @Test
    public void xCoordinateInBetweenFarLeftAndFarRightOfTargetAreaIsWithinTargetArea() {
        TargetArea targetArea = new TargetArea( 2, 18, DUMMY_COORDINATE, DUMMY_COORDINATE );

        assertThat( targetArea.containsX( 10 ) ).isTrue();
    }

    @Test
    public void xCoordinateOutsideHorizontalRangeOfTargetAreaIsNotWithinTargetArea() {
        TargetArea targetArea = new TargetArea( -16, 43, DUMMY_COORDINATE, DUMMY_COORDINATE );

        assertThat( targetArea.containsX( 52 ) ).isFalse();
    }

    @Test
    public void targetAreaIsNotLeftOfXCoordinateThatIsLeftOfTargetArea() {
        TargetArea targetArea = new TargetArea( 23, 52, DUMMY_COORDINATE, DUMMY_COORDINATE );

        assertThat( targetArea.isLeftOf( 20 ) ).isFalse();
    }

    @Test
    public void targetAreaIsNotLeftOfXCoordinateThatIsWithinTargetArea() {
        TargetArea targetArea = new TargetArea( -10, 14, DUMMY_COORDINATE, DUMMY_COORDINATE );

        assertThat( targetArea.isLeftOf( 3 ) ).isFalse();
    }

    @Test
    public void targetAreaIsNotLeftOfXCoordinateAtFarRightOfTargetArea() {
        TargetArea targetArea = new TargetArea( 18, 34, DUMMY_COORDINATE, DUMMY_COORDINATE );

        assertThat( targetArea.isLeftOf( 34 ) ).isFalse();
    }

    @Test
    public void targetAreaIsLeftOfXCoordinateToRightOfTargetArea() {
        TargetArea targetArea = new TargetArea( 27, 98, DUMMY_COORDINATE, DUMMY_COORDINATE );

        assertThat( targetArea.isLeftOf( 99 ) ).isTrue();
    }

    @Test
    public void targetAreaIsAbovePointThatIsBelowTargetArea() {
        TargetArea targetArea = new TargetArea( DUMMY_COORDINATE, DUMMY_COORDINATE, 3, 98 );

        Point point = new Point( DUMMY_COORDINATE, 2 );

        assertThat( targetArea.isAbove( point ) ).isTrue();
    }

    @Test
    public void targetAreaIsNotAbovePointThatIsAtBottomOfTargetArea() {
        TargetArea targetArea = new TargetArea( DUMMY_COORDINATE, DUMMY_COORDINATE, -3, 10 );

        Point point = new Point( DUMMY_COORDINATE, -3 );

        assertThat( targetArea.isAbove( point ) ).isFalse();
    }

    @Test
    public void targetAreaIsNotAbovePointThatIsWithinTargetArea() {
        TargetArea targetArea = new TargetArea( 3, 16, -16, -3 );

        Point point = new Point( 12, -7 );

        assertThat( targetArea.isAbove( point ) ).isFalse();
    }

    @Test
    public void targetAreaIsNotAbovePointThatIsAboveTargetArea() {
        TargetArea targetArea = new TargetArea( DUMMY_COORDINATE, DUMMY_COORDINATE, 82, 126 );

        Point point = new Point( DUMMY_COORDINATE, 127 );

        assertThat( targetArea.isAbove( point ) ).isFalse();
    }
}
