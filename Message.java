
public class Message {
   private int messageId =0;
   private String content;
   private String sentAt;
   private User sender;
   public Message(String content,String sentAt,User sender){
    this.content=content;
    this.sentAt= sentAt;
    this.sender = sender;
    messageId++;
   }
   public int getMessageId(){
      return this.messageId;
   }
   public User getSender(){
      return this.sender;
   }
}
