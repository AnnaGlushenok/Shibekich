import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String command;
        //SWITCH_PROTOCOL TCP
        //CREATE {"id":0,"price":4,"count":4,"author":"q56y","name":"acsca"}
        //UPDATE {"id":0,"price":2,"count":3,"author":"bdbfdb","name":"acsca"}
        //DELETE 0
        //GET ALL
        try {
            Socket socket = new Socket("LocalHost", 6789);
            System.out.println("Client Started...");
            Scanner scan = new Scanner(System.in);
            DataInputStream inp = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            while (true) {
                command = scan.nextLine();
                if (command.equals("q")) {
                    out.writeBytes("Client is down..." + '\n');
                    return;
                } else {
                    out.writeBytes(command + '\n');
                }
                System.out.println("Received from server: " + inp.readLine());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}