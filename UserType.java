public enum UserType{
    STUDENT("Student", false),
    ADVISOR("Advisor", false),
    ADMIN("Administrator", false);

    private String string;
    private boolean isStaff;

    UserType(String string, boolean isStaff){
        this.isStaff = isStaff;
        this.string = string;
    }

    public String toString(){
        return string;
    }
    public boolean isStaff(){
        return isStaff;
    }
}