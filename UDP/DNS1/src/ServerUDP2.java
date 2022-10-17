import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ServerUDP2 {
    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket(10053);

        DatagramPacket datagramPacketToReceive = new DatagramPacket( new byte[1400], 1400 );
        socket.receive(datagramPacketToReceive);
        DatagramPacket r = datagramPacketToReceive;
        System.out.println("Odebralem od kogo " + r.getAddress().toString() + ":" + r.getPort());
        String tekst = new String(r.getData(),0, r.getLength());
        System.out.println("Odebralem c0 " + tekst);
        for(int i=0; i<tekst.length(); i++){
            System.out.print(r.getData()[i]>0? 256+r.getData()[i] : r.getData() + " ");
        }
        System.out.println();

        DatagramPacket packetToSend = new DatagramPacket(
                r.getData(),
                r.getLength(),
                r.getAddress(),
                r.getPort());
        socket.send(packetToSend);
    }
}
