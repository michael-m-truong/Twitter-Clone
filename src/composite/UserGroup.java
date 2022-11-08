package composite;

import java.util.ArrayList;
import java.util.List;

import singleton.RootGroup;
import visitor.ITwitterVisitor;

public class UserGroup implements IUserCluster, IUserGroup {
    private List<IUserCluster> users = new ArrayList<>();
    private String UserGroupID;
    //IUserGroup root = RootGroup.getInstance();

    public UserGroup(String UserGroupID) {
        this.UserGroupID = UserGroupID;
    }

    @Override
    public void addUserCluster(IUserCluster user) {
        users.add(user);
    }

    // public void addUserCluster(IUserGroup user) {
    //     users.add(user);
    // }

    @Override
    public List<IUserCluster> getUserGroup() {
        // TODO Auto-generated method stub
        return users;
    }

    @Override
    public String getID() {
        // TODO Auto-generated method stub
        return UserGroupID;
    }

    public int accept(ITwitterVisitor visitor) {
        //System.out.println("test");
        return visitor.visit(this);
        // users.forEach(child -> {
        //     child.accept(visitor);
        //     //System.out.println("eeeee");
        // });
        //System.out.println(users.size());
        //System.out.println("test2");
    }
    
    
}
