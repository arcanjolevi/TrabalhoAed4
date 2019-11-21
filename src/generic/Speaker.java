package generic;

public interface Speaker {
    public void subscribe(Object listener) throws Exception;
    public void unscribe(Object listener) throws Exception;
    public void speak(String msg);
}
