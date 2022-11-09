package composite;

import java.util.List;

public interface IUser extends IUserCluster{
    
    void followUser(User user);

    void tweetMessage(String msg);

    List<User> getCurrentFollowingList();

    List<String> getNewsfeedList();
}
