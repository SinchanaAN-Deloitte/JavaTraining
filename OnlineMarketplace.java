/*Online Marketplace You are building an online marketplace where users can buy and
        sell products. Your application should allow users to browse products, add products to a
        shopping cart, and place orders.Design a class hierarchy for the application. Your
        class hierarchy should include a Product class, a User class, a Seller class, a Customer class,
         a ShoppingCart class, and an Order class.
        The Product class should have properties such as name, description, price, and seller.
        The User class should have properties such as name and email.
        The Seller class should inherit from the User class and should have additional properties
        such as the list of products that they are selling.
        The Customer class should inherit from the User class and should have additional properties
        such as the list of orders they have placed.
        The ShoppingCart class should have properties such as the list of products that have been
        added to the cart.
        The Order class should have properties such as the list of products that were ordered, the
        customer who placed the order, and the date the order was placed.
        In addition to the class hierarchy, implement the following methods in the Seller class:
        • addProduct(Product product) - adds a product to the seller's list of products.
        • removeProduct(Product product) - removes a product from the seller's list of
        products.
        • viewProducts() - displays a list of the products that the seller is selling.
        Implement the following methods in the Customer class:
        • addToCart(Product product, int quantity) - adds a product to the customer's
        shopping cart with the specified quantity.*/

import java.util.*;
class Product {
    private String name;
    private String description;
    private double price;
    private Seller seller;

    public Product(String name, String description, double price, Seller seller) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.seller = seller;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public Seller getSeller() {
        return seller;
    }
}

class User {
    private String name;
    private String email;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}

class Seller extends User {
    private List<Product> products;

    public Seller(String name, String email) {
        super(name, email);
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

    public void viewProducts() {
        if (products.isEmpty()) {
            System.out.println("You are not selling any products.");
        } else {
            System.out.println("Products you are selling:");
            for (Product product : products) {
                System.out.println(product.getName() + " - " + product.getDescription() + " - Price: " + product.getPrice());
            }
        }
    }

    public List<Product> getProducts() {
        return products;
    }
}

class Customer extends User {
    private List<Order> orders;
    private ShoppingCart shoppingCart;

    public Customer(String name, String email) {
        super(name, email);
        this.orders = new ArrayList<>();
        this.shoppingCart = new ShoppingCart();
    }

    public void addToCart(Product product, int quantity) {
        shoppingCart.addProduct(product, quantity);
    }

    public void removeFromCart(Product product, int quantity) {
        shoppingCart.removeProduct(product, quantity);
    }

    public void viewCart() {
        shoppingCart.viewCart();
    }

    public void placeOrder() {
        if (shoppingCart.isEmpty()) {
            System.out.println("Your shopping cart is empty. Add products before placing an order.");
            return;
        }

        Order order = new Order(shoppingCart.getProducts(), this);
        orders.add(order);
        shoppingCart.clearCart();

        System.out.println("Order placed successfully. Thank you for your purchase!");
        System.out.println(order.toString());
    }

    public List<Order> getOrders() {
        return orders;
    }
}

class ShoppingCart {
    private Map<Product, Integer> products;

    public ShoppingCart() {
        this.products = new HashMap<>();
    }

    public void addProduct(Product product, int quantity) {
        int currentQuantity = products.getOrDefault(product, 0);
        products.put(product, currentQuantity + quantity);
        System.out.println(quantity + " " + product.getName() + "(s) added to your shopping cart.");
    }

    public void removeProduct(Product product, int quantity) {
        int currentQuantity = products.getOrDefault(product, 0);
        if (quantity >= currentQuantity) {
            products.remove(product);
            System.out.println(product.getName() + " removed from your shopping cart.");
        } else {
            products.put(product, currentQuantity - quantity);
            System.out.println(quantity + " " + product.getName() + "(s) removed from your shopping cart.");
        }
    }

    public void viewCart() {
        if (products.isEmpty()) {
            System.out.println("Your shopping cart is empty.");
        } else {
            System.out.println("Products in your shopping cart:");
            for (Map.Entry<Product, Integer> entry : products.entrySet()) {
                Product product = entry.getKey();
                int quantity = entry.getValue();
                System.out.println(product.getName() + " - " + product.getDescription() + " - Price: " + product.getPrice() + " - Quantity: " + quantity);
            }
        }
    }

    public List<Product> getProducts() {
        List<Product> productList = new ArrayList<>();
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            for (int i = 0; i < quantity; i++) {
                productList.add(product);
            }
        }
        return productList;
    }

    public boolean isEmpty() {
        return products.isEmpty();
    }

    public void clearCart() {
        products.clear();
    }
}

class Order {
    private List<Product> products;
    private Customer customer;
    private Date orderDate;

    public Order(List<Product> products, Customer customer) {
        this.products = products;
        this.customer = customer;
        this.orderDate = new Date();
    }

    public double getTotal() {
        double total = 0;
        for (Product product : products) {
            total += product.getPrice();
        }
        return total;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order Date: ").append(orderDate).append("\n");
        sb.append("Customer: ").append(customer.getName()).append(" (").append(customer.getEmail()).append(")").append("\n");
        sb.append("Products:\n");
        for (Product product : products) {
            sb.append(product.getName()).append(" - ").append(product.getDescription()).append(" - Price: ").append(product.getPrice()).append("\n");
        }
        sb.append("Total: ").append(getTotal());
        return sb.toString();
    }
}

public class OnlineMarketplace {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Product> marketplaceProducts = new ArrayList<>(); // Maintains the list of all products in the marketplace

        // Create a Seller
        System.out.print("Enter seller's name: ");
        String sellerName = scanner.nextLine();
        System.out.print("Enter seller's email: ");
        String sellerEmail = scanner.nextLine();
        Seller seller = new Seller(sellerName, sellerEmail);

        // Create a Customer
        System.out.print("Enter customer's name: ");
        String customerName = scanner.nextLine();
        System.out.print("Enter customer's email: ");
        String customerEmail = scanner.nextLine();
        Customer customer = new Customer(customerName, customerEmail);

        // Add products for the Seller
        System.out.print("Enter the number of products the seller wants to add: ");
        int numProducts = scanner.nextInt();
        scanner.nextLine();
        //Details of the product
        for (int i = 1; i <= numProducts; i++) {
            System.out.println("Enter details for product " + i);
            System.out.print("Product name: ");
            String productName = scanner.nextLine();
            System.out.print("Product description: ");
            String productDescription = scanner.nextLine();
            System.out.print("Product price: ");
            double productPrice = scanner.nextDouble();
            scanner.nextLine();

            Product product = new Product(productName, productDescription, productPrice, seller);
            seller.addProduct(product);
            marketplaceProducts.add(product);
        }

        // Different actions for the Customer
        while (true) {
            System.out.println("\nWhat would you like to do?");
            System.out.println("1. View products for sale");
            System.out.println("2. Add product to cart");
            System.out.println("3. Remove product from cart");
            System.out.println("4. View cart");
            System.out.println("5. Place order");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    seller.viewProducts();
                    break;
                case 2:
                    System.out.print("Enter the name of the product to add: ");
                    String productName = scanner.nextLine();
                    System.out.print("Enter the quantity: ");
                    int quantity = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character

                    // Find the product with the given name (case-insensitive)
                    Product selectedProduct = null;
                    for (Product product : marketplaceProducts) {
                        if (product.getName().equalsIgnoreCase(productName)) {
                            selectedProduct = product;
                            break;
                        }
                    }

                    if (selectedProduct != null) {
                        customer.addToCart(selectedProduct, quantity);
                    } else {
                        System.out.println("Product not found.");
                    }
                    break;
                case 3:
                    System.out.print("Enter the name of the product to remove: ");
                    productName = scanner.nextLine();
                    System.out.print("Enter the quantity: ");
                    quantity = scanner.nextInt();
                    scanner.nextLine();
                    // Find the product with the given name
                    selectedProduct = null;
                    for (Product product : marketplaceProducts) {
                        if (product.getName().equalsIgnoreCase(productName)) {
                            selectedProduct = product;
                            break;
                        }
                    }

                    if (selectedProduct != null) {
                        customer.removeFromCart(selectedProduct, quantity);
                    } else {
                        System.out.println("Product not found.");
                    }
                    break;
                case 4:
                    customer.viewCart();
                    break;
                case 5:
                    customer.placeOrder();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
