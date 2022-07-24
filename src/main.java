
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.Semaphore;

public class main {
    public static void main(String[] args) {
        ArrayList<Node> nodes = new ArrayList<>();
        Broadcast broadcast = new Broadcast();
        for (int i = 1; i <= 10; i++) {
            Node node = new Node(i, broadcast);
            nodes.add(node);
        }
        broadcast.setAllNode(nodes);
        for (int j = 0; j < 3; j++){
            nodes.get(j).sendMessage();
        }
    }
}
