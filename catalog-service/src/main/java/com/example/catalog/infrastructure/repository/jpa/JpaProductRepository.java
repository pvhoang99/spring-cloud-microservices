package com.example.catalog.infrastructure.repository.jpa;

import com.example.catalog.application.query.product.GetProductListQuery.ProductFilter;
import com.example.catalog.application.vm.ProductVm;
import com.example.catalog.domain.product.Product;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    @Query(
        "SELECT " +
            "   NEW com.example.catalog.application.vm.ProductVm( " +
            "       p.id, " +
            "       p.name, " +
            "       p.description, " +
            "       p.image," +
            "       p.categoryId " +
            "   ) " +
            " FROM product p "
    )
    Page<ProductVm> search(ProductFilter filter, Pageable pageable);

}
