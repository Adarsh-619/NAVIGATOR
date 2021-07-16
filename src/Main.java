import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import structure.*;
import path_Finder.*;

/**
 * @author Adarsh
 * Algorithm Design 1 Project
 * 
 */

@SuppressWarnings("serial")
public class Main extends IOException {

    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) throws IOException {
    	Scanner Sc = new Scanner(System.in);
    	
//    	System.out.println("Enter the no. of cities :");
    	int number = 22;
        Graph graph = new Graph(number);
        String departure;
        String arrival;

        BufferedReader br = null;
        String line;

        System.out.println("Enter the entire path of the Cities file(.txt):");
        String path = Sc.nextLine();
        try{
            br = new BufferedReader(new FileReader(path));
        }catch(FileNotFoundException e){
            System.out.println(e.getMessage()+" The File was not found");
            System.exit(0);
        }


        try{
            while((line = br.readLine()) != null){
                graph.addVertex(line);
            }
        }catch(IOException io){
            System.out.println(io.getMessage()+"Failed to add verices.");
        }


        System.out.println("Enter the entire path of the Routes file(.txt):");
        String pathEdges = Sc.nextLine();
        try{
            br = new BufferedReader(new FileReader(pathEdges));
        }catch(FileNotFoundException e){
            System.out.println(e.getMessage()+" The File was not found");
        }

        try{
            while((line = br.readLine()) != null){
                String details[] = line.split("\s\s\s");
                graph.addEdge(details[0], details[1], Long.parseLong(details[2]));
            }
        }catch(IOException io){
            System.out.println(io.getMessage()+"Failed to add edges.");
        }


        try{
            br = new BufferedReader(new FileReader(path));
        }catch(FileNotFoundException e){
            System.out.println(e.getMessage()+" The File was not found");
            System.exit(0);
        }



        System.out.println("************CITIES TO CHOOSE FROM***********");
        try{
            int i = 1;
            while((line = br.readLine()) != null){
                System.out.println((i++) +". "+line);
            }
        }catch(IOException io){
            System.out.println(io.getMessage()+"Failed to add verices.");
        }
        System.out.println("****************************************");
        System.out.println();

        br.close();

        System.out.print("Enter Departure City: ");
        departure = sc.nextLine();
        System.out.println();
        System.out.print("Enter Arrival city: ");
        arrival = sc.nextLine();
        System.out.println();

        boolean checkDept = false;
        boolean checkArr = false;

        try{
            for(Vertex v : graph.getAdjList()){
                // System.out.println(v.toString());
                if( v.toString().equalsIgnoreCase(departure)){
                    checkDept = true;
                    break;
                }
            }
    
            for(Vertex v : graph.getAdjList()){
                if( v.toString().equalsIgnoreCase(arrival)){
                    checkArr = true;
                    break;
                }
            }
    
            if((checkDept && checkArr)){
                System.out.println("**************BFS Shortest Path**************\n");
                BFS b = new BFS();
                b.Bfs(graph, new Vertex(departure), new Vertex(arrival));
                System.out.println("\n");
                System.out.println("*********************************************");
                System.out.println("Shortest distance from .........");
                Dijkstra d = new Dijkstra();
                d.dijakstras(graph, new Vertex(departure), new Vertex(arrival));
    
            }else{
                throw new Exception("Oops, Input did not match.");
                
            }
        }
        catch(Exception e){
            System.err.println(e);
            main(args);
        }
        finally{
            System.out.println("Thank You!!!");
        }
    }
}
