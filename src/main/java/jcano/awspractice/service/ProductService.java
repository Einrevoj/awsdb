package jcano.awspractice.service;

import jcano.awspractice.dto.ProductDTO;
import jcano.awspractice.entity.ProductEntity;
import jcano.awspractice.model.ProductRequest;
import jcano.awspractice.repository.ProductRepository;
import jcano.awspractice.util.DateTimeUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    private final ModelMapper modelMapper;
    private final DateTimeUtil dateTimeUtil;

    public List<ProductDTO> getAllProducts() {

        // Get all data from database
        List<ProductEntity> allProducts = productRepository.findAll(Sort.by(Sort.Direction.ASC, "createdDate"));

        //initialize dto
        List<ProductDTO> allProductsDTO = new ArrayList<>();
        allProducts.forEach(product -> {
            allProductsDTO.add(modelMapper.map(product, ProductDTO.class));
        });
        return allProductsDTO;
    }
    public List<ProductDTO> addProduct(ProductRequest newProduct) {
        ProductEntity product = ProductEntity
                .builder()
                .productId(UUID.randomUUID())
                .productName(newProduct.getProductName())
                .imageLink(null)
                .price(newProduct.getPrice())
                .ratings(newProduct.getRatings())
                .type(newProduct.getType())
                .filter(newProduct.getFilter())
                .description(newProduct.getDescription())
                .createdDate(dateTimeUtil.currentDate())
                .modifiedDate(dateTimeUtil.currentDate())
                .build();

        return getAllProducts();
    }
}
