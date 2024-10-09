package com.Bhautik.order;

import com.Bhautik.product.PurchaseRequest;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

public record OrderRequest(

        Integer id,
        String reference,
        @Positive(message =  "Order amount should be positive")
        BigDecimal amount,
        @NotNull(message = "Payment method should be precised")
        PaymentMethod paymentMethod,
        @NotNull(message = "Customer should be  present")
        String customerId,
        @NotEmpty(message = "you should buy at least one product")
        List<PurchaseRequest> products

) {
}
