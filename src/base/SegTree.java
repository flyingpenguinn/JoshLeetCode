package base;

public class SegTree {
    int[] t;
    int n = 0;

    public SegTree(int[] a) {
        this.n = a.length;
        t = new int[4 * n + 1];
        build(a, 1, 0, a.length - 1);
    }

    private void build(int[] a, int idx, int l, int u) {
        if (l == u) {
            t[idx] = a[l];
            return;
        }
        int mid = l + (u - l) / 2;
        int left = 2 * idx;
        int right = 2 * idx + 1;
        build(a, left, l, mid);
        build(a, right, mid + 1, u);
        t[idx] = Math.max(t[left], t[right]);
    }

    public void updateValue(int pos, int nv) {
        update(1, 0, n - 1, pos, nv);
    }

    public void update(int idx, int l, int u, int pos, int nv) {
        if (l == u) {
            t[idx] = nv;
            return;
        }
        int mid = l + (u - l) / 2;
        if (pos <= mid) {
            update(2 * idx, l, mid, pos, nv);
        } else {
            update(2 * idx + 1, mid + 1, u, pos, nv);
        }
        t[idx] = Math.max(t[2 * idx], t[2 * idx + 1]);
    }


    private int lookup(int v) {
        return lookuptree(1, 0, n - 1, v);
    }

    private int lookuptree(int idx, int l, int u, int v) {
        if (t[idx] < v) {
            return -1;
        }
        if (l == u) {
            return t[idx] >= v ? l : -1;
        }
        int mid = l + (u - l) / 2;
        int left = lookuptree(2 * idx, l, mid, v);
        if (left != -1) {
            return left;
        }
        int right = lookuptree(2 * idx + 1, mid + 1, u, v);
        return right;
    }
}


