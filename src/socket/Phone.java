package socket;

import java.net.*;
import java.io.*;

public class Phone implements AutoCloseable {
    private ServerSocket server;
    private Socket clientSocket;
    private BufferedReader reader;
    private BufferedWriter writer;

    public Phone(Phone serverPhone){
        clientSocket = serverPhone.accept();
        createStreams();


    }

    public Phone(String port) {
        try {
             server = new ServerSocket(Integer.parseInt(port));

        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    public Phone(String path, String port) {
        try {
            clientSocket = new Socket(path, Integer.parseInt(port));
            createStreams();

        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void close() throws Exception {
        try {
            reader.close();
            writer.close();
            clientSocket.close();
        } catch (IOException e) {
            throw new RuntimeException();
        }

    }

    private Socket accept() {


        try {
            return clientSocket = server.accept();
        } catch (IOException e) {
            throw new RuntimeException();
        }


    }

    public String readLine() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "error";

    }

    public void writeLine(String message) {
        try {
            writer.write(message);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createStreams(){
        try {
            reader =
                    new BufferedReader(
                            new InputStreamReader(
                                    clientSocket.getInputStream()));
            writer =
                    new BufferedWriter(
                            new OutputStreamWriter(
                                    clientSocket.getOutputStream()));
        } catch (IOException e) {
            throw new RuntimeException();
        }

    }
    public void closeServer(){
        try {
            server.close();
        }
        catch (IOException e){
            throw new RuntimeException();
        }
    }


}
