import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class TCP_2 {
    private static final int SERVER_PORT = 26777;

    public static void log(String message){
        System.out.println("[C]: " + message);
    }

    public static void main(String[] args) throws IOException {
        log("Starting:");
        ServerSocket welcomeSocket = new ServerSocket(SERVER_PORT);
        while(true){
            log("-----------");
            log("Accepting client...");
            //UDP_2 s = new UDP_2(welcomeSocket.accept());
            Socket clientSocket = welcomeSocket.accept();
            log("Client accepted");

            try {
                String clientIP = clientSocket.getInetAddress().toString();
                InputStream is = clientSocket.getInputStream();
                OutputStream os = clientSocket.getOutputStream();
                InputStreamReader isr = new InputStreamReader(is);
                OutputStreamWriter osr = new OutputStreamWriter(os);
                BufferedReader br = new BufferedReader(isr);
                BufferedWriter bw = new BufferedWriter(osr);

                log("Reading...");
                String zapytanie = br.readLine();
                log("Read");
                String[] splitted = zapytanie.split(" ");
                if (zapytanie.equals("LIST")) {
                    String directoryPath = "C:\\Users\\s22825\\IdeaProjects\\TCP_UDP_prog\\FolderZPlikami";
                    File dir = new File(directoryPath);
                    String contents[] = dir.list();
                    String content = "";
                    for(int i=0; i<contents.length; i++){
                        content += contents[i] + ";";
                    }
                    content = content.substring(0, content.length() - 1);
                    log("Sending " + content + " ...");
                    bw.write(content);
                    bw.newLine();
                    bw.flush();
                    log("Sent!");

                } else if (splitted[0].equals("GET")) { //poprawic i wyslac do UDP

                    UDP_2 get = new UDP_2(welcomeSocket.accept(),zapytanie);
                    log("Turning thread...");
                    new Thread(get).start();
                    log("Thread done");

                    //to jest w TCP
//                    String send="";
//                    for(int i=1; i<splitted.length; i++){
//                        try{
//                            send += splitted[i]+"\n";
//                            String path = "C:\\Users\\s22825\\IdeaProjects\\TCP_UDP_prog\\FolderZPlikami\\" + splitted[i];
//                            File f = new File(path);
//                            Scanner read = new Scanner(f);
//                            while(read.hasNextLine()){
//                                send+=read.nextLine();
//                            }
//                        } catch(FileNotFoundException e) {
//                            System.out.println("Brak pliku " + splitted[i]);
//                        }
//                    }
//                    log("Sending ");

                }
            } catch (Exception e) {
                e.printStackTrace();
            }


            //new Thread(s).start();
            log("Thread done");
        }
    }

}

class UDP_2 implements Runnable {

    private Socket clientSocket;
    private String zapytanie;

    UDP_2(Socket cs,String zapytanie) {
        this.clientSocket = cs;
        this.zapytanie=zapytanie;
    }

    public static void log(String message) {
        System.out.println("[C]: " + message);
    }

    @Override
    public void run() {
        try {
//
//            String clientIP = clientSocket.getInetAddress().toString();
//            InputStream is = clientSocket.getInputStream();
//            OutputStream os = clientSocket.getOutputStream();
//            InputStreamReader isr = new InputStreamReader(is);
//            OutputStreamWriter osr = new OutputStreamWriter(os);
//            BufferedReader br = new BufferedReader(isr);
//            BufferedWriter bw = new BufferedWriter(osr);
//
//            log("Reading...");
//            String zapytanie = br.readLine();
//            log("Read");
//            String[] splitted = zapytanie.split(" ");
            if (zapytanie.equals("LIST")) {
                String directoryPath = "C:\\Users\\s22825\\IdeaProjects\\TCP_UDP_prog\\FolderZPlikami";
                File dir = new File(directoryPath);
                String contents[] = dir.list();
                String content = "";
                for(int i=0; i<contents.length; i++){
                    content += contents[i] + ";";
                }
                content = content.substring(0, content.length() - 1);
                log("Sending " + content + " ...");
                bw.write(content);
                bw.newLine();
                bw.flush();
                log("Sent!");
            } else if (splitted[0].equals("GET")) {
                String send="";
                for(int i=1; i<splitted.length; i++){
                    try{
                        send += splitted[i]+"\n";
                        String path = "C:\\Users\\s22825\\IdeaProjects\\TCP_UDP_prog\\FolderZPlikami\\" + splitted[i];
                        File f = new File(path);
                        Scanner read = new Scanner(f);
                        while(read.hasNextLine()){
                            send+=read.nextLine();
                        }
                    } catch(FileNotFoundException e) {
                        System.out.println("Brak pliku " + splitted[i]);
                    }
                }
                log("Sending ");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

            /*log("Writing");
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
    }*/
    }
}