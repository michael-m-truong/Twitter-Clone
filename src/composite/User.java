package composite;

import visitor.ITwitterVisitor;

public class User implements IUserCluster, IUser {
    private String UserID;

    public User(String UserID) {
        this.UserID = UserID;
    }

    @Override
    public void followUser(IUserCluster user) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void tweetMessage(String msg) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public String getID() {
        // TODO Auto-generated method stub
        return UserID;
    }

    @Override
    public int accept(ITwitterVisitor visitor) {
        return visitor.visit(this);
        // TODO Auto-generated method stub
        
    }
    
}
