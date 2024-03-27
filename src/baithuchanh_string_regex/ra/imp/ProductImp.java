package baithuchanh_string_regex.ra.imp;

import baithuchanh_string_regex.ra.entity.Product;

import java.util.Locale;
import java.util.Scanner;

public class ProductImp {
    Product[] arrProduct = new Product[100];
    int indexProduct = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ProductImp productImp = new ProductImp();
        do {
            System.out.println("***********************MENU**************************");
            System.out.println("1. Nhập thông tin n sản phẩm (n nhập từ bàn phím)\n" +
                    "2. Hiển thị thông tin các sản phẩm\n" +
                    "3. Tính lợi nhuận các sản phẩm\n" +
                    "4. Sắp xếp các sản phẩm theo lợi nhuận giảm dần\n" +
                    "5. Thống kê các sản phẩm theo giá\n" +
                    "6. Tìm các sản phẩm theo tên sản phẩm\n" +
                    "7. Nhập sản phẩm\n" +
                    "8. Bán sản phẩm\n" +
                    "9. Cập nhật trạng thái sản phẩm\n" +
                    "10. Thoát\n");
            System.out.println("Nhập lựa chọn của bạn :");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    productImp.inputListProduct(scanner);
                    break;
                case 2:
                    productImp.displayAllItem();
                    break;
                case 3:
                    productImp.productProfit();
                    break;
                case 4:
                    productImp.sortByProfit();
                    break;
                case 5:
                    productImp.productInRange(scanner);
                    break;
                case 6:
                    productImp.findProductByName(scanner);
                    break;
                case 7:
                    productImp.insertProduct(scanner);
                    break;
                case 8:
                    productImp.sellProduct(scanner);
                    break;
                case 9:
                    productImp.updateProductStatus(scanner);
                    break;
                case 10:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Vui lòng chọn từ 1-10");
            }
        } while (true);
    }

    //     Cập nhật trạng thái sản phẩm
    private void updateProductStatus(Scanner scanner) {
        System.out.println("Nhập mã sản phẩm muốn thay đổi trạng thái");
        String idProduct = scanner.nextLine();
        for (int i = 0; i < indexProduct; i++) {
            if (arrProduct[i].getProductId().equals(idProduct)){
                arrProduct[i].setStatus(!arrProduct[i].isStatus());
            }
        }
    }

    //     Bán sản phẩm
    private void sellProduct(Scanner scanner) {
        System.out.println("Nhập mã sản phẩm cần bán");
        String idSellProduct = scanner.nextLine();
        System.out.println("Nhập số lượng muốn bán");
        int quantitySell = Integer.parseInt(scanner.nextLine());
        boolean isExist = true;
        for (int i = 0; i < indexProduct; i++) {
            if (arrProduct[i].getProductId().equals(idSellProduct)) {
                if (arrProduct[i].getQuantity() < quantitySell) {
                    System.out.println("Số lượng muốn bán vượt quá số lượng đang tồn tại");
                    isExist = false;
                    break;
                } else {
                    arrProduct[i].setQuantity(arrProduct[i].getQuantity() - quantitySell);
                    isExist = false;
                    break;
                }
            }
        }
        if (isExist) {
            System.out.println("Sản phẩm không tồn tại");
        }
    }

    //Nhập sản phẩm
    private void insertProduct(Scanner scanner) {
        //Cho phép người dùng nhập vào mã sản phẩm và số lượng sản phẩm cần nhập
        System.out.println("Nhập mã sản phẩm muốn nhập");
        String idInsertProduct = scanner.nextLine();
        System.out.println("Nhập số lượng muốn nhập");
        int quantityInsert = Integer.parseInt(scanner.nextLine());
        boolean isExist = true;
        for (int i = 0; i < indexProduct; i++) {
            if (arrProduct[i].getProductId().equals(idInsertProduct)) {
                arrProduct[i].setQuantity(arrProduct[i].getQuantity() + quantityInsert);
                isExist = false;
                break;
            }
        }
        if (isExist) {
            System.out.println("Sản phẩm không tồn tại");
        }
    }


    //Sắp xếp các sản phẩm theo lợi nhuận giảm dần
    private void sortByProfit() {
        System.out.println("Danh sách sắp xếp các sản phẩm theo lợi nhuận giảm dần");
        for (int i = 0; i < indexProduct; i++) {
            for (int j = 0; j < indexProduct - i - 1; j++) {
                if (arrProduct[j + 1] == null) {
                    break;
                }
                if (arrProduct[j].calProfit() < arrProduct[j + 1].calProfit()) {
                    Product temp = arrProduct[j];
                    arrProduct[j] = arrProduct[j + 1];
                    arrProduct[j + 1] = temp;
                }
            }
        }
        displayAllItem();
    }

    //Tính lợi nhuận các sản phẩm
    private void productProfit() {
        System.out.println("Lợi nhuận các sản phẩm :");
        for (int i = 0; i < indexProduct; i++) {
            System.out.printf("Sản phẩm %s có lợi nhuận %f", arrProduct[i].getProductName(), arrProduct[i].calProfit());
        }
    }

    public void displayAllItem() {
        for (int i = 0; i < indexProduct; i++) {
            arrProduct[i].displayData();
        }
    }

    public void inputListProduct(Scanner scanner) {
        System.out.println("Nhập số sản phẩm cần nhập thông tin");
        int numberOfProduct = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < numberOfProduct; i++) {
            arrProduct[indexProduct] = new Product();
            arrProduct[indexProduct].inputData(scanner, arrProduct, indexProduct);
            indexProduct++;
        }
    }

    // Chức năng 5: Thống kê các sản phẩm theo giá
// Cho phép người dùng nhập vào khoảng giá cần thống kê sản phẩm (fromPrice - toPrice)
// Thống kê số lượng các sản phẩm có giá bán trong khoảng từ fromPrice đến toPrice
    public void productInRange(Scanner scanner) {
        System.out.println("Nhập giá trị của giá bắt đầu");
        float fromPrice = Float.parseFloat(scanner.nextLine());
        float toPrice;
        while (true) {
            System.out.println("Nhập giá trị của giá kết thúc");
            toPrice = Float.parseFloat(scanner.nextLine());
            if (toPrice <= fromPrice) {
                System.out.println("Giá trị của giá kết thúc phải lớn hơn giá bắt đầu");
            } else {
                break;
            }
        }
        System.out.println("Danh sách sản phẩm trong khoảng giá đó là :");
        boolean isExist = true;
        for (int i = 0; i < indexProduct; i++) {
            if (arrProduct[i].getExportPrice() >= fromPrice && arrProduct[i].getExportPrice() <= toPrice) {
                System.out.println(arrProduct[i].getProductName() + " có giá bán " + arrProduct[i].getExportPrice());
                isExist = false;
            }
        }
        if (isExist) {
            System.err.println("Không có sản phẩm nào tồn tại trong khoảng giá này");
        }
    }

    //Tìm các sản phẩm theo tên sản phẩm
    private void findProductByName(Scanner scanner) {
        System.out.println("Nhập tên sản phẩm cần tìm");
        String findNameProduct = scanner.nextLine().toLowerCase();
        boolean checkSearch = true;
        for (int i = 0; i < indexProduct; i++) {
            if (arrProduct[i].getProductName().contains(findNameProduct)) {
                System.out.print("Sản phẩm có tên trùng : ");
                System.out.println(arrProduct[i].getProductName());
                checkSearch = false;
            }
        }
        if (checkSearch) {
            System.err.println("Không có sản phẩm nào có tên trùng với tên đã nhập");
        }
    }
}
