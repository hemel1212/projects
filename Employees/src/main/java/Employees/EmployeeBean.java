package Employees;

import java.io.Serializable;
import java.util.List;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

@Named
@SessionScoped
public class EmployeeBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private Employee employee = new Employee();
	private EmployeeDAO employeeDAO = new EmployeeDAO();
	private List<Employee> employeeList;
	private boolean editMode = false;

	public String saveEmployee() {
		try {
			if (editMode) {
				employeeDAO.update(employee);
			} else {
				employeeDAO.create(employee);
			}
			refreshList();
			clearForm();
			return "employee?faces-redirect=true";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void editEmployee(Employee employee) {
		this.employee = employee;
		editMode = true;
	}

	public void deleteEmployee(int id) {
		try {
			employeeDAO.delete(id);
			refreshList();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clearForm() {
		employee = new Employee();
		editMode = false;
	}

	private void refreshList() {
		try {
			employeeList = employeeDAO.readAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Getters and Setters
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public List<Employee> getEmployeeList() {
		if (employeeList == null) {
			refreshList();
		}
		return employeeList;
	}
}
