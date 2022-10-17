import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ClientUDP2 {

    public static void main(String[] args) throws IOException {
        //byte[] doWyslania = {0x08, 0x54, 0x01, 0x00, 0x00, 0x01, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x03, 0x77, 0x77, 0x77, 0x02, 0x77, 0x70, 0x02, 0x70, 0x6c, 0x00, 0x00, 0x01, 0x00, 0x01};
        String dnsName = "172.21.48.158";
        String flaga = "126697";
        byte[] doWyslania = flaga.getBytes();
        InetAddress dnsAddress = InetAddress.getByName(dnsName);
        int dnsPort = 15559; //port dnsu

        DatagramPacket datagramPacketToSend = new DatagramPacket(
                doWyslania,
                doWyslania.length,
                dnsAddress,
                dnsPort
        );

        DatagramSocket socket = new DatagramSocket();
        socket.send(datagramPacketToSend);

//        doWyslania = (socket.getLocalPort()+"").getBytes();
//        datagramPacketToSend = new DatagramPacket(
//                doWyslania,
//                doWyslania.length,
//                dnsAddress,
//                dnsPort
//        );
//        socket.send(datagramPacketToSend);


        DatagramPacket datagramPacketToReceive = new DatagramPacket(
                new byte[1400],
                1400
        );
        socket.receive(datagramPacketToReceive);
        DatagramPacket r1= datagramPacketToReceive;
        datagramPacketToReceive = new DatagramPacket(
                new byte[1400],
                1400
        );
        socket.receive(datagramPacketToReceive);
        DatagramPacket r2= datagramPacketToReceive;
        datagramPacketToReceive = new DatagramPacket(
                new byte[1400],
                1400
        );
        socket.receive(datagramPacketToReceive);
        DatagramPacket r3= datagramPacketToReceive;
        datagramPacketToReceive = new DatagramPacket(
                new byte[1400],
                1400
        );
        socket.receive(datagramPacketToReceive);
        DatagramPacket r4= datagramPacketToReceive;
        datagramPacketToReceive = new DatagramPacket(
                new byte[1400],
                1400
        );
        socket.receive(datagramPacketToReceive);
        DatagramPacket r5= datagramPacketToReceive;

        String t1 = new String(r1.getData(),0, r1.getLength());
        String t2 = new String(r2.getData(),0, r2.getLength());
        String t3 = new String(r3.getData(),0, r3.getLength());
        String t4 = new String(r4.getData(),0, r4.getLength());
        String t5 = new String(r5.getData(),0, r5.getLength());
        System.out.println("Odebralem c0 " + t1 + ":" + t2 + ":" + t3 + ":" + t4 + ":" + t5);
//        for(int i=0; i<tekst.length(); i++){
//            System.out.print(r.getData()[i]>0? 256+r.getData()[i] : r.getData() + " ");
//        }
        System.out.println();
    }

}
