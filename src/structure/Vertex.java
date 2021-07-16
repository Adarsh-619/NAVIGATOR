package structure;

/**
 * @author Adarsh
 * Algorithm Design 1 Project
 * 
 */

public class Vertex {
    public String name;
    private long distance;
    private LinkedList friendsList = new LinkedList();
    private Vertex next;
    
    public Vertex(String n, long dis){
        name = n;
        setDistance(dis);
    }
    
    public Vertex(String n){
        name = n;
    }
    
    public String toString(){
        return this.name;
    }

	public long getDistance() {
		return distance;
	}

	public void setDistance(long distance) {
		this.distance = distance;
	}

	public LinkedList getFriendsList() {
		return friendsList;
	}

	public void setFriendsList(LinkedList friendsList) {
		this.friendsList = friendsList;
	}

	public Vertex getNext() {
		return next;
	}

	public void setNext(Vertex next) {
		this.next = next;
	}
}
