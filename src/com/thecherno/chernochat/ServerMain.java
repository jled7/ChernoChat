package com.thecherno.chernochat;

/**
 * Created by usuario on 10/09/14.
 */
public class ServerMain {

    private int port;

    public ServerMain(int port) {
        this.port = port;
    }

    public static void main(String[] args){
        int port;
        if(args.length != 1){
            System.out.println("Usage: java -jar ChernoChatServer.jar [port]");
            return;
        }
        port = Integer.parseInt(args[0]);
        new ServerMain(port);

    }
}
