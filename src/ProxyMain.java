import java.io.*;
import java.net.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ProxyMain {
    public static String nameLog;

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
            Date date = new Date();
            DateFormat dateFormat = new SimpleDateFormat("dd_MM_yyyy");
            nameLog="LogFile_"+dateFormat.format(date)+".txt";
            ProxyThread.createFile(nameLog);
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
