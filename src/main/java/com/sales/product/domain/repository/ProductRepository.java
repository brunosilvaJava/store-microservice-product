package com.sales.product.domain.repository;

import java.util.Optional;

import com.sales.product.domain.model.ProductEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<ProductEntity, Long> {

    Optional<ProductEntity> findByCode(Long code);

    Optional<ProductEntity> findTopByOrderByCodeDesc();

}
