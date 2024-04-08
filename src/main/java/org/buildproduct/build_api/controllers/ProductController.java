package org.buildproduct.build_api.controllers;

import org.buildproduct.build_api.dtos.ExceptionDto;
import org.buildproduct.build_api.models.Product;
import org.buildproduct.build_api.services.ProductServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    ProductServices productServices;
    ProductController(ProductServices productServices){
        this.productServices = productServices;
    }
    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return productServices.getAllProducts();


    }
    @GetMapping("/products/{id}")
    public ResponseEntity getSingleProduct(@PathVariable("id") Long id){
        Product product = null;
        ResponseEntity<Product> responseEntity =null;
         try{
            product= productServices.getSingleProduct(id);
            return new ResponseEntity<>(product, HttpStatus.OK);
//            return responseEntity;


         }
         catch(RuntimeException exception){
//             return null;
             ExceptionDto exceptionDto = new ExceptionDto();
             exceptionDto.setMessage("Oops, Some Error Occurred");
             return new ResponseEntity<>(exceptionDto ,HttpStatus.BAD_REQUEST);


        }



    }
    @PutMapping("/product/{id}")
    public Product updateProduct(@RequestBody Product product){
        return productServices.updateProduct(product);

    }
    @PostMapping("/products")
    public Product createProduct(@RequestBody Product product){
        return productServices.createProduct(product);
    }
    @DeleteMapping("/product/{id}")
    public Product deleteProduct(@PathVariable("id") Long id){
        return productServices.deleteProduct(id);

    }







}
