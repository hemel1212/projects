package firstProject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

	public void create(Student student) throws ClassNotFoundException {

		String sqlString = "insert into student_jsf(name, email, course) values(?, ?, ?)";

		try (Connection connection = DatabaseComfig.getConnection();
				PreparedStatement stmPreparedStatement = connection.prepareStatement(sqlString)) {
			stmPreparedStatement.setString(1, student.getName());
			stmPreparedStatement.setString(2, student.getEmail());
			stmPreparedStatement.setString(3, student.getCourse());
			stmPreparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Student> readAll() throws ClassNotFoundException {

		List<Student> students = new ArrayList<>();
		String sqlString = "SELECT * FROM student_jsf";

		try (Connection connection = DatabaseComfig.getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(sqlString)) {

			while (resultSet.next()) {
				Long idLong = resultSet.getLong("id");
				String nameString = resultSet.getString("name");
				String emailString = resultSet.getString("email");
				String courseString = resultSet.getString("course");

				Student student = new Student(idLong, nameString, emailString, courseString);
				students.add(student);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return students;

	}

	public void update(Student student) throws ClassNotFoundException {
		String sqlString = "insert into student_jsf(id, name, email, course) values(?, ?, ?, ?)";

		try (Connection connection = DatabaseComfig.getConnection();
				PreparedStatement PreparedStatement = connection.prepareStatement(sqlString)) {
			PreparedStatement.setLong(1, student.getId());
			PreparedStatement.setString(2, student.getName());
			PreparedStatement.setString(3, student.getEmail());
			PreparedStatement.setString(4, student.getCourse());

			PreparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(int id) throws ClassNotFoundException {
		String sqlString = "delete from student_jsf where id=?";

		try (Connection connection = DatabaseComfig.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sqlString)) {

			preparedStatement.setLong(0, id);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
