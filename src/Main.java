import java.util.Set;

public class Main {
    public static void main(String[] args) {
        // 1. Create a student using the constructor with no parameters
        Student student = new Student();
        
        // 2. Create 11 Courses
        Course course1 = new Course("Programming 1", 811104, 'P', 1, 1, 5.0, true);
        Course course2 = new Course("All kinds of basic studies", 112233, 'P', 1, 2, 45.0, true);
        Course course3 = new Course("More basic studies", 223344, 'A', 1, 1, 50.5, true);
        Course course4 = new Course("Even more basic studies", 556677, 'A', 0, 3, 50.0, true);
        Course course5 = new Course("Final basic studies", 123123, 'A', 1, 4, 50.5, true);
        Course course6 = new Course("Programming 2", 616161, 'A', 1, 3, 25.0, true);
        Course course7 = new Course("All kinds of master studies", 717171, 'P', 0, 2, 45.0, true);
        Course course8 = new Course("More master studies", 818181, 'A', 1, 1, 25.0, true);
        Course course9 = new Course("Even more master studies", 919191, 'S', 1, 3, 20.0, true);
        Course course10 = new Course("Extra master studies", 666666, 'S', 0, 5, 8.0, false);
        Course course11 = new Course("Final master studies", 888888, 'S', 1, 5, 18.0, false);
        
        // 3. Create StudentCourses
        StudentCourse sc1 = new StudentCourse(course1, 1, 2013);
        StudentCourse sc2 = new StudentCourse(course2, 1, 2014);
        StudentCourse sc3 = new StudentCourse(course3, 1, 2015);
        StudentCourse sc4 = new StudentCourse(course4, 4, 2016);
        StudentCourse sc5 = new StudentCourse(course5, 5, 2017);
        StudentCourse sc6 = new StudentCourse(course6, 1, 2018);
        StudentCourse sc7 = new StudentCourse(course7, 1, 2019);
        StudentCourse sc8 = new StudentCourse(course8, 2, 2020);
        StudentCourse sc9 = new StudentCourse(course9, 0, 2021);
        StudentCourse sc10 = new StudentCourse(course10, 'A', 2021);
        StudentCourse sc11 = new StudentCourse(course11, 'f', 2022);
        
        // 4. Create an array of bachelor StudentCourses
        StudentCourse[] bachelorCourses = {sc1, sc2, sc3, sc4, sc5};
        
        // 5. Create an array of master StudentCourses
        StudentCourse[] masterCourses = {sc6, sc7, sc8, sc9, sc10, sc11};
        
        // 6. Set the degreeTitle of the bachelor studies of the student to “Bachelor of Science"
        student.setDegreeTitle(0, "Bachelor of Science");
        
        // 7. Set the degreeTitle of the master studies of the student to “Master of Science”
        student.setDegreeTitle(1, "Master of Science");
        
        // 8. Set the title of the bachelor thesis of the student to “Bachelor thesis title”
        student.setTitleOfThesis(0, "Bachelor thesis title");
 
        // 9. Set the title of the master thesis of the student to “Masters thesis title”
        student.setTitleOfThesis(1, "Masters thesis title");
        
        // 10. Add the bachelor courses (see step 4) to bachelor studies for the student
        student.addCourses(0, bachelorCourses);

        // 11. Add the master courses (see step 5) to master studies for the student
        student.addCourses(1, masterCourses);
        
        // 12. Set the start year of the student to 2001
        student.setstartYear(2001);
        
        // 13. Set graduation year of the student to 2020
        student.setGraduationYear(2020);
        
        // 14. Set the first name of the student to “Donald”
        student.setFirstName("Donald");

        // 15. Set the last name of the student to “Duck”
        student.setLastName("Duck");
        
        // 16. Print the details of the student using toString method
        System.out.println(student.toString());
        
        // 17. Set birthdate of the student to “230498-045T”
        student.setBirthDate("230498-045T");
        
        // 18. Set the title of the bachelor thesis of the student to “Christmas - The most wonderful time of the year”
        student.setTitleOfThesis(0, "Christmas - The most wonderful time of the year");
        
        // 19. Set the title of the master thesis of the student to “Dreaming of a white Christmas"
        student.setTitleOfThesis(1, "Dreaming of a white Christmas");
        
        // 20. Print the degrees of the student (use method printDegrees)
        student.printDegrees();
        
        // 21.  Set the grade of the course 919191S to 3 for the student
        sc9.setGrade(3);   
        
        // 22.  Print the details of the student using toString method.
        System.out.println(student.toString());
        
        // 23.  Print the degrees of the student (use method printDegrees)
        student.printDegrees();
        
        // 24.  Print the courses of the student use method (printCourses)
        student.printCourses();
        
        // 25. Set the grade of the course 888888S (the last StudentCourse course) of the student to ‘X’
        sc11.setGrade('X');

        // 26. Print the details of the StudentCourse 888888S
        System.out.println(sc11.toString());

        // 27. Set the grade of the course 888888S (the last StudentCourse course) of the student to ‘a’
        sc11.setGrade('a');
        
        // 28. Print the details of the StudentCourse 888888S
        System.out.println(sc11.toString());

        // 29. Set the grade of the course 811104P (the first StudentCourse course) of the student to 6
        sc1.setGrade(1);

        // 30. Print the details of the StudentCourse 811104P
        System.out.println(sc1.toString());

        // 31. Set the grade of the course 811104P (the first StudentCourse course) of the student to 5
        sc1.setGrade(5);

        // 32. Print the details of the StudentCourse 811104P.
        System.out.println(sc1.toString());
    }
}