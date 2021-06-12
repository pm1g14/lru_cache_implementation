package adapter;

import domain.PriceElement;
import dto.StockDto;

import java.util.Optional;

public class DTOAdapter {

    public PriceElement map(Optional<StockDto> stockDto) {
        StockDto dto = stockDto.orElse(new StockDto("", 0.0));
        return new PriceElement(
                dto.getStockName(),
                dto.getStockPrice()
        );
    }
}
