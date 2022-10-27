package by.issoft.domain;

public class Product {
    private String name;
    private double rate;
    private double price;

    public Product(String name, double price, double rate) {
        this.name = name;
        this.price = price;
        this.rate = rate;
    }

    public String getName() {
        return name;
    }

    public double getRate() {
        return rate;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        String productInfo = String.format("Name: '%s', Price: '%s', Rate: '%s'", name, price, rate);
        return productInfo;
    }
}
