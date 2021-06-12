package store.impl;

import domain.PriceElement;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import sources.PricingSource;
import sources.impl.SomePricingSource;
import store.LRUPricingStore;

import java.util.ArrayList;
import java.util.List;


public class LRUPricingStoreTest {

    private LRUPricingStore ps = new LRUPricingStore(6);
    private PricingSource<String, PriceElement> pricingSource = new SomePricingSource();

    @Before
    public void setup() {
        List<PriceElement> priceElements = new ArrayList<>();
        priceElements.add(pricingSource.get("AMD"));
        priceElements.add(pricingSource.get("AAPL"));
        priceElements.add(pricingSource.get("GOOG"));
        priceElements.add(pricingSource.get("AACG"));
        priceElements.add(pricingSource.get("JPM"));
        priceElements.add(pricingSource.get("FB"));
        priceElements.forEach(s -> ps.set(s));
    }

    @Test
    public void searchingForNonExistentKeyShouldThrow() {
        Assert.assertThrows(IllegalArgumentException.class, () -> ps.get("doiuhgf"));
    }

    @Test
    public void pricingSourceShouldReplaceFirstInsertedElementIfFullAndNoneAccessedSoFar() {
        ps.set(pricingSource.get("MS"));
        List<String> expectedKeys = new ArrayList<>();
        expectedKeys.add("FB");
        expectedKeys.add("AAPL");
        expectedKeys.add("GOOG");
        expectedKeys.add("AACG");
        expectedKeys.add("JPM");
        expectedKeys.add("MS");
        Assert.assertEquals(expectedKeys.size(), ps.getKeys().size());
        Assert.assertTrue(expectedKeys.containsAll(ps.getKeys()));
    }

    @Test
    public void checkPricingStoreRemovingLeastRecentlyUsed() {
        ps.get("FB");
        ps.get("FB");
        ps.get("FB");
        ps.get("GOOG");
        ps.get("AACG");
        ps.get("AACG");
        ps.get("JPM");
        ps.set(pricingSource.get("ADI"));
        List<String> expectedKeys = new ArrayList<>();
        expectedKeys.add("FB");
        expectedKeys.add("AAPL");
        expectedKeys.add("GOOG");
        expectedKeys.add("AACG");
        expectedKeys.add("JPM");
        expectedKeys.add("ADI");
        Assert.assertTrue(ps.getKeys().size() == 6);
        Assert.assertTrue(expectedKeys.containsAll(ps.getKeys()));
        Assert.assertFalse(ps.getKeys().contains("MS"));
    }

}
