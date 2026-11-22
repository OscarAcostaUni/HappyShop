package ci553.happyshop.catalogue;

import java.util.ArrayList;
import java.util.Comparator;

public class Trolley {

    private final ArrayList<Product> products = new ArrayList<>();

    /**
     * Adds a product to the trolley.
     * If the product already exists in the trolley, the quantity will increase
     * Otherwise, a new product is added.
     * Products are sorted by ID in ascending order
     * @param product is the product to add
     * @throws IllegalArgumentException if the ordered quantity is zero or below (negative)
     */
    public void addProduct(Product product) {
        if (product.getOrderedQuantity() <= 0) {
            throw new IllegalArgumentException("Ordered quantity must be positive");
        }

        for (Product existing : products) {
            if (existing.getProductId().equals(product.getProductId())) {
                existing.setOrderedQuantity(
                        existing.getOrderedQuantity() + product.getOrderedQuantity()
                );
                return;
            }
        }

        Product copy = new Product(
                product.getProductId(),
                product.getProductDescription(),
                product.getProductImageName(),
                product.getUnitPrice(),
                product.getOrderedQuantity(),
                product.getStockQuantity()
        );

        products.add(copy);
        products.sort(Comparator.comparing(Product::getProductId));
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void clear() {
        products.clear();
    }
}
