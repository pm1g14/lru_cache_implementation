package store;

import domain.PriceElement;

import java.sql.Timestamp;
import java.util.*;

public class LRUPricingStore implements PricingStore<String, PriceElement> {

    private LinkedHashMap<String, PriceElement> map = new LinkedHashMap<>();
    private int size;

    public LRUPricingStore(int size) {
        this.size = size;
    }

    @Override
    public PriceElement get(String identifier) {
        if (map.containsKey(identifier)) {
            PriceElement value = map.get(identifier).updateLastAccessed();
            map.put(identifier, value);
            return map.get(identifier);
        } else {
            throw new IllegalArgumentException("Stock with identifier "+identifier+" not found in cache");
        }
    }

    public void set(PriceElement element) {

        if (map.size() < size) {
            map.put(element.getStockName(), element);
        } else {
            removeLeastRecentlyUsedAndUpdate(element);
        }
    }

    private void removeLeastRecentlyUsedAndUpdate(PriceElement element) {
        Timestamp now = new Timestamp(System.currentTimeMillis());

        Iterator it = map.entrySet().iterator();
        int index = 0;
        int maxSoFar = 0;
        int maxIndex = 0;
        while(it.hasNext()) {
            Map.Entry<String, PriceElement> mapElement = (Map.Entry)it.next();
            Optional<Timestamp> tmsmp = Optional.ofNullable(mapElement.getValue().getLastAccessed());
            int diff = now.compareTo(tmsmp.orElse(new Timestamp(0)));
            if (diff > maxSoFar) {
                maxSoFar = diff;
                maxIndex = index;
            }
            ++index;
        }
        String keyLeastAccessible = new LinkedList<>(map.keySet()).get(maxIndex);
        map.remove(keyLeastAccessible);
        map.put(element.getStockName(), element);

    }

    public Set<String> getKeys() {
        return map.keySet();
    }
}
