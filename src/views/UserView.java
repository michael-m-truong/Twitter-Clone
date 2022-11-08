package views;

import javax.swing.JFrame;

public class UserView extends JFrame{
    
    public UserView(String userID){
        this.setSize(800,800);
        this.setLayout(null);
        this.setTitle(userID);
    }
}
