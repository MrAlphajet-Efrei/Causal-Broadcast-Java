import java.util.ArrayList;

public class Broadcast {
    public void setAllNode(ArrayList<Node> allNode) {
        this.allNode = allNode;
    }

    private ArrayList<Node> allNode;

    public Broadcast() {
    }

    public void Sender(int sender, Message m) {
        for (int i = 0; i <= 8; i++){
            if (sender != i+1) {
                allNode.get(i).receive(m);
            }
        }
    }

    public void Deliver(int receiver, Message m) {
        System.out.println(receiver+": "+m.getContent());
    }
}
