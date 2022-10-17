import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class TCP_UDP1 {
    private static final String SERVER_NAME = "localhost";
    private static final int SERVER_PORT = 26777;
    public static void log(String message){
        System.out.println("[C]: " + message);
    }

    public static void main(String[] args) throws IOException {
        InetAddress serverAddress = InetAddress.getByName(SERVER_NAME);
        Socket serverSocket = new Socket(serverAddress, SERVER_PORT);

        InputStream is = serverSocket.getInputStream();
        OutputStream os = serverSocket.getOutputStream();
        InputStreamReader isr = new InputStreamReader(is);
        OutputStreamWriter osr = new OutputStreamWriter(os);
        BufferedReader br = new BufferedReader(isr);
        BufferedWriter bw = new BufferedWriter(osr);

        log("asking for list...");
        bw.write("LIST");
        bw.newLine();
        bw.flush();
        log(br.readLine());
//        log("Flag sent");
//        log("Sending 192.168.157.1"+":"+15000);
//        bw.write("10.13.63.8:"+15000);
//        bw.newLine();
//        bw.flush();
//        log(br.readLine());
    }
}
