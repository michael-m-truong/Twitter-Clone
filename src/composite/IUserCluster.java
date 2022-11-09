package composite;

import visitor.CountUsersVisitor;
import visitor.GetUserVisitor;
import visitor.ITwitterVisitor;

public interface IUserCluster {

    String getID();

    int accept(ITwitterVisitor visitor);
    
    User findUser(GetUserVisitor visitor);
}
