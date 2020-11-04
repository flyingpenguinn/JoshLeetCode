import base.ArrayUtils;

import java.util.*;

/*
LC#310
For an undirected graph with tree characteristics, we can choose any node as the root. The result graph is then a rooted tree. Among all possible rooted trees, those with minimum height are called minimum height trees (MHTs). Given such a graph, write a function to find all the MHTs and return a list of their root labels.

Format
The graph contains n nodes which are labeled from 0 to n - 1. You will be given the number n and a list of undirected edges (each edge is a pair of labels).

You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.

Example 1 :

Input: n = 4, edges = [[1, 0], [1, 2], [1, 3]]

        0
        |
        1
       / \
      2   3

Output: [1]
Example 2 :

Input: n = 6, edges = [[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]

     0  1  2
      \ | /
        3
        |
        4
        |
        5

Output: [3, 4]
Note:

According to the definition of tree on Wikipedia: “a tree is an undirected graph in which any two vertices are connected by exactly one path. In other words, any connected graph without simple cycles is a tree.”
The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf.
 */
public class MinHeightTree {
    // get one leaf by bfs on one random node
    // get the other end by bfs on that leaf above
    // during bfs we keep the parent link in an array so that we can back out the root quickly. no need to keep a list
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer>[] g = new ArrayList[n];
        for(int i=0; i<n; i++){
            g[i] = new ArrayList<>();
        }
        for(int[] e: edges){
            g[e[0]].add(e[1]);
            g[e[1]].add(e[0]);
        }
        // node and parent for circle avoidance
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{0, -1});
        int last = 0;
        while(!q.isEmpty()){
            int[] top = q.poll();
            last = top[0];
            for(int ne: g[top[0]]){
                if(ne != top[1]){
                    q.offer(new int[]{ne, top[0]});
                }
            }
        }
        q.clear();
        q.offer(new int[]{last, -1,0});
        int[] pre = new int[n];
        int first = last;
        int firstdist = 0;
        pre[last] = -1;
        while(!q.isEmpty()){
            int[] top = q.poll();
            first = top[0];
            firstdist = top[2];
            for(int ne: g[top[0]]){
                if(ne != top[1]){
                    pre[ne] = top[0];
                    q.offer(new int[]{ne, top[0], top[2]+1});
                }
            }
        }
        int t1 = firstdist/2;
        int t2 = (firstdist+1)/2;
        List<Integer> res = new ArrayList<>();
        int node = first;
        res.add(node);
        while(pre[node]!= -1){
            node = pre[node];
            res.add(node);
        }
        if(t1==t2){
            return List.of(res.get(t1));
        }else{
            return List.of(res.get(t1), res.get(t2));
        }
    }
}

class MinHeightTreeTopoSort {
    // keep killing leaves. the last round contains the solution
    // output the mid point, must be on path from last -> first, can't be arbitrarily dist/2 that would include wrong nodes
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        Set<Integer>[] g = new HashSet[n];
        List<Integer> res = new ArrayList<>();
        // leaves in each round
        for (int i = 0; i < n; i++) {
            g[i] = new HashSet<>();
        }
        for (int[] e : edges) {
            g[e[0]].add(e[1]);
            g[e[1]].add(e[0]);
        }
        for (int i = 0; i < n; i++) {
            if (g[i].size() <= 1) {
                res.add(i);
            }
        }
        int rem = n;
        // can't use res.size()>2. we must have processed at least n-2 elements!
        // note we iterate by generations! here generation matters as the last generation is the answer
        // if we dont iterate by generation we won't be able to tell which one in the last 2 is the answer, or both of them.
        // we will have to judge the last 2 by checking their height one by one
        while (rem > 2) {
            List<Integer> nextleaves = new ArrayList<>();
            for (int i = 0; i < res.size(); i++) {
                int j = res.get(i);
                for (int ne : g[j]) {
                    g[j].remove(ne);
                    g[ne].remove(j);
                    if (g[ne].size() == 1) {
                        nextleaves.add(ne);
                    }
                }
            }
            rem -= res.size();
            res = nextleaves;
        }
        return res;
    }
}