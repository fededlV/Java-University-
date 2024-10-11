package org.example.models;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PathListTest {

    @Test
    void testShortestPathWhenTheListIsEmpty() {
        PathList pathList = new PathList(Collections.emptyList());
        var optionalPath = pathList.shortestPath();

        assertFalse(optionalPath.isPresent());
    }

    @Test
    void testShortestPath() {
        Path path1 = new Path(
                List.of(
                        new Segment(new Point(BigDecimal.ZERO, BigDecimal.ZERO),
                                new Point(BigDecimal.ZERO, BigDecimal.ZERO)
                        ),
                        new Segment(
                                new Point(BigDecimal.ZERO, BigDecimal.ONE),
                                new Point(BigDecimal.ONE, BigDecimal.ONE)
                        ))
        );

        Path path2 = new Path(
                List.of(
                        new Segment(new Point(BigDecimal.ZERO, BigDecimal.ZERO),
                                new Point(BigDecimal.ZERO, BigDecimal.ONE)
                        ),
                        new Segment(
                                new Point(BigDecimal.TEN, BigDecimal.ONE),
                                new Point(BigDecimal.ONE, BigDecimal.ONE)
                        ))
        );
        PathList pathList = new PathList(List.of(path1, path2));
        var optionalPath = pathList.shortestPath();

        assertTrue(optionalPath.isPresent());

        assertEquals(path1, optionalPath.get());
    }

    @Test
    void testShortestPathIncludingPoint() {
        Path path1 = new Path(
                List.of(
                        new Segment(new Point(BigDecimal.ZERO, BigDecimal.ZERO),
                                new Point(BigDecimal.ZERO, BigDecimal.ZERO)
                        ),
                        new Segment(
                                new Point(BigDecimal.ZERO, BigDecimal.ONE),
                                new Point(BigDecimal.ONE, BigDecimal.ONE)
                        ))
        );

        Point includingPoint = new Point(BigDecimal.TEN, BigDecimal.ONE);
        Path path2 = new Path(
                List.of(
                        new Segment(new Point(BigDecimal.ZERO, BigDecimal.ZERO),
                                new Point(BigDecimal.ZERO, BigDecimal.ONE)
                        ),
                        new Segment(
                                includingPoint,
                                new Point(BigDecimal.ONE, BigDecimal.ONE)
                        ))
        );
        PathList pathList = new PathList(List.of(path1, path2));
        var optionalPath = pathList.shortestPath(List.of(includingPoint));

        assertTrue(optionalPath.isPresent());

        assertEquals(path2, optionalPath.get());
    }

    @Test
    void testShortestPathIncludingTwoPoints() {
        Path path1 = new Path(
                List.of(
                        new Segment(new Point(BigDecimal.ZERO, BigDecimal.ZERO),
                                new Point(BigDecimal.ZERO, BigDecimal.ZERO)
                        ),
                        new Segment(
                                new Point(BigDecimal.ZERO, BigDecimal.ONE),
                                new Point(BigDecimal.ONE, BigDecimal.ONE)
                        ))
        );

        Point includingPoint = new Point(BigDecimal.TEN, BigDecimal.ONE);
        Point includingPoint2 = new Point(BigDecimal.ONE, BigDecimal.ONE);
        Path path2 = new Path(
                List.of(
                        new Segment(new Point(BigDecimal.ZERO, BigDecimal.ZERO),
                                new Point(BigDecimal.ZERO, BigDecimal.ONE)
                        ),
                        new Segment(
                                includingPoint,
                                includingPoint2
                        ))
        );
        PathList pathList = new PathList(List.of(path1, path2));
        var optionalPath = pathList.shortestPath(List.of(includingPoint, includingPoint2));

        assertTrue(optionalPath.isPresent());

        assertEquals(path2, optionalPath.get());
    }

    @Test
    void testShortestPathNotIncludingPoint() {
        Path path1 = new Path(
                List.of(
                        new Segment(new Point(BigDecimal.ZERO, BigDecimal.ZERO),
                                new Point(BigDecimal.ZERO, BigDecimal.ZERO)
                        ),
                        new Segment(
                                new Point(BigDecimal.ZERO, BigDecimal.ONE),
                                new Point(BigDecimal.ONE, BigDecimal.ONE)
                        ))
        );

        Point includingPoint = new Point(BigDecimal.TEN, BigDecimal.ONE);
        Path path2 = new Path(
                List.of(
                        new Segment(new Point(BigDecimal.ZERO, BigDecimal.ZERO),
                                new Point(BigDecimal.ZERO, BigDecimal.ONE)
                        ),
                        new Segment(
                                includingPoint,
                                new Point(BigDecimal.ONE, BigDecimal.ONE)
                        ))
        );
        PathList pathList = new PathList(List.of(path1, path2));
        var optionalPath = pathList.shortestPath(List.of(new Point(BigDecimal.TEN, BigDecimal.TEN)));

        assertFalse(optionalPath.isPresent());
    }
}