package composite;

import java.util.List;

import visitor.GetLastUpdatedUserVisitor;

public interface IUserGroup extends IUserCluster{
    
    void addUserCluster(IUserCluster user);

    List<IUserCluster> getUserGroup();

    String accept(GetLastUpdatedUserVisitor lastUpdatedVisitor);

}
