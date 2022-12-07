package composite;

import java.util.ArrayList;
import java.util.List;

import visitor.GetLastUpdatedUserVisitor;
import visitor.GetUserVisitor;
import visitor.ITwitterVisitor;

public class UserGroup implements IUserGroup {
    private List<IUserCluster> users = new ArrayList<>();
    private String UserGroupID;
    private long creationTime;

    public UserGroup(String UserGroupID, long creationTime) {
        this.UserGroupID = UserGroupID;
        this.creationTime = creationTime;
    }

    @Override
    public void addUserCluster(IUserCluster user) {
        users.add(user);
    }

    @Override
    public List<IUserCluster> getUserGroup() {
        return users;
    }

    @Override
    public String getID() {
        return UserGroupID;
    }

    public int accept(ITwitterVisitor visitor) {
        return visitor.visit(this);
    }
    
    public User findUser(GetUserVisitor visitor) {
        return visitor.visit(this);
    }

    public String accept(GetLastUpdatedUserVisitor visitor) {
        return visitor.visit(this);
    }

}
