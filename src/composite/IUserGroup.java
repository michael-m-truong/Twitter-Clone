package composite;

import java.util.List;

public interface IUserGroup extends IUserCluster{
    
    void addUserCluster(IUserCluster user);

    List<IUserCluster> getUserGroup();

}
