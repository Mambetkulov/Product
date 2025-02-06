package peaksoft_16.dao;

import peaksoft_16.config.Models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductDao {


    boolean addProduct(Product product);

    Optional<Product> getProductById(Long id);

    boolean updateProduct(Product product , Long id);

    boolean deleteProduct(Long id);

    Optional<Product> getLowRatingProduct();

    List<Product> getProductsWithPriceRange();
}
