import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class Student {
    private int id;
    private String name;
    private int age;
    private String course;

    public Student(int id, String name, int age, String course) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.course = course;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public String getCourse() { return course; }
    public void setCourse(String course) { this.course = course; }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Age: " + age + ", Course: " + course;
    }
}

public class StudentManagementGUI {
    private ArrayList<Student> students = new ArrayList<>();
    private JFrame frame;
    private JTextArea displayArea;
    
    public StudentManagementGUI() {
        frame = new JFrame("Student Management System");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        frame.add(new JScrollPane(displayArea), BorderLayout.CENTER);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1));
        
        JButton addBtn = new JButton("Add Student");
        JButton viewBtn = new JButton("View Students");
        JButton searchBtn = new JButton("Search Student");
        JButton updateBtn = new JButton("Update Student");
        JButton deleteBtn = new JButton("Delete Student");
        JButton exitBtn = new JButton("Exit");

        panel.add(addBtn);
        panel.add(viewBtn);
        panel.add(searchBtn);
        panel.add(updateBtn);
        panel.add(deleteBtn);
        panel.add(exitBtn);
        frame.add(panel, BorderLayout.EAST);

        addBtn.addActionListener(e -> addStudent());
        viewBtn.addActionListener(e -> viewStudents());
        searchBtn.addActionListener(e -> searchStudent());
        updateBtn.addActionListener(e -> updateStudent());
        deleteBtn.addActionListener(e -> deleteStudent());
        exitBtn.addActionListener(e -> System.exit(0));

        frame.setVisible(true);
    }

    private void addStudent() {
        int id = Integer.parseInt(JOptionPane.showInputDialog("Enter Student ID:"));
        String name = JOptionPane.showInputDialog("Enter Student Name:");
        int age = Integer.parseInt(JOptionPane.showInputDialog("Enter Student Age:"));
        String course = JOptionPane.showInputDialog("Enter Student Course:");

        students.add(new Student(id, name, age, course));
        JOptionPane.showMessageDialog(frame, "Student Added Successfully!");
    }

    private void viewStudents() {
        displayArea.setText("");
        if (students.isEmpty()) {
            displayArea.setText("No students found.");
            return;
        }
        for (Student student : students) {
            displayArea.append(student.toString() + "\n");
        }
    }

    private void searchStudent() {
        int id = Integer.parseInt(JOptionPane.showInputDialog("Enter Student ID to Search:"));
        for (Student student : students) {
            if (student.getId() == id) {
                JOptionPane.showMessageDialog(frame, student.toString());
                return;
            }
        }
        JOptionPane.showMessageDialog(frame, "Student Not Found.");
    }

    private void updateStudent() {
        int id = Integer.parseInt(JOptionPane.showInputDialog("Enter Student ID to Update:"));
        for (Student student : students) {
            if (student.getId() == id) {
                student.setName(JOptionPane.showInputDialog("Enter New Name:", student.getName()));
                student.setAge(Integer.parseInt(JOptionPane.showInputDialog("Enter New Age:", student.getAge())));
                student.setCourse(JOptionPane.showInputDialog("Enter New Course:", student.getCourse()));
                JOptionPane.showMessageDialog(frame, "Student Updated Successfully!");
                return;
            }
        }
        JOptionPane.showMessageDialog(frame, "Student Not Found.");
    }

    private void deleteStudent() {
        int id = Integer.parseInt(JOptionPane.showInputDialog("Enter Student ID to Delete:"));
        for (Student student : students) {
            if (student.getId() == id) {
                students.remove(student);
                JOptionPane.showMessageDialog(frame, "Student Deleted Successfully!");
                return;
            }
        }
        JOptionPane.showMessageDialog(frame, "Student Not Found.");
    }

    public static void main(String[] args) {
        new StudentManagementGUI();
    }
}
