package sources.impl;

import adapter.DTOAdapter;
import domain.PriceElement;
import dto.StockDto;
import sources.PricingSource;

import java.util.Optional;
import java.util.stream.Stream;

public class SomePricingSource implements PricingSource<String, PriceElement> {

    private DTOAdapter dtoAdapter = new DTOAdapter();

    private Stream<StockDto> getFromStream = Stream.of(
            new StockDto("AMD", 81.19),
            new StockDto("AAPL", 127.35),
            new StockDto("MS", 91.2),
            new StockDto("MSFT", 257.24),
            new StockDto("GOOG", 2521.60),
            new StockDto("ADBE", 514.68),
            new StockDto("ADI", 166.37),
            new StockDto("AACG", 3.190),
            new StockDto("AACQ", 9.990),
            new StockDto("FB", 331.26),
            new StockDto("JPM", 160.29),
            new StockDto("WMT", 140.75)
    );

    public PriceElement get(String identifier) {
        Optional<StockDto> stockDto = Stream.of(
                new StockDto("AMD", 81.19),
                new StockDto("AAPL", 127.35),
                new StockDto("MS", 91.2),
                new StockDto("MSFT", 257.24),
                new StockDto("GOOG", 2521.60),
                new StockDto("ADBE", 514.68),
                new StockDto("ADI", 166.37),
                new StockDto("AACG", 3.190),
                new StockDto("AACQ", 9.990),
                new StockDto("FB", 331.26),
                new StockDto("JPM", 160.29),
                new StockDto("WMT", 140.75)
        ).filter(s -> s.getStockName().equals(identifier)).findFirst();
        return dtoAdapter.map(stockDto);
    }
}
