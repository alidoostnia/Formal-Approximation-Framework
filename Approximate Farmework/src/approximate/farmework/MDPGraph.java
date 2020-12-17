package approximate.farmework;

import java.io.*; 
import java.util.*; 
import java.util.LinkedList; 


/**
 *
 * @author Mehran
 */
public class MDPGraph {
    
    
    private final int V;   // No. of vertices 
    private final LinkedList<Integer> adj[]; //Adjacency List 
    List<Object[]> trans = new ArrayList<>();//list of transitions with probabilities and actions
    List<int[]> scc = new ArrayList<>();
    private final int[] arr = new int[10];//Each SCC has minimum 1 and maximum 10 members
    int j=0;
    //dedicated for each transition info including source node, destination node, probability of transition and action
  
    //Constructor 
    MDPGraph(int v) 
    { 
        V = v; 
        adj = new LinkedList[v]; 
        for (int i=0; i<v; ++i) 
            adj[i] = new LinkedList(); 
    } 
  
    //Function to add an edge into the graph 
    void transition(int v, int w, double p, char a)  { 
        adj[v].add(w);//add the destination node to the adjacency linked list
        Object[] tmp = new Object[4];
        tmp[0] = v;
        tmp[1] = w;
        tmp[2] = p;
        tmp[3] = a;
        trans.add(tmp);   
    
    } 
  
    // A recursive function to print DFS starting from v 
    int [] DFSUtil(int v, boolean visited[]) 
    { 
        

        //for each SCC
        // Mark the current node as visited and print it 
        
        visited[v] = true; 
        System.out.print(v + " "); 
        arr[j]=v;
        //System.out.println("test"+j+arr[j]);
        j=j+1;
        
  
        int n; 
  
        // Recur for all the vertices adjacent to this vertex 
        Iterator<Integer> i =adj[v].iterator(); 
        if(!i.hasNext())
            j=0;
        while (i.hasNext()) 
        { 
            n = i.next(); 
            if (!visited[n]) 
            {
                DFSUtil(n,visited);            
                
            }
            else
            {
                j=0;
            }
            
        } 
        //System.out.println(arr[0]+arr[1]+arr[2]);
        
        return arr;
    } 
    

// Function that returns reverse (or transpose) of this graph 
    MDPGraph getTranspose() 
    { 
        MDPGraph g = new MDPGraph(V); 
        for (int v = 0; v < V; v++) 
        { 
            // Recur for all the vertices adjacent to this vertex 
            Iterator<Integer> i =adj[v].listIterator(); 
            while(i.hasNext()) 
                g.adj[i.next()].add(v); 
        } 
        return g; 
    } 
  
    void fillOrder(int v, boolean visited[], Stack stack) 
    { 
        // Mark the current node as visited and print it 
        visited[v] = true; 
  
        // Recur for all the vertices adjacent to this vertex 
        Iterator<Integer> i = adj[v].iterator(); 
        while (i.hasNext()) 
        { 
            int n = i.next(); 
            if(!visited[n]) 
                fillOrder(n, visited, stack); 
        } 
  
        // All vertices reachable from v are processed by now, 
        // push v to Stack 
        stack.push(new Integer(v)); 
    } 

    List<int[]> SCC() 
    { 
        Stack stack = new Stack(); 
  
        // Mark all the vertices as not visited (For first DFS) 
        boolean visited[] = new boolean[V]; 
        for(int i = 0; i < V; i++) 
            visited[i] = false; 
  
        // Fill vertices in stack according to their finishing times 
        for (int i = 0; i < V; i++) 
            if (visited[i] == false) 
                fillOrder(i, visited, stack); 
  
        // Create a reversed graph 
        MDPGraph gr = getTranspose(); 
        
  
        // Mark all the vertices as not visited (For second DFS) 
        for (int i = 0; i < V; i++) 
            visited[i] = false; 
  
        // Now process all vertices in order defined by Stack 
        while (stack.empty() == false) 
        { 
            // Pop a vertex from stack 
            int v = (int)stack.pop(); 
  
            // Print Strongly connected component of the popped vertex 
            if (visited[v] == false) 
            { 
                int [] te ;
                Arrays.fill(arr, 0);
                //System.out.println("before"+arr[0]+" "+arr[1]+" "+arr[2]);

                te=gr.DFSUtil(v, visited);
               // System.out.println("\n after"+arr[0]+" "+arr[1]+" "+arr[2]+" "+arr[3]+" "+arr[4]+" "+arr[5]+" "+arr[6]+" "+arr[7]+" "+arr[8]);
                //System.out.println("\n after"+te[0]+" "+te[1]+" "+te[2]);

                scc.add(te.clone());
                Arrays.fill(te, 0);
                System.out.println(); 
            } 
        } 
        return scc;
    }
    
    void TransPrint(){
        System.out.println("The transitions are as follows:");
        for(int i = 0; i < trans.size(); i++)
        {
        System.out.println(trans.get(i)[0].toString() +"\t"+ trans.get(i)[1].toString()+"\t"+ trans.get(i)[2].toString()+"\t"+ trans.get(i)[3].toString());
        }
    }
    
    void SCCPrint(){
        System.out.println("The ArrayList of the components are as follows:");
        for(int i = 0; i < scc.size(); i++) 
        {
        System.out.println(scc.get(i)[0] + "\t"+ scc.get(i)[1]+"\t"+ scc.get(i)[2]+"\t"+ scc.get(i)[3]+"\t"+ scc.get(i)[4]);
        }
    }
    }
