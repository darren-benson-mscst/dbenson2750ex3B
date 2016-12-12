package dbenson2750ex3b_domain;
import java.util.ArrayList;

import dbenson2750ex3b_dataAccess.SchoolReader;

public class Term {
	private int yearTerm;
	private String termName;
	public ArrayList<Section> sections = new ArrayList<Section>();
	
	public Term(int yearTerm, String termName) {
		this.yearTerm = yearTerm;
		this.termName = termName;
	}

	public int getYearTerm() {
		return this.yearTerm;
	}

	public void setYearTerm(int yearTerm) {
		this.yearTerm = yearTerm;
	}

	public String getTermName() {
		return this.termName;
	}

	public void setTermName(String termName) {
		this.termName = termName;
	}

	public void setSections(ArrayList<Section> sections) {
		this.sections = sections;
	}

	

	public void addSection(Section section) {
		throw new UnsupportedOperationException();
	}

	public Section newSection(int sectionNum, int capacity) {
		throw new UnsupportedOperationException();
	}

	public ArrayList<Section> getSections() {
		return sections;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();		
		builder.append(termName);		
		return builder.toString();
	}
	
	
}