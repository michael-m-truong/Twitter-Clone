package composite;

import java.util.ArrayList;
import java.util.List;

import visitor.GetUserVisitor;
import visitor.ITwitterVisitor;

public class User implements IUserCluster, IUser {
    private String UserID;
    private List<User> currentFollowing = new ArrayList<>();

    public User(String UserID) {
        this.UserID = UserID;
    }

    @Override
    public void followUser(User user) {
        currentFollowing.add(user);
        
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

    public User findUser(GetUserVisitor visitor) {
        return visitor.visit(this);
    }
    
}
