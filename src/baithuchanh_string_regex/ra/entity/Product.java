package baithuchanh_string_regex.ra.entity;

import java.util.Scanner;

public class Product {
    private String productId;
    private String productName;
    private float importPrice;
    private float exportPrice;
    private float profit;
    private int quantity;
    private String descriptions;
    private boolean status;

    public Product() {
    }

    public Product(String productId, String productName, float importPrice, float exportPrice, float profit, int quantity, String descriptions, boolean status) {
        this.productId = productId;
        this.productName = productName;
        this.importPrice = importPrice;
        this.exportPrice = exportPrice;
        this.profit = profit;
        this.quantity = quantity;
        this.descriptions = descriptions;
        this.status = status;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(float importPrice) {
        this.importPrice = importPrice;
    }

    public float getExportPrice() {
        return exportPrice;
    }

    public void setExportPrice(float exportPrice) {
        this.exportPrice = exportPrice;
    }

    public float getProfit() {
        return profit;
    }

    public void setProfit(float profit) {
        this.profit = profit;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void inputData(Scanner scanner, Product[] arrProduct, int indexProduct) {
        this.productId = inputProductId(scanner, arrProduct, indexProduct);
        this.productName = inputProductName(scanner, arrProduct, indexProduct);
        this.importPrice = inputImportPrice(scanner);
        this.exportPrice = inputExportPrice(scanner);
        this.quantity = inputQuantity(scanner);
        this.descriptions = inputDescriptions(scanner);
        this.status = inputStatus(scanner);
    }

    private boolean inputStatus(Scanner scanner) {
        System.out.println("Nhập trạng thái của sản phẩm");
        while (true) {
            String status = scanner.nextLine();
            if (status == "true" || status == "false") {
                return Boolean.parseBoolean(status);
            } else {
                System.err.println("Trạng thái chỉ nhận 2 giá trị true , false");
            }
        }
    }

    private String inputDescriptions(Scanner scanner) {
        System.out.println("Nhập mô tả sản phẩm");
        while (true) {
            String descriptions = scanner.nextLine();
            if (descriptions.trim().isEmpty()) {
                System.err.println("Không được để trống mô tả");
            } else {
                return descriptions;
            }
        }
    }

    //Lợi nhuận tính theo công thức: profit = exportPrice – importPrice
    public float calProfit() {
        return this.exportPrice - this.importPrice;
    }


    //    Mã sản phẩm gồm 4 ký tự, bắt đầu là ký tự P,không được trùng lặp
    public String inputProductId(Scanner scanner, Product[] arrProduct, int indexProduct) {
        System.out.println("Nhập mã sản phẩm");
        do {
            String productId = scanner.nextLine();
            String idRegex = "^P.{3}$";
            if (productId.matches(idRegex)) {
                boolean isExist = false;
                for (int i = 0; i < indexProduct; i++) {
                    if (arrProduct[i].getProductId().equals(productId)) {
                        isExist = true;
                        break;
                    }
                }
                if (isExist) {
                    System.err.println("Mã sản phẩm đã tồn tại, vui lòng nhập lại");
                } else {
                    return productId;
                }
            } else {
                System.out.println("Nhập mã sản phẩm sai định dạng(P___)");
            }
        } while (true);
    }

    //    Tên sản phẩm có từ 6-50 ký tự, không trùng lặp
    private String inputProductName(Scanner scanner, Product[] arrProduct, int indexProduct) {
        System.out.println("Nhập tên sản phẩm");
        do {
            String productName = scanner.nextLine();
            if (productName.length() >= 6 && productName.length() <= 50) {
                boolean isExist = false;
                for (int i = 0; i < indexProduct; i++) {
                    if (arrProduct[i].getProductName().equals(productName)) {
                        isExist = true;
                        break;
                    }
                }
                if (isExist) {
                    System.err.println("Tên sản phẩm đã tồn tại, vui lòng nhập tên khác");
                } else {
                    return productName;
                }
            } else {
                System.err.println("Tên sản phẩm phải nằm trong khoảng 6-50 kí tự");
            }
        } while (true);
    }

    // Giá nhập có giá trị lớn hơn 0
    public float inputImportPrice(Scanner scanner) {
        System.out.println("Nhập giá nhập vào cuả sản phẩm");
        do {
            float importPrice = Float.parseFloat(scanner.nextLine());
            if (importPrice > 0) {
                return importPrice;
            } else {
                System.err.println("Giá nhập phải lớn hơn 0");
            }
        } while (true);
    }

    //     Giá xuất có giá trị lơn hơn ít nhất 20% so với giá nhập
    public float inputExportPrice(Scanner scanner) {
        System.out.println("Nhập giá xuất của sản phẩm");
        do {
            float exportPrice = Float.parseFloat(scanner.nextLine());
            if (exportPrice >= this.importPrice * 1.2) {
                return exportPrice;
            } else {
                System.err.println("Giá xuất phải lớn hơn ít nhất 20% so với giá nhập");
            }
        } while (true);
    }

    // Số lượng sản phẩm có giá trị lớn hơn 0
    public int inputQuantity(Scanner scanner) {
        System.out.println("Nhập số lượng sản phẩm");
        do {
            int quantity = Integer.parseInt(scanner.nextLine());
            if (quantity > 0) {
                return quantity;
            } else {
                System.out.println("Số lượng sản phẩm phải là số nguyên lớn hơn 0");
            }
        } while (true);
    }

    public void displayData() {
        System.out.printf("Mã sản phẩm : %s - Tên sản phẩm : %s - Giá nhập : %f - Giá xuất : %f\n", this.productId, this.productName, this.importPrice, this.exportPrice);
        System.out.printf("Lợi nhuận : %f - Số lượng : %d - Mô tả : %s - Trạng thái : %s", calProfit(), this.quantity, this.descriptions, this.status ? "Đang bán" : "Không bán");
    }
}
