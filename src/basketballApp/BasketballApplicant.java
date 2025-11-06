package basketballApp;
/**
 * <p>Title: Basketball Applicant class</p>
 * <p>Description: This is a class that will contain the information for each basketball
 * applicant. </p>
 * @author Gaetano Re 
 * N#: N00918949
 */
import java.time.*;

public class BasketballApplicant implements Comparable<BasketballApplicant>{
	private String firstName;
	private String lastName;
	private LocalDate birthDate;
	private LocalDate applicationDate;
	
	public BasketballApplicant(String firstName, String lastName, int birthYear, int birthMonth, int birthDay, 
			int appYear, int appMonth, int appDay) {
		this.birthDate = LocalDate.of(birthYear, birthMonth, birthDay);
		this.applicationDate = LocalDate.of(appYear, appMonth, appDay);
		this.firstName = firstName;
		this.lastName = lastName;
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
	   // First and Foremost, compare the times the applications were submitted
	    int c = this.applicationDate.compareTo(o.applicationDate);
	    if (c != 0) {
	    	return c; // If those are different, sumbit it
	    }

	    // If the first check fails, compare the last names of the applicants
	    c = this.lastName.compareToIgnoreCase(o.lastName);
	    if (c != 0) {
	    	return c;
	    }

	    // If that failed, compare their first names
	    c = this.firstName.compareToIgnoreCase(o.firstName);
	    if (c != 0) {
	    	return c;
	    }

	    // Lastly, compare them by their birth dates
	    return this.birthDate.compareTo(o.birthDate);
	}
}
