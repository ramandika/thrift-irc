package irc;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

public class IRCClient {
    public static void main(String [] args) {
        try {
            TTransport transport;
            transport = new TSocket("localhost", 9090);
            transport.open();
            TProtocol protocol = new TBinaryProtocol(transport);
            ChatApplication.Client client = new ChatApplication.Client(protocol);
            perform(client);
            transport.close();
        } catch (TException x) {
            x.printStackTrace();
        }
    }
    private static void perform(ChatApplication.Client client) throws TException {
        try {
            int currentUId = 0;
            int anonUId = 0;
            String currentUser = null;
            boolean x = false;
            List<messageRecv> myMessages = new ArrayList<messageRecv>();
            while (true) {
                Scanner client_input = new Scanner(System.in);
                String input = client_input.nextLine();
                String[] buffer = input.split("\\s+");
                if (buffer.length > 0) {
                    switch (buffer[0]) {
                        case "/NICK":
                            if (buffer.length > 1) {
                                currentUId = client.createNick(buffer[1]);
                                currentUser = buffer[1];
                            } else {
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
                                client.leaveChannel(currentUId, buffer[1]);
                            }
                            break;
                        case "/EXIT":
                            return;
                        default:
                            messageSend M = new messageSend();
                            M.setUsrid(currentUId);
                            //String timeStamp = new SimpleDateFormat("ddMMyyyy_HHmmss").format(Calendar.getInstance().getTime())
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
                            if(client.sendMessage(M)){
                                myMessages = client.pullMessage(currentUId);
                                for(messageRecv m : myMessages){
                                    System.out.println("["+ m.getChname() +"] ("+ m.getNickname() +"):"+m.getMessage()+" ("+m.getTime()+")");
                                }
                            }
                            else{
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
