package com.Bhautik.ecommerce.product;


import com.Bhautik.ecommerce.exception.ProductPurchaseException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;
    private final ProductMapper mapper;

    public Integer createProduct(ProductRequest request) {
        var product  = mapper.toProduct(request);
       return repository.save(product).getId();
    }

    public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> request) {
         var productIds = request
                 .stream()
                 .map(ProductPurchaseRequest::productId)
                 .toList();
        var storedProducts = repository.findAllByIdInOrderById(productIds);
        if(productIds.size() != storedProducts.size()){
           throw new ProductPurchaseException("one or more products does not exists");
        }
        var sortedRequest = request.stream().sorted(Comparator.comparing(ProductPurchaseRequest::productId)).toList();
        var purchasedProducts = new ArrayList<ProductPurchaseResponse>();
        for (int i = 0; i < storedProducts.size(); i++) {
            var product = storedProducts.get(i);
            var productRequest = sortedRequest.get(i);
            if (product.getAvailableQuantity() < productRequest.quantity()) {
                throw new ProductPurchaseException("Insufficient stock quantity for product with ID:: " + productRequest.productId());
            }
            var newAvailableQuantity = product.getAvailableQuantity() - productRequest.quantity();
            product.setAvailableQuantity(newAvailableQuantity);
            repository.save(product);
            purchasedProducts.add(mapper.toproductPurchaseResponse(product, productRequest.quantity()));
        }
        return purchasedProducts;
    }

    public ProductResponse findById(Integer productId) {
        return repository.findById(productId).map(mapper::toProductResponse)
                .orElseThrow(() ->  new EntityNotFoundException("Product not found with the ID::"+productId));
    }

    public List<ProductResponse> findAll() {
         return repository.findAll().stream().map(mapper::toProductResponse).collect(Collectors.toList());
    }
}
