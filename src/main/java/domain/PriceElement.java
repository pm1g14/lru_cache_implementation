package domain;


import java.sql.Timestamp;

final public class PriceElement {

    private Timestamp lastAccessed;
    private String stockName;
    private Double stockValue;

    public PriceElement(String name, double value) {
        this.stockName = name;
        this.stockValue = value;
    }

    public Double getStockValue() {
        return stockValue;
    }

    public String getStockName() {
        return stockName;
    }

    public Timestamp getLastAccessed() {
        return lastAccessed;
    }

    public PriceElement updateLastAccessed() {
        this.lastAccessed = new Timestamp(System.currentTimeMillis());
        return this;
    }
}
