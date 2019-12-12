package uy.com.ideas.demente.model;

import java.io.Serializable;

/**
 * @author diego.gonzalezdurand
 */
public class Person implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long idPerson;
	private String name;
	private String lastName;
	private String streetAddress;
	private int age;
	private String cellPhone;
	private String email;
//	private Set<BookDTO> books;

	public Long getIdPerson() {
		return idPerson;
	}

	public void setIdPerson(Long idPerson) {
		this.idPerson = idPerson;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getCellPhone() {
		return cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "PersonDTO [idPerson=" + idPerson + ", name=" + name + ", lastName=" + lastName + ", streetAddress="
				+ streetAddress + ", age=" + age + ", cellPhone=" + cellPhone + ", email=" + email + "]";
	}

//	public Set<BookDTO> getBooks() {
//		return books;
//	}
//
//	public void setBooks(Set<BookDTO> books) {
//		this.books = books;
//	}

}
