package org.fede.coursespring1.records;

import com.fasterxml.jackson.annotation.JsonProperty;

public record OrderRecord(
        String customerName,
        String productName,
        int quantity
) {
}
