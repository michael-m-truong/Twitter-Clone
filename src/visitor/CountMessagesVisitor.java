package visitor;

import composite.User;
import composite.UserGroup;

public class CountMessagesVisitor implements ITwitterVisitor{
    
    private int count;

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
        return user.getUserTweets().size();
    }
}
