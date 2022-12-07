package visitor;

import composite.User;
import composite.UserGroup;

public class GetLastUpdatedUserVisitor{

    private String userID;
    private User user;
    private String lastUpdatedUser;
    private long latestUpdateTime;

    public String visit(UserGroup userGroup) {
        userGroup.getUserGroup().forEach(child -> {
            long userUpdatedTime = ((User) child).getLastUpdateTime();
            if (userUpdatedTime > latestUpdateTime) {
                latestUpdateTime = userUpdatedTime;
                lastUpdatedUser = child.getID();
            }
            child.accept(this);
        });
        return lastUpdatedUser;
    }

    public String visit(User user2) {
        return null;
    }
    
}
