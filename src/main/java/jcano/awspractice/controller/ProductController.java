package jcano.awspractice.controller;

import jcano.awspractice.dto.ProductDTO;

import jcano.awspractice.model.ProductRequest;
import jcano.awspractice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/getAll")
    public List<ProductDTO> getAllProducts() {
        return productService.getAllProducts();
    }

    @PutMapping("/add")
    public List<ProductDTO> addProduct(@RequestBody ProductRequest productRequest) {
        return productService.addProduct(productRequest);
    }

    @DeleteMapping("/delete/{productId}")
    public List<ProductDTO> deleteProduct(@PathVariable UUID productId) {
        return productService.deleteProduct(productId);
    }

}