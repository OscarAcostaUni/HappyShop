
package ci553.happyshop.catalogue;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TrolleyTest {

    @Test
    void addingOneProductToEmptyTrolley() {
        // Arrange
        Trolley trolley = new Trolley();
        Product toaster = new Product(
                "0003", "Toaster", "0003.jpg", 19.99, 10
        );

        // Act
        trolley.addProduct(toaster);

        // Assert
        assertEquals(1, trolley.getProducts().size());
        assertEquals("0003", trolley.getProducts().get(0).getProductId());
        assertEquals(1, trolley.getProducts().get(0).getOrderedQuantity());
    }


    @Test
    void addingSameProductTwiceCombinesQuantities() {
        // Arrange
        Trolley trolley = new Trolley();
        Product toaster = new Product(
                "0003", "Toaster", "0003.jpg", 19.99, 10
        );

        // Act
        trolley.addProduct(toaster);
        trolley.addProduct(toaster);

        // Assert
        assertEquals(1, trolley.getProducts().size());
        assertEquals(2, trolley.getProducts().get(0).getOrderedQuantity());
    }

    @Test
    void productsAreSortedById() {
        Trolley trolley = new Trolley();

        Product toaster = new Product("0003", "Toaster", "0003.jpg", 19.99, 10);
        Product kettle = new Product("0001", "Kettle", "0001.jpg", 14.99, 10);
        Product microwave = new Product("0002", "Microwave", "0002.jpg", 49.99, 10);

        trolley.addProduct(toaster);
        trolley.addProduct(kettle);
        trolley.addProduct(microwave);

        assertEquals("0001", trolley.getProducts().get(0).getProductId());
        assertEquals("0002", trolley.getProducts().get(1).getProductId());
        assertEquals("0003", trolley.getProducts().get(2).getProductId());
    }

    @Test
    void addingDuplicateAfterSortingCombinesQuantities() {
        Trolley trolley = new Trolley();

        Product toaster = new Product("0003", "Toaster", "0003.jpg", 19.99, 10);
        Product kettle = new Product("0001", "Kettle", "0001.jpg", 14.99, 10);
        Product microwave = new Product("0002", "Microwave", "0002.jpg", 49.99, 10);

        trolley.addProduct(toaster);
        trolley.addProduct(kettle);
        trolley.addProduct(microwave);
        trolley.addProduct(toaster);

        assertEquals(3, trolley.getProducts().size());

        assertEquals("0001", trolley.getProducts().get(0).getProductId());
        assertEquals("0002", trolley.getProducts().get(1).getProductId());
        assertEquals("0003", trolley.getProducts().get(2).getProductId());

        assertEquals(2, trolley.getProducts().get(2).getOrderedQuantity());
    }

    @Test
    void addingProductDoesNotModifyOriginal() {
        Trolley trolley = new Trolley();
        Product toaster = new Product("0003", "Toaster", "0003.jpg", 19.99, 10);

        trolley.addProduct(toaster);
        trolley.addProduct(toaster);

        assertEquals(1, toaster.getOrderedQuantity());
        assertEquals(2, trolley.getProducts().get(0).getOrderedQuantity());
    }

    @Test
    void addingProductWithZeroQuantityThrowsException() {
        Trolley trolley = new Trolley();

        Product toaster = new Product(
                "0003", "Toaster", "0003.jpg", 19.99, 0, 10
        );

        assertThrows(IllegalArgumentException.class, () -> {
            trolley.addProduct(toaster);
        });
    }

    @Test
    void addingProductWithNegativeQuantityThrowsException() {
        Trolley trolley = new Trolley();

        Product toaster = new Product(
                "0003", "Toaster", "0003.jpg", 19.99, -2, 10
        );

        assertThrows(IllegalArgumentException.class, () -> {
            trolley.addProduct(toaster);
        });
    }

}