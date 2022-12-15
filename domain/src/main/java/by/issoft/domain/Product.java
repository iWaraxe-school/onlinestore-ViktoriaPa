package by.issoft.domain;

public class Product {
    private String name;
    private double price;
    private double rate;

    Product(ProductBuilder productBuilder) {
        this.name = productBuilder.getName();
        this.price = productBuilder.getPrice();
        this.rate = productBuilder.getRate();
    }

    public Product() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setRate(double rate) {
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
