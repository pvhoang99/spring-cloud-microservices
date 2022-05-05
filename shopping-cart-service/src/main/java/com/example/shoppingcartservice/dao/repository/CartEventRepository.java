package com.example.shoppingcartservice.dao.repository;

import com.example.shoppingcartservice.dao.entity.CartEvent;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CartEventRepository extends JpaRepository<CartEvent, Long> {

  @Query(value = "SELECT c.*\n" +
      "FROM (\n" +
      "       SELECT *\n" +
      "       FROM cart_event\n" +
      "       WHERE user_id = :userId AND (cart_event_type = 'CLEAR_CART' OR cart_event_type = 'CHECKOUT')\n" +
      "       ORDER BY cart_event.created_at DESC\n" +
      "       LIMIT 1\n" +
      "     ) t\n" +
      "RIGHT JOIN cart_event c ON c.user_id = t.user_id\n" +
      "WHERE c.created_at BETWEEN coalesce(t.created_at, 0) AND 9223372036854775807 AND coalesce(t.id, -1) != c.id\n" +
      "ORDER BY c.created_at ASC", nativeQuery = true)
  List<CartEvent> getCartEventStreamByUserId(@Param("userId") Long userId);
}
