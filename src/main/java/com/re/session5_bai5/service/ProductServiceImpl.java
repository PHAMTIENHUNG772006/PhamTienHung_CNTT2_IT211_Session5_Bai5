package com.re.session5_bai5.service;

import com.re.session5_bai5.model.entity.Product;
import com.re.session5_bai5.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;


    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Product getById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product update(Product product) {

        if(productRepository.existsById(product.getId())){
            product.setId(product.getId());
            return productRepository.save(product);
        }
        return null;
    }

    @Override
    public boolean delete(Long id) {
        productRepository.deleteById(id);
        return true;
    }

    @Override
    public Product updatePrice(Long id, double price) {
        Product product = productRepository.findById(id).orElse(null);

        if(product != null){
            product.setPrice(price);
            return productRepository.save(product);
        }
        return null;
    }
}
