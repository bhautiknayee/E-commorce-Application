package com.Bhautik.order;


import com.Bhautik.customer.CustomerClient;
import com.Bhautik.exception.BusinessException;
import com.Bhautik.orderLine.OrderLineRequest;
import com.Bhautik.orderLine.OrderLineService;
import com.Bhautik.product.ProductClient;
import com.Bhautik.product.PurchaseRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

     private final CustomerClient customerClient;
      private final OrderRepository repository;
     private final ProductClient productClient;
     private final OrderMapper mapper;
     private final OrderLineService orderLineService;
    public Integer createOrder(OrderRequest request) {

        // Find the customer from customerId

      var customer = this.customerClient.findCustomerById(request.customerId())
              .orElseThrow(() -> new BusinessException("Cannot create order :: no Customer exists with the provided Id::"));

      this.productClient.purchaseProducts(request.products());

      var order = this.repository.save(mapper.toOrder(request));

      for(PurchaseRequest purchaseRequest : request.products()){
          orderLineService.saveOrderLine(
                  new OrderLineRequest(null,
                          order.getId(),
                          purchaseRequest.productId()
                          ,purchaseRequest.quantity()));
      }

      return 0;
    }
}
