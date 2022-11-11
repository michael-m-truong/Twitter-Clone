package composite;

import java.util.ArrayList;
import java.util.List;

import controllers.UserController;
import observer.Observer;
import observer.Subject;
import visitor.GetUserVisitor;
import visitor.ITwitterVisitor;

public class User extends Subject implements IUser, Observer {
    private String UserID;
    private List<User> currentFollowing = new ArrayList<>();
    private List<String> newsfeed = new ArrayList<>();
    private List<String> userTweets = new ArrayList<>();
    private UserController controller = null;

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
    
    
}
