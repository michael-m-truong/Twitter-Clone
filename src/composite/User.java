package composite;

import java.util.ArrayList;
import java.util.List;

import observer.Observer;
import observer.Subject;
import visitor.GetUserVisitor;
import visitor.ITwitterVisitor;

public class User extends Subject implements IUserCluster, IUser, Observer {
    private String UserID;
    private List<User> currentFollowing = new ArrayList<>();
    private List<String> newsfeed = new ArrayList<>();
    private List<String> userTweets = new ArrayList<>();
    //private String latestTweet;  /* this is the state */

    public User(String UserID) {
        this.UserID = UserID;
    }

    @Override
    public void followUser(User user) {
        currentFollowing.add(user);
        
    }

    @Override
    public void tweetMessage(String msg) {
        userTweets.add(msg);
        System.out.println("size: " + userTweets.size());
        newsfeed.add(msg);
        notifyFollowers();
    }

    public void notifyFollowers() {
        for (Observer user: this.getObservers()) {
            user.update(this);
        }
    }

    @Override
    public void update(Subject user) {
        System.out.println(((User) user).getID());
        System.out.println(userTweets.size());
        System.out.println("user tweets: " + userTweets);
        this.addToNewsFeed(((User) user).getLatestTweet());
        
    }

    public String getLatestTweet() {
        return userTweets.get(userTweets.size()-1);
    }

    @Override
    public String getID() {
        // TODO Auto-generated method stub
        return UserID;
    }

    @Override
    public int accept(ITwitterVisitor visitor) {
        return visitor.visit(this);
        // TODO Auto-generated method stub
        
    }

    public User findUser(GetUserVisitor visitor) {
        return visitor.visit(this);
    }

    public List<User> getCurrentFollowingList() {
        return currentFollowing;
    }

    public List<String> getNewsfeedList() {
        return newsfeed;
    }

    public void addToNewsFeed(String tweet) {
        newsfeed.add(tweet);
    }
    
    
}
