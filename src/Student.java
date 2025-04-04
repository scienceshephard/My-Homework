

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
    private String birthDate = ConstantValues.NO_BIRTHDATE;//by default not available

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

        for(int i = 0; i < degreeCount; i++)
            if(degrees[i] != null)
                System.out.println(degrees[i].toString());
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
         PersonID pId= new PersonID();
        
        String result = pId.setPersonID(personId);
        if (result.equals("Ok")) {
            this.birthDate = pId.getBirthDate();
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
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Student id: ").append(id).append("\n");
        stringBuilder.append("\tFirst name: ").append(firstName)
                .append(", Last name: ").append(lastName).append("\n");
        stringBuilder.append("\tDate of birth: \"").append(birthDate).append("\"\n");

        if (hasGraduated()) {
            stringBuilder.append("\tStatus: The student has graduated in ").append(graduationYear).append("\n");
        } else {
            stringBuilder.append("\tStatus: The student has not graduated, yet\n");
        }

        stringBuilder.append("\tStart year: ").append(startYear)
                .append(" (studies have lasted for ").append(getStudyYears()).append(" years)\n");

        double totalCredits = degrees[0].getCredits() + degrees[1].getCredits();
        stringBuilder.append("\tTotal credits: ").append(String.format("%.1f", totalCredits)).append("\n");

        // Bachelor credits info
        double bachelorCredits = degrees[0].getCredits();
        stringBuilder.append("\tBachelor credits: ").append(String.format("%.1f", bachelorCredits)).append("\n");
        if (bachelorCredits >= ConstantValues.BACHELOR_CREDITS) {
            stringBuilder.append("\t\tTotal bachelor credits completed (")
                    .append(String.format("%.1f", bachelorCredits)).append("/180.0)\n");
        } else {
            stringBuilder.append("\t\tMissing bachelor credits ")
                    .append(String.format("%.1f", ConstantValues.BACHELOR_CREDITS - bachelorCredits))
                    .append(" (").append(String.format("%.1f", bachelorCredits)).append("/180.0)\n");
        }
        stringBuilder.append("\t\tTitle of BSc Thesis: \"").append(degrees[0].getTitleOfThesis()).append("\"\n");

        // Master credits info
        double masterCredits = degrees[1].getCredits();
        stringBuilder.append("\tMaster credits: ").append(String.format("%.1f", masterCredits)).append("\n");
        if (masterCredits >= ConstantValues.MASTER_CREDITS) {
            stringBuilder.append("\t\tTotal master's credits completed (")
                    .append(String.format("%.1f", masterCredits)).append("/120.0)\n");
        } else {
            stringBuilder.append("\t\tMissing master's credits ")
                    .append(String.format("%.1f", ConstantValues.MASTER_CREDITS - masterCredits))
                    .append(" (").append(String.format("%.1f", masterCredits)).append("/120.0)\n");
        }
        stringBuilder.append("\t\tTitle of MSc Thesis: \"").append(degrees[1].getTitleOfThesis()).append("\"\n");

        return stringBuilder.toString();
    }
}
