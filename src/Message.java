import java.lang.reflect.Array;
import java.util.ArrayList;

public class Message {
    public int getUserID() {
        return this.userID;
    }

    private int userID;

    public String getContent() {
        return this.content;
    }

    private String content;

    public ArrayList<Integer> getVectorClock() {
        return this.vectorClock;
    }

    private ArrayList<Integer> vectorClock;

    public Message(int _userID, String _message, ArrayList<Integer> _clock) {
        this.userID = _userID;
        this.content = _message;
        this.vectorClock = _clock;
    }

}
