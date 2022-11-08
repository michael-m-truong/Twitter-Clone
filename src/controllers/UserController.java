package controllers;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JTextField;

import composite.IUser;
import views.UserView;

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
    }

    public void display() {
        view.setVisible(true);
    }

    public void updateFollowingList() {
        JButton button = view.getFollowUserButton();
        DefaultListModel<String> model = view.getListModel();
        JTextField textField = view.getFollowUserTextField();
        button.addActionListener(e -> { 
            model.add(model.size(),textField.getText());
        });
    }
}
