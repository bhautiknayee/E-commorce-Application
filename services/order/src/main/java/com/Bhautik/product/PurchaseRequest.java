package com.Bhautik.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PurchaseRequest(
        @NotNull(message = "Product is madatory")
        Integer productId,

        @Positive(message = "Quantity is mandatory")
        double quantity
) {
}
