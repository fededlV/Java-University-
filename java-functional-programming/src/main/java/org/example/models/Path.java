package org.example.models;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public record Path(List<Segment> segmentList) {

    public BigDecimal distance() {
        /*
        * var distance = BigDecimal.ZERO
        * for (Segment segment : segmentList) {
        * distance = distance.add(segment.distance());
        * }
        * return distance
        * Those codes are the same thing, and do the same thing.*/
       return segmentList.stream()
               .map(Segment::getDistance)
               .reduce(BigDecimal::add)
               .orElse(BigDecimal.ZERO);
    }

    public boolean includesPoints(List<Point> points) {
        var pointsInPath =  segmentList().stream()
                .flatMap(s -> Stream.of(s.first(), s.second()))
                .collect(Collectors.toSet());
        return pointsInPath.containsAll(points);
    }


}
