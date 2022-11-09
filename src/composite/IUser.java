package composite;

public interface IUser extends IUserCluster{
    
    void followUser(User user);

    void tweetMessage(String msg);
}
