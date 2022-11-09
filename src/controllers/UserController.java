package controllers;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JTextField;

import composite.IUser;
import composite.IUserGroup;
import composite.User;
import singleton.RootGroup;
import views.UserView;
import visitor.GetUserVisitor;

public class UserController {
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
            user.followUser(followedUser);
            model.add(model.size(),textField.getText());
            System.out.println(followedUser);
        });
    }

    public void updateNewsFeed() {
        JButton button = view.getTweetButton();
        DefaultListModel<String> model = view.getTweetListModel();
        JTextField textField = view.getTweetTextField();
        button.addActionListener(e -> { 
            model.add(model.size(),textField.getText());
        });
    }
}
