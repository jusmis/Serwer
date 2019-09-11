import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class SeverSocet extends Thread {

    ServerSocket serverSocket;
    int clientCode; //Tu znajdzie sie "kod", który otrzymamy od klienta 0-255
    int serverCode =  0;
    private boolean isConnected = false;

    public String getUserName() {
        return userName;
    }

    String userName;

    public boolean isConnected() {
        return isConnected;
    }

    @Override
    public void run() {
        super.run();
        try {
            serverSocket = new ServerSocket(1207);
            Socket connectionSocket = serverSocket.accept(); //Czekaj na połączenie
            isConnected = true;
            BufferedReader inFromClient =
                    new BufferedReader(new InputStreamReader(connectionSocket.getInputStream())); //Przygotuj buffer do którego wpisujesz dane od klienta
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream()); //Przygotuj buffer z którego wypisujesz dane do klienta
            userName = inFromClient.readLine();

            while (true) {
                clientCode = inFromClient.read(); //Odczytaj dane od klienta
                System.out.println("Received: " + clientCode); //Pokaż co odczytałeś
                outToClient.write(serverCode);
                //outToClient.flush();

               /* switch (clientCode){
                    case 0:
                        break;
                    case 1:
                        clientCode = inFromClient.read(); //Odczytaj dane od klienta
                        System.out.println("Received: " + clientCode); //Pokaż co odczytałeś

                        //zmiana serverCode poprzez funkcję
                        outToClient.write(serverCode);
                        //obsługa plików
                        break;
                    default:
                        break;
                }*/
            }
        }catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
