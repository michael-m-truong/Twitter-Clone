package composite;

import java.util.ArrayList;
import java.util.List;

import controllers.UserController;
import observer.Observer;
import observer.Subject;
import visitor.GetLastUpdatedUserVisitor;
import visitor.GetUserVisitor;
import visitor.ITwitterVisitor;

public class User extends Subject implements IUser, Observer {
    private String UserID;
    private List<User> currentFollowing = new ArrayList<>();
    private List<String> newsfeed = new ArrayList<>();
    private List<String> userTweets = new ArrayList<>();
    private UserController controller = null;
    private long creationTime;
    private long lastUpdateTime;

    public User(String UserID, long creationTime) {
        this.UserID = UserID;
        this.creationTime = creationTime;
        this.lastUpdateTime = creationTime;   // the first time updated is when created
    }

    @Override
    public void followUser(User user) {
        currentFollowing.add(user);
        
    }

    @Override
    public void tweetMessage(String msg) {
        userTweets.add(msg);
        newsfeed.add(msg);
        notifyFollowers(System.currentTimeMillis());
    }

    public void notifyFollowers(long lastUpdateTime) {
        this.setLastUpdateTime(lastUpdateTime);
        for (Observer user: this.getObservers()) {
            user.update(this);
            ((User) user).setLastUpdateTime(lastUpdateTime);
        }
    }

    @Override
    public void update(Subject user) {
        this.addToNewsFeed(((User) user).getLatestTweet());
        
    }

    public String getLatestTweet() {
        return userTweets.get(userTweets.size()-1);
    }

    @Override
    public String getID() {
        return UserID;
    }

    @Override
    public int accept(ITwitterVisitor visitor) {
        return visitor.visit(this);
        
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

    public UserController getUserController() {
        return controller;
    }

    public void setUserController(UserController userController) {
        this.controller = userController;
    }

    public List<String> getUserTweets() {
        return userTweets;
    }
    
    public long getCreationTime() {
        return creationTime;
    }

    public long getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(long lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    @Override
    public String accept(GetLastUpdatedUserVisitor visitor) {
        return visitor.visit(this);
    }

    
}
