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

    public static UserType fromString(String string){
        for(UserType type : UserType.values())
            if(string.equalsIgnoreCase(type.toString()))
                return type;
        return null;
    }

    public String toString(){
        return string.toUpperCase();
    }
    public boolean isStaff(){
        return isStaff;
    }
}