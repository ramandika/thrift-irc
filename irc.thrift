namespace java irc

typedef i32 int
typedef i64 long
typedef bool boolean
typedef string String
typedef string Timestamp


struct messageSend{
	1: int usrid,
	2: String message,
	3: String chname,
	4: long time,
}

struct messageRecv{
	1: String nickname,
	2: String message,
	3: String chname,
	4: Timestamp time,
}
service ChatApplication
{
	boolean sendMessage(1:messageSend m),
	boolean joinChannel(1:int uId,2:String chname),
	boolean leaveChannel(1:int uId,2:String chname),
	list<messageRecv> pullMessage(1:int userId),
	int createNick(1:String nickname),
	boolean exit(1:int userId),
	long getServerTime(),
}