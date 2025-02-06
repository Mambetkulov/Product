package peaksoft_16.dao.imp;

import peaksoft_16.config.JDBCconfig;
import peaksoft_16.config.Models.Product;
import peaksoft_16.dao.ProductDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class productDaoImp implements ProductDao {
    private final Connection con = JDBCconfig.getConnection();




    @Override
    public boolean addProduct(Product product) {
        String sql = """
                insert into products (name, rating,price)
                 values (?,?,?);
                """;
        try{
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setInt(2, product.getRating());
            preparedStatement.setBigDecimal(3, product.getPrice());
            boolean result  = preparedStatement.execute();
            preparedStatement.close();
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        String sql = """
                select * from products where id = ?;
                """;
        try{
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Product product = new Product();
            if(resultSet.next()){
                product.setId(resultSet.getLong("id"));
                product.setName(resultSet.getString("name"));
                product.setRating(resultSet.getInt("rating"));
                product.setPrice(resultSet.getBigDecimal("price"));

            }
            preparedStatement.close();
            return Optional.of(product);

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateProduct(Product product, Long id) {
        String sql = "update products set name = ?, rating = ? , price = ? where id = ?";
        try{
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setInt(2, product.getRating());
            preparedStatement.setBigDecimal(3, product.getPrice());
            preparedStatement.setLong(4, id);
            boolean execute = preparedStatement.execute();
            preparedStatement.close();
            return execute;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean deleteProduct(Long id) {
       String sql = "delete from products where id = ?;";
       try{
           PreparedStatement preparedStatement = con.prepareStatement(sql);
           preparedStatement.setLong(1,id);
           boolean execute = preparedStatement.execute();
           preparedStatement.close();
           return execute;
       }catch (SQLException e){
           throw new RuntimeException(e);
       }
    }

    @Override
    public Optional<Product> getLowRatingProduct() {
        Product product = new Product();
        String sql = "SELECT * FROM products WHERE rating = (SELECT min(rating) FROM products);";
        try{
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                product.setId(resultSet.getLong("id"));
                product.setName(resultSet.getString("name"));
                product.setRating(resultSet.getInt("rating"));
                product.setPrice(resultSet.getBigDecimal("price"));

            }
         preparedStatement.close();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return Optional.of(product);
    }

    @Override
    public List<Product> getProductsWithPriceRange() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products WHERE rating = (SELECT min(rating) FROM products);";
        String sql2 = "SELECT * FROM products WHERE rating = (SELECT max(rating) FROM products);";
        try{
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            PreparedStatement preparedStatement2 = con.prepareStatement(sql2);
            ResultSet resultSet = preparedStatement.executeQuery();
            ResultSet resultSet2 = preparedStatement2.executeQuery();
            Product product = new Product();
            Product product2 = new Product();
            if(resultSet.next()||resultSet2.next()){
                product.setId(resultSet.getLong("id"));
                product.setName(resultSet.getString("name"));
                product.setRating(resultSet.getInt("rating"));
                product.setPrice(resultSet.getBigDecimal("price"));
                products.add(product);


            }
            if(resultSet2.next()){
                product2.setId(resultSet2.getLong("id"));
                product2.setName(resultSet2.getString("name"));
                product2.setRating(resultSet2.getInt("rating"));
                product2.setPrice(resultSet2.getBigDecimal("price"));
                products.add(product2);
            }
            preparedStatement.close();
            preparedStatement2.close();
            return products;

        }catch (SQLException e){
            throw new RuntimeException(e);
        }

    }
}
