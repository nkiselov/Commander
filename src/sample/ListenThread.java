package sample;

import java.io.IOException;
import java.io.InputStream;

class ListenThread extends Thread
{
    InputStream is;
    NetworkDelegate nd;
    boolean shouldRun;

    public ListenThread(InputStream is, NetworkDelegate nd){
        this.is = is;
        this.nd = nd;
    }

    public void run ()
    {
        shouldRun = true;
        try {
            while(shouldRun){
                sleep(50);
                if(is.available()>0){
                    nd.onData(is.readAllBytes());
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}