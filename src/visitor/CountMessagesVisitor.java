package visitor;

import composite.User;
import composite.UserGroup;

public class CountMessagesVisitor implements ITwitterVisitor{
    
    private int count;

    public int visit(UserGroup userGroup) {
        count = 0;
        userGroup.getUserGroup().forEach(child -> {
            count+=child.accept(this);
        });
        return count;
    }

    public int visit(User user) {
        return user.getUserTweets().size();
    }
}
