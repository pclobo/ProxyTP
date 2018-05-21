import java.io.*;
import java.net.*;

public class ProxyMain {
    public static void main(String[] args)throws IOException{
        boolean listener= true;
        ServerSocket serverSocket = null;
        int port= 9800;//Puerto usado.
        try{
            port= Integer.parseInt(args[0]);
        }catch(Exception e){

        }
        try{
            serverSocket = new ServerSocket(port);
            ProxyThread.createFile("LogFile.txt");
            ProxyThread.writeOnFile("Starting on Port: " + port);

        }catch(IOException e){
            System.err.println("Can't be listening anything at Port: " + args[0]);
            ProxyThread.writeOnFile("Can't be listening anything at Port: " + args[0]);
            System.exit(-1);

        }
        while (listener) {
            new ProxyThread(serverSocket.accept()).start();
        }
        serverSocket.close();
    }

}
