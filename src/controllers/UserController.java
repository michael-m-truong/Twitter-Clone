package controllers;

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

    }

    public void display() {
        view.setVisible(true);
    }

}
