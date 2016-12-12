package dbenson2750ex3b_ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import dbenson2750ex3b_domain.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class SchoolForm extends JFrame implements ListSelectionListener {

	private JPanel contentPane;
	private ArrayListModel<Term> termJListModel;
	private ArrayListModel<Department> departmentJListModel;
	private ArrayListModel<Course> courseJListModel;
	private ArrayListModel<Section> sectionJListModel;
	private ArrayListModel<Assignment> assignmentJListModel;
	private ArrayListModel<CourseGrade> courseGradeJListModel;
	private JList termJList;
	private JList departmentJList;
	private JList courseJList;
	private JList sectionJList;
	private JList assignmentJList;
	private JList studentJList;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SchoolForm frame = new SchoolForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SchoolForm() {
		// School domain object
		School school = new School("Southeast Tech");
		school.initialize();
		
		
		//Form objects
		setTitle("Southeast Tech: DBenson COMC2750 Ex3b");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 593);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTerm = new JLabel("Term:");
		lblTerm.setBounds(10, 11, 46, 14);
		contentPane.add(lblTerm);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(123, 11, 250, 65);
		contentPane.add(scrollPane);
		
		JLabel lblDepartment = new JLabel("Department:");
		lblDepartment.setBounds(10, 95, 80, 14);
		contentPane.add(lblDepartment);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(122, 94, 253, 72);
		contentPane.add(scrollPane_1);
		
		JLabel lblCourse = new JLabel("Course:");
		lblCourse.setBounds(10, 185, 46, 14);
		contentPane.add(lblCourse);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(117, 185, 261, 70);
		contentPane.add(scrollPane_2);
		
		JLabel lblSection = new JLabel("Section:");
		lblSection.setBounds(10, 267, 46, 14);
		contentPane.add(lblSection);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(115, 267, 264, 70);
		contentPane.add(scrollPane_3);
		
		JLabel lblAssignment = new JLabel("Assignment:");
		lblAssignment.setBounds(10, 361, 80, 14);
		contentPane.add(lblAssignment);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(117, 361, 262, 79);
		contentPane.add(scrollPane_4);
		
		JLabel lblStudent = new JLabel("Student");
		lblStudent.setBounds(10, 458, 46, 14);
		contentPane.add(lblStudent);
		
		JScrollPane scrollPane_5 = new JScrollPane();
		scrollPane_5.setBounds(113, 458, 267, 70);
		contentPane.add(scrollPane_5);
		
		//Load Terms
		ArrayList<Term> termsArrayList = school.getTerms();
		termJListModel = new ArrayListModel<Term>();
		termJListModel.addAll(termsArrayList);
		
		termJList = new JList(termJListModel);
		
		termJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(termJList);
		termJList.setSelectedIndex(1);
								
				
		//Load Departments
		ArrayList<Department> departmentsArrayList = school.getDepartments();
		departmentJListModel = new ArrayListModel<Department>();
		departmentJListModel.addAll(departmentsArrayList);
		
		departmentJList = new JList(departmentJListModel);
		
		departmentJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_1.setViewportView(departmentJList);
		departmentJList.setSelectedIndex(2);
		
				
		//Load Courses
		Department selectedDept = (Department) departmentJList.getSelectedValue();
		ArrayList<Course> coursesArrayList =  selectedDept.getCourses();
		courseJListModel = new ArrayListModel<Course>();
		courseJListModel.addAll(coursesArrayList);
				
		courseJList = new JList(courseJListModel);		
		courseJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_2.setViewportView(courseJList);
		courseJList.setSelectedIndex(0);
		
		//Load Sections
		Term selectedTerm = (Term) termJList.getSelectedValue();
		Course selectedCourse = (Course) courseJList.getSelectedValue();		
		ArrayList<Section> sectionsArrayList =  selectedCourse.getSections(selectedTerm);
		sectionJListModel = new ArrayListModel<Section>();
		sectionJListModel.addAll(sectionsArrayList);
					
		sectionJList = new JList(sectionJListModel);
		
		sectionJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_3.setViewportView(sectionJList);
		sectionJList.setSelectedIndex(1);
		
		//Load Assignments		
		Section selectedSection = (Section) sectionJList.getSelectedValue();
		ArrayList<Assignment> assignmentsArrayList = selectedSection.getAssignments();
		assignmentJListModel = new ArrayListModel<Assignment>();
		assignmentJListModel.addAll(assignmentsArrayList);
		
		assignmentJList = new JList(assignmentJListModel);
		assignmentJList.addListSelectionListener(this);
		assignmentJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_4.setViewportView(assignmentJList);
		
		//Load CourseGrades
		ArrayList<CourseGrade> courseGradeArrayList = selectedSection.getCourseGrades();
		courseGradeJListModel = new ArrayListModel<CourseGrade>();
		courseGradeJListModel.addAll(courseGradeArrayList);
		
		studentJList = new JList(courseGradeJListModel);
		studentJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_5.setViewportView(studentJList);
				
		
		termJList.addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent arg0) {
				displaySections();
				
			}
		});
		
		
		departmentJList.addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent arg0) {
				displayCourses();
				
			}
		});
		
		courseJList.addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent arg0) {
				displaySections();				
			}
		});
		
		
		sectionJList.addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent arg0) {
				displayAssignments();
				displayCourseGrades();
				
			}
		});
		
					
	}
	
					
	protected void displayAssignments(){
		Section selectedSection = (Section) sectionJList.getSelectedValue();
		ArrayList<Assignment> assignmentsArrayList = new ArrayList<Assignment>();
		if (selectedSection != null){
			assignmentsArrayList = selectedSection.getAssignments();
		}		
		assignmentJListModel = new ArrayListModel<Assignment>();
		assignmentJListModel.addAll(assignmentsArrayList);		
		assignmentJList.setModel(assignmentJListModel);
				
	}
	
	protected void displayCourseGrades(){
		Section selectedSection = (Section) sectionJList.getSelectedValue();		
		ArrayList<CourseGrade> courseGradeArrayList = new ArrayList<CourseGrade>();
		if (selectedSection != null){
			courseGradeArrayList = selectedSection.getCourseGrades();
		}		
		courseGradeJListModel = new ArrayListModel<CourseGrade>();
		courseGradeJListModel.addAll(courseGradeArrayList);		
		studentJList.setModel(courseGradeJListModel);
	}
	
	protected void displaySections(){
		if (courseJList.getSelectedIndex() == -1 && courseJList.getModel().getSize() > 0){
			courseJList.setSelectedIndex(0);
		}
		Term selectedTerm = (Term) termJList.getSelectedValue();
		Course selectedCourse = (Course) courseJList.getSelectedValue();		
		ArrayList<Section> sectionsArrayList =  selectedCourse.getSections(selectedTerm);
		sectionJListModel = new ArrayListModel<Section>();
		sectionJListModel.addAll(sectionsArrayList);					
		sectionJList.setModel(sectionJListModel);		
		sectionJList.setSelectedIndex(0);
	}
	
	protected void displayCourses(){
		Department selectedDept = (Department) departmentJList.getSelectedValue();
		ArrayList<Course> coursesArrayList =  selectedDept.getCourses();
		courseJListModel = new ArrayListModel<Course>();
		courseJListModel.addAll(coursesArrayList);				
		courseJList.setModel(courseJListModel);	
		courseJList.setSelectedIndex(0);
	}
	
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (e.getSource() == termJList) {
			do_termJList_valueChanged(e);
		}
		if (e.getSource() == departmentJList) {
			do_departmentJList_valueChanged(e);
		}
		if (e.getSource() == courseJList) {
			do_courseJList_valueChanged(e);
		}
		// TODO Auto-generated method stub
		
	}
	protected void do_courseJList_valueChanged(ListSelectionEvent e) {
	}
	protected void do_departmentJList_valueChanged(ListSelectionEvent e) {
	}
	protected void do_termJList_valueChanged(ListSelectionEvent e) {
	}
}
