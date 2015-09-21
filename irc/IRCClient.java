package irc;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import java.util.*;

public class IRCClient {
    private static int currentUId = -1;
    private static int anonUId = 0;
    private static String currentUser = null;
    private static List<messageRecv> myMessages = new ArrayList<messageRecv>();

    public static void main(String [] args) {
        try {
            TTransport transport;
            transport = new TFramedTransport(new TSocket("localhost", 9090));
            transport.open();
            TProtocol protocol = new TBinaryProtocol(transport);
            ChatApplication.Client client = new ChatApplication.Client(protocol);
            perform(client);
            transport.close();
        } catch (Exception x) {
            x.printStackTrace();
        }
    }



    private static void perform(ChatApplication.Client client) throws TException {
        try {
            Timer timer = new Timer();
            TimerTask asyncTask = new TimerTask() {
                @Override
                public void run() {
                    try{
                        if(currentUId>-1 && currentUser!=null) {
                            myMessages = client.pullMessage(currentUId);
                            if (!myMessages.isEmpty()) {
                                for (messageRecv m : myMessages) {
                                    System.out.println("[" + m.getChname() + "] (" + m.getNickname() + "):" + m.getMessage() + " (" + m.getTime() + ")");
                                }
                            }
                        }
                    } catch(TException x){
                        x.printStackTrace();
                    }

                }
            };
            timer.schedule(asyncTask, 0, 100);
            Scanner client_input = new Scanner(System.in);
            while (true) {
                String input = client_input.nextLine();
                String[] buffer = input.split("\\s+");
                if (buffer.length > 0) {
                    switch (buffer[0]) {
                        case "/NICK":
                            if (buffer.length > 1) {
                                if(currentUser==null){
                                    currentUId = client.createNick(buffer[1]);
                                    if(currentUId>-1){
                                        currentUser = buffer[1];
                                    }
                                    else{
                                        System.out.println("Username exist ! Please use another nickname");
                                    }
                                }
                                else{
                                    System.out.println("You are logged in as " + currentUser + ". Reopen the client to use another nickname");
                                }
                            }
                            else {
                                currentUser = "user_"+anonUId;
                                currentUId = client.createNick(currentUser);
                                anonUId++;
                            }
                            break;
                        case "/JOIN":
                            if (buffer.length > 1) {
                                client.joinChannel(currentUId, buffer[1]);
                                System.out.println("You have joined " +buffer[1]);
                            } else {
                                client.joinChannel(currentUId, "general");
                                System.out.println("You have joined general channel");
                            }
                            break;
                        case "/LEAVE":
                            if (buffer.length > 1) {
                                if(!client.leaveChannel(currentUId, buffer[1])){
                                    System.out.println("channel not found");
                                }
                            }
                            break;
                        case "/EXIT":
                            timer.cancel();
                            timer.purge();
                            client.exit(currentUId);
                            return;
                        default:
                            messageSend M = new messageSend();
                            M.setUsrid(currentUId);
                            M.setTime(client.getServerTime());
                            if (buffer[0].contains("@")) {
                                M.setChname(buffer[0].substring(1));
                                StringBuilder sb = new StringBuilder();
                                for (int i = 1; i < buffer.length; i++) {
                                    sb.append(buffer[i]);
                                }
                                M.setMessage(sb.toString());
                            } else {
                                StringBuilder sb = new StringBuilder();
                                for (int i = 0; i < buffer.length; i++) {
                                    sb.append(buffer[i]);
                                }
                                M.setMessage(sb.toString());
                            }
                            if(!client.sendMessage(M)){
                                System.out.println("channel unknown, please join a channel or recheck your message");
                            }
                    }
                }
            }
        } catch(TException x){
            x.printStackTrace();
        }
    }

}
