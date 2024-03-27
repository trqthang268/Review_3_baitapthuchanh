package baithuchanh_abstract_interface.ra.presentation;

import baithuchanh_abstract_interface.ra.businessImp.Employee;

import java.util.Arrays;
import java.util.Scanner;

public class EmployeeManagement {
    public static void main(String[] args) {
        EmployeeManagement employeeManagement = new EmployeeManagement();
        Scanner scanner = new Scanner(System.in);
        Employee[] arrEmployee = new Employee[100];
        int indexEmployee = 0;
        do {
            System.out.println("****************MENU*********************");
            System.out.println("1. Nhập thông tin cho n nhân viên\n" +
                    "2. Hiển thị thông tin nhân viên\n" +
                    "3. Tính lương cho các nhân viên\n" +
                    "4. Tìm kiếm nhân viên theo tên nhân viên\n" +
                    "5. Cập nhật thông tin nhân viên\n" +
                    "6. Xóa nhân viên theo mã nhân viên\n" +
                    "7. Sắp xếp nhân viên theo lương tăng dần (Comparable)\n" +
                    "8. Sắp xếp nhân viên theo tên nhân viên giảm dần (Comparator)\n" +
                    "9. Sắp xếp nhân vên theo năm sinh tăng dần (Comparator)\n" +
                    "10. Thoát");
            System.out.println("Nhập lựa chọn");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    employeeManagement.addNewEmployee(scanner, arrEmployee, indexEmployee);
                    break;
                case 2:
                    employeeManagement.displayEmployee(arrEmployee, indexEmployee);
                    break;
                case 3:
                    employeeManagement.calSalaryEmployee(arrEmployee, indexEmployee);
                    break;
                case 4:
                    employeeManagement.searchEmployeeByName(scanner, arrEmployee, indexEmployee);
                    break;
                case 5:
                    employeeManagement.updateEmployee(scanner, arrEmployee, indexEmployee);
                    break;
                case 6:
                    employeeManagement.deleteEmployee(scanner, arrEmployee, indexEmployee);
                    break;
                case 7:
                    System.out.println("Sắp xếp nhân viên theo lương tăng dần");
                    employeeManagement.sortWithComparable(arrEmployee, indexEmployee);
                    employeeManagement.displayEmployee(arrEmployee, indexEmployee);
                    break;
                case 8:
                    System.out.println("Sắp xếp nhân viên theo tên nhân viên giảm dần");
                    Arrays.sort(arrEmployee, (o1, o2) -> {
                        if (o1 != null && o2 != null) {
                            return (int) (o1.getSalary()-o2.getSalary());
                        }else {
                            return 0;
                        }
                    });
                    employeeManagement.displayEmployee(arrEmployee, indexEmployee);
                    break;
                case 9:
                    System.out.println("Sắp xếp nhân vên theo năm sinh tăng dần");
                    Arrays.sort(arrEmployee, (o1, o2) -> {
                        if (o1 != null && o2 != null) {
                            return o1.getYear()-o2.getYear();
                        }else {
                            return 0;
                        }
                    });
                    employeeManagement.displayEmployee(arrEmployee, indexEmployee);
                    break;
                case 10:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Vui lòng nhập lại từ 1-10");
                    break;
            }
        } while (true);
    }

    public void sortWithComparable(Employee[] arrEmployee, int indexEmployee) {
        Employee[] sortArrEmployee = miniArrEmployee(arrEmployee, indexEmployee);
        Arrays.sort(sortArrEmployee); //sắp xếp mảng mini
        for (int i = 0; i < indexEmployee; i++) {
            arrEmployee[i] = sortArrEmployee[i]; // sao chép mảng mới đã sắp xếp sang mảng cũ theo từng index
        }
    }

    //    tảo 1 mảng chỉ có indexEpl phần tử
    private Employee[] miniArrEmployee(Employee[] arrEmployee, int indexEmployee) {
        Employee[] miniArrEmployee = new Employee[indexEmployee];
//        sao chép đối tượng ở mảng cũ sang mảng mới có index phần tử
        for (int i = 0; i < indexEmployee; i++) {
            miniArrEmployee[i] = arrEmployee[i];
        }
        return miniArrEmployee;
    }

    public void deleteEmployee(Scanner scanner, Employee[] arrEmployee, int indexEmployee) {
        System.out.println("Nhập mã nhân viên muốn xóa");
        String idDeleteEmployee = scanner.nextLine();
        int index = -1;
        for (int i = 0; i < indexEmployee; i++) {
            if (arrEmployee[i].getId().equals(idDeleteEmployee)) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            for (int i = index; i < indexEmployee - 1; i++) {
                arrEmployee[i] = arrEmployee[i + 1];
            }
            arrEmployee[indexEmployee] = null;
            indexEmployee--;
        } else {
            System.out.println("Nhân viên không tồn tại");
        }
    }

    public void updateEmployee(Scanner scanner, Employee[] arrEmployee, int indexEmployee) {
        System.out.println("Nhập mã nhân viên cần chỉnh sửa");
        String idUpdateEmployee = scanner.nextLine();
        boolean isExist = true;
        for (int i = 0; i < indexEmployee; i++) {
            if (arrEmployee[i].getId().equals(idUpdateEmployee)) {
                arrEmployee[i].inputData(scanner);
                isExist = false;
                break;
            }
        }
        if (isExist) {
            System.out.println("Nhân viên không tồn tại");
        }
    }

    public void searchEmployeeByName(Scanner scanner, Employee[] arrEmployee, int indexEmployee) {
        System.out.println("Nhập tên nhân viên");
        String nameEmployee = scanner.nextLine();
        for (int i = 0; i < indexEmployee; i++) {
            if (arrEmployee[i].getName().contains(nameEmployee)) {
                arrEmployee[i].displayData();
                break;
            }
        }
    }

    public void calSalaryEmployee(Employee[] arrEmployee, int indexEmployee) {
        for (int i = 0; i < indexEmployee; i++) {
            arrEmployee[i].calSalary();
        }
        System.out.println("Đã tính lương cho các nhân viên");
    }

    public void displayEmployee(Employee[] arrEmployee, int indexEmployee) {
        for (int i = 0; i < indexEmployee; i++) {
            arrEmployee[i].displayData();
        }
    }

    public void addNewEmployee(Scanner scanner, Employee[] arrEmployee, int indexEmployee) {
        System.out.println("Nhập số nhân viên muốn thêm");
        int numberOfEmployee = Integer.parseInt(scanner.nextLine());
        for (int i = indexEmployee; i < indexEmployee + numberOfEmployee; i++) {
            Employee newEmployee = new Employee();
            arrEmployee[i] = newEmployee;
            arrEmployee[i].inputData(scanner);
            indexEmployee++;
        }
    }


}
