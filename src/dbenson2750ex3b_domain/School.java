package dbenson2750ex3b_domain;
import java.util.ArrayList;

import dbenson2750ex3b_dataAccess.SchoolReader;

public class School {
	private String name;
	public ArrayList<Department> departments = new ArrayList<Department>();
	public ArrayList<Campus> campuses = new ArrayList<Campus>();
	public ArrayList<Term> terms = new ArrayList<Term>();
	public ArrayList<Person> persons = new ArrayList<Person>();
	private SchoolReader schoolReader = null;
	
	public School(String name) {
		this.name = name;
	}
	
	public void initialize(){
		SchoolReader.createSchoolReader();
		this.schoolReader = SchoolReader.getSchoolReader();
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Person> getPersons() {
		return persons;
	}

	public void setPersons(ArrayList<Person> persons) {
		this.persons = persons;
	}

	public void setDepartments(ArrayList<Department> departments) {
		this.departments = departments;
	}

	public void setCampuses(ArrayList<Campus> campuses) {
		this.campuses = campuses;
	}

	public void setTerms(ArrayList<Term> terms) {
		this.terms = terms;
	}

	

	public void addCampus(Campus campus) {
		throw new UnsupportedOperationException();
	}

	public void addDepartment(Department dept) {
		throw new UnsupportedOperationException();
	}

	public Department newDepartment(String id, String name) {
		Department dept = new Department(id, name);
		this.departments.add(dept);
		return dept;
	}

	public void addStudent(Student student) {
		throw new UnsupportedOperationException();
	}

	public Student newStudent(int id, Person person) {
		Student student = new Student(id, person);
		this.persons.add(person);
		return student;
	}

	public void addTerm(Term term) {
		throw new UnsupportedOperationException();
	}

	public Term newTerm(int yearTerm, String termName) {
		Term term = new Term(yearTerm, termName);
		this.terms.add(term);
		return term;
		
	}

	public Campus getCampus(int id) {
		throw new UnsupportedOperationException();
	}

	public ArrayList<Campus> getCampuses() {
		throw new UnsupportedOperationException();
	}

	public Department getDepartment(String id) {
		Department dept = null;
		for(Department d : departments){
			if(d.getId().equals(id)){
				dept = d;
				break;
			}
		}
		return dept;
	}

	public ArrayList<Department> getDepartments() {
		if (this.departments.isEmpty()){
			departments = schoolReader.getDepartments();
		}
		return this.departments;
	}

	public Term getTerm(int yearTerm) {
		Term term = null;
		for (Term t : terms){
			if(t.getYearTerm() == yearTerm){
				term = t;
				break;
			}
		}
		return term;
	}

	public ArrayList<Term> getTerms() {
		if (this.terms.isEmpty()){
			terms = schoolReader.getTerms();
		}
		return this.terms;
	}

	public Student getStudent(int id) {
		Student student = null;
		for(Person p : persons){
			if(p.getStudent().getId() == id){
				student = p.getStudent();
				break;
			}
		}
		return student;
	}

	public ArrayList<Student> getStudents() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("School [name=");
		builder.append(name);
		builder.append("]");
		return builder.toString();
	}
	
	
}