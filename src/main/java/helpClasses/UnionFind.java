package helpClasses;

import java.util.HashMap;
import java.util.Map;

public class UnionFind {
    private final Map<Integer, Integer> parents;
    private final Map<Integer, Integer> ranks;

    public UnionFind(int n) {
        parents = new HashMap<>();
        ranks = new HashMap<>();
        for (int i = 0; i < n; i++) {
            parents.put(i, i);
            ranks.put(i, 0);
        }
    }

    public Integer find(Integer u) {
        while (!u.equals(parents.get(u))) {
            u = parents.get(u);
        }
        return u;
    }

    public void union(Integer u, Integer v) {
        Integer uParent = find(u);
        Integer vParent = find(v);
        if (uParent.equals(vParent)) {
            return;
        }

        if (ranks.get(uParent) < ranks.get(vParent)) {
            parents.put(uParent, vParent);
        } else if (ranks.get(uParent) > ranks.get(vParent)) {
            parents.put(vParent, uParent);
        } else {
            parents.put(vParent, uParent);
            ranks.put(uParent, ranks.get(uParent) + 1);
        }
    }
}

