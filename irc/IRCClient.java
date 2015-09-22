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
    private static Object obj=new Object();
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



    private static void perform(final ChatApplication.Client client) throws TException {
        try {
            Timer timer = new Timer();
            TimerTask asyncTask = new TimerTask() {
                @Override
                public void run() {
                    try{
                        if (currentUId > -1 && currentUser != null) {
                            synchronized (client) {
                                myMessages = client.pullMessage(currentUId);
                            }
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
            String buffer,content;
            while (true) {
                String input = client_input.nextLine();
                if(!input.isEmpty()) {
                    if (input.contains(" ")) {
                        buffer = input.substring(0, input.indexOf(' '));
                        content = input.substring(input.indexOf(' ') + 1, input.length());
                    }else{
                        buffer=input;
                        content=input;
                    }
                    if (buffer.length() > 0) {
                        switch (buffer) {
                            case "/NICK":
                                if (!content.isEmpty()) {
                                    if(currentUser==null){
                                        synchronized (client) {
                                            currentUId = client.createNick(content);
                                        }
                                        if(currentUId>-1){
                                            currentUser = content;
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
                                    synchronized (client) {
                                        currentUId = client.createNick(currentUser);
                                        anonUId++;
                                    }
                                }
                                break;
                            case "/JOIN":
                                if (!content.isEmpty()) {
                                    synchronized (client) {
                                        client.joinChannel(currentUId, content);
                                        System.out.println("You have joined " + content);
                                    }
                                } else {
                                    synchronized (client) {
                                        client.joinChannel(currentUId, "general");
                                        System.out.println("You have joined general channel");
                                    }
                                }
                                break;
                            case "/LEAVE":
                                if (!content.isEmpty()) {
                                    synchronized (client) {
                                        if (!client.leaveChannel(currentUId, content)) {
                                            System.out.println("channel not found");
                                        }
                                    }
                                }
                                break;
                            case "/EXIT":
                                timer.cancel();
                                timer.purge();
                                synchronized (client) {
                                    client.exit(currentUId);
                                }
                                return;
                            default:
                                messageSend M = new messageSend();
                                M.setUsrid(currentUId);
                                synchronized (client) {
                                    M.setTime(client.getServerTime());
                                }
                                if (buffer.contains("@")) {
                                    M.setChname(buffer.substring(1));
                                    M.setMessage(content);
                                } else {
                                    M.setMessage(input);
                                }
                                synchronized (client) {
                                    if (!client.sendMessage(M)) {
                                        System.out.println("channel unknown, please join a channel or recheck your message");
                                    }
                                }
                        }
                    }
                }else{
                    System.out.println("format input wrong");
                }

            }
        } catch(TException x){
            x.printStackTrace();
        }
    }

}
