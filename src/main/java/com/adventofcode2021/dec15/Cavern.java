package com.adventofcode2021.dec15;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Stream;

import com.adventofcode2021.common.Point;

class Cavern {

    private final int[][] risks;
    private final int width;
    private final int height;

    Cavern( int[][] risks ) {
        this.risks = risks;
        this.width = risks[0].length;
        this.height = risks.length;
    }

    int width() {
        return width;
    }

    int height() {
        return height;
    }

    int lowestRisk( Point from, Point to ) {
        int[][] lowestRisks = new int[height][width];
        for ( int y = 0; y < height; ++y ) {
            for ( int x = 0; x < width; ++x ) {
                lowestRisks[y][x] = Integer.MAX_VALUE;
            }
        }

        Queue<PointRisk> pointsToConsider = new LinkedList<>();
        pointsToConsider.add( new PointRisk( from, 0 ) );
        while ( ! pointsToConsider.isEmpty() ) {
            PointRisk currentPointRisk = pointsToConsider.poll();
            Point currentPoint = currentPointRisk.point;
            int currentRisk = currentPointRisk.risk;
            for ( Point neighbour : getNeighbours( currentPoint ) ) {
                int riskToNeighbour = currentRisk + riskAt( neighbour );
                if ( riskToNeighbour < lowestRisks[neighbour.y()][neighbour.x()] ) {
                    lowestRisks[neighbour.y()][neighbour.x()] = riskToNeighbour;
                    pointsToConsider.add( new PointRisk( neighbour, riskToNeighbour ) );
                }
            }
        }

        return lowestRisks[to.y()][to.x()];
    }

    private List<Point> getNeighbours( Point point ) {
        return Stream.of( point.translateX( -1 ), point.translateY( -1 ), point.translateX( 1 ), point.translateY( 1 ) )
            .filter( this::isInbounds )
            .collect( toList() );
    }

    private boolean isInbounds( Point point ) {
        return point.x() >= 0
            && point.x() < width
            && point.y() >= 0
            && point.y() < height;
    }

    private int riskAt( Point point ) {
        return risks[point.y()][point.x()];
    }

    private static class PointRisk {

        private final Point point;
        private final int risk;

        PointRisk( Point point, int risk ) {
            this.point = point;
            this.risk = risk;
        }
    }
}
