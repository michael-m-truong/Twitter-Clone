package composite;

public class Tweet implements INotification{

    private String notification;

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
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setUserID(String userID) {
        // TODO Auto-generated method stub
        
    }
    
    
}
