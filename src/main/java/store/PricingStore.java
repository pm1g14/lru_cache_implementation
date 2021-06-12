package store;

public interface PricingStore<I, P> {

    P get(I identifier);
}
