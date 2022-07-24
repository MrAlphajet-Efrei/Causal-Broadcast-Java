import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class Node {
    public int getNodeID() {
        return nodeID;
    }

    private int nodeID;
    private ArrayList<Integer>  localClock = new ArrayList<Integer>();
    private int sequence = 0;
    private ArrayList<Message> messageBuffer = new ArrayList<Message>();
    private Broadcast brd;


    public Node(int id, Broadcast _brd) {
        this.nodeID = id;
        this.brd = _brd;
        for (int i = 1; i<=10; i++){
            this.localClock.add(0);
        }
    }

    public void sendMessage() {
        this.sequence += 1;
        ArrayList<Integer> vectorClock = this.localClock;
        vectorClock.set((this.nodeID-1), this.sequence);
        System.out.println("enter your message");
        Scanner myScanner = new Scanner(System.in);
        String myMessage = myScanner.nextLine();
        Message newMessage = new Message(nodeID, myMessage, vectorClock);
        brd.Sender(nodeID, newMessage);
    }

    public void receive(Message m) {
        messageBuffer.add(m);
        ArrayList<Message> messageToDelete = new ArrayList<Message>();
        messageBuffer.forEach(message -> {
            if (isMessageClockGood(message)) {
                int sender = message.getUserID()-1;
                int currentSequenceInMessage = localClock.get(sender);
                brd.Deliver(nodeID, message);
                messageToDelete.add(message);
                localClock.set(sender, (currentSequenceInMessage+1));
            }
        });
        for (int i = 0; i < messageToDelete.size(); i++){
            messageBuffer.remove(messageToDelete.get(i));
        }
    }

    private boolean isMessageClockGood(Message m) {
        int size = m.getVectorClock().size();
        int sender = m.getUserID()-1;
        int isClockValide = 0;
        for (int i = 0; i< size; i++){
            if (i == sender && m.getVectorClock().get(sender) == (localClock.get(sender) + 1)) {
                isClockValide++;
            }else if (m.getVectorClock().get(i) >= localClock.get(i)) {
                isClockValide++;
            }
        }
        if (isClockValide == size) return true;
        else return false;
    }
}
