package org.buildproduct.build_api.services;

import org.buildproduct.build_api.models.Product;

import java.util.List;

public interface ProductServices {
     Product getSingleProduct(Long id);


     List<Product> getAllProducts();

     Product updateProduct(Product product);

     Product createProduct(Product product);
      Product deleteProduct(Long id);

}
