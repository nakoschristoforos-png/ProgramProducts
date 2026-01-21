package soa;

import soa.dto.ProductInsertDTO;
import soa.dto.ProductReadOnlyDTO;
import soa.service.IProductService;
import soa.service.ProductServiceImpl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static IProductService productService = new ProductServiceImpl();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            showMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            try {
                switch (choice) {
                    case 1 -> addProduct();
                    case 2 -> viewProduct();
                    case 3 -> viewAllProducts();
                    case 4 -> updateStock();
                    case 5 -> deleteProduct();
                    case 6 -> {
                        System.out.println("Goodbye!");
                        System.exit(0);
                    }
                    default -> System.out.println("Invalid choice!");
                }
            } catch (Exception e) {
                System.out.println("❌ Error: " + e.getMessage());
            }
        }
    }

    private static void showMenu() {
        System.out.println("\n========== PRODUCT MANAGEMENT ==========");
        System.out.println("1. Add Product");
        System.out.println("2. View Product");
        System.out.println("3. View All Products");
        System.out.println("4. Update Stock");
        System.out.println("5. Delete Product");
        System.out.println("6. Exit");
        System.out.print("Choose option: ");
    }

    private static void addProduct() throws Exception {
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();

        System.out.print("Enter price: ");
        BigDecimal price = scanner.nextBigDecimal();

        System.out.print("Enter stock quantity: ");
        int stock = scanner.nextInt();
        scanner.nextLine(); // consume newline

        ProductInsertDTO dto = new ProductInsertDTO(name, price, stock);
        productService.addProduct(dto);

        System.out.println("✅ Product added successfully!");
    }

    private static void viewProduct() throws Exception {
        System.out.print("Enter product ID: ");
        Long id = scanner.nextLong();
        scanner.nextLine(); // consume newline

        ProductReadOnlyDTO product = productService.getProduct(id);
        displayProduct(product);
    }

    private static void viewAllProducts() throws Exception {
        List<ProductReadOnlyDTO> products = productService.getAllProducts();

        if (products.isEmpty()) {
            System.out.println("No products found.");
            return;
        }

        System.out.println("\n========== ALL PRODUCTS ==========");
        for (ProductReadOnlyDTO product : products) {
            displayProduct(product);
            System.out.println("-----------------------------------");
        }
    }

    private static void updateStock() throws Exception {
        System.out.print("Enter product ID: ");
        Long id = scanner.nextLong();

        System.out.print("Enter quantity to add/remove (+/-): ");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // consume newline

        productService.updateStock(id, quantity);
        System.out.println("✅ Stock updated successfully!");
    }

    private static void deleteProduct() throws Exception {
        System.out.print("Enter product ID: ");
        Long id = scanner.nextLong();
        scanner.nextLine(); // consume newline

        productService.deleteProduct(id);
        System.out.println("✅ Product deleted successfully!");
    }

    private static void displayProduct(ProductReadOnlyDTO product) {
        System.out.println("\nID: " + product.id());
        System.out.println("Name: " + product.name());
        System.out.println("Price: €" + product.price());
        System.out.println("Stock: " + product.stock());
    }
}



