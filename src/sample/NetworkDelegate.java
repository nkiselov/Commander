package sample;

public interface NetworkDelegate {
    public void onConnect();
    public void onData(byte[] data);
}
