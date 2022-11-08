package composite;

import visitor.CountUsersVisitor;
import visitor.ITwitterVisitor;

public interface IUserCluster {

    String getID();

    int accept(ITwitterVisitor visitor);
    
}
