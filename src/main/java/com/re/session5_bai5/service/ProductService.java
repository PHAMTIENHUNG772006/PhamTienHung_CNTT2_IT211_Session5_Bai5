package com.re.session5_bai5.service;

import com.re.session5_bai5.model.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAll();
    Product getById(Long id);
    Product save(Product product);
    Product update(Product product);
    boolean delete(Long id);
    Product updatePrice(Long id, double price);
}
