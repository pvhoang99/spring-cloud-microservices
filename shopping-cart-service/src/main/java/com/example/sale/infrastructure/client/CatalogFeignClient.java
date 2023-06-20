package com.example.sale.infrastructure.client;

import com.example.sale.infrastructure.client.dto.request.GetProductsByIdsRequest;
import com.example.sale.infrastructure.client.dto.response.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(name = "order-service")
@RequestMapping("/catalog/api")
public interface CatalogFeignClient {

    @GetMapping("/v1/products/get-by-ids")
    List<ProductDTO> getProductsByIds(@RequestBody GetProductsByIdsRequest request);

}
