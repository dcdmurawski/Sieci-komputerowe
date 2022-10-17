import java.io.*;
import java.net.ServerSocket;

public class Server {
    private static final int SERVER_PORT = 15000;
    public static void log(String message){
        System.out.println("[C]: " + message);
    }

    public static void main(String[] args) throws IOException {
        log("Starting:");
        ServerSocket welcomeSocket = new ServerSocket(SERVER_PORT);
        while(true){
            log("-----------");
            log("Accepting client...");
            Service s = new Service(welcomeSocket.accept());
            log("Client accepted");
            log("Turning thread...");
            new Thread(s).start();
            log("Thread done");
        }
    }
}
