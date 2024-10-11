package org.example.models;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Objects;

public record Point(BigDecimal x,
                    BigDecimal y) {
    public BigDecimal distance(Point p) {
        Objects.requireNonNull(p);
        //Pitagoras d = sqrt((x1 - x2)2 + (y1 - y2)2)
        return (this.x.subtract(p.x).pow(2)
                .add(this.y.subtract(p.y).pow(2)))
                .sqrt(MathContext.DECIMAL64);
    }
}
