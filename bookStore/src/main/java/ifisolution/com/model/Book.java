package ifisolution.com.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the BOOK database table.
 * 
 */
@Entity
@NamedQuery(name="Book.findAll", query="SELECT b FROM Book b")
public class Book implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idbook;

	private String author;

	private String catergory;

	private String description;

	private String namebook;

	private float price;

	public Book() {
	}

	public int getIdbook() {
		return this.idbook;
	}

	public void setIdbook(int idbook) {
		this.idbook = idbook;
	}

	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getCatergory() {
		return this.catergory;
	}

	public void setCatergory(String catergory) {
		this.catergory = catergory;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNamebook() {
		return this.namebook;
	}

	public void setNamebook(String namebook) {
		this.namebook = namebook;
	}

	public float getPrice() {
		return this.price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

}