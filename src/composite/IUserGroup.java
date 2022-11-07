package composite;

import java.util.ArrayList;
import java.util.List;

public interface IUserGroup extends IUserCluster{
    
    void addUserCluster(IUserCluster user);

    List<IUserCluster> getUserGroup();

    /**
     * Interface because in the future what if we want to delete user
     */
}
