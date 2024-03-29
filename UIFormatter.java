import java.util.ArrayList;

public class UIFormatter {
    public static String studentHomePage(String username){
        Student student = (Student)UserList.getInstance().findUser(username);
        String homePage = "\n";
        homePage += student.getFirstName() + " " + student.getLastName() + "\n";
        homePage += "College: " + student.getMajor().getSchool() + "\n";
        homePage += "Major: " +student.getMajor().getName() + "\n";
        homePage += "Application Area: " + student.getApplicationArea() + "\n";
        homePage += "\n***** Courses *****\n\n";
        Semester earliestSemester = Semester.current();
        Semester latestSemester = Semester.current();
        for(Credit credit : student.getCredits()){
            if(credit.getSemesterTaken().compare(earliestSemester) < 0)
                earliestSemester = credit.getSemesterTaken();
            if(credit.getSemesterTaken().compare(latestSemester) > 0)
                latestSemester = credit.getSemesterTaken();
        }
        for(Semester i = earliestSemester.trueSemester(); i.compare(latestSemester.trueSemester()) < 0; i.incrementSemester()){
            homePage += i.getAbbreviation();
            if(i.equals(Semester.current().trueSemester()))
                homePage += " (Current/Upcoming)";
            homePage += "\n";
            homePage += coursesForSemester(student, i) + "\n";
        }
        homePage += "*** Notes ***\n";
        String notes = advisorNotes(username);
        if(notes == "" || notes == null)
            homePage += "*EMPTY*";
        else
            homePage += notes;
        return homePage;
    }
    private static String coursesForSemester(Student student, Semester semester){
        String coursesString = "";
        ArrayList<Credit> credits = student.getCredits(semester);
        for(Credit credit : credits){
            coursesString += "\t" + credit.getCourseID() + ": " + credit.getCourseName() + " - ";
            if(semester.trueSemester().compare(Semester.current().trueSemester()) < 0){
                switch (Status.getStatus(credit)) {
                    case COMPLETE:
                        coursesString += "Passed with a " + credit.getGrade();
                        break;
                    case UNSATISFACTORY:
                        if(credit.getType() == CreditType.WITHDRAWN)
                            coursesString += "Withdrawn";
                        else
                            coursesString += "Failed with a " + credit.getGrade();
                        break;
                    default:
                        coursesString += Status.getStatus(credit);
                }
            }
            else if(semester.trueSemester().compare(Semester.current().trueSemester()) > 0)
                coursesString += "Future Course";
            else
                coursesString += "Current/Upcoming Course";
            coursesString += "\n";
        }
        return coursesString;
    }
    public static String studentUnsatisfiedRequirements(String username){
        Student student = (Student)UserList.getInstance().findUser(username);
        String unsatisfiedRequiremetns = "\n";
        for(Requirement requirement : student.getMajor().getRequirements())
            if(!student.checkRequierment(requirement)){
                int takenHours = 0;
                for(Credit credit : student.getCredits(requirement))
                    takenHours += credit.getCreditHours();
                int remainingHours = requirement.getCreditHoursRequired() - takenHours;
                unsatisfiedRequiremetns += requirement.getCategory().abbreviation + ": " + requirement.getName() + " - " + remainingHours + " Credit Hours Remaining" + "\n";
            }
                
        return unsatisfiedRequiremetns;
    }
    public static String studentPossibleRequirementCredits(String username, String requirement){
        String courses = "";
        Student student = (Student)UserList.getInstance().findUser(username);
        Requirement majoRequirement = student.getMajor().getRequirementFromAbbreviation(requirement);
        for(Course course : majoRequirement.getCourses())
            courses += course.getCourseID() + ": " + course.getCourseName() + "\n";
        return courses;
    }
    public static String adivsorHomePage(String username){
        String homePage = "\n";
        Advisor advisor = (Advisor)UserList.getInstance().findUser(username);
        homePage += advisor.getFirstName() + " " +  advisor.getLastName() + "\n\n";
        homePage += "*** Advisees ***\n";
        if(advisor.getAdvisees().size() == 0)
            homePage += "*EMPTY*";
        for(Student student : advisor.getAdvisees())
            homePage += student.getFirstName() + " " + student.getLastName() + " (" + student.getUscID() + ") - " + student.getUsername() + "\n";
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
            notes += note + "\n\n";
        return notes;
    }

    public static String possibleApplicationAreas(String username){
        return "*** Application Areas ***\n"
                + "Science\n"
                + "Math\n"
                + "Digital Design\n"
                + "Robotics\n"
                + "Speech\n";
    }
}
