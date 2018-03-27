package com.dalvinlabs.recyclerviewmultiselect;

import java.util.ArrayList;
import java.util.List;

public class Product {

  public final String name;
  public final String size;
  public final String color;
  public final String price;

  public Product(String name, String size, String color, String price) {
    this.name = name;
    this.size = size;
    this.color = color;
    this.price = price;
  }

  public static List<Product> getProducts() {
    List<Product> list = new ArrayList<>();
    list.add(
        new Product("These are good socks", "This is small size", "This is a red color", "$10"));
    list.add(new Product("These are good shirts", "This is medium size", "This is a green color",
        "$20"));
    list.add(
        new Product("These are good shoes", "This is large size", "This is a blue color", "$30"));
    list.add(
        new Product("These are good pants", "This is large size", "This is a black color", "$40"));
    list.add(
        new Product("These are good socks", "This is small size", "This is a red color", "$10"));
    list.add(new Product("These are good shirts", "This is medium size", "This is a green color",
        "$20"));
    list.add(
        new Product("These are good shoes", "This is large size", "This is a blue color", "$30"));
    list.add(
        new Product("These are good pants", "This is large size", "This is a black color", "$40"));

    return list;
  }
}
