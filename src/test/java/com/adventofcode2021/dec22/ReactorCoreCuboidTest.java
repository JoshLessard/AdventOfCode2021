package com.adventofcode2021.dec22;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;

import org.junit.jupiter.api.Test;

import com.adventofcode2021.common.Point3d;

public class ReactorCoreCuboidTest {

    private static final ReactorCoreCuboid.State DUMMY_STATE = ReactorCoreCuboid.State.OFF;

    @Test
    public void offCuboidHasNoCubesOn() {
        ReactorCoreCuboid cuboid = new ReactorCoreCuboid( ReactorCoreCuboid.State.OFF, new Point3d( -10283, 12983, 16 ), new Point3d( 8839298, 284802, 1298938 ) );

        assertThat( cuboid.numberOfCubesOff() ).isEqualTo( 8849582L * 271820L * 1298923L );
        assertThat( cuboid.numberOfCubesOn() ).isZero();
    }

    @Test
    public void addingOffCuboidToOffCuboidDoesNotTurnOnAnyCubes() {
        ReactorCoreCuboid cuboid = new ReactorCoreCuboid( ReactorCoreCuboid.State.OFF, new Point3d( -100, -100, -100 ), new Point3d( 100, 100, 100 ) );

        cuboid.addCuboid( new ReactorCoreCuboid( ReactorCoreCuboid.State.OFF, new Point3d( -50, -50, -50 ), new Point3d( 50, 50, 50 ) ) );

        assertThat( cuboid.numberOfCubesOff() ).isEqualTo( 201L * 201L * 201L );
        assertThat( cuboid.numberOfCubesOn() ).isZero();
    }

    @Test
    public void addingOnCuboidToOffCuboidTurnsOnCubesInAddedCuboid() {
        ReactorCoreCuboid cuboid = new ReactorCoreCuboid( ReactorCoreCuboid.State.OFF, new Point3d( -1000, -1000, -1000 ), new Point3d( 3000, 3000, 3000 ) );

        cuboid.addCuboid( new ReactorCoreCuboid( ReactorCoreCuboid.State.ON, new Point3d( 20, 20, 20 ), new Point3d( 30, 43, 26 ) ) );

        assertThat( cuboid.numberOfCubesOff() ).isEqualTo( 4001L * 4001L * 4001L - 11L * 24L * 7L );
        assertThat( cuboid.numberOfCubesOn() ).isEqualTo( 11L * 24L * 7L );
    }

    @Test
    public void addingNonoverlappingOnCuboidsToOffCuboidTurnsOnCubesInAddedCuboids() {
        ReactorCoreCuboid cuboid = new ReactorCoreCuboid( ReactorCoreCuboid.State.OFF, new Point3d( -5000, -5000, -5000 ), new Point3d( 5000, 5000, 5000 ) );

        cuboid.addCuboid( new ReactorCoreCuboid( ReactorCoreCuboid.State.ON, new Point3d( 10, 10, 10 ), new Point3d( 20, 20, 20 ) ) );
        cuboid.addCuboid( new ReactorCoreCuboid( ReactorCoreCuboid.State.ON, new Point3d( 100, 100, 100 ), new Point3d( 200, 200, 200 ) ) );

        assertThat( cuboid.numberOfCubesOff() ).isEqualTo( 10001L * 10001L * 10001L - 11L * 11L * 11L - 101L * 101L * 101L );
        assertThat( cuboid.numberOfCubesOn() ).isEqualTo( 11L * 11L * 11L + 101L * 101L * 101L );
    }

    @Test
    public void addingOverlappingOnCuboidsToOffCuboidDoesNotDoubleCountOverlappingCubesInAddedCuboids() {
        ReactorCoreCuboid cuboid = new ReactorCoreCuboid( ReactorCoreCuboid.State.OFF, new Point3d( 0, 0, 0 ), new Point3d( 25000, 25000, 25000 ) );

        cuboid.addCuboid( new ReactorCoreCuboid( ReactorCoreCuboid.State.ON, new Point3d( 10, 10, 10 ), new Point3d( 20, 20, 20 ) ) );
        cuboid.addCuboid( new ReactorCoreCuboid( ReactorCoreCuboid.State.ON, new Point3d( 20, 20, 20 ), new Point3d( 30, 30, 30 ) ) );

        assertThat( cuboid.numberOfCubesOff() ).isEqualTo( 25001L * 25001L * 25001L - 11L * 11L * 11L - 11L * 11L * 11L + 1L );
        assertThat( cuboid.numberOfCubesOn() ).isEqualTo( 11L * 11L * 11L + 11L * 11L * 11L - 1L );
    }

    // TODO
    //  Add a cuboid that completely engulfs a previously-added cuboid
    //  Turn off and then back on a section (or portion thereof)
    //  Make sure all analogous tests exist for ON cuboid

    @Test
    public void onCuboidHasAllCubesOn() {
        ReactorCoreCuboid cuboid = new ReactorCoreCuboid( ReactorCoreCuboid.State.ON, new Point3d( 27, 823, 9784432 ), new Point3d( 7894, 674383, 874385394 ) );

        assertThat( cuboid.numberOfCubesOff() ).isZero();
        assertThat( cuboid.numberOfCubesOn() ).isEqualTo( 7868L * 673561L * 864600963L );
    }

    @Test
    public void addingOnCuboidToOnCuboidDoesNotTurnOnAnyCubes() {
        ReactorCoreCuboid cuboid = new ReactorCoreCuboid( ReactorCoreCuboid.State.ON, new Point3d( -10000, -20000, -30000 ), new Point3d( 10000, 20000, 30000 ) );
        cuboid.addCuboid( new ReactorCoreCuboid( ReactorCoreCuboid.State.ON, new Point3d( -100, -50, -25 ), new Point3d( 1832, 42789, 29842 ) ) );

        assertThat( cuboid.numberOfCubesOff() ).isZero();
        assertThat( cuboid.numberOfCubesOn() ).isEqualTo( 20001L * 40001L * 60001L );
    }

    @Test
    public void addingOffCuboidToOnCuboidTurnsOffCubesInAddedCuboid() {
        ReactorCoreCuboid cuboid = new ReactorCoreCuboid( ReactorCoreCuboid.State.ON, new Point3d( 33, 16, 293 ), new Point3d( 1283, 8983, 47693 ) );
        cuboid.addCuboid( new ReactorCoreCuboid( ReactorCoreCuboid.State.OFF, new Point3d( 50, 55, 400 ), new Point3d( 83, 729, 5164 ) ) );

        assertThat( cuboid.numberOfCubesOff() ).isEqualTo( 34L * 675L * 4765L );
        assertThat( cuboid.numberOfCubesOn() ).isEqualTo( 1251L * 8968L * 47401L - 34L * 675L * 4765L );
    }

    @Test
    public void addingNonoverlappingOffCuboidsToOnCuboidTurnsOfCubesInAddedCuboids() {
        ReactorCoreCuboid cuboid = new ReactorCoreCuboid( ReactorCoreCuboid.State.ON, new Point3d( -10000, -10000, -10000 ), new Point3d( 25000, 25000, 25000 ) );

        cuboid.addCuboid( new ReactorCoreCuboid( ReactorCoreCuboid.State.OFF, new Point3d( 250, 250, 250 ), new Point3d( 600, 600, 600 ) ) );
        cuboid.addCuboid( new ReactorCoreCuboid( ReactorCoreCuboid.State.OFF, new Point3d( 1000, 1000, 1000 ), new Point3d( 2000, 2000, 2000 ) ) );

        assertThat( cuboid.numberOfCubesOff() ).isEqualTo( 351L * 351L * 351L + 1001L * 1001L * 1001L );
        assertThat( cuboid.numberOfCubesOn() ).isEqualTo( 35001L * 35001L * 35001L - 351L * 351L * 351L - 1001L * 1001L * 1001L );
    }

    @Test
    public void cuboidContainsCuboidWithIdenticalDimension() {
        ReactorCoreCuboid cuboid = new ReactorCoreCuboid( DUMMY_STATE, new Point3d( 16, 9882, 924324 ), new Point3d( 768324, 9234842, 202839221 ) );
        ReactorCoreCuboid sameCuboid = new ReactorCoreCuboid( DUMMY_STATE, new Point3d( 16, 9882, 924324 ), new Point3d( 768324, 9234842, 202839221 ) );

        assertThat( cuboid.contains( sameCuboid ) ).isTrue();
    }

    @Test
    public void cuboidContainsSubCuboid() {
        ReactorCoreCuboid cuboid = new ReactorCoreCuboid( DUMMY_STATE, new Point3d( -29823, 4978233, 98243 ), new Point3d( -12832, 8478235, 473928 ) );
        ReactorCoreCuboid subCuboid = new ReactorCoreCuboid( DUMMY_STATE, new Point3d( -29822, 4978234, 98244 ), new Point3d( -12833, 8478234, 473927 ) );

        assertThat( cuboid.contains( subCuboid ) ).isTrue();
    }

    @Test
    public void cuboidDoesNotContainSuperCuboid() {
        ReactorCoreCuboid cuboid = new ReactorCoreCuboid( DUMMY_STATE, new Point3d( 978423, -23498, -6789432 ), new Point3d( 824832443, 392736, 8385 ) );
        ReactorCoreCuboid superCuboid = new ReactorCoreCuboid( DUMMY_STATE, new Point3d( 978424, -23499, -6789433 ), new Point3d( 824832444, 392737, 8386 ) );

        assertThat( cuboid.contains( superCuboid ) ).isFalse();
    }

    @Test
    public void removingOverlapFromContainingCuboidLeavesNothing() {
        ReactorCoreCuboid cuboid = new ReactorCoreCuboid( DUMMY_STATE, new Point3d( -200, -25, 389 ), new Point3d( 583, -10, 529 ) );
        ReactorCoreCuboid containingCuboid = new ReactorCoreCuboid( DUMMY_STATE, new Point3d( -201, -26, 388 ), new Point3d( 584, -9, 530 ) );

        assertThat( cuboid.withOverlapRemoved( containingCuboid ) ).isEmpty();
    }

    @Test
    public void removingOverlapFromSameCuboidLeavesNothing() {
        ReactorCoreCuboid cuboid = new ReactorCoreCuboid( DUMMY_STATE, new Point3d( 27, 9834, 9882934 ), new Point3d( 432, 746732, 58306832 ) );
        ReactorCoreCuboid identicalCuboid = new ReactorCoreCuboid( DUMMY_STATE, new Point3d( 27, 9834, 9882934 ), new Point3d( 432, 746732, 58306832 ) );

        assertThat( cuboid.withOverlapRemoved( identicalCuboid ) ).isEmpty();
    }

    @Test
    public void removingOverlapFromStrictSubCuboidLeavesOnlySurroundingSpaceWithSameStateAsOriginalCuboid() {
        ReactorCoreCuboid cuboid = new ReactorCoreCuboid( ReactorCoreCuboid.State.ON, new Point3d( 0, 0, 0 ), new Point3d( 50, 50, 50 ) );
        ReactorCoreCuboid subCuboid = new ReactorCoreCuboid( DUMMY_STATE, new Point3d( 1, 1, 1 ), new Point3d( 49, 49, 49 ) );

        Set<ReactorCoreCuboid> expectedRemaining = Set.of(
            new ReactorCoreCuboid(ReactorCoreCuboid.State.ON, new Point3d( 0, 0, 0 ), new Point3d( 0, 50, 50 ) ),
            new ReactorCoreCuboid(ReactorCoreCuboid.State.ON, new Point3d( 50, 0, 0 ), new Point3d( 50, 50, 50 ) ),
            new ReactorCoreCuboid(ReactorCoreCuboid.State.ON, new Point3d( 1, 0, 0 ), new Point3d( 49, 50, 0 ) ),
            new ReactorCoreCuboid(ReactorCoreCuboid.State.ON, new Point3d( 1, 0, 50 ), new Point3d( 49, 50, 50 ) ),
            new ReactorCoreCuboid(ReactorCoreCuboid.State.ON, new Point3d( 1, 0, 1 ), new Point3d( 49, 0, 49 ) ),
            new ReactorCoreCuboid(ReactorCoreCuboid.State.ON, new Point3d( 1, 50, 1 ), new Point3d( 49, 50, 49 ) )
        );
        assertThat( cuboid.withOverlapRemoved( subCuboid ) ).isEqualTo( expectedRemaining );

        long totalNumberOfCubes = expectedRemaining.stream().mapToLong( ReactorCoreCuboid::numberOfCubes ).sum() + subCuboid.numberOfCubes();
        assertThat( totalNumberOfCubes ).isEqualTo( cuboid.numberOfCubes() );
    }
}
