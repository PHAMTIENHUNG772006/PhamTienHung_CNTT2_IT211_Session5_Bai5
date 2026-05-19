package com.re.session5_bai5.repository;

import com.re.session5_bai5.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product ,Long> {
}
