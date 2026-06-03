package vn.devpro.java94.model;

public class Author {
	private int id;
	private String code;
	private String firstName;
	private String lastName;

	public Author() {
	}

	public Author(int id, String code, String firstName, String lastName) {
		this.id = id;
		this.code = code;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

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

	public String getFullName() {
		return firstName + " " + lastName;
	}

	@Override
	public String toString() {
		return "Author [id=" + id + ", code=" + code + ", fullName=" + getFullName() + "]";
	}
}