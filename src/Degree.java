public class Degree {
    private static final int MAX_COURSES =50;
    private int count; // Number of courses for this degree
    private String degreeTitle= ConstantValues.NO_TITLE;
    private String titleOfThesis= ConstantValues.NO_TITLE;
    private StudentCourse[] myCourses = new StudentCourse[MAX_COURSES];

    public StudentCourse[] getCourses(){
        return myCourses;
    }

    public void addStudentCourses(StudentCourse[] courses){
        if (courses != null) {
            for (StudentCourse course : courses) {
                if (course != null) {
                    addStudentCourse(course);
                }
            }
        }
    }

    public boolean addStudentCourse(StudentCourse course){
        if (course != null) {
            if( count < MAX_COURSES ){
                myCourses[count++] = course;
                return true;
            }
        }
        return false;
    }

    public String getDegreeTitle() {
        return degreeTitle;
    }

    public void setDegreeTitle(String degreeTitle) {
        if(degreeTitle != null)
            this.degreeTitle = degreeTitle;
        
    }

    public String getTitleOfThesis() {
        return titleOfThesis;
    }

    public void setTitleOfThesis(String titleOfThesis) {
        if(titleOfThesis != null)
            this.titleOfThesis = titleOfThesis;
    }

    public double getCreditsByBase(Character base){
        double total = 0.0;

        int i=0;
        while(i< count){
            if (myCourses[i] != null)
                if(myCourses[i].isPassed()) 
                    if(myCourses[i].getCourse().getCourseBase() == base)
                        total += myCourses[i].getCourse().getCredits();
            i++;
        }
        return total;
    }

    public double getCreditsByType(final int courseType){
        double total = 0.0;

        int i=0;
        while(i< count){
            if (myCourses[i] != null)
                if(myCourses[i].isPassed()) 
                    if(myCourses[i].getCourse().getCourseType() == courseType)
                        total += myCourses[i].getCourse().getCredits();
            i++;
        }
        return total;
    }
    public double getCredits(){
        double total = 0.0;

        int i=0;
        while(i < count){
            if(myCourses[i] != null)
                if(myCourses[i].isPassed())
                    total += myCourses[i].getCourse().getCredits();
            i++;
        }
        return total;
    }

    private boolean isCourseCompleted(StudentCourse c){
        return c != null && c.isPassed();
    }
    public void printCourses(){
        for(int i= 0; i < count; i++) {
            if (myCourses[i] != null) {
                System.out.println(myCourses[i]);
            }
        }
    }
    @Override
    public String toString(){
        
        String result= String.format("Degree [Title: \"%s\" (courses: %d)\n \t\t Thesis title: \"%s\"\n", degreeTitle, count, titleOfThesis);
        for (int i = 0; i < count; i++) {
            if (myCourses[i] != null) {
                result = result + String.format("\t\t %d. %s\n", i + 1, myCourses[i]);
            }
        }
        return result;
    }
}
