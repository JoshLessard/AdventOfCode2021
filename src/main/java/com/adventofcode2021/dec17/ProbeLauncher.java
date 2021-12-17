package com.adventofcode2021.dec17;

import java.util.ArrayList;
import java.util.List;

import com.adventofcode2021.common.Delta;
import com.adventofcode2021.common.Point;

class ProbeLauncher {

    private final Point initialLocation;
    private final TargetArea targetArea;

    ProbeLauncher( Point initialLocation, TargetArea targetArea ) {
        this.initialLocation = initialLocation;
        this.targetArea = targetArea;
    }

    ProbeLaunchResult launch( Velocity velocity ) {
        Point currentLocation = initialLocation;
        List<Point> pointsVisited = new ArrayList<>();
        pointsVisited.add( currentLocation );
        while ( true ) {
            Point previousLocation = currentLocation;
            currentLocation = currentLocation.translate( new Delta( velocity.x(), velocity.y() ) );
            pointsVisited.add( currentLocation );
            velocity = velocity.drag();
            if ( targetArea.contains( currentLocation ) ) {
                return new ProbeLaunchResult( TargetAreaResult.HIT_TARGET, pointsVisited );
            } else if ( passedThroughTargetArea( previousLocation, currentLocation ) || wentOverTargetArea( previousLocation, currentLocation ) ) {
                return new ProbeLaunchResult( TargetAreaResult.OVERSHOT_TARGET, pointsVisited );
            } else if ( targetArea.isAbove( currentLocation ) ) {
                return new ProbeLaunchResult( TargetAreaResult.MISSED_TARGET, pointsVisited );
            }
        }
    }

    private boolean passedThroughTargetArea( Point previousLocation, Point currentLocation ) {
        return targetArea.isBelow( previousLocation ) && targetArea.isAbove( currentLocation );
    }

    private boolean wentOverTargetArea( Point previousLocation, Point currentLocation ) {
        return previousLocation.x() > targetArea.maxX() && previousLocation.y() > targetArea.minY()
            && currentLocation.x() > targetArea.maxX() && currentLocation.y() < targetArea.minY();
    }
}
