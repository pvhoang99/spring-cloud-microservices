package com.example.cart.infrastructure.client;

import com.example.cart.infrastructure.client.dto.request.GetProductsByIdsRequest;
import com.example.cart.infrastructure.client.dto.response.ProductDTO;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "order-service")
public interface CatalogFeignClient {

    @GetMapping("/v1/products/get-by-ids")
    List<ProductDTO> getProductsByIds(@RequestBody GetProductsByIdsRequest request);

}
