package Products;

import java.io.Serializable;
import java.util.List;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

@Named
@SessionScoped // Change to SessionScoped to maintain student state across requests
public class ProductBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Product product = new Product();
	private ProductDAO productDAO = new ProductDAO();
	private List<Product> productList;
	private boolean editMode = false;

	public String saveProduct() {
		try {
			if (editMode) {
				// Update existing student record in database
				productDAO.updateProduct(product);
			} else {
				// Create new student record
				productDAO.createProduct(product);
			}

			refreshList(); // Refresh student list after saving
			clearForm(); // Clear form after saving
			return "index?faces-redirect=true"; // Redirect to student page

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void editProduct(Product product) {
		this.product = product; // Assign the selected student to the form
		editMode = true; // Set edit mode to true
	}

	public void deleteProduct(int id) {
		try {
			productDAO.deleteProduct(id);
			refreshList(); // Refresh student list after deletion
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clearForm() {
		product = new Product(); // Clear form fields
		editMode = false; // Reset edit mode
	}

	private void refreshList() {
		try {
			productList = productDAO.allProducts(); // Refresh student list from the database
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Getters and Setters
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public List<Product> getProductList() {
		if (productList == null) {
			refreshList();
		}
		return productList;
	}
}