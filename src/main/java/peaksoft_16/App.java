package peaksoft_16;

import peaksoft_16.config.Models.Product;
import peaksoft_16.service.ProductService;
import peaksoft_16.service.imp.ProductServiceImp;

import java.math.BigDecimal;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        ProductService product = new ProductServiceImp();
        Scanner scan1 = new Scanner(System.in);



        while(true) {
            System.out.println("""
                    press 1 to addProduct
                    press 2 to removeProduct
                    press 3 to updateProduct
                    press 4 to getProduct
                    press 5 to get low rating product
                    press 6 to get low price and  high price product
                    """);
            int choice = scan1.nextInt();
            switch (choice) {
                case 1 -> System.out.println(product.addProduct(new Product("bake", 9, BigDecimal.valueOf(500))));

                case 2 -> {
                    String text = product.deleteProduct(3L);
                    System.out.println(text);
                }
                case 3 -> {
                    String text = product.updateProduct(new Product("bike",7,BigDecimal.valueOf(300)),3L);
                    System.out.println(text);
                }
                case 4 -> System.out.println(product.getProductById(3L));

                case 5 -> System.out.println(product.getLowRatingProduct());

                case 6 -> System.out.println(product.getProductsWithPriceRange());

            }
        }
    }
}
