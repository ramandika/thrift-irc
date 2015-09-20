package irc;

import org.apache.thrift.server.TServer;

import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;

/**
 * Created by nim_13512086 on 9/19/15.
 */
public class IRCServer {

    public static ChatHandler handler;

    public static ChatApplication.Processor processor;
    public static void main(String [] args) {
        try {
            handler = new ChatHandler();
            processor = new ChatApplication.Processor(handler);
            Runnable simple = new Runnable() {
                public void run() {
                    simple(processor);
                }
            };
            new Thread(simple).start();
        } catch (Exception x) {
            x.printStackTrace();
        }
    }
    public static void simple(ChatApplication.Processor processor) {
        try {
            TServerTransport serverTransport = new TServerSocket(9090);
            TServer server = new TThreadPoolServer(new TThreadPoolServer.Args(serverTransport).processor(processor));
            System.out.println("Starting the simple server...");
            server.serve();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
