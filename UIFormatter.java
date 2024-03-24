public class UIFormatter {
    public static String studentHomePage(String username){
        Student student = (Student)UserList.getInstance().findUser(username);
        String homePage = "\n";
        homePage += student.getFirstName() + " " + student.getLastName() + "\n";
        homePage += "College: " + student.getMajor().getSchool();
        homePage += "Major: " +student.getMajor().getName();
        homePage += "Application Area: " + student.getApplicationArea();
        homePage += "Courses: ";
        Semester earliestSemester = Semester.current();
        Semester latestSemester = Semester.current();
        for(Credit credit : student.getCredits()){
            if(credit.getSemesterTaken().compare(earliestSemester) < 0)
                earliestSemester = credit.getSemesterTaken();
            if(credit.getSemesterTaken().compare(latestSemester) > 0)
                latestSemester = credit.getSemesterTaken();
        }
        
        return "";
    }
    public static String studentUnsatisfiedRequirements(String username){
        Student student = (Student)UserList.getInstance().findUser(username);
        String unsatisfiedRequiremetns = "\n";
        for(Requirement requirement : student.getMajor().getRequirements())
            if(!student.checkRequierment(requirement))
                unsatisfiedRequiremetns += requirement.getCategory().abbreviation + requirement.toString() + "\n";
        return unsatisfiedRequiremetns;
    }
    public static String studentPossibleRequirementCredits(String username, String requirement){
        String courses = "";
        Student student = (Student)UserList.getInstance().findUser(username);
        Requirement majoRequirement = student.getMajor().getRequirement(requirement);
        for(Course course : majoRequirement.getCourses())
            courses += course.getCourseID() + ": " + course.getCourseName() + "\n";
        return courses;
    }
    public static String adivsorHomePage(String username){
        String homePage = "\n";
        Advisor advisor = (Advisor)UserList.getInstance().findUser(username);
        homePage += advisor.getFirstName() + " " +  advisor.getLastName() + "\n";
        for(Student student : advisor.getAdvisees())
            homePage += student.getFirstName() + " " + student.getLastName() + "(" + student.getUscID() + ")\n";
        return homePage;
    }
    public static String advisorStudentPage(String studentUsername){
        String studentHomepage = "\n";
        studentHomepage += "Currently viewing " + studentUsername + "(" + ((Student)UserList.getInstance().findUser(studentUsername)).getUscID() + "):\n";
        studentHomepage += studentHomePage(studentHomepage);
        return studentHomepage;
    }
    public static String advisorNotes(String username){
        String notes = "";
        Student student = (Student)UserList.getInstance().findUser(username);
        for(String note : student.getNotes())
            notes += note + "\n";
        return notes;
    }
}
