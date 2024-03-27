package baithuchanh_abstract_interface.ra.businessImp;

import baithuchanh_abstract_interface.ra.business.IEmployee;

import java.util.Scanner;

public class Employee implements IEmployee, Comparable<Employee> {
    private String Id;
    private String Name;
    private int Year;
    private float Rate;
    private float Commission;
    private float Salary;
    private boolean Status;

    public Employee() {
    }

    public Employee(String id, String name, int year, float rate, float commission, float salary, boolean status) {
        Id = id;
        Name = name;
        Year = year;
        Rate = rate;
        Commission = commission;
        Salary = salary;
        Status = status;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getYear() {
        return Year;
    }

    public void setYear(int year) {
        Year = year;
    }

    public float getRate() {
        return Rate;
    }

    public void setRate(float rate) {
        Rate = rate;
    }

    public float getCommission() {
        return Commission;
    }

    public void setCommission(float commission) {
        Commission = commission;
    }

    public float getSalary() {
        return Salary;
    }

    public void setSalary(float salary) {
        Salary = salary;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean status) {
        Status = status;
    }

    @Override
    public void inputData(Scanner scanner) {
        System.out.println("Nhập mã nhân viên:");
        this.Id = scanner.nextLine();
        System.out.println("Nhập tên nhân viên:");
        this.Name = scanner.nextLine();
        System.out.println("Nhập năm sinh:");
        this.Year=Integer.parseInt(scanner.nextLine());
        System.out.println("Nhập hệ số lương:");
        this.Rate=Float.parseFloat(scanner.nextLine());
        System.out.println("Nhập hoa hồng:");
        this.Commission=Float.parseFloat(scanner.nextLine());
        System.out.println("Nhập trạng thái:");
        this.Status = Boolean.parseBoolean(scanner.nextLine());
    }

    @Override
    public void displayData() {
        System.out.printf("Mã nhân viên : %s - Tên nhân viên : %s - Năm sinh : %d - Hệ số lương : %f\n",this.Id,this.Name,this.Year,this.Rate);
        System.out.printf("Hoa hồng : %f - Lương : %f - Trạng thái : %s",this.Commission,calSalary(),this.Status?"Nghỉ việc":"Đang làm");
        System.out.println("------------------------------");
    }

    public float calSalary() {
        return this.Salary = (this.Rate*BASIC_SALARY+this.Commission);
    }

    @Override
    public int compareTo(Employee o) {
        return (int) (o.getSalary()-this.getSalary());
    }
}
