package controllers;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JTextField;

import composite.Follower;
import composite.IUser;
import composite.IUserGroup;
import composite.Tweet;
import composite.User;
import models.IUserModel;
import observer.Observer;
import observer.Subject;
import singleton.RootGroup;
import views.UserView;
import visitor.GetUserVisitor;

public class UserController extends Subject implements IUserModel{
    private UserView view;
    private IUser user;

    public UserController(UserView view, IUser user) {
        this.view = view;
        this.user = user;
        initController();
    }

    public void initController() {
        updateFollowingList();
        updateNewsFeed();
        view.initializeFollowingList(user);
        view.initializeNewsfeed(user);
    }

    public void display() {
        view.setVisible(true);
    }

    public void updateFollowingList() {
        JButton button = view.getFollowUserButton();
        DefaultListModel<String> model = view.getListModel();
        JTextField textField = view.getFollowUserTextField();
        button.addActionListener(e -> { 
            IUserGroup rootGroup = RootGroup.getInstance();
            User followedUser = rootGroup.findUser(new GetUserVisitor(textField.getText()));
            user.followUser((User) followedUser);
            followedUser.attach((Observer) user);
            model.add(model.size(),textField.getText());
            if (followedUser.getUserController() != null){
                followedUser.getUserController().attach(view);
            }
            Follower follower = new Follower();
            follower.setNotification(user.getID());
            follower.setUserID(user.getID());
            setLatestData(follower);
            notifyObservers();
        });
        
    }

    public void updateNewsFeed() {
        JButton button = view.getTweetButton();
        DefaultListModel<String> model = view.getTweetListModel();
        JTextField textField = view.getTweetTextField();
        button.addActionListener(e -> { 
            user.tweetMessage(user.getID() + ": " + textField.getText());
            model.add(model.size(), user.getID() + ": " + textField.getText());
            Tweet tweet = new Tweet();
            tweet.setNotification(user.getID() + ": " + textField.getText());
            setLatestData(tweet);
            notifyObservers();
        });
        
    }


}
