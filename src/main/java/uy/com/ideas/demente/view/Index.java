package uy.com.ideas.demente.view;

import java.util.Arrays;
import java.util.List;

import javax.inject.Named;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.google.gson.Gson;

import uy.com.ideas.demente.model.Person;

@Named
public class Index {

	private final String protocolo = "http://";
	private final String host = "localhost";
	private final String port = ":8080";
	private final String api = "/api";
	private final String version = "/v1";
	private final String resource = "/persons";

	private final String urlResourcePersons = protocolo + host + port //
			+ api + version + resource;

	private String firstName = "";
	private String lastName = "";
	private String cellphone = "";
	private String email = "";
	private String streetAddress = "";
	private int age;
	private int idPerson;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getIdPerson() {
		return idPerson;
	}

	public void setIdPerson(int idPerson) {
		this.idPerson = idPerson;
	}

	private String getPerson(int id) {

		ResteasyClient client = new ResteasyClientBuilder().build();
		ResteasyWebTarget target = client //
				.target(urlResourcePersons + "/" + id);

		Response response = target.request().get();
		String value = response.readEntity(String.class);
		Person personDTO = new Gson().fromJson(value, Person.class);
		System.out.println(personDTO.toString());

		response.close();

		setIdPerson(id);
		return personDTO.toString();
	}

	private String getAllPersons() {

		ResteasyClient client = new ResteasyClientBuilder().build();
		ResteasyWebTarget target = client //
				.target(urlResourcePersons);

		Response response = target.request().get();
		String value = response.readEntity(String.class);
//		Person personDTO = new Gson().fromJson(value, Person.class);

		Person[] mcArray = new Gson().fromJson(value, Person[].class);
		List<Person> mcList = Arrays.asList(mcArray);

		StringBuilder sb = new StringBuilder();
		for (Person person : mcList) {
			sb.append(person.toString());
		}

		response.close();

		return sb.toString();
	}

	private String insertPerson() {

		Person person = new Person();

		person.setName(getFirstName());
		person.setLastName(getLastName());
		person.setEmail(getEmail());
		person.setCellPhone(getCellphone());
		person.setStreetAddress(getStreetAddress());

		ResteasyClient client = new ResteasyClientBuilder().build();
		ResteasyWebTarget target = client.target(urlResourcePersons);
//		Response response = target.request().post(Entity.entity(person, MediaType.APPLICATION_JSON));

		// Read output in string format
//		System.out.println(response.getStatus());
//		String value = response.readEntity(String.class);
//		Person personDTO = new Gson().fromJson(value, Person.class);
//		System.out.println(personDTO.toString());
//
//		response.close();

//		return personDTO.toString();
		return person.toString();
	}

	public String showInsertPerson() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		return "Hola " + authentication.getName() + "!, Se inserto la siguiente" //
				+ " personas al sistema\n" + insertPerson();
	}

	public String showAllPersons() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		return "Hola " + authentication.getName() + "!, estas son todas" //
				+ " las personas del sistema\n" + getAllPersons();
	}

	public String showPerson() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		return "Hola " + authentication.getName() + "!, Ultima persona ingresada. " //
				+ "Id: " + getIdPerson() + "\n" + getPerson(getIdPerson());
	}
}
