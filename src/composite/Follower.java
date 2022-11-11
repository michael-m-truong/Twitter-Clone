package composite;

public class Follower implements INotification{

    private String notification;
    private String userID;

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

    public void setUserID(String userID) {
        this.userID = userID;
    }

    
    
}
