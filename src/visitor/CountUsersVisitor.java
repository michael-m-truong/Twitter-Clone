package visitor;

import composite.IUserCluster;
import composite.User;
import composite.UserGroup;

public class CountUsersVisitor implements ITwitterVisitor{
    
    private int state;
    private int count;

    public CountUsersVisitor() {
        this.state = 0;
    }    

    public int visit(UserGroup userGroup) {
        count = 0;
        System.out.println(userGroup.getID());
        userGroup.getUserGroup().forEach(child -> {
            count+=child.accept(this);
            //System.out.println("eeeee");
        });
        //state+=count;
        return count;
    }

    public int visit(User user) {
        System.out.println(user.getID());
        return 1;
    }
}
