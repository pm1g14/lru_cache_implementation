package sources;

public interface PricingSource<I, P> {

    P get(I identifier);
}
