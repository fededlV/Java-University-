package org.example.models;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class SegmentTest {
    @Test
    void testDistance() {
        var segment = new Segment(
                new Point(BigDecimal.ZERO, BigDecimal.ZERO),
                new Point(BigDecimal.ZERO, BigDecimal.ONE)
        );

        var segment2= new Segment(
                new Point(BigDecimal.ZERO, BigDecimal.ONE),
                new Point(BigDecimal.ONE, BigDecimal.ONE)
        );

        assertEquals(BigDecimal.ONE, segment2.getDistance());
    }

    @Test
    void testIncludesPointsWhen1Point() {
        Point zeroZero = new Point(BigDecimal.ZERO, BigDecimal.ZERO);
        var segment = new Segment(
                zeroZero,
                new Point(BigDecimal.ZERO, BigDecimal.ONE)
        );
        assertTrue(segment.includesPoint(zeroZero));
    }

    @Test
    void testIncludesPointsWhenPointIsNotIncluded() {
        Point zeroZero = new Point(BigDecimal.ZERO, BigDecimal.ZERO);
        Point oneOne = new Point(BigDecimal.ONE, BigDecimal.ONE);
        var segment = new Segment(
                zeroZero,
                new Point(BigDecimal.ZERO, BigDecimal.ONE)
        );
        assertFalse(segment.includesPoint(oneOne));
    }

}