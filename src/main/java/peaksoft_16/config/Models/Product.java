package peaksoft_16.config.Models;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@ToString


public class Product {
   private Long id;
   private String name;
   private int rating;
   private BigDecimal price;

   public Product(String name,int rating, BigDecimal price) {
      this.name = name;
      this.rating = rating;
      this.price = price;
   }

}
