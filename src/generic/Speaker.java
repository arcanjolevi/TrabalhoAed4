package generic;

//Class that represents a Speaker
public interface Speaker {
    public void subscribe(Object listener) throws Exception; //Connect a listener to this speaker
    public void unscribe(Object listener) throws Exception; //Disconnect a listener from this speaker
    public void speak(String msg); //Speak a message to all connected listeners
}
