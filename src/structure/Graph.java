package structure;

import java.io.Console;

/**
 * @author Adarsh
 * Algorithm Design 1 Project
 * 
 */

import java.util.ArrayList;
import path_Finder.Dijkstra;

public class Graph {
	
    private Vertex[] adjList;
    private Edge[] list;
    private int count;
    private int ecount;
    public String st = "";
    public Graph(int s) {
        setAdjList(new Vertex[s]);
        list = new Edge[5*s];
        count = 0;
        ecount = 0;
    }
    
	public Vertex[] getAdjList() {
		return adjList;
	}

	public void setAdjList(Vertex[] adjList) {
		this.adjList = adjList;
	}
    
    public void addVertex(String l){
        Vertex v = new Vertex(l,0);
        getAdjList()[count] = v;
        count++;
    }
    
    public void addEdge(String source, String des, long dis){
        for(int i=0; i<getAdjList().length; i++) {
            if (getAdjList()[i] != null) {
                if (getAdjList()[i].name.equalsIgnoreCase(source)) {
                    getAdjList()[i].getFriendsList().insert(des,dis);
                }
                if (getAdjList()[i].name.equalsIgnoreCase(des)) {
                    getAdjList()[i].getFriendsList().insert(source,dis);
                }
            }
        }
        
        Edge e = new Edge(new Vertex(source,0),new Vertex(des,0),dis);
        list[ecount] = e;
        ecount++;
        Edge f = new Edge(new Vertex(des,0),new Vertex(source,0),dis);
        list[ecount] = f;
        ecount++;

    }
    
    public void addEdge(String source, String des){
        for(int i=0; i<getAdjList().length; i++) {
            if (getAdjList()[i] != null) {
                if (getAdjList()[i].name.equalsIgnoreCase(source)) {
                    getAdjList()[i].getFriendsList().insert(des);
                }
                if (getAdjList()[i].name.equalsIgnoreCase(des)) {
                    getAdjList()[i].getFriendsList().insert(source);
                }
            }
        }
    }
    
    public void display(long a){
        for(int i=0; i<getAdjList().length; i++) {
            if (getAdjList()[i] != null) {
                System.out.print(getAdjList()[i].name + "---> ");
                Vertex temp = getAdjList()[i].getFriendsList().head;
                while (temp != null) {
                    System.out.print(temp.name + " = " + temp.getDistance() + ",  ");
                    temp = temp.getNext();
                }
                System.out.println("  ");
            }
        }
    }
    
    public void display(){
        for(int i=0; i<getAdjList().length; i++) {
            if (getAdjList()[i] != null) {
                System.out.print(getAdjList()[i].name + "---> ");
                Vertex temp = getAdjList()[i].getFriendsList().head;
                while (temp != null) {
                    System.out.print(temp.name + ", ");
                    temp = temp.getNext();
                }
                System.out.println("  ");
            }
        }
    }
    
    Dijkstra d = new Dijkstra();
    
    public long[] evaluate(Vertex v,boolean[] visited, ArrayList<Vertex> q, Vertex[] prev, long[] low, Graph g){
        Vertex temp = v.getFriendsList().head;
        while(temp != null){
            if(!hasVisited(temp,visited)) {
                long dis = getDis(temp,v);
                long newdis = v.getDistance() + dis;
                if(temp.getDistance() > newdis){
                    temp.setDistance(newdis);
                    for(int i=0; i<getAdjList().length; i++){
                        if(getAdjList()[i] != null){
                            if(getAdjList()[i].name.equalsIgnoreCase(temp.name)){
                                getAdjList()[i].setDistance(newdis);
                                if(low[i] > newdis){
                                    low[i] = newdis;
                                    mark_prev(temp,prev,v);
                                }
                            }
                        }
                    }
                    d.add2(g, temp, q);
                }
                temp=temp.getNext();
            }
            else temp=temp.getNext();
        }
        return low;
    }
    
    public long getDis(Vertex v,Vertex e){
        for(int i=0; i< list.length; i++){
            if(list[i] != null) {
                if (list[i].getSource().name.equalsIgnoreCase(v.name) && list[i].getDestination().name.equalsIgnoreCase(e.name)) {
                    return list[i].getDistance();
                }
            }
        }
        return -1;
    }

    public boolean hasVisited(Vertex v, boolean[] visited){
        for(int i=0; i< getAdjList().length; i++){
            if(getAdjList()[i] != null) {
                if (getAdjList()[i].name.equalsIgnoreCase(v.name)) {
                    if (visited[i]) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public void update(Vertex temp,Vertex v,long dis){
        for(int i=0; i< getAdjList().length; i++){
            if(getAdjList()[i] != null) {
                if (getAdjList()[i].name.equalsIgnoreCase(v.name)) {
                   Vertex temp1 = getAdjList()[i].getFriendsList().head;
                   while(temp1 != null){
                       if(temp1.name.equalsIgnoreCase(temp.name)){
                           temp1.setDistance(dis);
                       }
                       temp1 = temp1.getNext();
                   }
                }
            }
        }
    }
    
    public void mark(Vertex v, boolean[] visited){
        for(int i=0; i< getAdjList().length; i++){
            if(getAdjList()[i] != null) {
                if (getAdjList()[i].name.equalsIgnoreCase(v.name)) {
                    visited[i] = true;
                }
            }
        }
    }
        
    public Vertex lowest(ArrayList<Vertex> q){
        long lowest = Integer.MAX_VALUE;
        int index = -1;
        for(int i=0; i<q.size(); i++){
            //System.out.println(q.get(i).distance);
            if(q.get(i).getDistance()<lowest){
                lowest = q.get(i).getDistance();
                index = i;
            }
        }
        return q.get(index);
    }
    
    public void mark_prev(Vertex v, Vertex[] prev, Vertex p){
        for(int i=0; i< getAdjList().length; i++){
            if(getAdjList()[i] != null) {
                if (getAdjList()[i].name.equalsIgnoreCase(v.name)) {
                    prev[i] = p;
                }
            }
        }
    }
    
    public int getIn(Vertex v, Vertex[] v1){
        for(int i=0; i<v1.length; i++){
            if(getAdjList()[i] != null) {
                if (v1[i].name.equalsIgnoreCase(v.name)) {
                    return i;
                }
            }
        }
        return -1;
    }
    
    public void shortestPath(Vertex des, Vertex[] prev, Vertex[] adj){
        Vertex parent = des;
        int x = 0;
        Vertex[] path = new Vertex[prev.length];

        while(parent != null){
            path[x] = parent;
            x++;
            int index = getIn(parent,adj);
            parent = prev[index];
        }
        
        for(int i=path.length-1; i>=0; i--){
            if(path[i] != null) {
                if(i == 0){
                	st = st + path[i].name;
                    System.out.print(path[i].name + " ");
                }
                else {
                	st += path[i].name + " " + "--> ";
                	System.out.print(path[i].name + " " + "--> ");
                }
            }
        }
    }


}
