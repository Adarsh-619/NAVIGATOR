package structure;

/**
 * @author Adarsh
 * Algorithm Design 1 Project
 * 
 */

public class Edge {
    private Vertex source;
    private Vertex destination;
    private Long distance;
    
    
    public Edge(Vertex src, Vertex des, long dis){
        this.setSource(src);
        this.setDestination(des);
        this.setDistance(dis);
    }


	public Long getDistance() {
		return distance;
	}


	public void setDistance(Long distance) {
		this.distance = distance;
	}


	public Vertex getDestination() {
		return destination;
	}


	public void setDestination(Vertex destination) {
		this.destination = destination;
	}


	public Vertex getSource() {
		return source;
	}


	public void setSource(Vertex source) {
		this.source = source;
	}
}
