import java.net.ServerSocket;
import java.net.Socket;


public class Main {
    public static void main(String[] args) {
        try {
            Protocol protocol = new Protocol();
            protocol.setProtocol(ProtocolType.FILE);
            ServerSocket serverSocket = new ServerSocket(6789);
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Server Started...");

                ClientHandler clientHandler = new ClientHandler(socket, protocol);
                new Thread(clientHandler).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
