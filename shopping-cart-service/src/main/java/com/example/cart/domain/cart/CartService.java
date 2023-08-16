package com.example.cart.domain.cart;

import static java.util.Objects.isNull;

import com.example.cart.infrastructure.client.CatalogFeignClient;
import com.example.cart.infrastructure.client.dto.request.GetProductsByIdsRequest;
import com.example.cart.infrastructure.client.dto.response.ProductDTO;
import com.example.common.utils.SecurityContext;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final CatalogFeignClient catalogClient;

    public Map<Long, ProductDTO> collectProduct(Set<Long> productIds) {
        if (CollectionUtils.isEmpty(productIds)) {
            return Map.of();
        }
        List<ProductDTO> productDTOs = this.catalogClient.getProductsByIds(GetProductsByIdsRequest.of(productIds));

        return productDTOs.stream().collect(Collectors.toMap(ProductDTO::getId, Function.identity()));
    }

    public Cart getCurrentCartOrCreateEmpty() {
        String currentUsername = SecurityContext.currentUsername();
        Cart cart = this.cartRepository.findActiveCart(currentUsername);
        if (isNull(cart)) {
            cart = Cart.createEmpty();
        }

        return cart;
    }

}
