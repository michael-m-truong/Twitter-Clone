package views;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import composite.IUser;
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


    public UserView(String userID){
        

        this.setSize(600,800);
        this.setLayout(null);
        this.setTitle(userID);

        //currentFollowing[0] = "my";
        //currentFollowing[1] = "mom";
        model = new DefaultListModel<>();
        tweetListModel = new DefaultListModel<>();

        followPanel = new JPanel();
        //followingListPane = new JScrollPane();
        followUserTextField = new JTextField();
        followUserButton = new JButton("Follow User");
        followingList = new JList<String>(model);
        // model.add(model.size(), "test");
        // model.add(model.size(), "efee");
        // model.add(model.size(), "tr");
        tweetButton = new JButton("Tweet Message");
        tweetPanel = new JPanel();
        tweetTextField = new JTextField();
        tweetListPane = new JScrollPane();
        tweetListModel = new DefaultListModel<>();
        newsfeed = new JList<>(tweetListModel);
        


        //followingList.setVisibleRowCount(2);
        followingListPane = new JScrollPane(followingList);
        tweetListPane = new JScrollPane(newsfeed);
    

        followUserButton.setSize(150,50);
        followUserTextField.setSize(145, 25);
        followPanel.setSize(600, 200);
        followingListPane.setSize(600,200);
        followPanel.setBackground(new Color(29,161,242));
        followingListPane.setBackground(Color.lightGray);
        //followingList.setSize(200, 200);
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
        //this.add(followingListPane);
        //followingListPane.setLayout(null);
        followPanel.add(followUserButton);
        followPanel.add(followUserTextField);
        followPanel.setLayout(null);
        //followingListPane.add(followingList);
        //followPanel.add(followingListPane);
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
        System.out.println(user.getNewsfeedList());
        System.out.println("observers: " + ((Subject) user).getObservers());
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
        model.add(model.size(), user.getLatestData().get(0));
        tweetListModel.add(tweetListModel.size(),user.getLatestData().get(1));
        System.out.println("notified!!!!!!!!");
    }


}
