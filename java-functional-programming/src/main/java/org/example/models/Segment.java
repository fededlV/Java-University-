package org.example.models;

import java.math.BigDecimal;
import java.util.List;

public record Segment(Point first,
                      Point second) {

    public BigDecimal getDistance() {
        return first.distance(second);
    }

    public boolean includesPoint(Point p) {
        return p.equals(first) || p.equals(second);
    }
}
