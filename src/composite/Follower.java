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

    @Override
    public String getUserID() {
        return userID;
    }

    @Override
    public void setUserID(String userID) {
        this.userID = userID;
        
    }

    
    
}
