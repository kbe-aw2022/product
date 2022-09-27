package kbe.aw.product.repository;

import org.springframework.data.repository.CrudRepository;

import kbe.aw.product.model.Product;

public interface ProductRepository extends CrudRepository<Product, Integer>
{
}
