

public class Course {
    private String name ; // Name of the course
    private String courseCode; // Code of the course
    private Character courseBase; // One of the values ‘A’, ‘P’ or ‘S’
    private int courseType; // 0 = Optional, 1 = Mandatory
    private int period; // Between 1 - 5
    private double credits; // Valid values are MIN_CREDITS <= credits <= MAX_COURSE_CREDITS
    private boolean numericGrade; // Whether a course has numeric grading (true) or not (false).



    public Course(){
        this.name = ConstantValues.NO_TITLE;
        this.courseCode = "";
        this.courseBase = ' ';
        this.courseType = ConstantValues.OPTIONAL;
        this.period = 0;
        this.credits = 0.0;
        this.numericGrade = false;
    }

    public Course(String name, final int code, Character courseBase, final int type, final int period, final double credits, boolean numericGrade){

        this();
        setName(name);
        setCourseCode(code, courseBase);
        setCourseType(type);
        setPeriod(period);
        setCredits(credits);
        setNumericGrade(numericGrade);
    }
    public Course (Course course){
        this(course.name, Integer.parseInt(course.courseCode.substring(0, 6)),
                course.courseBase, course.courseType, course.period,
                course.credits, course.numericGrade);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name != null)
            if(!name.isEmpty())
                this.name = name;
    }

    public String getCourseTypeString(){
        return courseType == ConstantValues.OPTIONAL? "Optional": "Mandatory";
    }

    public int getCourseType() {
        return courseType;
    }

    public void setCourseType(final int type) {
        if(type == ConstantValues.OPTIONAL || type == ConstantValues.MANDATORY){
            this.courseType = type;
        }
    }

    public String getCourseCode(){
        return this.courseCode;
    }

    public void setCourseCode( final int courseCode, Character courseBase){
        if (courseCode > 0 && courseCode < 1000000 && (courseBase == 'A' || courseBase == 'P' || courseBase == 'S')) {
            this.courseCode = String.format("%06d", courseCode) + courseBase;
            this.courseBase = courseBase;
        }
    }

    public Character getCourseBase(){
        return this.courseBase;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public double getCredits() {
        return credits;
    }

    public void setCredits(double credits) {
        this.credits = credits;
    }
    public boolean isNumericGrade() {
        return numericGrade;
    }
    public void setNumericGrade(boolean numericGrade){
        this.numericGrade = numericGrade;
    }

    @Override
    public String toString() {
        return String.format("[%s (%.2f cr), \" %s\". %s, period: %d.]", courseCode, credits, name, getCourseTypeString(), period);
    }
    
}
