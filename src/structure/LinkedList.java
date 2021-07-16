package structure;

/**
 * @author Adarsh
 * Algorithm Design 1 Project
 * 
 */

public class LinkedList {
    public Vertex head;
    
    public void insert(String d,long dis) {
        Vertex n = new Vertex(d,dis);
        {
            if (head == null) {
                head = n;
            } else {
                Vertex temp = head;
                while (temp.getNext() != null) {
                    temp = temp.getNext();
                }
                temp.setNext(n);
            }
        }
    }
    
    public void insert(String d) {
        Vertex n = new Vertex(d);
        {
            if (head == null) {
                head = n;
            } else {
                Vertex temp = head;
                while (temp.getNext() != null) {
                    temp = temp.getNext();
                }
                temp.setNext(n);
            }
        }
    }

    public Vertex find(String d) {
        Vertex temp = head;
        while (temp.getNext() != null) {
            if (d.compareTo(temp.name) == 0) {
                return temp;
            }
            temp = temp.getNext();
        }
        if(d.compareTo(temp.name) == 0){
            return temp;
        }
        return null;
    }

    public void clear() {
        head = null;
    }

    public void delete(String d) {
        Vertex t = find(d);
        if (t == null) {
            System.out.println("value not found");
        }
        boolean flag = false;
        Vertex temp = head;
        while (temp.getNext() != t) {
            if(t == head){
                head = head.getNext();
                flag = true;
                break;
            }
            temp = temp.getNext();

        }
        if(flag == false) {
            temp.setNext(t.getNext());
        }
    }
    public String toString() {
        String s = " ";
        Vertex temp = head;
            while (temp != null) {
                s += temp.name + " ";
                temp = temp.getNext();
            }
        return s;
    }
}
