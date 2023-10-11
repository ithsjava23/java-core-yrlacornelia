package org.example.warehouse;

import java.util.HashMap;
import java.util.Map;

public final class Category {
    private String name;

    private static Map<String, Category> categoryMap = new HashMap<>();

    private Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Category of(String name) {
        // Check if name is null
        if (name == null) {
            throw new IllegalArgumentException("Category name can't be null");
        }

        // Use the categoryMap to ensure the same instance is returned for the same name
        Category category = categoryMap.get(name);
        if (category == null) {
            String s1 = name.substring(0, 1).toUpperCase();
            String s2 = name.substring(1);
            category = new Category(s1 + s2);
            categoryMap.put(name, category);
        }
        return category;
    }


}

