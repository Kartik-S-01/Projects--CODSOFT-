import java.util.*;

class Student                        //Class for student information / details 
{
    private String studentId;
    private String name;
    private int age;
    private String courseId;
    private int batch;

    public Student(String studentId, String name, int age, String courseId, int batch)  //Parameters 
    {
        this.studentId = studentId;
        this.name = name;                   //Parameter is assigned to the Name attribute of student object 
        this.age = age;                     //Parameter is assigned to the Age attribute of student object 
        this.courseId = courseId;          //Parameter is assigned to the CourseId attribute of student object 
        this.batch = batch;                //Parameter is assigned to the Batch attribute of student object 
    }

    public String getStudentId() 
    {
        return studentId;
    }

    public String getName() 
    {
        return name;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public int getAge() 
    {
        return age;
    }

    public void setAge(int age) 
    {
        this.age = age;
    }

    public String getCourseId() 
    {
        return courseId;
    }

    public int getBatch() 
    {
        return batch;
    }

    @Override
    
    public String toString() 
    {
        return "Student ID: " + studentId + ", Name: " + name + ", Age: " + age + ", Course ID: " + courseId + ", Batch Year: " + batch;
    }
}

class Course                        //Class for course details and store information  
{
    private String courseId;
    private String courseName;
    private int capacity;
    private List<Student> students;

    public Course(String courseId, String courseName, int capacity) 
    {
        this.courseId = courseId;               //It provided courseId parameter to the courseId attribute of the Course instance
        this.courseName = courseName;           //It provided courseName parameter to the courseName attribute of the Course instance
        this.capacity = capacity;               //It provided capacity parameter to the capacity attribute of the Course instance
        this.students = new ArrayList<>();     //This list will hold the students enrolled in the course
    }

    public String getCourseId() 
    {
        return courseId;
    }

    public String getCourseName() 
    {
        return courseName;
    }

    public int getCapacity() 
    {
        return capacity;
    }

    public List<Student> getStudents()      
    {
        return students;
    }

    public boolean addStudent(Student student)             //Method for add new addmission
    {
        if (students.size() < capacity) 
        {
            students.add(student);
            return true;
        } 
        
        else 
        {
            System.out.println("Course is full!");
            return false;
        }
    }

    public boolean removeStudent(String studentId)            //Method for remove students details which are passed out or drop their studies
    {
        return students.removeIf(student -> student.getStudentId().equals(studentId));
    }

    public Student findStudentById(String studentId) 
    {
        for (Student student : students) 
        {
            if (student.getStudentId().equals(studentId)) 
            {
                return student;
            }
        }
        return null;
    }

    public Student findStudentByName(String name) 
    {
        for (Student student : students) 
        {
            if (student.getName().equalsIgnoreCase(name)) 
            {
                return student;
            }
        }
        return null;
    }

    @Override
    
    public String toString() 
    {
        return "Course ID: " + courseId + ", Course Name: " + courseName + ", Capacity: " + capacity;
    }
}

public class CourseManagementSystem 
{
    private Map<String, Course> courses;
    private Map<String, Integer> studentCounters;
    private Map<String, List<List<String>>> courseTables;  //List<List<String>> is used to display output in two-dimensional list or table

    public CourseManagementSystem() 
    {
        courses = new HashMap<>();               //Hashmap is used to keep track of student ID, coursees and datastructures 
        studentCounters = new HashMap<>();
        courseTables = new HashMap<>();
        initializeCourses();
    }

    private void initializeCourses()            //Mentioned courses which are modifiable according to user 
    {
        addCourse("BA", "B.A.", 120);
        addCourse("BSC_PCM", "B.Sc (PCM)", 80);
        addCourse("BSC_ZBC", "B.Sc (ZBC)", 80);
        addCourse("BCA", "BCA", 100);
        addCourse("BBA", "BBA", 90);
        addCourse("BCOM", "B.Com", 150);
        addCourse("BCOM_HONS", "B.Com (Hons.)", 40);
        addCourse("MSC_PHY", "M.Sc (Physics)", 40);
        addCourse("MSC_CHE", "M.Sc (Chemistry)", 40);
        addCourse("MSC_CS", "M.Sc (Computer Science)", 30);
        addCourse("MCA", "MCA", 70);
        addCourse("MBA", "MBA", 70);
        addCourse("MCOM", "M.Com", 70);
        addCourse("MA", "M.A.", 60);
    }

    private void addCourse(String courseId, String courseName, int capacity) 
    {
        courses.put(courseId, new Course(courseId, courseName, capacity));
        studentCounters.put(courseId, 1);  // Initialize student counter for each course
    }

    private String generateStudentId(String courseId) 
    {
        int counter = studentCounters.get(courseId);
        String studentId = courseId + "_" + String.format("%03d", counter);
        studentCounters.put(courseId, counter + 1);
        return studentId;
    }

    public void addStudent(String courseId, String name, int age, int batch)          //For add new student's details 
    {
        Course course = courses.get(courseId);
        
        if (course != null) 
        {
            String studentId = generateStudentId(courseId);
            Student student = new Student(studentId, name, age, courseId, batch);
            
            if (course.addStudent(student)) 
            {
                updateCourseTable(courseId);
                System.out.println("Student added successfully! Student ID: " + studentId);
            } 
            
            else 
            {
                System.out.println("Failed to add student.");
            }
        } 
        
        else 
        {
            System.out.println("Course not found!");
        }
    }

    public void removeStudent(String courseId, String studentId)     //For removing student's details 
    {
        Course course = courses.get(courseId);
        
        if (course != null) 
        {
            if (course.removeStudent(studentId)) 
            {
                System.out.println("Student removed successfully!");
                updateCourseTable(courseId);
            } 
            
            else 
            {
                System.out.println("Student not found.");
            }
        } 
        
        else 
        {
            System.out.println("Course not found!");
        }
    }

    public void modifyStudentDetails(String courseId, String studentId, String newName, int newAge)     //For modifying student details
    {
        Course course = courses.get(courseId);
        if (course != null) 
        {
            Student student = course.findStudentById(studentId);
            
            if (student != null) 
            {
                student.setName(newName);
                student.setAge(newAge);
                updateCourseTable(courseId);
                System.out.println("Student details modified successfully!");
            } 
            
            else 
            {
                System.out.println("Student not found.");
            }
        } 
        
        else 
        {
            System.out.println("Course not found!");
        }
    }

    private void updateCourseTable(String courseId)                  //For add update students in each course
    {
        Course course = courses.get(courseId);
        
        if (course != null) 
        {
            List<List<String>> table = new ArrayList<>();
            
            for (Student student : course.getStudents()) 
            {
                List<String> row = new ArrayList<>();
                row.add(student.getStudentId());
                row.add(student.getName());
                row.add(String.valueOf(student.getAge()));
                row.add(student.getCourseId());
                row.add(String.valueOf(student.getBatch()));
                table.add(row);
            }
            courseTables.put(courseId, table);
        }
    }

    public void displayCourseTable(String courseId)                     //Student details display for each course
    {
        List<List<String>> table = courseTables.get(courseId);
        
        if (table != null)                    //For getting details in tabular form 
        {
            System.out.println("\nCourse: " + courses.get(courseId).getCourseName());
            System.out.println("----------------------------------------------------------");
            System.out.printf("%-10s %-15s %-5s %-10s %-10s%n", "Student ID", "Name", "Age", "Course ID", "Batch Year");
            System.out.println("----------------------------------------------------------");
            
            for (List<String> row : table) 
            {
                System.out.printf("%-10s %-15s %-5s %-10s %-10s%n", row.get(0), row.get(1), row.get(2), row.get(3), row.get(4));
            }
        } 
        
        else 
        {
            System.out.println("No data available for Course ID: " + courseId);
        }
    }

    public void displayAllCourses()                    //For getting all courses details on output
    {
        System.out.println("Available Courses:");
        
        for (Course course : courses.values()) 
        {
            System.out.println(course);
        }
    }

    public static void main(String[] args) 
    {
        CourseManagementSystem cms = new CourseManagementSystem();
        Scanner sc = new Scanner(System.in);

        while (true) 
        {
            System.out.println("\n--- Course Management System ---");
            System.out.println("1. New Student Entry");
            System.out.println("2. Remove Student Entry");
            System.out.println("3. Modify Student Details");
            System.out.println("4. Display Course Student Details in Table");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int option = sc.nextInt();
            sc.nextLine();  // Consume the newline character

            switch (option) 
            {
                case 1:
                    cms.displayAllCourses();
                    System.out.print("\nEnter Course ID: ");
                    String courseId = sc.nextLine();

                    System.out.print("Enter Student Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Student Age: ");
                    int age = sc.nextInt();
                    sc.nextLine();  // Consume the newline character

                    System.out.print("Enter Batch Year: ");
                    int batch = sc.nextInt();
                    sc.nextLine();  // Consume the newline character
                    cms.addStudent(courseId, name, age, batch);
                    break;

                case 2:
                    System.out.print("\nEnter Course ID: ");
                    courseId = sc.nextLine();

                    System.out.print("Enter Student ID: ");
                    String studentId = sc.nextLine();
                    cms.removeStudent(courseId, studentId);
                    break;

                case 3:
                    System.out.print("\nEnter Course ID: ");
                    courseId = sc.nextLine();

                    System.out.print("Enter Student ID: ");
                    studentId = sc.nextLine();

                    System.out.print("Enter New Name: ");
                    String newName = sc.nextLine();

                    System.out.print("Enter New Age: ");
                    int newAge = sc.nextInt();
                    sc.nextLine();  // Consume the newline character
                    cms.modifyStudentDetails(courseId, studentId, newName, newAge);
                    break;

                case 4:
                    System.out.print("\nEnter Course ID: ");
                    courseId = sc.nextLine();
                    cms.displayCourseTable(courseId);
                    break;

                case 5:
                    System.out.println("\nThanks For Using...");
                    sc.close();
                    return;

                default:
                    System.out.println("\nInvalid option!");
            }

            System.out.print("\nDo you want to manage more options? (yes/no): ");
            String repeat = sc.nextLine();

            if (!repeat.equalsIgnoreCase("yes")) 
            {
                System.out.println("\nThanks For Using...");
                break;
            }
        }
    }
}