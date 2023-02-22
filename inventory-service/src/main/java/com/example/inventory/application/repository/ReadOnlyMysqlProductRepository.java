package com.example.inventory.application.repository;

import com.example.inventory.application.vm.ProductVm;
import java.lang.annotation.Native;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.NativeQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReadOnlyMysqlProductRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    public ProductVm find(String code) {
        Query query = this.entityManager.createQuery(
            "SELECT " +
                "   NEW com.example.inventory.application.vm.ProductVm( " +
                "       p.code," +
                "       p.name" +
                "   ) " +
                " FROM product p " +
                " WHERE p.code = :code ", ProductVm.class);
        query.setParameter("code", code);

        return (ProductVm) query.getSingleResult();
    }

}
