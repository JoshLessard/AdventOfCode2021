package com.adventofcode2021.dec17;

import java.util.List;

import com.adventofcode2021.common.Point;

class ProbeLaunchResult {

    private final TargetAreaResult targetAreaResult;
    private final List<Point> pointsVisited;

    ProbeLaunchResult( TargetAreaResult targetAreaResult, List<Point> pointsVisited ) {
        this.targetAreaResult = targetAreaResult;
        this.pointsVisited = pointsVisited;
    }

    boolean hitTarget() {
        return targetAreaResult.equals( TargetAreaResult.HIT_TARGET );
    }

    int highestHeight() {
        return pointsVisited.stream()
            .mapToInt( Point::y )
            .max()
            .orElseThrow();
    }
}
