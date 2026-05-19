package com.re.session5_bai5.controller;

import com.re.session5_bai5.model.dto.ApiDataResponse;
import com.re.session5_bai5.model.entity.Product;
import com.re.session5_bai5.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public List<Product> getAll(){
        return productService.getAll();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id){
        return productService.getById(id);
    }

    @PostMapping
    public Product saveProduct(@RequestBody Product product){
        return productService.save(product);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ApiDataResponse<Product>> updateProduct(
            @PathVariable Long id,
            @RequestBody Product product) {

        if (product.getName() == null || product.getPrice() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiDataResponse<>(false, null,
                            "Thiếu name hoặc price", HttpStatus.BAD_REQUEST));
        }

        product.setId(id);

        Product updated = productService.update(product);
        if (updated == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiDataResponse<>(false, null,
                            "Không tìm thấy sản phẩm với id " + id, HttpStatus.NOT_FOUND));
        }

        return ResponseEntity.ok(new ApiDataResponse<>(true, updated,
                "Cập nhật thành công", HttpStatus.OK));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ApiDataResponse<Void>> deleteProduct(@PathVariable Long id) {
        boolean deleted = productService.delete(id);
        if (!deleted) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiDataResponse<>(false, null,
                            "Không tìm thấy sản phẩm với id " + id, HttpStatus.NOT_FOUND));
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(new ApiDataResponse<>(true, null,
                        "Xóa thành công", HttpStatus.NO_CONTENT));
    }



    @PatchMapping("/{id}")
    public ResponseEntity<ApiDataResponse<Product>> patchProduct(
            @PathVariable Long id,
            @RequestBody Product product) {
        Product updated = productService.updatePrice(id, product.getPrice());
        if (updated == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiDataResponse<>(false, null,
                            "Không tìm thấy sản phẩm với id " + id, HttpStatus.NOT_FOUND));
        }

        return ResponseEntity.ok(new ApiDataResponse<>(true, updated,
                "Cập nhật một phần thành công", HttpStatus.OK));
    }

}
