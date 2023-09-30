import com.google.gson.Gson;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class Main {
    public static void main(String[] args) {
        try {
            Protocol protocol = new Protocol();
            protocol.setProtocol(ProtocolType.TCP);
            String[] command;
            ServerSocket serverSocket = new ServerSocket(6789);
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Server Started...");
                DataInputStream inp = new DataInputStream(socket.getInputStream());
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                while (true) {
                    command = inp.readLine().split(" ");
                    System.out.println("Received from client: " + command[0] + " " + command[1]);
                    switch (Command.valueOf(command[0])) {
                        case SWITCH_PROTOCOL -> {
                            protocol.setProtocol(ProtocolType.valueOf(command[1]));
                            out.writeBytes("Protocol switched \n");
                        }
                        case CREATE -> {
                            protocol.create(new Library(command[1]));
                            out.writeBytes("Created\n");
                        }
                        case UPDATE -> {
                            protocol.update(new Library(command[1]));
                            out.writeBytes("Updated\n");
                        }
                        case DELETE -> {
                            protocol.delete(Integer.parseInt(command[1]));
                            out.writeBytes("Deleted\n");
                        }
                        case GET -> {
                            out.writeBytes(new Gson().toJson(protocol.get()) + "\n");
                        }
                        default -> {
                            out.writeBytes("No such command.");
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}