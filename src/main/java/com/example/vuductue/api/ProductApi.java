package com.example.vuductue.api;

import com.example.vuductue.entity.Product;
import com.example.vuductue.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@RestController
@RequestMapping("/api/v1/products")
public class ProductApi {
    @Autowired
    ProductService productService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Iterable<Product>> getAllProducts(){
        return ResponseEntity.ok(productService.findAll());
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addProduct(@RequestBody Product product){
        return ResponseEntity.ok(productService.save(product));
    }

    @RequestMapping(method = RequestMethod.PUT,path = "{id}")
    public ResponseEntity<?> sellProduct(@PathVariable String id, @RequestBody int quantity){
        Optional<Product> optionalProduct = productService.findById(id);
        if (!optionalProduct.isPresent()){
            ResponseEntity.badRequest().build();
        }
        Product product = optionalProduct.get();
        product.setQuantity(quantity);
        return ResponseEntity.ok(productService.save(product));
    }
}
