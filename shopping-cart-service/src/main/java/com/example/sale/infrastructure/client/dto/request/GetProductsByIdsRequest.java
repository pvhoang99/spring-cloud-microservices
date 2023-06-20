package com.example.sale.infrastructure.client.dto.request;

import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(staticName = "of")
public class GetProductsByIdsRequest {

  private Set<Long> ids;

}
