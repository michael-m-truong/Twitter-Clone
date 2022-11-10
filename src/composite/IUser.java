package composite;

import java.util.List;

import controllers.UserController;

public interface IUser extends IUserCluster{
    
    void followUser(User user);

    void tweetMessage(String msg);

    List<User> getCurrentFollowingList();

    List<String> getNewsfeedList();

    public UserController getUserController();

    public void setUserController(UserController userController);
}
