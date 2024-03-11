public enum Category {
    REQUIREMENT(null, "Requirement"),
    CC(REQUIREMENT, "Carolina Core"),
    CR(REQUIREMENT, "College Requirement"),
    PR(REQUIREMENT, "Program Requirement"),
    MR(REQUIREMENT, "Major Requirement"),
    FND(REQUIREMENT, "Founding Documents"),
    CC_AIU(CC, "Asthetic and Interpretive Understanding"),
    CC_ARP(CC, "Analytical Reasoning and Problem Solving"),
    CC_CMS(CC, "Effective, Engaged, and Persuasive Communication: Spoken Component"),
    CC_CMW(CC, "Effective, Engaged, and Persuasive Communication: Written"),
    CC_GFL(CC, "Global Citizenship and Multicultural Understanding: Foreign Language"),
    CC_GHS(CC, "Global Citizenship and Multicultural Understanding: Historival Thinking"),
    CC_GSS(CC, "Global Citizenship and Multicultural Understanding: Social Sciences"),
    CC_INF(CC, "Informative Literacy"),
    CC_INT(CC, "Integrative Course"),
    CC_SCI(CC, "Science Literacy"),
    CC_VSR(CC, "Values, Ethics, and Social Responsibility"),
    CC_IntegrativeCourse(CC, "Integrative Course"),
    PR_FoundationalCourse(PR, "Foundational Course"),
    PR_LabSci(PR, "Lab Science"),
    PR_LibArts(PR, "Liberal Arts"),
    PR_LowerDiv(PR, "Lower Division"),
    PR_AppArea(PR, "Application Area"),
    MR_MajorCourse(MR, "Major Course"),
    MR_Elective(MR, "College Elective");
    

    Category parentCategory;
    String string;

    Category(Category parentCategory, String string){
        this.parentCategory = parentCategory;
        this.string = string;
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
