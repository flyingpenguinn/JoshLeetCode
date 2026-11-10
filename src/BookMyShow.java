import java.util.Arrays;

// LC 2286. seg tree over free seats per row: max for gather (first row with >= k), sum for scatter (prefix descent)
// `first` = first row with free seats; each row becomes full once, so scatter is amortized O((n + q) log n)
class BookMyShow {
        class Node {
            int l, r;
            long min, max, sum;

            Node(int l, int r, long min, long max, long sum) {
                this.l = l;
                this.r = r;
                this.min = min;
                this.max = max;
                this.sum = sum;
            }

            int len() {
                return r - l + 1;
            }
        }

        class SegTree {
            Node[] tree;
            long[] lazy;
            int n;

            SegTree(int[] a) {
                this.n = a.length;
                this.tree = new Node[4 * n + 5];
                build(1, 0, n - 1, a);
            }

            private void build(int idx, int l, int r, int[] a) {
                if (l == r) {
                    long v = a[l];
                    tree[idx] = new Node(l, r, v, v, v);
                    return;
                }

                int mid = l + (r - l) / 2;
                build(idx * 2, l, mid, a);
                build(idx * 2 + 1, mid + 1, r, a);

                tree[idx] = merge(tree[idx * 2], tree[idx * 2 + 1]);
            }

            private Node merge(Node left, Node right) {
                return new Node(
                        left.l,
                        right.r,
                        Math.min(left.min, right.min),
                        Math.max(left.max, right.max),
                        left.sum + right.sum
                );
            }

            private void apply(int idx, long delta) {
                Node cur = tree[idx];

                cur.min += delta;
                cur.max += delta;
                cur.sum += delta * cur.len();
            }


            public void pointAdd(int q, long delta) {
                rangeAdd(1, q, q, delta);
            }


            private void rangeAdd(int idx, int ql, int qr, long delta) {
                Node cur = tree[idx];

                if (qr < cur.l || cur.r < ql) {
                    return;
                }

                if (ql <= cur.l && cur.r <= qr) {
                    apply(idx, delta);
                    return;
                }

                rangeAdd(idx * 2, ql, qr, delta);
                rangeAdd(idx * 2 + 1, ql, qr, delta);

                tree[idx] = merge(tree[idx * 2], tree[idx * 2 + 1]);
            }

            public Node query(int ql, int qr) {
                return query(1, ql, qr);
            }

            private Node query(int idx, int ql, int qr) {
                Node cur = tree[idx];

                if (qr < cur.l || cur.r < ql) {
                    return null;
                }

                if (ql <= cur.l && cur.r <= qr) {
                    return cur;
                }
                Node left = query(idx * 2, ql, qr);
                Node right = query(idx * 2 + 1, ql, qr);

                if (left == null) {
                    return right;
                }
                if (right == null) {
                    return left;
                }
                return merge(left, right);
            }


            private Node find(int idx, int ql, int qr, long t) {
                Node cur = tree[idx];
                if (cur.sum < t) {
                    return null;
                }
                
                if (qr < cur.l || cur.r < ql) {
                    return null;
                }
                if (tree[idx].len() == 1) {
                    return tree[idx];
                }

                int left = idx * 2;
                int right = idx * 2 + 1;
                if (tree[left].sum >= t) {
                    return find(left, ql, qr, t);
                } else {
                    return find(right, ql, qr, t - tree[left].sum);
                }
            }

            private Node findMax(int idx, int ql, int qr, long t) {
                Node cur = tree[idx];
                if (cur.max < t) {
                    return null;
                }
                if (qr < cur.l || cur.r < ql) {
                    return null;
                }

                if (tree[idx].len() == 1) {
                    return tree[idx];
                }

                int left = idx * 2;
                int right = idx * 2 + 1;
                if (tree[left].max >= t) {
                    return findMax(left, ql, qr, t);
                } else {
                    return findMax(right, ql, qr, t);
                }
            }
        }

        private int first = 0;
        private int[] a;
        private int m;
        private int n ;
        private SegTree seg;

        public BookMyShow(int n, int m) {
            a = new int[n];
            Arrays.fill(a, m);
            seg = new SegTree(a);
            this.m = m;
            this.n = n;
        }

        public int[] gather(int k, int maxRow) {
            Node found = seg.findMax(1, first, maxRow, k);
            if (found == null) {
                return new int[0];
            }
            int index = found.l;
            int[] rt = new int[]{index, (int) ((long) (m) - found.sum)};
            a[index] -= k;
            seg.pointAdd(index, -k);
            while(first < n && a[first]==0){
                ++first;
            }
            return rt;
        }

        public boolean scatter(int k, int maxRow) {
            Node found = seg.find(1, first, maxRow, k);
            if (found == null) {
                return false;
            }
            for (int i = first; i <= found.r && k > 0; ++i) {
                int taken = Math.min(a[i], k);
                a[i] -= taken;
                k -= taken;
                seg.pointAdd(i, -taken);
            }
            while(first < n && a[first]==0){
                ++first;
            }
            return true;
        }
    }
