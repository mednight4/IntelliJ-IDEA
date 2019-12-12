package java网络编程.远程通讯;

//服务器端RemoteFileServer.java
import java.io.*;
import java.net.*;
public class RemoteFileServer {
    int listenPort;
    public RemoteFileServer(int listenPort) {
        this.listenPort = listenPort;
    }
    // 允许客户机连接到服务器,等待客户机请求
    public void acceptConnections() {
        try {
            ServerSocket server = new ServerSocket(listenPort);
            Socket incomingConnection = null;
            while (true) {
                incomingConnection = server.accept();
                handleConnection(incomingConnection);
            }
        } catch (BindException e) {
            System.out.println("Unable to bind to port " + listenPort);
        } catch (IOException e) {
            System.out.println("Unable to instantiate a ServerSocket on port: "
                    + listenPort);
        }
    }
    // 与客户机Socket交互以将客户机所请求的文件的内容发送到客户机
    public void handleConnection(Socket incomingConnection) {
        try {
            OutputStream outputToSocket = incomingConnection.getOutputStream();
            InputStream inputFromSocket = incomingConnection.getInputStream();
            BufferedReader streamReader = new BufferedReader(
                    new InputStreamReader(inputFromSocket));
            FileReader fileReader = new FileReader(new File(streamReader
                    .readLine()));
            BufferedReader bufferedFileReader = new BufferedReader(fileReader);
            PrintWriter streamWriter = new PrintWriter(outputToSocket);
            String line = null;
            while ((line = bufferedFileReader.readLine()) != null) {
                streamWriter.println(line);
            }
            fileReader.close();
            streamWriter.close();
            streamReader.close();
        } catch (Exception e) {
            System.out.println("Error handling a client: " + e);
            e.printStackTrace();
        }
    }
    public static void main(String args[]) {
        RemoteFileServer server = new RemoteFileServer(1001);
        server.acceptConnections();
    }
}
