import java.io.*;
import java.net.Socket;

public class Service implements Runnable{

    private Socket clientSocket;

    Service(Socket cs){
        this.clientSocket=cs;
    }

    public static void log(String message){
        System.out.println("[C]: " + message);
    }

    @Override
    public void run() {
        try {
            String clientIP = clientSocket.getInetAddress().toString();
            InputStream is = clientSocket.getInputStream();
            OutputStream os = clientSocket.getOutputStream();
            InputStreamReader isr = new InputStreamReader(is);
            OutputStreamWriter osr = new OutputStreamWriter(os);
            BufferedReader br = new BufferedReader(isr);
            BufferedWriter bw = new BufferedWriter(osr);

            log("Reading");
            int l1 = Integer.parseInt(br.readLine());
            log("l1: "+l1+"\n");

            log("Writing");
            bw.write(l1+"");
            bw.newLine();
            bw.flush();

            int l2 = Integer.parseInt(br.readLine());
            log("l2: " + l2+"");
            int l3 = Integer.parseInt(br.readLine());
            log("l3: " + l3);
            int l4 = Integer.parseInt(br.readLine());
            log("l4: " +"\n");

            long suma = l4+l2+l3;

            log("Writing suma");
            log("Suma: " + suma+"");
            bw.write(suma+"");
            bw.newLine();
            bw.flush();
            log("Suma sent\n");

            log("Log after suma...");
//            String log = br.readLine();
//            log(log);
            log("Log after suma\n");

            log("Sending port number...");
            bw.write("15000");
            bw.newLine();
            bw.flush();
            log("Port number sent\n");

            log("Log after port...");
            String number = br.readLine();
            log(number);
            log("Log after port");

            int l = Integer.parseInt(number);
            int sprawdzanie=1;
            int pom=sprawdzanie;
            int res=0;
            while(true) {
                for (int i = 1; i <=7; i++){
                    pom*=sprawdzanie;
                }
                if(pom<=l) {
                    sprawdzanie++;
                    pom=sprawdzanie;
                } else {
                    res=sprawdzanie-1;
                    break;
                }
            }

            log("Sending potega 7...");
            bw.write(res+"");
            bw.newLine();
            bw.flush();
            log("Potega 7 sent\n");

            log("Wysylam NWD...");
            bw.write(NWD_1()+"");
            bw.newLine();
            bw.flush();

            this.clientSocket.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static int NWD_1(int pierwsza, int druga)
    {
        while (pierwsza != druga) // dopóki dwie liczby nie są sobie równe
        {
            if (pierwsza > druga)  // sprawdzamy, która z nich jest większa
            {
                pierwsza = pierwsza - druga; // odejmujemy mniejszą liczbę
            }                               // od większej
            else
            {
                druga = druga - pierwsza;
            }
        }
        return pierwsza;
    }
}
