package dto;

public class StockDto {

    private String stockName;
    private Double stockPrice;

    public StockDto(String name, Double price) {
        this.stockName = name;
        this.stockPrice = price;
    }

    public Double getStockPrice() {
        return stockPrice;
    }

    public String getStockName() {
        return stockName;
    }
}
