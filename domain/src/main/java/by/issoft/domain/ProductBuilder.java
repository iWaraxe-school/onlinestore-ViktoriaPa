package by.issoft.domain;

public class ProductBuilder {
    private String name;
    private double price;
    private double rate;


    public ProductBuilder name(String name){
        this.name = name;
        return this;
    }
    public ProductBuilder price(double price){
        this.price = price;
        return this;
    }
    public ProductBuilder rate(double rate){
        this.rate = rate;
        return this;
    }
    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
    public double getRate() {
        return rate;
    }
    public Product build(){
        return new Product(this);
    }
}
