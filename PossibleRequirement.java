public class PossibleRequirement{
    Requirement requirement;
    boolean possible;

    public PossibleRequirement(Requirement requirement){
        this.requirement = requirement;
    }
    public PossibleRequirement(Requirement requirement, boolean possible){
        this.requirement = requirement;
        this.possible = possible;
    }

    public Requirement getRequirement(){
        return requirement;
    }
    public void setRequirement(Requirement requirement){
        this.requirement = requirement;
    }
    public boolean getPossible(){
        return possible;
    }
    public void setPossible(boolean possible){
        this.possible = possible;
    }
}