# mosaic_code_challenge

#ASSUMPTIONS:
#1. If none of the elements in the cache are accessed and new ones are inserted
#   while capacity is already full, then the first elements will be replaced.
#
#2. If an invalid element is given for lookup in cache, an IllegalArgumentException is thrown.
#3. The current implementation assumes prices come from an external source as String, StockDTO, a data transfer object.
#   It's also assumed that the key of the cache is the name of the stock in string format.

#COMMENTS:
# In this implementation, the least recently used value is determined by the oldest timestamp compared to now.
# The LRUMap of apache commons could be used to implement the cache if external libraries were allowed.
# More test cases should be added to check edge cases, base case works.
# For strict immutability we should provide an alternative to update the timestamp of each object in cache.
# More checks around the pricing source, can be added, current implementation allows "", 0.0 to be inserted in cache.