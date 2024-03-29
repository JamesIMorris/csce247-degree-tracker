public enum Category {
    REQUIREMENT(null, "", "Requirement"),
    CC(REQUIREMENT, "CC", "Carolina Core"),
    CR(REQUIREMENT, "CR", "College Requirement"),
    PR(REQUIREMENT, "PR", "Program Requirement"),
    MR(REQUIREMENT, "MR", "Major Requirement"),
    FND(REQUIREMENT, "FND", "Founding Documents"),
    AA(REQUIREMENT, "AA", "Application Area"),
    CC_AIU(CC, "CC-AIU", "Asthetic and Interpretive Understanding"),
    CC_ARP(CC, "CC-ARP", "Analytical Reasoning and Problem Solving"),
    CC_CMS(CC, "CC-CMS", "Effective, Engaged, and Persuasive Communication: Spoken Component"),
    CC_CMW(CC, "CC-CMW", "Effective, Engaged, and Persuasive Communication: Written"),
    CC_GFL(CC, "CC-GFL", "Global Citizenship and Multicultural Understanding: Foreign Language"),
    CC_GHS(CC, "CC-GHS", "Global Citizenship and Multicultural Understanding: Historival Thinking"),
    CC_GSS(CC, "CC-GSS", "Global Citizenship and Multicultural Understanding: Social Sciences"),
    CC_INF(CC, "CC-INF", "Informative Literacy"),
    CC_INT(CC, "CC-INT", "Integrative Course"),
    CC_SCI(CC, "CC-SCI", "Science Literacy"),
    CC_VSR(CC, "CC-VSR", "Values, Ethics, and Social Responsibility"),
    CC_IntegrativeCourse(CC, "CC-IC", "Integrative Course"),
    PR_FoundationalCourse(PR, "PR-FC", "Foundational Course"),
    PR_LabSci(PR, "PR-LS", "Lab Science"),
    PR_LibArts(PR, "PR-LA", "Liberal Arts"),
    PR_LowerDiv(PR, "PR-LD", "Lower Division"),
    PR_AppArea(PR, "PR-AA", "Application Area"),
    MR_MajorCourse(MR, "MR-MC", "Major Course"),
    MR_Elective(MR, "MR-E", "College Elective"),
    AA_SCI(AA, "AA-SCI", "Science"),
    AA_MATH(AA, "AA-MATH", "Math"),
    AA_DD(AA, "AA-DD", "Digital Design"),
    AA_ROBO(AA, "AA-ROBO", "Robotics"),
    AA_SPCH(AA, "AA-SPCH", "Speech");
    

    Category parentCategory;
    String abbreviation;
    String string;

    private void init(Category parentCategory, String abbreviation, String string){
        this.parentCategory = parentCategory;
        this.abbreviation = abbreviation;
        this.string = string;
    }

    Category(Category parentCategory, String abbreviation, String string){
        init(parentCategory, abbreviation, string);
    }

    Category(String abbreviation){
        
    }

    public static Category fromAbbreviation(String abbreviation){
        for(Category category : Category.values()){
            if(category.getAbbreviation().equalsIgnoreCase(abbreviation))
                return category;
        }
        return null;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public Category getParentCategory() {
        return parentCategory;
    }

    public String getString() {
        return string;
    }

    public Category parentCategory(){
        if(this == REQUIREMENT)
            return REQUIREMENT;
        return parentCategory;
    }

    public String toString(){
        if(this.parentCategory == REQUIREMENT)
            return this.string;
        return this.parentCategory.toString() + " - " + this.string;
    }
}
