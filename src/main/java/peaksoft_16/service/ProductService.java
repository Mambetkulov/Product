package peaksoft_16.service;

import peaksoft_16.config.Models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    String addProduct(Product product);

    Product getProductById(Long id);

    String updateProduct(Product product , Long id);

    String deleteProduct(Long id);

    Product getLowRatingProduct();

    List<Product> getProductsWithPriceRange();
}
