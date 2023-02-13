package me.suryaakasam.entities;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.StringJoiner;

@Document("Orders")
public class Order {
    @Id
    private String orderId;
    private String segment;
    private String productId;
    private float sales;
    private float profit;

    public Order() {
    }

    public Order(String orderId, String segment, String productId, float sales, float profit) {
        this.orderId = orderId;
        this.segment = segment;
        this.productId = productId;
        this.sales = sales;
        this.profit = profit;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getSegment() {
        return segment;
    }

    public void setSegment(String segment) {
        this.segment = segment;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public float getSales() {
        return sales;
    }

    public void setSales(float sales) {
        this.sales = sales;
    }

    public float getProfit() {
        return profit;
    }

    public void setProfit(float profit) {
        this.profit = profit;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Order.class.getSimpleName() + "[", "]")
                .add("orderId='" + orderId + "'")
                .add("segment='" + segment + "'")
                .add("productId='" + productId + "'")
                .add("sales=" + sales)
                .add("profit=" + profit)
                .toString();
    }
}
