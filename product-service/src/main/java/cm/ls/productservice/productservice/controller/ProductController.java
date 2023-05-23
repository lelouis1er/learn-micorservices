package cm.ls.productservice.productservice.controller;

import cm.ls.productservice.productservice.dto.ProductRequest;
import cm.ls.productservice.productservice.dto.ProductResponse;
import cm.ls.productservice.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produits")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductRequest productRequest) {
        productService.createProduct(productRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public List<ProductResponse> getAllProduct() {
        return productService.getAllProduct();
    }

    @GetMapping("/$id_produit")
    @ResponseStatus(HttpStatus.FOUND)
    public ProductResponse getProduct(Integer id){
        return productService.getProduct(id);
    }

}
