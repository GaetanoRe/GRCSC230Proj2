package basketballApp;
/**
 * <p>Title: BinaryTree class</p>
 * <p>Description: This is a generic binary tree class that contains nodes that have an integer key and a generic piece of data</p>
 * @author Gaetano Re 
 * N#: N00918949
 */
import java.time.*;

public class BasketballApplicant implements Comparable<BasketballApplicant>{
	private String firstName;
	private String lastName;
	private LocalDate birthDate;
	private LocalDate applicationDate;
	private long studentID;
	
	public BasketballApplicant(String firstName, String lastName, int birthYear, int birthMonth, int birthDay, 
			int appYear, int appMonth, int appDay) {
		this.birthDate = LocalDate.of(birthYear, birthMonth, birthDay);
		this.applicationDate = LocalDate.of(appYear, appMonth, appDay);
		this.firstName = firstName;
		this.lastName = lastName;
		this.studentID = 0;
	}
	
	public int getAge() {
		LocalDate timeNow = LocalDate.of(2025, 12, 1);
		int age = timeNow.compareTo(birthDate);
		if(timeNow.getMonthValue() < birthDate.getMonthValue()) {
			--age;
		}
		else if(timeNow.getMonthValue() == birthDate.getMonthValue() && timeNow.getDayOfMonth() < birthDate.getDayOfMonth()) {
			--age;
		}
		return age;
	}
	
	public int applicationToDueDate() {
		return (int) applicationDate.datesUntil(LocalDate.of(2025, 12, 1)).count();
	}
	
	public String getFirstName() {
		return this.firstName;
	}
	
	public String getLastName() {
		return this.lastName;
	}
	
	public String toString() {
		StringBuilder strbld = new StringBuilder();
		strbld.append(this.firstName + " " + this.lastName + "(" + this.getAge() + ")");
		return strbld.toString();
	}

	
	public LocalDate getApplicationDate() {
		return this.applicationDate;
	}

	@Override
	public int compareTo(BasketballApplicant o) {
		if(o.applicationToDueDate() == this.applicationToDueDate()) {
			return (o.getFirstName().hashCode() + o.getLastName().hashCode()) +
					(this.firstName.hashCode() + this.lastName.hashCode());
		}
		
		return o.applicationToDueDate() - this.applicationToDueDate();
	}
}
