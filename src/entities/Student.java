package entities;

public class Student{
    private String name;
    private boolean workingHand; // правая - true
private byte visionRestriction; // нет проблем - 0; "1-3 парты" - 1; "1-2 парты" - 2; "1 парта, средний ряд" - 3;
    private byte height; // низкий - 1; средний - 2; высокий - 3
    private boolean concentrationProblem; // есть - true

    public void setConcentrationProblem(String concentrationProblem) {
        this.concentrationProblem = concentrationProblem.equals("TRUE");

    }

    public void setHeight(String height) {
        if (height.equals("Низкий"))
            this.height = 1;
        else if (height.equals("Средний"))
            this.height = 2;
        else
            this.height = 3;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVisionRestriction(String visionRestriction) {
        if (visionRestriction.equals(""))
            this.visionRestriction = 0;
        else if (visionRestriction.equals("1-3 парты"))
            this.visionRestriction = 1;
        else if (visionRestriction.equals("1-2 парты"))
            this.visionRestriction = 2;
        else
            this.visionRestriction = 3;
    }

    public void setWorkingHand(String workingHand) {
        this.workingHand = workingHand.equals("Правая");
    }

    public boolean getConcentrationProblem() {
        return concentrationProblem;
    }

    public byte getHeight() {
        return height;
    }

    public String getName() {
        return name;
    }

    public byte getVisionRestriction() {
        return visionRestriction;
    }

    public boolean getWorkingHand() {
        return workingHand;
    }

    @Override
    public String toString() {
        return name + " " + workingHand + " " + visionRestriction + " " + height + " " + concentrationProblem;
    }
}
