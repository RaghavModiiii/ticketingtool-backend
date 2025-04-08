package com.example.TicketingSystem;
import java.util.*;
import java.util.stream.Collectors;


public class Employee {
    private static List<Employee> employees = new ArrayList<>();

    private String name;
    private int age;
    private String gender;
    private double salary;
    private String designation;
    private String department;

    public Employee(String name, int age, String gender, double salary, String designation, String department) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.salary = salary;
        this.designation = designation;
        this.department = department;
        employees.add(this);
    }

    public static Employee highestSalary() {
        return employees.stream().max(Comparator.comparingDouble(emp -> emp.salary)).orElse(null);
    }

    public static Map<String, Long> genderCount() {
        return employees.stream().collect(Collectors.groupingBy(emp -> emp.gender, Collectors.counting()));
    }

    public static Map<String, Double> departmentExpense() {
        return employees.stream().collect(Collectors.groupingBy(emp -> emp.department, Collectors.summingDouble(emp -> emp.salary)));
    }

    public static List<Employee> topSeniorEmployees(int count) {
        return employees.stream()
                .sorted(Comparator.comparingInt(emp -> -emp.age))
                .limit(count)
                .collect(Collectors.toList());
    }

    public static List<String> managerNames() {
        return employees.stream()
                .filter(emp -> emp.designation.contains("manager"))
                .map(emp -> emp.name)
                .collect(Collectors.toList());
    }

    public static void hikeSalary() {
        employees.stream()
                .filter(emp -> !emp.designation.toLowerCase().contains("manager"))
                .forEach(emp -> emp.salary *= 1.2);
    }

    public static long totalEmployees() {
        return employees.size();
    }

    public String getName() {
        return name;
    }

    public static void main(String[] args) {
        new Employee("Pinak", 29, "Female", 90000, "manager", "HR");
        new Employee("Ayush", 30, "Male", 120000, "junior engineer", "IT");
        new Employee("Manan", 25, "Male", 100000, "manager", "Finance");
        new Employee("Raghav", 26, "Male", 70000, "developer", "IT");
        new Employee("Aakshi", 23, "Female", 75000, "hr executive", "HR");
        new Employee("Ram", 45, "Male", 130000, "director", "Management");
        new Employee("Riya", 32, "Female", 85000, "manager", "Sales");
        new Employee("Mehra", 39, "Male", 65000, "analyst", "Finance");
        new Employee("Ridhima", 38, "Female", 110000, "senior consultant", "Consulting");
        new Employee("Sahil", 53, "Male", 72000, "developer", "IT");

        System.out.println("Highest Paid Employee: " + highestSalary().getName());
        System.out.println("Male & Female Employees: " + genderCount());
        System.out.println("Department-wise Expense: " + departmentExpense());
        System.out.println("Top 5 Senior Employees: " + topSeniorEmployees(5).stream().map(Employee::getName).collect(Collectors.toList()));
        System.out.println("Managers: " + managerNames());
        hikeSalary();
        System.out.println("Total Employees: " + totalEmployees());
    }
}