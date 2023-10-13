package org.example.warehouse;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Warehouse {
    private String name;
    private ArrayList <ProductRecord> products;
    private ArrayList <ProductRecord> changedProducts;
    private Warehouse(String name) {
        this.name = name;
        this.products = new ArrayList<>();
        this.changedProducts = new ArrayList<>();

    }

    public static Warehouse getInstance(String warehouseName) {
        return new Warehouse(warehouseName);
    }

    public static Warehouse getInstance() {
        return new Warehouse("defaultWarehouse");
    }
//
    public boolean isEmpty() {
        return products.isEmpty();
    }
public List<ProductRecord> getProducts() {
    List<ProductRecord> list = new ArrayList<>(products);
    return Collections.unmodifiableList(list);
}


    public ProductRecord addProduct(UUID uuidMilk, String milk, Category dairy, BigDecimal bigDecimal) {
        if (milk == null || milk.isEmpty()) {
            throw new IllegalArgumentException("Product name can't be null or empty.");
        }
        if (dairy == null ) {
            throw new IllegalArgumentException("Category can't be null.");
        }
        if (bigDecimal == null) {
            bigDecimal = BigDecimal.ZERO;
        }

        if (uuidMilk == null) {
            uuidMilk = UUID.randomUUID();
        }
        Optional<ProductRecord> existingProduct = getProductById(uuidMilk);
        if (existingProduct.isPresent()) {
            throw new IllegalArgumentException("Product with that id already exists, use updateProduct for updates.");
        }
        ProductRecord product = new ProductRecord(uuidMilk, milk, dairy, bigDecimal);
        products.add(product);
        return product;
    }

    public Optional<ProductRecord> getProductById(UUID uuid) {
        return products.stream()
                .filter(product -> product.uuid().equals(uuid))
                .findFirst();

    }
   public List<ProductRecord> getProductsBy(Category category) {
       return products.stream()
               .filter(product -> Objects.equals(product.getCategory(), category))
               .collect(Collectors.toList());
   }

public Map<Category, List<ProductRecord>> getProductsGroupedByCategories() {
    return products.stream()
            .filter(product -> product.getCategory() != null)
            .collect(Collectors.groupingBy(ProductRecord::getCategory));
}


    public void updateProductPrice(UUID uuid, BigDecimal price) {
        boolean productFound = products.stream()
                .filter(product -> product.uuid().equals(uuid))
                .peek(product -> {
                    product.setPrice(price);
                    changedProducts.add(product);
                })
                .findFirst()
                .isPresent();

        if (!productFound) {
            throw new IllegalArgumentException("Product with that id doesn't exist.");
        }
    }
    public List<ProductRecord> getChangedProducts() {
        return changedProducts;
    }
}

