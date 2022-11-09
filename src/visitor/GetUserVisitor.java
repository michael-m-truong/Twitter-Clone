package visitor;

import composite.User;
import composite.UserGroup;

public class GetUserVisitor{

    private String userID;
    private User user;

    public GetUserVisitor(String userID) {
        this.userID = userID;
    }

    public User visit(UserGroup userGroup) {
        System.out.println(userGroup.getID());
        userGroup.getUserGroup().forEach(child -> {
            if (child.findUser(this) != null) {
                user = child.findUser(this);
            }
            //System.out.println("eeeee");
        });
        return user;
    }

    public User visit(User user) {
        if (user.getID().equals(userID)) {
            return user;
        }
        return null;
    }
    

}
