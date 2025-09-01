package dev.markgm.microservices.product.repository;

import dev.markgm.microservices.product.domain.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
