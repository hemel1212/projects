package Products;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

	public void createProduct(Product product) throws ClassNotFoundException {
		String sql = "insert into ProductJSf(name, price, brand)values( ?, ?, ?)";
		try (Connection connection = DatabaseComfig.getConnection();
				PreparedStatement stm = connection.prepareStatement(sql)) {

			stm.setString(1, product.getName());
			stm.setDouble(2, product.getPrice());
			stm.setString(3, product.getBrand());

			stm.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Product> allProducts() throws ClassNotFoundException {
		String sql = "select * from ProductJSf order by id";
		List<Product> products = new ArrayList<>();

		try (Connection connection = DatabaseComfig.getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(sql)) {

			while (resultSet.next()) {

				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				double price = resultSet.getDouble("price");
				String brand = resultSet.getString("brand");

				Product product = new Product(id, name, price, brand);
				products.add(product);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return products;
	}

	public void updateProduct(Product product) throws ClassNotFoundException {

		String sql = "update ProductJSf set name=?, price=?, brand=? where id=?";
		try (Connection connection = DatabaseComfig.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {

			statement.setString(1, product.getName());
			statement.setDouble(2, product.getPrice());
			statement.setString(3, product.getBrand());
			statement.setInt(4, product.getId());

			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();// TODO: handle exception
		}
	}

	public void deleteProduct(int id) throws ClassNotFoundException {
		String sql = "DELETE FROM ProductJSf WHERE id = ?";

		try (Connection connection = DatabaseComfig.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {

			statement.setInt(1, id);
			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}