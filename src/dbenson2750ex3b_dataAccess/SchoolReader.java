package dbenson2750ex3b_dataAccess;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import org.xml.sax.*;
import org.xml.sax.helpers.*;
import java.io.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import dbenson2750ex3b_domain.*;

/**
 *
 * @author mswanson
 */
public class SchoolReader extends DefaultHandler {
    // =========================== Static members ===========================
    private static SchoolReader reader = null;

    public static void createSchoolReader() {
        if (reader == null) {
            reader = new SchoolReader();
        }
    }

    public static SchoolReader getSchoolReader() {
        return SchoolReader.reader;
    }

    // =============================== fields ===============================
    School school = new School("");
    private final String XMLFILENAME = "2750Ex3bSchool.xml";

    // Term variables
    private int yearTerm = -1;
    private String termName = "";

    // Student variables
    private String lastName = "";
    private String firstName = "";
    private int studentID = -1;

    // Department variables
    private String deptID = "";
    private String deptName = "";

    // Course variables
    private String courseDept = "";
    private String courseNumber = "";
    private String courseName = "";
    private int credits = -1;

    // Section variables
    private String sectionDept = "";
    private String sectionCrsNum = "";
    private int sectionNum = -1;
    private int capacity = -1;
    private int sectionTerm = -1;

    // Assignment variables
    private String assignDept = "";
    private String assignCrsNum = "";
    private int assignTerm = -1;
    private int assignSectionNum = -1;
    private int unit = -1;
    private char assignNum = ' ';
    private String description = "";
    private int dueDateYear = -1;
    private int dueDateMonth = -1;
    private int dueDateDay = -1;
    private int maxPoints = -1;
//    private char type = ' ';

    // CourseGrade variables
    private String crsGradeDept = "";
    private String crsGradeCrsNum = "";
    private int crsGradeTerm = -1;
    private int crsGradeSecNum = -1;
    private int crsGradeStudId = -1;
    private char crsGradeGrade = ' ';

    // Buffer for collecting data from
    // the "characters" SAX event.
    private CharArrayWriter contents = new CharArrayWriter();

    // Reset variables

    public SchoolReader() {     // ------ Constructor -----
        try {
             // Create SAX 2 parser...
             XMLReader xr = XMLReaderFactory.createXMLReader();

             // Set the ContentHandler...
             xr.setContentHandler( this );

             // Parse the file...
             xr.parse( new InputSource(
                new FileReader(XMLFILENAME)) );
        }catch ( Exception e ) {
             e.printStackTrace();
        }
    }

    public void resetTermVariables() {
        yearTerm = -1;
        termName = "";
    }

    public void resetStudentVariables() {
        lastName = "";
        firstName = "";
        studentID = -1;
    }

    public void resetDepartmentVariables() {
        deptID = "";
        deptName = "";
    }

    public void resetCourseVariables() {
        courseDept = "";
        courseNumber = "";
        courseName = "";
        credits = -1;
    }

    public void resetSectionVariables() {
        sectionDept = "";
        sectionCrsNum = "";
        sectionNum = -1;
        capacity = -1;
        sectionTerm = -1;
    }

    public void resetAssignmentVariables() {
        assignDept = "";
        assignCrsNum = "";
        assignTerm = -1;
        assignSectionNum = -1;
        unit = -1;
        assignNum = ' ';
        description = "";
        dueDateYear = -1;
        dueDateMonth = -1;
        dueDateDay = -1;
        maxPoints = -1;
    }

    public void resetCourseGradeVariables() {
        crsGradeDept = "";
        crsGradeCrsNum = "";
        crsGradeTerm = -1;
        crsGradeSecNum = -1;
        crsGradeStudId = -1;
        crsGradeGrade = ' ';
    }

    // Override methods of the DefaultHandler class
    // to gain notification of SAX Events.
    //
    // See org.xml.sax.ContentHandler for all available events.
    //
    public @Override void startElement( String namespaceURI, String localName,
              String qName, Attributes attr ) throws SAXException {
      contents.reset();
   }

   public @Override void endElement( String namespaceURI,
            String localName, String qName ) throws SAXException {
      if ( localName.equals( "yearTerm" ) ) {
         yearTerm = Integer.valueOf(contents.toString().trim()).intValue();
      }
      else if ( localName.equals( "termName" ) ) {
         termName = contents.toString();
      }
      else if ( localName.equals( "term" ) ) {
          school.newTerm(yearTerm, termName);
          resetTermVariables();
      }
      else if ( localName.equals( "lastName" ) ) {
          lastName = contents.toString();
      }
      else if ( localName.equals( "firstName" ) ) {
          firstName = contents.toString();
      }
      else if ( localName.equals( "studentID" ) ) {
         studentID = Integer.valueOf(contents.toString().trim()).intValue();
      }
      else if ( localName.equals( "student" ) ) {
          school.newStudent(studentID, new Person(firstName, lastName));
          resetStudentVariables();
      }
      else if ( localName.equals( "deptID" ) ) {
          deptID = contents.toString();
      }
      else if ( localName.equals( "deptName" ) ) {
          deptName = contents.toString();
      }
      else if ( localName.equals( "department" ) ) {
//          school.addDepartment(new Department(deptID, deptName));
          school.newDepartment(deptID, deptName);
          resetDepartmentVariables();
      }
      else if ( localName.equals( "courseDept" ) ) {
          courseDept = contents.toString();
      }
      else if ( localName.equals( "courseNumber" ) ) {
         courseNumber = contents.toString();
      }
      else if ( localName.equals( "courseName" ) ) {
          courseName = contents.toString();
      }
      else if ( localName.equals( "credits" ) ) {
         credits = Integer.valueOf(contents.toString().trim()).intValue();
      }
      else if ( localName.equals( "course" ) ) {
          Department d = school.getDepartment(courseDept);
          d.newCourse(courseNumber, courseName, credits);
          resetCourseVariables();
      }
      else if ( localName.equals( "sectionDept" ) ) {
          sectionDept = contents.toString();
      }
      else if ( localName.equals( "sectionCrsNum" ) ) {
          sectionCrsNum = contents.toString();
      }
      else if ( localName.equals( "sectionNum" ) ) {
         sectionNum = Integer.valueOf(contents.toString().trim()).intValue();
      }
      else if ( localName.equals( "capacity" ) ) {
         capacity = Integer.valueOf(contents.toString().trim()).intValue();
      }
      else if ( localName.equals( "sectionTerm" ) ) {
         sectionTerm = Integer.valueOf(contents.toString().trim()).intValue();
      }
      else if ( localName.equals( "section" ) ) {
          Department d = this.school.getDepartment(sectionDept);
          Course c = d.getCourse(sectionCrsNum);
          Term t = this.school.getTerm(sectionTerm);
          Section newSec = c.newSection(t, sectionNum, capacity);
          // ---------- Add dummy Assignment and Student for sections with no matches
          if (!(sectionNum == 107 || sectionNum == 108)) {
              newSec.newAssignment(1, 'Z', "Sample assignment", new GregorianCalendar(), 0);
              newSec.newCourseGrade(this.school.getStudent(999999), 'Z');
          }
          resetSectionVariables();
      }
      else if ( localName.equals( "assignDept" ) ) {
          assignDept = contents.toString();
      }
      else if ( localName.equals( "assignCrsNum" ) ) {
          assignCrsNum = contents.toString();
      }
      else if ( localName.equals( "assignTerm" ) ) {
         assignTerm = Integer.valueOf(contents.toString().trim()).intValue();
      }
      else if ( localName.equals( "assignSectionNum" ) ) {
         assignSectionNum = Integer.valueOf(contents.toString().trim()).intValue();
      }
      else if ( localName.equals( "unit" ) ) {
         unit = Integer.valueOf(contents.toString().trim()).intValue();
      }
      else if ( localName.equals( "assignmentNum" ) ) {
          assignNum = contents.toString().trim().charAt(0);
      }
      else if ( localName.equals( "description" ) ) {
          description = contents.toString();
      }
      else if ( localName.equals( "dueDateYear" ) ) {
         dueDateYear = Integer.valueOf(contents.toString().trim()).intValue();
      }
      else if ( localName.equals( "dueDateMonth" ) ) {
         dueDateMonth = Integer.valueOf(contents.toString().trim()).intValue();
      }
      else if ( localName.equals( "dueDateDay" ) ) {
         dueDateDay = Integer.valueOf(contents.toString().trim()).intValue();
      }
      else if ( localName.equals( "maxPoints" ) ) {
         maxPoints = Integer.valueOf(contents.toString().trim()).intValue();
      }
      else if ( localName.equals( "assignment" ) ) {
          Department d = this.school.getDepartment(assignDept);
          Course c = d.getCourse(assignCrsNum);
          Term t = this.school.getTerm(assignTerm);
          Section s = c.getSection(assignSectionNum, t);
          s.newAssignment(unit, assignNum, description,
                new GregorianCalendar(dueDateYear, dueDateMonth, dueDateDay, 0, 0),
                maxPoints);
          resetAssignmentVariables();
      }
      else if ( localName.equals( "crsGradeDept" ) ) {
          crsGradeDept = contents.toString();
      }
      else if ( localName.equals( "crsGradeCrsNum" ) ) {
          crsGradeCrsNum = contents.toString();
      }
      else if ( localName.equals( "crsGradeTerm" ) ) {
         crsGradeTerm = Integer.valueOf(contents.toString().trim()).intValue();
      }
      else if ( localName.equals( "crsGradeSecNum" ) ) {
         crsGradeSecNum = Integer.valueOf(contents.toString().trim()).intValue();
      }
      else if ( localName.equals( "crsGradeStudId" ) ) {
         crsGradeStudId = Integer.valueOf(contents.toString().trim()).intValue();
      }
      else if ( localName.equals( "crsGradeGrade" ) ) {
         crsGradeGrade = contents.toString().trim().charAt(0);
      }
      else if ( localName.equals( "courseGrade" ) ) {
          Department d = this.school.getDepartment(crsGradeDept);
          Course c = d.getCourse(crsGradeCrsNum);
          Term t = this.school.getTerm(crsGradeTerm);
          Section sec = c.getSection(crsGradeSecNum, t);
          Student st = this.school.getStudent(crsGradeStudId);
          sec.newCourseGrade(st, crsGradeGrade);
          resetCourseGradeVariables();
      }
   }

   public @Override void characters( char[] ch, int start, int length )
                throws SAXException {
         contents.write( ch, start, length );
   }

    public ArrayList<Term> getTerms() {
        ArrayList<Term> origTerms = this.school.getTerms();
        ArrayList<Term> copyTerms = new ArrayList<Term>();
        for (Term t : origTerms) {
//            copyTerms.add(new Term(t.getYearTerm(), t.getName()));
            copyTerms.add(new Term(t.getYearTerm(), t.getTermName()));
        }
        return copyTerms;
    }

    public ArrayList<Student> getStudents() {
        ArrayList<Student> origStudents = this.school.getStudents();
        ArrayList<Student> copyStudents = new ArrayList<Student>();
        for (Student s : origStudents) {
            copyStudents.add(new Student(s.getId(), s.getPerson()));
        }
        return copyStudents;
    }

    public ArrayList<Department> getDepartments() {
        ArrayList<Department> origDepts = this.school.getDepartments();
        ArrayList<Department> copyDepts = new ArrayList<Department>();
        for (Department d : origDepts) {
            copyDepts.add(new Department(d.getId(), d.getName()));
        }
        return copyDepts;
    }

    public ArrayList<Course> getCourses(Department department) {
        Department origDept = this.school.getDepartment(department.getId());
        ArrayList<Course> origCourses = origDept.getCourses();
        ArrayList<Course> copyCourses = new ArrayList<Course>();
        for (Course c : origCourses) {
            copyCourses.add(new Course(department, c.getCourseNumber(),
                    c.getCourseName(), c.getCredits()));
        }
        return copyCourses;
    }

    public ArrayList<Section> getSections(Course course, Term term) {
        Department origDept = this.school.getDepartment(course.getDepartment().getId());
        Course origCourse = origDept.getCourse(course.getCourseNumber());
//        ArrayList<Section> origSections = origCourse.getSections(term);    // Bug fix: no term
        ArrayList<Section> origSections = origCourse.getSections();
        ArrayList<Section> copySections = new ArrayList<Section>();
        for (Section s : origSections) {
            if (s.term.getYearTerm() == term.getYearTerm()) {
                copySections.add(new Section(course, term,
                        s.getSectionNum(), s.getCapacity()));
            }
        }
        return copySections;
    }

    public ArrayList<Assignment> getAssignments(Section section) {
        Department origDept = this.school.getDepartment(
                section.getCourse().getDepartment().getId());
        Course origCourse = origDept.getCourse(
                section.getCourse().getCourseNumber());
        Section origSection = origCourse.getSection(
                section.getSectionNum(), section.getTerm());
        ArrayList<Assignment> origAssign = origSection.getAssignments();
        ArrayList<Assignment> copyAssign = new ArrayList<Assignment>();
        for (Assignment a : origAssign) {
            copyAssign.add(new Assignment(section, a.getUnit(),
                    a.getAssignment(), a.getDescription(), a.getDueDate(),
                    a.getMaxPoints()));
        }
        return copyAssign;
    }

    public ArrayList<CourseGrade> getCourseGrades(Section section) {
        Department origDept = this.school.getDepartment(
                section.getCourse().getDepartment().getId());
        Course origCourse = origDept.getCourse(
                section.getCourse().getCourseNumber());
        Section origSection = origCourse.getSection(
                section.getSectionNum(), section.getTerm());
        ArrayList<CourseGrade> origGrades = origSection.getCourseGrades();
        ArrayList<CourseGrade> copyGrades = new ArrayList<CourseGrade>();
        for (CourseGrade cg : origGrades) {
            copyGrades.add(new CourseGrade(section, cg.getStudent(),
                    cg.getGrade()));
        }
        if (copyGrades.isEmpty()) {
            Student st = this.school.getStudent(999999);
          section.newCourseGrade(st, 'Z');
        }
        return copyGrades;
    }
}
