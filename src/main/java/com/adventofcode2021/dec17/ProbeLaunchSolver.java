package com.adventofcode2021.dec17;

import java.util.stream.IntStream;

import com.adventofcode2021.common.Point;

class ProbeLaunchSolver {

    private final TargetArea targetArea;
    private final Point originPoint;

    ProbeLaunchSolver( TargetArea targetArea, Point originPoint ) {
        this.targetArea = targetArea;
        this.originPoint = originPoint;
    }

    int getHighestAttainableHeight() {
        return possibleXVelocities( originPoint.x() )
            .map( this::highestAttainableHeight )
            .max()
            .orElseThrow();
    }

    private IntStream possibleXVelocities( int initialX ) {
        int minXVelocity = determineMinXVelocity( initialX );
        return IntStream.rangeClosed( minXVelocity, targetArea.maxX() );
    }

    private int highestAttainableHeight( int xVelocity ) {
        int highestHeight = Integer.MIN_VALUE;
        ProbeLauncher launcher = new ProbeLauncher( originPoint, targetArea );
        for ( int yVelocity = targetArea.minY(); yVelocity < -targetArea.minY(); ++yVelocity ) {
            ProbeLaunchResult result = launcher.launch( new Velocity( xVelocity, yVelocity ) );
            if ( result.hitTarget() ) {
                highestHeight = Math.max( highestHeight, result.highestHeight() );
            }
        }
        return highestHeight;
    }

    private int determineMinXVelocity( int initialX ) {
        for ( int candidateXVelocity = 0; ; ++candidateXVelocity ) {
            int finalXPosition = initialX + candidateXVelocity * (candidateXVelocity + 1) / 2;
            if ( targetArea.containsX( finalXPosition ) ) {
                return candidateXVelocity;
            }
        }
    }

    int countSuccessfulVelocities() {
        return possibleXVelocities( originPoint.x() )
            .map( this::countSuccessfulVelocities )
            .sum();
    }

    private int countSuccessfulVelocities( int xVelocity ) {
        int count = 0;
        ProbeLauncher launcher = new ProbeLauncher( originPoint, targetArea );
        for ( int yVelocity = targetArea.minY(); yVelocity < -targetArea.minY(); ++yVelocity ) {
            ProbeLaunchResult result = launcher.launch( new Velocity( xVelocity, yVelocity ) );
            if ( result.hitTarget() ) {
                ++count;
            }
        }
        return count;
    }
}
