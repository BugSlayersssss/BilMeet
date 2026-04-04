import java.time.LocalDateTime;

public class Message {
   private static  int messageIdCounter =1;
   private String messageId;
   private String content;   private User sender;
   private LocalDateTime sentAt;
   public Message(String content,User sender){
    this.content=content;
    this.sentAt= LocalDateTime.now();
    this.sender = sender;
    this.messageId="M"+ messageIdCounter++;
   }
   public User getSender(){
      return this.sender;
   }
   public String getContent(){
      return this.content;
   }
   public LocalDateTime getSentAt(){
      return this.sentAt;
   }
}
