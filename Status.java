import java.util.ArrayList;

public enum Status {
    COMPLETE,
    IN_PROGRESS,
    NOT_STARTED,
    UNSATISFACTORY;

    public static Status getStatus(ArrayList<Credit> credits) {
        boolean complete = false;
        boolean inProgress = false;
        boolean notStarted = false;
        for(Credit credit : credits){
            switch (Status.getStatus(credit)) {
            case COMPLETE:
                complete = true;
                break;
            case IN_PROGRESS:
                inProgress = true;
                break;
            case NOT_STARTED: 
                notStarted = true;
            default:
                break;
            }
        }
        if(complete && !inProgress && !notStarted)
            return COMPLETE;
        if(notStarted && !complete && !inProgress)
            return NOT_STARTED;
        return IN_PROGRESS;
    }
    
    public static Status getStatus(Credit credit) {
        if(credit.getType() == CreditType.WITHDRAWN)
            return UNSATISFACTORY;
        
        int semesterComparison = credit.getSemesterTaken().compare(Semester.current());
        switch (semesterComparison) {
        case 1:
            return NOT_STARTED;
        case 0:
            return IN_PROGRESS;
        case -1:
            break;
        }

        if(credit.getGrade() < 60)
            return UNSATISFACTORY;
        return COMPLETE;
    }
}

