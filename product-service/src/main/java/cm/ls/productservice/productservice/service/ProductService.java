package cm.ls.productservice.productservice.service;

import cm.ls.productservice.productservice.dto.ProductRequest;
import cm.ls.productservice.productservice.dto.ProductResponse;
import cm.ls.productservice.productservice.model.Product;
import cm.ls.productservice.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public void createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .nom(productRequest.getNom())
                .description(productRequest.getDescription())
                .prix(productRequest.getPrix())
                .build();

        productRepository.save(product);
        log.info("Le produit a ete cree");
    }

    public List<ProductResponse> getAllProduct() {
        List<Product> temp = productRepository.findAll();
        return temp.stream().map(this::mapProductToProductResponse).collect(Collectors.toList());
    }

    public ProductResponse getProduct(Integer id){
        Product product = productRepository.getById(String.valueOf(id));
        return mapProductToProductResponse(product);
    }

    private ProductResponse mapProductToProductResponse (Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .nom(product.getNom())
                .description(product.getDescription())
                .prix(product.getPrix())
                .build();
    }

}
