import com.google.gson.Gson;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

class ClientHandler implements Runnable {
    private Socket socket;
    private Protocol protocol;
    private DataInputStream inp;
    private DataOutputStream out;

    public ClientHandler(Socket socket, Protocol protocol) {
        this.socket = socket;
        this.protocol = protocol;
    }

    @Override
    public void run() {
        try {
            inp = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            String[] command;

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
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
                inp.close();
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}