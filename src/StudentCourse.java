

import java.util.Calendar;

public class StudentCourse {
    private Course course;
    private int gradeNum;
    private int yearCompleted;
    
    StudentCourse(){

    }

    StudentCourse(Course course, final int gradeNum, final int yearCompleted){
        this.course = course;
        setGrade(gradeNum);
        setYear(yearCompleted);
    }

    public Course getCourse() {
        return course;
    }
    public void setCourse(Course course) {
        this.course = course;
    }

    public int getGradeNum() {
        if (gradeNum == 0) return 0;
        if (course == null || !course.isNumericGrade()) return -1;
        return gradeNum;
    }

    protected void setGrade(int gradeNum) {
        if(checkGradeValidity(gradeNum)){
            this.gradeNum = gradeNum;
            if (yearCompleted == 0) {
                yearCompleted = Calendar.getInstance().get(Calendar.YEAR);
            }
        }
    }  

    private boolean checkGradeValidity( final int gradeNum ){
        if(course ==null)
            return false;



        if (course.isNumericGrade()) {
            return gradeNum <= ConstantValues.MAX_GRADE && gradeNum >= ConstantValues.MIN_GRADE;
        } else {
            char gradeChar = (char) gradeNum;
            return gradeChar == ConstantValues.GRADE_ACCEPTED || gradeChar == ConstantValues.GRADE_FAILED;
        }
    }

    public boolean isPassed(){
        if(course.isNumericGrade()){
            return gradeNum > ConstantValues.MIN_GRADE;
        }else{
            return gradeNum == ConstantValues.GRADE_ACCEPTED;
        }
    }

    public int getYearCompleted() {
        return yearCompleted;
    }

    public void setYear(int yearCompleted) {
        this.yearCompleted = yearCompleted;
    }

    @Override
    public String toString() {
        // 1. Start with the course information
        String courseString = course.toString(); // [CODE (X.XX cr), "Name", Type, period: X.]

        // 2. Format the grade appropriately
        String gradeString;
        if (gradeNum == 0)
            gradeString = "\"Not graded\"";
        else if (course.isNumericGrade())
            gradeString = String.valueOf(getGradeNum()); // 1-5
        else {
            gradeString = String.valueOf((char) getGradeNum()); // 'A' or 'F'
        }

        return String.format("%s Year: %d, Grade: %s.]", courseString, yearCompleted, gradeString);
    }
}
