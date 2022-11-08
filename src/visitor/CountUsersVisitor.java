package visitor;

import composite.IUserCluster;
import composite.User;
import composite.UserGroup;

public class CountUsersVisitor implements ITwitterVisitor{
    
    public int visit(UserGroup userGroup) {
        int[] count = {0};
        System.out.println(userGroup.getID());
        userGroup.getUserGroup().forEach(child -> {
            count[0]+=child.accept(this);
            //System.out.println("eeeee");
        });
        return count[0];
    }

    public int visit(User user) {
        System.out.println(user.getID());
        return 1;
    }
}
