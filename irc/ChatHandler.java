package irc;

import irc.ChatApplication.Iface;
import org.apache.thrift.TException;

import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Created by ramandika on 19/09/15.
 */
public class ChatHandler implements Iface {
    private Integer lastUId=0;
    private static Map<Integer,List<messageSend>> chatbox;
    private static Map<Integer,String> activeUsers;
    private static Map<String,ArrayList<Integer>> channelActive;
    private static Map<Integer,ArrayList<String>> userChannels;
    public ChatHandler(){
        chatbox=new HashMap<Integer, List<messageSend>>();
        activeUsers=new HashMap<Integer, String>();
        channelActive=new HashMap<String, ArrayList<Integer>>();
        userChannels=new HashMap<Integer, ArrayList<String>>();
    }

    private List<messageSend> addBasedOnTime(messageSend r,List<messageSend> lr){
        int size=lr.size();
        if(size==0){lr.add(r);return lr;}
        for(int i=0;i<size;i++) {
            if (lr.get(i).getTime() > r.getTime()) {
                lr.add(i, r);
                return lr;
            }
        }
        lr.add(r);
        return lr;
    }

    @Override
    public boolean sendMessage(messageSend m) throws TException{
        try {
            int UId = m.getUsrid();
            String chname = m.getChname();
            if (chname == null) {//Send to all channel user belongs to
                ArrayList<String> channels = userChannels.get(m.getUsrid());
                if(channels.size()>0) {
                    for (String channel : channels) {
                        ArrayList<Integer> users = channelActive.get(channel);
                        m.setChname(channel);
                        if(users.contains(UId)){
                            for (Integer user : users) {
                                List<messageSend> messages = chatbox.get(user);
                                messageSend r=new messageSend();
                                r.setChname(m.getChname());
                                r.setMessage(m.getMessage());
                                r.setUsrid(m.getUsrid());
                                r.setTime(m.getTime());
                                //Add r based on time
                                messages=addBasedOnTime(r,messages);
                                System.out.println(m);
                            }
                        }
                        else{
                            throw new Exception("a");
                        }
                    }
                }
                else{
                    throw new Exception("b");
                }
            } else {
                ArrayList<Integer> users = channelActive.get(chname);
                if(users.contains(UId)) {
                    for (Integer user : users) {
                        List<messageSend> messages = chatbox.get(user);
                        messageSend r=new messageSend();
                        r.setChname(m.getChname());
                        r.setMessage(m.getMessage());
                        r.setUsrid(m.getUsrid());;
                        r.setTime(m.getTime());
                        //Add r based on time
                        messages=addBasedOnTime(r,messages);
                        System.out.println(m);
                    }
                }
                else{
                    throw new Exception("c");
                }
            }
        } catch(Exception x){
            System.out.println(x.getMessage());
            System.out.println(m.getChname() +" channel not found");
            return false;
        }
        return true;
    }

    @Override
    public boolean joinChannel(int uId,String chname) throws TException{
        if(channelActive.get(chname)!=null){
            ArrayList<Integer> users=channelActive.get(chname);
            users.add(uId);
            if(userChannels.get(uId)!=null){
                ArrayList<String> channels=userChannels.get(uId);
                channels.add(chname);
            }
            else{
                ArrayList<String> channels=new ArrayList<String>();
                channels.add(chname);
                userChannels.put(uId, channels);
            }
        }else{
            ArrayList<Integer> users=new ArrayList<Integer>();
            users.add(uId);
            channelActive.put(chname, users);
            System.out.println("channel " + chname + " created");
            if(userChannels.get(uId)!=null){
                ArrayList<String> channels=userChannels.get(uId);
                channels.add(chname);
            }
            else{
                ArrayList<String> channels=new ArrayList<String>();
                channels.add(chname);
                userChannels.put(uId, channels);
            }
        }

        System.out.println(activeUsers.get(uId) + " join " +chname);
        return true;
    }

    @Override
    public boolean leaveChannel(int uId,String chname) throws TException{
        ArrayList<Integer> users=channelActive.get(chname);
        ArrayList<String> channels=userChannels.get(uId);
        try{
            if(users!=null && channels!=null) {
                users.remove(uId);
                channels.remove(chname);
                System.out.println(activeUsers.get(uId) + " leave " + chname);
                return true;
            }
        } catch(Exception x){
            x.printStackTrace();
        }
        return false;
    }

    @Override
    public List<messageRecv> pullMessage(int userId) throws TException{
        List<messageRecv> l=new ArrayList<>();
        if(chatbox.get(userId)!=null) {
            for (messageSend m : chatbox.get(userId)) {
                messageRecv temp = new messageRecv();
                temp.setNickname(activeUsers.get(m.getUsrid()));
                SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm");
                Date resultdate = new Date(m.getTime());
                temp.setTime(sdf.format(resultdate));
                temp.setMessage(m.getMessage());
                temp.setChname(m.getChname());
                l.add(temp);
            }
            chatbox.get(userId).clear();
        }
        return l;
    }

    @Override
    public int createNick(String nickname) throws TException{
        for (Map.Entry<Integer, String> entry : activeUsers.entrySet())
        {
            if(nickname.equals(entry.getValue())){
                return -1; //username exist
            }
        }
        List<messageSend> messages = new ArrayList<messageSend>();
        activeUsers.put(lastUId, nickname);
        chatbox.put(lastUId, messages);
        System.out.println("created new User : " + nickname);
        lastUId++;
        return lastUId-1;
    }

    @Override
    public boolean exit(int uId) throws TException{
        userChannels.remove(uId);
        activeUsers.remove(uId);
        chatbox.remove(uId);
        return true;
    }

    @Override
    public long getServerTime() throws TException {
        return System.currentTimeMillis();
    }


}

