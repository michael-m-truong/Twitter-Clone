package visitor;

import composite.User;
import composite.UserGroup;

public class CountGroupsVisitor implements ITwitterVisitor{
    private int count;

    public int visit(UserGroup userGroup) {
        count = 0;
        System.out.println(userGroup.getID());
        userGroup.getUserGroup().forEach(child -> {
            count+=child.accept(this);
            //System.out.println("eeeee");
        });
        //state+=count;
        return count+1;
    }

    public int visit(User user) {
        System.out.println(user.getID());
        return 0;
    }
}
