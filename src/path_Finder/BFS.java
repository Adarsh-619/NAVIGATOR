package path_Finder;

/**
 * @author Adarsh
 * Algorithm Design 1 Project
 * 
 */

import java.util.ArrayDeque;
import structure.Graph;
import structure.Vertex;

public class BFS {
	
    public ArrayDeque<Vertex> add(Graph g, Vertex v, ArrayDeque<Vertex> q){
        for(int i=0; i<g.getAdjList().length; i++){
            if(g.getAdjList()[i] != null) {
                if (g.getAdjList()[i].name.equalsIgnoreCase(v.name)) {
                    q.add(g.getAdjList()[i]);
                    //System.out.println(adjList[i].friendsList.toString());
                }
            }
        }
        return q;
    }
    
    public void Bfs(Graph g, Vertex src, Vertex des){
        ArrayDeque<Vertex> q = new ArrayDeque<Vertex>();
        boolean[] visited = new boolean[g.getAdjList().length];
        Vertex[] prev = new Vertex[g.getAdjList().length];
        q = add(g, src, q);
        g.mark(src, visited);
        while(!q.isEmpty()){
            Vertex v = q.remove();
            Vertex temp = v.getFriendsList().head;
            while(temp != null){
                if(!g.hasVisited(temp, visited)) {
                    q = add(g, temp, q);
                    g.mark(temp, visited);
                    g.mark_prev(temp, prev, v);
                    temp = temp.getNext();
                }
                else{
                temp=temp.getNext();}
            }
        }
        g.shortestPath(des, prev, g.getAdjList());
    }
}
