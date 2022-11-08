package composite;

public interface IUser extends IUserCluster{
    
    void followUser(IUserCluster user);

    void tweetMessage(String msg);
}
