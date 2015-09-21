package irc;

import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TNonblockingServerTransport;


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
            TNonblockingServerTransport serverTransport = new TNonblockingServerSocket(9090);
            TServer server = new THsHaServer(new THsHaServer.Args(serverTransport).processor(processor));
            System.out.println("Starting the server...");
            server.serve();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
