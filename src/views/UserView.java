package views;

import java.awt.Color;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import composite.Follower;
import composite.IUser;
import composite.Tweet;
import composite.User;
import observer.Observer;
import observer.Subject;

public class UserView extends JFrame implements Observer{
    private JPanel followPanel;
    private JTextField followUserTextField;
    private JButton followUserButton;
    private JList<String> followingList;
    private JScrollPane followingListPane;
    private DefaultListModel<String> model;

    private JPanel tweetPanel;
    private JTextField tweetTextField;
    private JButton tweetButton;
    private JList<String> newsfeed;
    private JScrollPane tweetListPane;
    private DefaultListModel<String> tweetListModel;

    private User user;

    public UserView(String userID, User user){
        
        this.user = user;
        this.setSize(600,800);
        this.setLayout(null);
        this.setTitle(userID);

        model = new DefaultListModel<>();
        tweetListModel = new DefaultListModel<>();

        followPanel = new JPanel();
        followUserTextField = new JTextField();
        followUserButton = new JButton("Follow User");
        followingList = new JList<String>(model);
        tweetButton = new JButton("Tweet Message");
        tweetPanel = new JPanel();
        tweetTextField = new JTextField();
        tweetListPane = new JScrollPane();
        tweetListModel = new DefaultListModel<>();
        newsfeed = new JList<>(tweetListModel);
        


        followingListPane = new JScrollPane(followingList);
        tweetListPane = new JScrollPane(newsfeed);
    

        followUserButton.setSize(150,50);
        followUserTextField.setSize(145, 25);
        followPanel.setSize(600, 200);
        followingListPane.setSize(600,200);
        followPanel.setBackground(new Color(29,161,242));
        followingListPane.setBackground(Color.lightGray);
        tweetButton.setSize(150,50);
        tweetTextField.setSize(145, 25);
        tweetPanel.setSize(600,180);
        tweetPanel.setBackground(new Color(29,161,242));
        tweetListPane.setSize(600,220);
        tweetListPane.setBackground(Color.lightGray);


        followUserButton.setLocation(300, 25);
        followUserTextField.setLocation(25, 25);
        followingList.setLocation(25, 50);
        followingListPane.setLocation(0, 200);
        tweetButton.setLocation(300,25);
        tweetTextField.setLocation(25,25);
        tweetPanel.setLocation(0,400);
        tweetListPane.setLocation(0, 580);
        


        this.setLayout(null);
        this.add(followPanel);
        this.add(followingListPane);
        this.add(tweetPanel);
        this.add(tweetListPane);
        followPanel.add(followUserButton);
        followPanel.add(followUserTextField);
        followPanel.setLayout(null);
        tweetPanel.add(tweetButton);
        tweetPanel.add(tweetTextField);
        tweetPanel.setLayout(null);
        
    }

    public void initializeFollowingList(IUser user) {
        for (User userFollowing : user.getCurrentFollowingList()) {
            model.add(model.size(),userFollowing.getID());
        }
    }

    public void initializeNewsfeed(IUser user) {
        for (String tweet : user.getNewsfeedList()) {
            tweetListModel.add(tweetListModel.size(), tweet);
        }
    }

    public JButton getFollowUserButton() { return followUserButton; }

    public DefaultListModel<String> getListModel() { return model; }  //make visitor to search

    public JList<String> getJList() { return followingList; }

    public JTextField getFollowUserTextField() { return followUserTextField; }

    public JButton getTweetButton() { return tweetButton; }

    public DefaultListModel<String> getTweetListModel() { return tweetListModel; }

    public JList<String> getNewsfeed() {return newsfeed; }

    public JTextField getTweetTextField() {return tweetTextField; }

    @Override
    public void update(Subject user) {
        if (user.getLatestData() instanceof Tweet) {
            tweetListModel.add(tweetListModel.size(),user.getLatestData().getNotification());
        }
        else if (user.getLatestData() instanceof Follower) {
            if (this.user.getID() == user.getLatestData().getUserID()) {
                model.add(model.size(), user.getLatestData().getNotification());
            }
        }
    }

    public User getUser() {
        return user;
    }


}
