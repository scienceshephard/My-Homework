

import java.util.Calendar;
import java.util.Random;

public class Student {
    private String firstName = ConstantValues.NO_NAME;
    private String lastName = ConstantValues.NO_NAME;
    private int id; // random numbers between 1 - 100
    private int startYear = Calendar.getInstance().get(Calendar.YEAR); //By default current year. It should be between year 2000 and currentYear
    private int graduationYear;
    private int degreeCount = 3;
    private Degree[] degrees = new Degree[3];
    private String birthDate = ConstantValues.NO_BIRTHDATE;

    Student(){
        this.id = getRandomId();
        this.startYear= Calendar.getInstance().get(Calendar.YEAR);
        for(int i = 0; i < degreeCount; i++)
            degrees[i] = new Degree();
    }
    Student( String lname, String fname ){
        this();
        setLastName(lname);
        setFirstName(fname);
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        if(firstName != null)
            this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        if(lastName != null)
            this.lastName = lastName;
    }
    public int getId() {
        return id;
    }
    public void setId(final int id) {
        if(id <= ConstantValues.MAX_ID)
            if(id >= ConstantValues.MIN_ID)
                this.id = id;
    }
    public int getstartYear() {
        return startYear;
    }
    public void setstartYear(final int startYear) {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
            if(startYear > 2000 && startYear <= currentYear)
                this.startYear = startYear;
    }
    public int getGraduationYear() {
        return graduationYear;
    }
    public String setGraduationYear(final int graduationYear) {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        
        if (!canGraduate()) {
            return "Check amount of required credits";
        }
        
        if (graduationYear < startYear || graduationYear > currentYear) {
            return "Check graduation year";
        }
        
        this.graduationYear = graduationYear;
        return "Ok";
    }

    public void setDegreeTitle(final int i, String dName){
        if(i >= 0)
            if(i < degreeCount)
                if(dName != null)
                    degrees[i].setDegreeTitle(dName);
    }

    public boolean addCourse(final int i, StudentCourse course){
        if(i >= 0)
            if(i < degreeCount)
                if(course != null)
                    return degrees[i].addStudentCourse(course);
        return false;
    }

    public int addCourses(final int i, StudentCourse [] courses){
        if (i >= 0 && i < degreeCount && courses != null) {
            int out = 0;
            for (StudentCourse course : courses) {
                if (course != null && addCourse(i, course)) {
                    out++;
                }
            }
            return out;
        }
        return 0;
    }

    public void printCourses(){
        for(Degree degree: degrees)
            if(degree != null)
                degree.printCourses();
    }

    public void printDegrees(){
        for(Degree degree: degrees)
            if(degree != null)
                System.out.println(degree);
    }

    public void setTitleOfThesis(final int i, String title){
        if(i >= 0) 
            if(i < degreeCount ) 
                if(title != null)
                   degrees[i].setTitleOfThesis(title);
    }


    public String getBirthDate(){
        return this.birthDate;
    }

    public String setBirthDate(String personId){
         if (personId == null) {
            return "No change";
        }
        
        String result = new PersonID().setPersonID(personId);
        if (result.equals("Ok")) {
            this.birthDate = new PersonID().getBirthDate();
            return birthDate;
        }
        return "No change";
    }

    public boolean hasGraduated(){
        return graduationYear > 0;
    }

    private boolean canGraduate(){
        double bachelorCredits = degrees[0].getCredits();
        double masterCredits = degrees[1].getCredits();
        
        boolean bachelorThesis = !degrees[0].getTitleOfThesis().equals(ConstantValues.NO_TITLE);
        boolean masterThesis = !degrees[1].getTitleOfThesis().equals(ConstantValues.NO_TITLE);
        
        return bachelorCredits >= ConstantValues.BACHELOR_CREDITS && masterCredits >= ConstantValues.MASTER_CREDITS && bachelorThesis && masterThesis;
    }

    public int getStudyYears(){
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        if (hasGraduated())
            return graduationYear - startYear;
        else
            return currentYear - startYear;
    }

    private int getRandomId(){
        return new Random().nextInt(ConstantValues.MAX_ID) + 1;
    }

    @Override
    public String toString() {
        String result = String.format("Student id: %d\n\t\tFirst name: %s, Last name: %s\n\t\tDate of birth: \"%s\"\n\t\t", id, firstName, lastName, birthDate);
        if(hasGraduated())
            result = result + String.format("Status: The student has graduated in %d\n\t\t", graduationYear);
        else
            result = result + "Status: The student has not graduated, yet\n\t\t";
        result = result + String.format("Start year: %d (studies have lasted for %d years)\n\t\t", startYear, getStudyYears());
        
        double totalCredits = degrees[0].getCredits() + degrees[1].getCredits();
        result = result + String.format("Total credits: %.1f\n\t\t", totalCredits);
        
        double bachelorCredits= degrees[0].getCredits();
        if(bachelorCredits >= ConstantValues.BACHELOR_CREDITS)
            result = result + String.format("Bachelor credits: %.1f\n\t\t\t\tTotal bachelor credits completed (%.1f/180.0)\n\t\t\t\t", bachelorCredits, bachelorCredits);
        else
            result= result + String.format("Bachelor credits: %.1f\n\t\t\t\tMissing bachelor credits %.1f (%.1f/180.0)\n", bachelorCredits,  ConstantValues.BACHELOR_CREDITS - bachelorCredits, bachelorCredits);
        
        result= result+ String.format("Title of BSc Thesis: \"%s\"\n\t\t", degrees[0].getTitleOfThesis());

        double masterCredits = degrees[1].getCredits();
        if (masterCredits >= ConstantValues.MASTER_CREDITS)
            result = result + String.format("Master credits: %.1f\n\t\t\t\tTotal master's credits completed (%.1f/120.0)\n", masterCredits, masterCredits);
        else
            result = result + String.format("Master credits: %.1f\n\t\t\t\tMissing master's credits %.1f (%.1f/120.0)\n", masterCredits, ConstantValues.MASTER_CREDITS - masterCredits, masterCredits);
            result = result + String.format("\t\t\t\tTitle of MSc Thesis: \"%s\"\n", degrees[1].getTitleOfThesis());
        return result;
    }

}
