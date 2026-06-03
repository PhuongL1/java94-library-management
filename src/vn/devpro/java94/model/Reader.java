package vn.devpro.java94.model;

public class Reader {
	private int id;
	private String code;
	private String firstName;
	private String lastName;
	private String dateOfBirth;
	private String gender;
	private boolean student;

	public Reader() {
	}

	public Reader(int id, String code, String firstName, String lastName, String dateOfBirth, String gender,
			boolean student) {
		this.id = id;
		this.code = code;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.student = student;
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

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public boolean isStudent() {
		return student;
	}

	public void setStudent(boolean student) {
		this.student = student;
	}

	public String getFullName() {
		return firstName + " " + lastName;
	}

	@Override
	public String toString() {
		return "Reader [id=" + id + ", code=" + code + ", fullName=" + getFullName() + ", dateOfBirth=" + dateOfBirth
				+ ", gender=" + gender + ", student=" + student + "]";
	}
}
