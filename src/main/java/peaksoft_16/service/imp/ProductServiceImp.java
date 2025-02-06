package peaksoft_16.service.imp;

import peaksoft_16.config.Models.Product;
import peaksoft_16.dao.ProductDao;
import peaksoft_16.dao.imp.productDaoImp;
import peaksoft_16.service.ProductService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class ProductServiceImp implements ProductService {
    private final ProductDao productDao = new productDaoImp();



    @Override
    public String addProduct(Product product) {
        try{
            boolean result = productDao.addProduct(product);
            if(result){
                return "error";
            }
        }catch(Exception e){
            return e.getMessage();
        }
        return "success";
    }

    @Override
    public Product getProductById(Long id) {
        return productDao.getProductById(id).orElseThrow(() -> new NoSuchElementException("Product not found"));
    }

    @Override
    public String updateProduct(Product product, Long id) {
        try{
           boolean result =  productDao.updateProduct(product, id);
           if(result){
               return "product not found";
           }
        }catch(Exception e){
            return e.getMessage();
        }
        return "success";
    }

    @Override
    public String deleteProduct(Long id) {
        try{
           boolean result = productDao.deleteProduct(id);
           if(result){
               return "product not found";
           }
        }catch(Exception e){
            return e.getMessage();
        }
        return "success";
    }

    @Override
    public Product getLowRatingProduct() {
          return productDao.getLowRatingProduct().orElseThrow(() -> new NoSuchElementException("Product not found"));
    }

    @Override
    public List<Product> getProductsWithPriceRange() {
        try{
            return productDao.getProductsWithPriceRange();
        }catch(Exception e){
            System.out.println(e.getMessage());
            return List.of();
        }
    }
}
