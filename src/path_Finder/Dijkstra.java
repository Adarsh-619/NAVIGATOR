package path_Finder;

/**
 * @author Adarsh
 * Algorithm Design 1 Project
 * 
 */

import java.util.ArrayList;

import structure.Graph;
import structure.Vertex;

public class Dijkstra {
	
    public ArrayList<Vertex> add2(Graph g, Vertex v, ArrayList<Vertex> q){
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
	
    public void dijakstras(Graph g, Vertex src, Vertex des){
        for(int i=0; i<g.getAdjList().length; i++) {
            if (g.getAdjList()[i] != null) {
                if(g.getAdjList()[i].name.equalsIgnoreCase(src.name)) {
                    g.getAdjList()[i].setDistance(0);
                }
                else{
                    g.getAdjList()[i].setDistance(Integer.MAX_VALUE);
                }
                Vertex temp = g.getAdjList()[i].getFriendsList().head;
                while (temp != null) {
                    temp.setDistance(Integer.MAX_VALUE);
                    temp = temp.getNext();
                }
            }
        }
        boolean[] visited  = new boolean[g.getAdjList().length];
        ArrayList<Vertex> q = new ArrayList<>();
        Vertex[] prev = new Vertex[g.getAdjList().length];
        long[] low = new long[g.getAdjList().length];
        for(int i=0;i<low.length; i++) {
           low[i] = Integer.MAX_VALUE;
        }
        q = add2(g, src, q);

        while (!q.isEmpty()){

            Vertex v = g.lowest(q);
            q.remove(v);
            g.mark(v, visited);
            low = g.evaluate(v, visited, q, prev, low, g);
        }
        
        String s = "";
        for(int i=0; i<g.getAdjList().length; i++){
            if(g.getAdjList()[i] != null){
                if(g.getAdjList()[i].name.equalsIgnoreCase(src.name)) 
                	continue;
                else {
                	s = src.name + "  " + "--> " + g.getAdjList()[i].name + "  " + low[i];    		
//                	System.out.println(src.name + "  " + "--> " + g.getAdjList()[i].name + "  " + low[i]);
                	
                }
            }
        }
        System.out.println(s);
        
        System.out.println("*******************Dijakstra's Shortest Path*******************\n");
        g.shortestPath(des, prev, g.getAdjList());
        System.out.println("\n");
        System.out.println("***************************************************************");
    }
    
}
