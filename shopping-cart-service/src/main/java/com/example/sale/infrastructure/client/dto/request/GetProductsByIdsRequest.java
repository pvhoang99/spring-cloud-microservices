package com.example.sale.infrastructure.client.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@Getter
@AllArgsConstructor(staticName = "of")
public class GetProductsByIdsRequest {

    private Set<Long> ids;

}
