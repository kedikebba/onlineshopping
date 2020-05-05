package edu.miu.pm.onlineshopping.payment.paypal.model;

/*Author:
 * Addisu Marilign (Id 610135)*/
public class Order {

    private double totalPrice;
    private String currency;
    private String method;
    private String intent;
    private String description;

    public Order() {
        super();
        // TODO Auto-generated constructor stub
    }
    public Order(double totalPrice, String currency, String method, String intent, String description) {
        super();
        this.totalPrice = totalPrice;
        this.currency = currency;
        this.method = method;
        this.intent = intent;
        this.description = description;
    }
    public double getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
    public String getCurrency() {
        return currency;
    }
    public void setCurrency(String currency) {
        this.currency = currency;
    }
    public String getMethod() {
        return method;
    }
    public void setMethod(String method) {
        this.method = method;
    }
    public String getIntent() {
        return intent;
    }
    public void setIntent(String intent) {
        this.intent = intent;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
