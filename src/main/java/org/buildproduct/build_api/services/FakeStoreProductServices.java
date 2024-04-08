package org.buildproduct.build_api.services;

import org.buildproduct.build_api.dtos.FakeStoreProductDto;
import org.buildproduct.build_api.models.Category;
import org.buildproduct.build_api.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductServices implements ProductServices{


    RestTemplate restTemplate = new RestTemplate();



    private Product convertProducttoFakeStore(FakeStoreProductDto fakeStoreProductDto){
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setImage(fakeStoreProductDto.getImage());
        Category category = new Category();
        category.setId(fakeStoreProductDto.getId());
        category.setCategory(fakeStoreProductDto.getCategory());
        product.setCategory(category);
        return product;
    }
    private FakeStoreProductDto convertFakeStoretoProduct(Product product){
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setId(product.getId());
        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setCategory(product.getCategory().getCategory());

        return fakeStoreProductDto;


    }

    @Override
    public Product getSingleProduct(Long id) {
        FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject(
                "https://fakestoreapi.com/products/"+id,
                FakeStoreProductDto.class
        );
        if(fakeStoreProductDto==null)
            return null;

        return convertProducttoFakeStore(fakeStoreProductDto);
    }

    @Override
    public List<Product> getAllProducts() {

        FakeStoreProductDto[] allProducts = restTemplate.getForObject(
                "https://fakestoreapi.com/products",
                FakeStoreProductDto[].class
        );
        List<Product> list = new ArrayList<>();

        assert allProducts != null;
        for(FakeStoreProductDto ele: allProducts){
            list.add(convertProducttoFakeStore(ele));

        }
        return list;


    }

    @Override
    public Product updateProduct(Product product) {
        FakeStoreProductDto newProduct  = convertFakeStoretoProduct(product);

        restTemplate.put(
            "https://fakestoreapi.com/products/"+ product.getId(),
                newProduct
        );
        
        
        return product;




    }
    

    @Override
    public Product createProduct( Product product) {
        FakeStoreProductDto productDto = convertFakeStoretoProduct(product);
        
        restTemplate.postForObject(
                "https://fakestoreapi.com/products",
                product,
                FakeStoreProductDto.class
        );
        
        return product;
    }

    @Override
    public Product deleteProduct(Long id) {
        Product product = getSingleProduct(id);
        restTemplate.delete(
                "https://fakestoreapi.com/products/"+id
        );
        return product;
    }
}
