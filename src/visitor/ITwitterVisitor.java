package visitor;

import composite.User;
import composite.UserGroup;

public interface ITwitterVisitor {
    
    int visit(UserGroup user);

    int visit(User user);
}
