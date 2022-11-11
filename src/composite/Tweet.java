package composite;

public class Tweet implements INotification{

    private String userID;
    private String notification;

    @Override
    public String getNotification() {
        return notification;
    }

    @Override
    public void setNotification(String notification) {
        this.notification = notification;
        
    }

    public String getUserID() {
        return userID;
    }
    
}
