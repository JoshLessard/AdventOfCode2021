package com.adventofcode2021.dec19;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.Collectors.toSet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import com.adventofcode2021.common.Delta3d;
import com.adventofcode2021.common.Point3d;
import com.adventofcode2021.common.RotationMatrix3d;

// TODO Make the language consistent between location and position...pick one or the other
class BeaconMap {

    // Facing each of +/- x, +/- y, and +/- z, rotate around the axis you're facing, for a total of 24 possible orientations
    // Assume the scanner starts facing positive X
    static final List<RotationMatrix3d> POSSIBLE_ROTATION_MATRICES = List.of(
        // Check each "up" direction while facing positive X
        RotationMatrix3d.ROTATE_COUNTERCLOCKWISE_ABOUT_X,
        RotationMatrix3d.ROTATE_COUNTERCLOCKWISE_ABOUT_X,
        RotationMatrix3d.ROTATE_COUNTERCLOCKWISE_ABOUT_X,
        RotationMatrix3d.ROTATE_COUNTERCLOCKWISE_ABOUT_X,

        // Flip to negative X and check each "up" direction
        RotationMatrix3d.ROTATE_COUNTERCLOCKWISE_ABOUT_Y.multipliedBy( RotationMatrix3d.ROTATE_COUNTERCLOCKWISE_ABOUT_Y ),
        RotationMatrix3d.ROTATE_COUNTERCLOCKWISE_ABOUT_X,
        RotationMatrix3d.ROTATE_COUNTERCLOCKWISE_ABOUT_X,
        RotationMatrix3d.ROTATE_COUNTERCLOCKWISE_ABOUT_X,

        // Flip to the Y axis and check each "up" direction
        RotationMatrix3d.ROTATE_COUNTERCLOCKWISE_ABOUT_Z,
        RotationMatrix3d.ROTATE_COUNTERCLOCKWISE_ABOUT_Y,
        RotationMatrix3d.ROTATE_COUNTERCLOCKWISE_ABOUT_Y,
        RotationMatrix3d.ROTATE_COUNTERCLOCKWISE_ABOUT_Y,

        // Flip to the other Y direction and check each "up" direction
        RotationMatrix3d.ROTATE_COUNTERCLOCKWISE_ABOUT_Z.multipliedBy( RotationMatrix3d.ROTATE_COUNTERCLOCKWISE_ABOUT_Z ),
        RotationMatrix3d.ROTATE_COUNTERCLOCKWISE_ABOUT_Y,
        RotationMatrix3d.ROTATE_COUNTERCLOCKWISE_ABOUT_Y,
        RotationMatrix3d.ROTATE_COUNTERCLOCKWISE_ABOUT_Y,

        // Flip to the Z axis and check each "up" direction
        RotationMatrix3d.ROTATE_COUNTERCLOCKWISE_ABOUT_X,
        RotationMatrix3d.ROTATE_COUNTERCLOCKWISE_ABOUT_Z,
        RotationMatrix3d.ROTATE_COUNTERCLOCKWISE_ABOUT_Z,
        RotationMatrix3d.ROTATE_COUNTERCLOCKWISE_ABOUT_Z,

        // Flip to the other Z direction and check each "up" direction
        RotationMatrix3d.ROTATE_COUNTERCLOCKWISE_ABOUT_X.multipliedBy( RotationMatrix3d.ROTATE_COUNTERCLOCKWISE_ABOUT_X ),
        RotationMatrix3d.ROTATE_COUNTERCLOCKWISE_ABOUT_Z,
        RotationMatrix3d.ROTATE_COUNTERCLOCKWISE_ABOUT_Z,
        RotationMatrix3d.ROTATE_COUNTERCLOCKWISE_ABOUT_Z
    );

    private final Map<Integer, Point3d> scannerPositionsById;
    private final Set<Point3d> beaconLocations;
    private final SortedMap<Long, Set<Set<Point3d>>> pointPairsBySquaredDistance;

    BeaconMap( int scannerId, Set<Point3d> beaconLocations ) {
        this.scannerPositionsById = new HashMap<>();
        this.scannerPositionsById.put( scannerId, Point3d.ORIGIN );
        this.beaconLocations = new HashSet<>( beaconLocations );
        this.pointPairsBySquaredDistance = mapBeaconLocationsBySquaredDistance( beaconLocations );
    }

    private SortedMap<Long, Set<Set<Point3d>>> mapBeaconLocationsBySquaredDistance( Set<Point3d> beaconLocations ) {
        List<Point3d> beaconLocationList = new ArrayList<>( beaconLocations );
        SortedMap<Long, Set<Set<Point3d>>> pointPairsBySquaredDistance = new TreeMap<>();
        for ( int i = 0; i < beaconLocationList.size() - 1; ++i ) {
            for ( int j = i + 1; j < beaconLocationList.size(); ++j ) {
                Point3d beacon1Location = beaconLocationList.get( i );
                Point3d beacon2Location = beaconLocationList.get( j );
                pointPairsBySquaredDistance.computeIfAbsent( beacon1Location.squaredDistanceTo( beacon2Location ), d -> new HashSet<>() )
                    .add( Set.of( beacon1Location, beacon2Location ) );
            }
        }
        return pointPairsBySquaredDistance;
    }

    boolean sharesBeaconsWith( BeaconMap other, int minimumNumberOfBeacons ) {
        Set<Point3d> overlappingBeaconPositions = uniqueLocations( overlappingBeaconsBySquaredDistance( other ) );
        int overlappingBeaconCount = overlappingBeaconPositions.size();
        return overlappingBeaconCount >= minimumNumberOfBeacons;
    }

    private Set<Point3d> uniqueLocations( Map<Long, Set<Set<Point3d>>> locationsBySquaredDistance ) {
        return locationsBySquaredDistance.values()
            .stream()
            .flatMap( Set::stream )
            .flatMap( Set::stream )
            .collect( toSet() );
    }

    private Map<Long, Set<Set<Point3d>>> overlappingBeaconsBySquaredDistance( BeaconMap other ) {
        return pointPairsBySquaredDistance.entrySet().stream()
            .filter( e -> other.pointPairsBySquaredDistance.containsKey( e.getKey() ) )
            .collect( toMap( Map.Entry::getKey, Map.Entry::getValue ) );
    }

    boolean absorb( BeaconMap other ) {
        if ( other.mapOnto( this ) ) { // get the other map oriented like this one
            absorbScanners( other );
            absorbPointPairsBySquaredDistance( other );
            beaconLocations.addAll( other.beaconLocations );
            return true;
        } else {
            return false;
        }
    }

    private void absorbPointPairsBySquaredDistance( BeaconMap other ) {
        // TODO Fix me
//        other.pointPairsBySquaredDistance.forEach( (squaredDistance, points) -> {
//            if ( pointPairsBySquaredDistance.containsKey( squaredDistance ) && ! pointPairsBySquaredDistance.get( squaredDistance ).equals( points ) ) {
//                // The two maps disagree on the positions of a pair of points separated by the same distance.  That (hopefully) shouldn't happen.
//                throw new IllegalStateException( "Disagreement on positions of points separated by " + squaredDistance );
//            }
//            pointPairsBySquaredDistance.put( squaredDistance, points );
//        } );
        pointPairsBySquaredDistance.putAll( other.pointPairsBySquaredDistance );
    }

    private void absorbScanners( BeaconMap other ) {
        other.scannerPositionsById.forEach( (id, position) -> {
            if ( scannerPositionsById.containsKey( id ) && ! scannerPositionsById.get( id ).equals( position ) ) {
                // The two maps disagree on the position of a scanner.  That shouldn't happen.
                throw new IllegalStateException( "Disagreement on position of scanner " + id );
            }
            scannerPositionsById.put( id, position );
        } );
    }

    private boolean mapOnto( BeaconMap target ) {
        // TODO Would be more efficient to check the current orientation, then try the other 23
        for ( RotationMatrix3d rotationMatrix : POSSIBLE_ROTATION_MATRICES ) {
            rotateAll( rotationMatrix );
            Map<Long, Set<Set<Point3d>>> myOverlappingBeaconsBySquaredDistance = overlappingBeaconsBySquaredDistance( target );
            Map<Long, Set<Set<Point3d>>> targetOverlappingBeaconsBySquaredDistance = myOverlappingBeaconsBySquaredDistance.keySet().stream()
                .collect( toMap( d -> d, target.pointPairsBySquaredDistance::get ) );
            Delta3d translation = translationFor( myOverlappingBeaconsBySquaredDistance, targetOverlappingBeaconsBySquaredDistance );
            if ( translation != null ) {
                translateAll( translation );
                return true;
            }
        }

        return false;
    }

    private Delta3d translationFor(
        Map<Long, Set<Set<Point3d>>> myOverlappingBeaconsBySquaredDistance,
        Map<Long, Set<Set<Point3d>>> targetOverlappingBeaconsBySquaredDistance
    ) {
        for ( long squaredDistance : myOverlappingBeaconsBySquaredDistance.keySet() ) {
            for ( Set<Point3d> myPairOfPointsAtThatDistance : myOverlappingBeaconsBySquaredDistance.get( squaredDistance ) ) {
                for ( Set<Point3d> targetPairOfPointsAtThatDistance : targetOverlappingBeaconsBySquaredDistance.get( squaredDistance ) ) {
                    // We now have a pair of points from each map, separated by the same distance.  Find the translation that maps the other pair
                    // to this one, and see if it maps *all* the other overlapping points to this map's overlapping points.
                    for ( Delta3d possibleTranslation : possibleTranslations( myPairOfPointsAtThatDistance, targetPairOfPointsAtThatDistance ) ) {
                        Set<Point3d> myOverlappingBeacons = uniqueLocations( myOverlappingBeaconsBySquaredDistance );
                        Set<Point3d> targetOverlappingBeacons = uniqueLocations( targetOverlappingBeaconsBySquaredDistance );
                        Set<Point3d> myTranslatedBeacons = translate( myOverlappingBeacons, possibleTranslation );
                        long overlappingBeaconCountAfterTranslation = myTranslatedBeacons.stream().filter( targetOverlappingBeacons::contains ).count();
                        if ( overlappingBeaconCountAfterTranslation >= 12 ) {
                            return possibleTranslation;
                        }
                    }
                }
            }
        }

        return null;
    }

    private List<Delta3d> possibleTranslations( Set<Point3d> from, Set<Point3d> to ) {
        List<Delta3d> possibleDeltas = to.stream().flatMap( t -> from.stream().map( t::deltaFrom ) ).collect( toList() );
        return possibleDeltas.stream()
            .filter( d -> translate( from, d ).equals( to ) )
            .collect( toList() );
    }

    private Set<Point3d> translate( Set<Point3d> positions, Delta3d delta ) {
        return positions.stream()
            .map( p -> p.translate( delta ) )
            .collect( toSet() );
    }

    private Set<Point3d> rotate( Set<Point3d> positions, RotationMatrix3d rotationMatrix ) {
        return positions.stream()
            .map( rotationMatrix::applyTo )
            .collect( toSet() );
    }

    private void rotateAll( RotationMatrix3d rotationMatrix ) {
        // Shift scanners
        for ( int scannerId : scannerPositionsById.keySet() ) {
            Point3d currentScannerPosition = scannerPositionsById.get( scannerId );
            scannerPositionsById.put( scannerId, rotationMatrix.applyTo( currentScannerPosition ) );
        }

        // Shift beacons
        Set<Point3d> rotatedBeacons = rotate( beaconLocations, rotationMatrix );
        beaconLocations.clear();
        beaconLocations.addAll( rotatedBeacons );

        // Shift pairs of points by squared distance
        for ( long squaredDistance : pointPairsBySquaredDistance.keySet() ) {
            Set<Set<Point3d>> rotatedPointPairs = pointPairsBySquaredDistance.get( squaredDistance ).stream().map( s -> rotate( s, rotationMatrix ) ).collect( toSet() );
            pointPairsBySquaredDistance.put( squaredDistance, rotatedPointPairs );
        }
    }

    private void translateAll( Delta3d translation ) {
        // Shift scanners
        for ( int scannerId : scannerPositionsById.keySet() ) {
            Point3d currentScannerPosition = scannerPositionsById.get( scannerId );
            scannerPositionsById.put( scannerId, currentScannerPosition.translate( translation ) );
        }

        // Shift beacons
        Set<Point3d> translatedBeacons = translate( beaconLocations, translation );
        beaconLocations.clear();
        beaconLocations.addAll( translatedBeacons );

        // Shift pairs of points by squared distance
        for ( long squaredDistance : pointPairsBySquaredDistance.keySet() ) {
            Set<Set<Point3d>> translatedPointPairs = pointPairsBySquaredDistance.get( squaredDistance ).stream().map( s -> translate( s, translation ) ).collect( toSet() );
            pointPairsBySquaredDistance.put( squaredDistance, translatedPointPairs );
        }
    }

    int numberOfBeacons() {
        return beaconLocations.size();
    }

    boolean hasBeaconsAt( Set<Point3d> beaconLocations ) {
        return this.beaconLocations.containsAll( beaconLocations );
    }

    Point3d scannerLocation( int scannerId ) {
        return Optional.ofNullable( scannerPositionsById.get( scannerId ) ).orElseThrow( IllegalArgumentException::new );
    }

    int largestManhattanDistanceBetweenScanners() {
        int largestDistance = Integer.MIN_VALUE;
        List<Point3d> scannerPositions = new ArrayList<>( scannerPositionsById.values() );
        for ( int i = 0; i < scannerPositions.size() - 1; ++i ) {
            for ( int j = i + 1; j < scannerPositions.size(); ++j ) {
                int manhattanDistance = scannerPositions.get( i ).manhattanDistanceFrom( scannerPositions.get( j ) );
                largestDistance = Math.max( largestDistance, manhattanDistance );
            }
        }
        return largestDistance;
    }
}
