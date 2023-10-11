package org.example.warehouse;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;
///change to record later if needed
public class ProductRecord {
    private final UUID uuid;
    private final String name;
    private final Category category;
    private BigDecimal price;

    public ProductRecord(UUID uuid, String name, Category category, BigDecimal price) {
        this.uuid = uuid;
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public UUID uuid() {
        return uuid;
    }

    public String name() {
        return name;
    }

    public Category category() {
        return category;
    }

    public BigDecimal price() {
        return price;
    }

    public void setPrice(BigDecimal newPrice) {
        this.price = newPrice;
    }

    public Category getCategory() {
        return category;
    }
}
