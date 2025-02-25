package Employees;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {

	public void create(Employee employee) throws ClassNotFoundException {

		String sqlString = "insert into employee_jsf(name, address, email, phone) values(?, ?, ?, ?)";

		try (Connection connection = DatabaseComfig.getConnection();
				PreparedStatement stmPreparedStatement = connection.prepareStatement(sqlString)) {
			stmPreparedStatement.setString(1, employee.getName());
			stmPreparedStatement.setString(2, employee.getAddress());
			stmPreparedStatement.setString(3, employee.getEmail());
			stmPreparedStatement.setString(4, employee.getPhone());

			stmPreparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Employee> readAll() throws ClassNotFoundException {

		List<Employee> employees = new ArrayList<>();
		String sqlString = "SELECT * FROM employee_jsf";

		try (Connection connection = DatabaseComfig.getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(sqlString)) {

			while (resultSet.next()) {
				Long idLong = resultSet.getLong("id");
				String nameString = resultSet.getString("name");
				String addressString = resultSet.getString("address");
				String phoneString = resultSet.getString("phone");
				String emailString = resultSet.getString("email");

				Employee employee = new Employee(idLong, nameString, addressString, phoneString, emailString);
				employees.add(employee);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employees;

	}

	public void update(Employee employee) throws ClassNotFoundException {
		String sqlString = "insert into employee_jsf(id, name, adress, phone, email,) values(?, ?, ?, ?, ?)";

		try (Connection connection = DatabaseComfig.getConnection();
				PreparedStatement PreparedStatement = connection.prepareStatement(sqlString)) {
			PreparedStatement.setLong(1, employee.getId());
			PreparedStatement.setString(2, employee.getName());
			PreparedStatement.setString(2, employee.getAddress());
			PreparedStatement.setString(2, employee.getPhone());
			PreparedStatement.setString(3, employee.getEmail());

			PreparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(int id) throws ClassNotFoundException {
		String sqlString = "delete from employee_jsf where id=?";

		try (Connection connection = DatabaseComfig.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sqlString)) {

			preparedStatement.setLong(0, id);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
