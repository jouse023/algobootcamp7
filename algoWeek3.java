/**
 * Construct Binary Tree from Inorder and Postorder Traversal
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        Map<Integer, Integer> indexMap = new HashMap<>();
        for(int i = 0; i < inorder.length; ++i) {
            indexMap.put(inorder[i], i);
        }
        return build(indexMap, inorder, 0, inorder.length-1, postorder, 0, postorder.length-1);
    }
    
    public TreeNode build(Map<Integer, Integer> indexMap, int[] inorder, int inStart, int inEnd, 
                          int[] postorder, int postStart, int postEnd) {
        if(inStart > inEnd) return null;
        TreeNode root = new TreeNode(postorder[postEnd]);
        int i = indexMap.get(postorder[postEnd]);
        int len = i - inStart;
        
        root.left = build(indexMap, inorder, inStart, i-1, postorder, postStart, postStart + len-1);                
        root.right = build(indexMap, inorder, i+1, inEnd, postorder, postStart+len, postEnd-1);
        
        return root;
    }
}


/**
* Course Schedule II
*
*
*/
class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        
        for (int i = 0; i < numCourses; ++i)
            graph.add(new ArrayList<Integer>());
        
        for (int i = 0; i < prerequisites.length; ++i) {
            int course = prerequisites[i][0];
            int prerequisite = prerequisites[i][1];            
            graph.get(course).add(prerequisite);
        }
        
        int[] visited = new int[numCourses];
        List<Integer> ans = new ArrayList<Integer>();
        Integer index = numCourses;
        for (int i = 0; i < numCourses; ++i)
            if (dfs(i, graph, visited, ans)) return new int[0];        
        
        return ans.stream().mapToInt(i->i).toArray();
    }
    
    private boolean dfs(int curr, ArrayList<ArrayList<Integer>> graph, int[] visited, List<Integer> ans) {
        if (visited[curr] == 1) return true;
        if (visited[curr] == 2) return false;
        
        visited[curr] = 1;
        for (int next : graph.get(curr))
            if (dfs(next, graph, visited, ans)) return true;
        
        visited[curr] = 2;
        ans.add(curr);
        
        return false;
    }    
}
