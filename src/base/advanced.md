| Pattern | Use |
|---|---|
| Point update, range query, cheap merge | Segment tree |
| Range update, range query, cheap merge/apply | Lazy segment tree |
| Range update, query scans boundary blocks, full-block contribution cheap | Sqrt decomposition |
| Static array, offline range query, merge hard, add/remove cheap | Mo |
| Static array, count value in [l,r] | value -> sorted positions + binary search |
| Static array, range sum/xor | Prefix |
| Static array, range min/max/gcd, no updates | Sparse table |
| One-shot kth in array | Quickselect |
| Kth/order statistic over changing set | Binary search answer + count/Fenwick/segment tree |
