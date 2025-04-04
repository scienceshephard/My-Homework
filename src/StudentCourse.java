

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
        if (course.isNumericGrade()) {
            return gradeNum <= ConstantValues.MAX_GRADE && gradeNum >= ConstantValues.MIN_GRADE;
        } else {
            char gradeChar = (char) gradeNum;
            return gradeChar == ConstantValues.GRADE_ACCEPTED || 
                   gradeChar == ConstantValues.GRADE_FAILED;
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
        String grade;
        if(gradeNum == 0)
            grade ="Not graded";
        else if(course.isNumericGrade())
            grade= String.valueOf(gradeNum);
        else
            grade= String.valueOf((char) gradeNum);

        return String.format("%s Year: %d, Grade: \"%s\".]", course.toString(), yearCompleted, grade);
    }
    
}
