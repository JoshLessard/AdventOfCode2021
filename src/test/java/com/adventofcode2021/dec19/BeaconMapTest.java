package com.adventofcode2021.dec19;

import static java.util.stream.Collectors.toSet;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;

import org.junit.jupiter.api.Test;

import com.adventofcode2021.common.Delta3d;
import com.adventofcode2021.common.Point3d;

public class BeaconMapTest {

    @Test
    public void mapAbsorbsOtherMapWithAllBeaconsAtSamePointsInSameOrientationWithNoChangesToBeacons() {
        Set<Point3d> beaconPositions = Set.of(
            new Point3d( 13, 27, -82 ),
            new Point3d( -31, 66, -98 ),
            new Point3d( -59, -74, 62 ),
            new Point3d( 81, -84, 49 ),
            new Point3d( 16, 27, 36 ),
            new Point3d( 92, 48, -17 )
        );
        BeaconMap map1 = new BeaconMap( 1, beaconPositions );
        BeaconMap map2 = new BeaconMap( 2, beaconPositions );

        map1.absorb( map2 );

        assertThat( map1.numberOfBeacons() ).isEqualTo( 6 );
        assertThat( map1.hasBeaconsAt( beaconPositions ) ).isTrue();
        assertThat( map1.scannerLocation( 1 ) ).isEqualTo( Point3d.ORIGIN );
        assertThat( map1.scannerLocation( 2 ) ).isEqualTo( Point3d.ORIGIN );
    }

    @Test
    public void mapAbsorbsOtherMapWithAllBeaconsTranslatedButInSameOrientationWithoutAnyNewBeaconsButWithExtraScanner() {
        Set<Point3d> beaconPositions = Set.of(
            new Point3d( 29, -47, 34 ),
            new Point3d( 16, 29, 43 ),
            new Point3d( 52,  0, -69 ),
            new Point3d( -14, -39, 84 ),
            new Point3d( -50, -30, 90 ),
            new Point3d( 64, -128, 42 )
        );
        Delta3d translationDelta = new Delta3d( -8, 17, 24 );
        Set<Point3d> translatedBeaconPositions = beaconPositions.stream()
            .map( p -> p.translate( translationDelta ) )
            .collect( toSet() );
        BeaconMap map1 = new BeaconMap( 1, beaconPositions );
        BeaconMap map2 = new BeaconMap( 2, translatedBeaconPositions );

        map1.absorb( map2 );

        assertThat( map1.numberOfBeacons() ).isEqualTo( 6 );
        assertThat( map1.hasBeaconsAt( beaconPositions ) ).isTrue();
        assertThat( map1.scannerLocation( 1 ) ).isEqualTo( Point3d.ORIGIN );
        assertThat( map1.scannerLocation( 2 ) ).isEqualTo( Point3d.ORIGIN.translate( translationDelta.inverse() ) );
    }
}
