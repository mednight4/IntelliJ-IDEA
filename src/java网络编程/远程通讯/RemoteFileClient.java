package java网络编程.远程通讯;

//客户端RemoteFileClient.java

import java.io.*;
import java.net.*;

public class RemoteFileClient {
    protected BufferedReader socketReader;
    protected PrintWriter socketWriter;
    protected String hostIp;
    protected int hostPort;

    // 构造方法
    public RemoteFileClient(String hostIp, int hostPort) {
        this.hostIp = hostIp;
        this.hostPort = hostPort;
    }

    // 向服务器请求文件的内容
    public String getFile(String fileNameToGet) {
        StringBuffer fileLines = new StringBuffer();
        try {
            socketWriter.println(fileNameToGet);
            socketWriter.flush();
            String line = null;
            while ((line = socketReader.readLine()) != null)
                fileLines.append(line + "\n");
        } catch (IOException e) {
            System.out.println("Error reading from file: " + fileNameToGet);
        }
        return fileLines.toString();
    }

    // 连接到远程服务器
    public void setUpConnection() {
        try {
            Socket client = new Socket(hostIp, hostPort);
            socketReader = new BufferedReader(new InputStreamReader(client
                    .getInputStream()));
            socketWriter = new PrintWriter(client.getOutputStream());
        } catch (UnknownHostException e) {
            System.out
                    .println("Error1 setting up socket connection: unknown host at "
                            + hostIp + ":" + hostPort);
        } catch (IOException e) {
            System.out.println("Error2 setting up socket connection: " + e);
        }
    }

    // 断开远程服务器
    public void tearDownConnection() {
        try {
            socketWriter.close();
            socketReader.close();
        } catch (IOException e) {
            System.out.println("Error tearing down socket connection: " + e);
        }
    }

    public static void main(String[] args) {
        RemoteFileClient remoteFileClient = new RemoteFileClient("localhost", 1001);
        remoteFileClient.setUpConnection();
        StringBuffer fileContents = new StringBuffer();
        fileContents.append(remoteFileClient.getFile("d:\\test.txt"));
        System.out.println("dssssssssss" + fileContents);
    }
}


