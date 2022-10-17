import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Server {
    static int PORT = 44555;

    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket(PORT);
        DatagramPacket r = new DatagramPacket(
                new byte[1400],
                1400
        );
        socket.receive(r);

        InetAddress address = r.getAddress();
        int port = r.getPort();

        String tekst = new String(r.getData(), 0, r.getLength());
        System.out.println("[S] Received: " + tekst);

        String res="";
        for(int i=0; i<tekst.length(); i++){
            if(tekst.charAt(i)!='2'){
                res+=tekst.charAt(i);
            }
        }
        res=res.substring(0,res.length()-1);

        System.out.println("[S] Received: " + res);
        DatagramPacket r2 = new DatagramPacket(
                res.getBytes(),
                res.length(),
                address,
                port
        );
        socket.send(r2);

        DatagramPacket r3 = new DatagramPacket(
                new byte[1400],
                1400
        );
        DatagramPacket r4 = new DatagramPacket(
                new byte[1400],
                1400
        );
        DatagramPacket r5 = new DatagramPacket(
                new byte[1400],
                1400
        );
        DatagramPacket r6 = new DatagramPacket(
                new byte[1400],
                1400
        );
        socket.receive(r3);
        socket.receive(r4);
        socket.receive(r5);
        socket.receive(r6);

        String l1 = new String(r3.getData(),0, r3.getLength());
        String l2 = new String(r4.getData(),0, r4.getLength());
        String l3 = new String(r5.getData(),0, r5.getLength());
        String l4 = new String(r6.getData(),0, r6.getLength());
        int li1 = Integer.parseInt(l1.substring(0,l1.length()-1));
        int li2 = Integer.parseInt(l2.substring(0,l2.length()-1));
        int li3 = Integer.parseInt(l3.substring(0,l3.length()-1));
        int li4 = Integer.parseInt(l4.substring(0,l4.length()-1));
        int suma = li1+li2+li3+li4;
        String sum = String.valueOf(suma);

        r2 = new DatagramPacket(
                sum.getBytes(),
                sum.length(),
                address,
                port
        );
        socket.send(r2);

        r = new DatagramPacket(
                new byte[1400],
                1400
        );
        socket.receive(r);
        String ostatnie = new String(r.getData(), 0, r.getLength());
        int k = zad4(Integer.parseInt(ostatnie.substring(0,ostatnie.length()-1)));
        res = String.valueOf(k);
        r2 = new DatagramPacket(
                res.getBytes(),
                res.length(),
                address,
                port
        );
        socket.send(r2);

        r = new DatagramPacket(
                new byte[1400],
                1400
        );
        socket.receive(r);
        String fin = new String(r.getData(),0,r.getLength());
        System.out.println("Final: " + fin);
    }
    public static int zad4(int x){
        int k=1;
        while(Math.pow(k,8)<=x){
            k++;
        }
        return --k;
    }
}