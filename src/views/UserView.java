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

public class UserView extends JFrame{
    private JPanel followPanel;
    private JTextField followUserTextField;
    private JButton followUserButton;
    private JList<String> followingList;
    private JScrollPane followingListPane;
    private DefaultListModel<String> model;

    public UserView(String userID){
        this.setSize(600,800);
        this.setLayout(null);
        this.setTitle(userID);

        //currentFollowing[0] = "my";
        //currentFollowing[1] = "mom";
        model = new DefaultListModel<>();

        followPanel = new JPanel();
        followingListPane = new JScrollPane();
        followUserTextField = new JTextField();
        followUserButton = new JButton("Follow User");
        followingList = new JList<String>(model);
        model.add(model.size(), "test");
        model.add(model.size(), "efee");
        model.add(model.size(), "tr");


        //followingList.setVisibleRowCount(2);
        followingListPane = new JScrollPane(followingList);

        followUserButton.setSize(150,50);
        followUserTextField.setSize(145, 25);
        followPanel.setSize(600, 200);
        followingListPane.setSize(600,200);
        followPanel.setBackground(new Color(29,161,242));
        followingListPane.setBackground(Color.lightGray);
        //followingList.setSize(200, 200);

        followUserButton.setLocation(300, 25);
        followUserTextField.setLocation(25, 25);
        followingList.setLocation(25, 50);
        followingListPane.setLocation(0, 200);

        


        this.setLayout(null);
        this.add(followPanel);
        this.add(followingListPane);
        //this.add(followingListPane);
        //followingListPane.setLayout(null);
        followPanel.add(followUserButton);
        followPanel.add(followUserTextField);
        followPanel.setLayout(null);
        //followingListPane.add(followingList);
        //followPanel.add(followingListPane);
    }

    public JButton getFollowUserButton() { return followUserButton; }

    public DefaultListModel<String> getListModel() { return model; }  //make visitor to search

    public JList<String> getJList() { return followingList; }

    public JTextField getFollowUserTextField() { return followUserTextField; }
}
