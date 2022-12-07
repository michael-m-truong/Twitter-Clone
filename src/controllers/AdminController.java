package controllers;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import composite.IUser;
import composite.IUserCluster;
import composite.IUserGroup;
import composite.User;
import composite.UserGroup;
import models.IAdminModel;
import singleton.RootGroup;

import java.util.ArrayList;
import java.util.List;

import views.AdminView;
import views.TwitterTreeNode;
import views.UserView;
import visitor.CountGroupsVisitor;
import visitor.CountMessagesVisitor;
import visitor.CountPositiveMessagesVisitor;
import visitor.CountUsersVisitor;
import visitor.GetLastUpdatedUserVisitor;
import visitor.ITwitterVisitor;
import visitor.VerifyValidUserAndGroupVisitor;

public class AdminController implements IAdminModel{
    private AdminView view;
    private DefaultMutableTreeNode selectedNode;
    private List<UserView> userViews = new ArrayList<>();

    public AdminController(AdminView view) {
        this.view = view;
        initController();
    }

    public void initController() {
        saveUserID();
        saveUserGroupID();
        displayUserView();
        displayStats();
    }

    public void display() {
        view.getTree().addTreeSelectionListener(e-> {
            selectedNode = (TwitterTreeNode) view.getTree().getLastSelectedPathComponent();
        });
        view.setVisible(true);
    }

    public void displayUserView() {
        JButton button = view.getOpenUserViewButton();

        button.addActionListener(e -> {
            IUserGroup rootGroup = RootGroup.getInstance();
            List<IUserCluster> selectedUserGroup = rootGroup.getUserGroup();
            IUser selectedUser = null;
            for (int i = 1; i<selectedNode.getPath().length; i++) {
                if (i != selectedNode.getPath().length-1) {
                    IUserGroup tempSelectedGroup = (IUserGroup) selectedUserGroup.get(selectedNode.getPath()[i].getParent().getIndex(selectedNode.getPath()[i]));
                    selectedUserGroup = tempSelectedGroup.getUserGroup();
                }
                else {
                    selectedUser = (IUser) selectedUserGroup.get(selectedNode.getPath()[i].getParent().getIndex(selectedNode.getPath()[i]));
                }
            }
            if (selectedUser == null) {
                return;
            }
            UserView userView = new UserView(selectedUser.getID(), (User) selectedUser);
            UserController userController = new UserController(userView, selectedUser);
            selectedUser.setUserController(userController);
            userViews.add(userView);
            for (UserView view : userViews) {
                if (view == userView) {
                    continue;
                }
                 else if (view.getUser().getID() == userView.getUser().getID()) {
                     userController.attach(view);
                    
                }
                else if (!view.getUser().getCurrentFollowingList().contains(selectedUser)) {
                    continue;
                }
                else {
                    userController.attach(view);
                    userController.getObservers();
                }
            }
            userController.display();
        });
    }

    public void saveUserID() {
        JButton button = view.getUserIDButton();

        button.addActionListener(e -> {
            String userID = view.getUserIDTextField().getText();
            if (selectedNode == null) {
                RootGroup root =  RootGroup.getInstance();
                selectedNode = root.getRootNode();
            }
            DefaultTreeModel model = (DefaultTreeModel) view.getTree().getModel();
            model.insertNodeInto(new TwitterTreeNode(userID, false), selectedNode, selectedNode.getChildCount()); // change index to 0 if wanted
            IUserGroup rootGroup = RootGroup.getInstance();
            List<IUserCluster> selectedUserGroup = rootGroup.getUserGroup();
            for (int i = 1; i<selectedNode.getPath().length; i++) {
                IUserGroup tempSelectedGroup = (IUserGroup) selectedUserGroup.get(selectedNode.getPath()[i].getParent().getIndex(selectedNode.getPath()[i]));
                selectedUserGroup = tempSelectedGroup.getUserGroup();
            }
            IUserCluster newUser = new User(userID, System.currentTimeMillis());
            selectedUserGroup.add(newUser);
        });
    }

    public void saveUserGroupID() {
        JButton button = view.getUserGroupIDButton();

        button.addActionListener(e -> {
            String userGroupID = view.getUserGroupIDTextField().getText();
            if (selectedNode == null) {
                RootGroup root =  (RootGroup) RootGroup.getInstance();
                selectedNode = root.getRootNode();
            }
            DefaultTreeModel model = (DefaultTreeModel) view.getTree().getModel();
            model.insertNodeInto(new TwitterTreeNode(userGroupID, true), selectedNode, selectedNode.getChildCount()); // change index to 0 if wanted
            IUserGroup rootGroup = RootGroup.getInstance();
            List<IUserCluster> selectedUserGroup = rootGroup.getUserGroup();
            for (int i = 1; i<selectedNode.getPath().length; i++) {
                IUserGroup tempSelectedGroup = (IUserGroup) selectedUserGroup.get(selectedNode.getPath()[i].getParent().getIndex(selectedNode.getPath()[i]));
                selectedUserGroup = tempSelectedGroup.getUserGroup();
            }
            IUserCluster newUserGroup = new UserGroup(userGroupID, System.currentTimeMillis());
            selectedUserGroup.add(newUserGroup);
        });
    }

    public void displayStats() {
        JButton userTotalButton = view.getShowUserTotalButton();
        JButton groupTotalButton = view.getShowGroupTotalButton();
        JButton messagesTotalButton = view.getShowMessageTotalButton();
        JButton positivePercentageButton = view.getShowPositivePercentageButton();
        JButton verifyValidUserAndGroupNameButton = view.getVerifyValidUserAndGroupNameButton();
        JButton showLastUpdatedUserButton = view.getShowLastUpdatedUserButton();

        userTotalButton.addActionListener(e -> {
            JFrame jframe = new JFrame();
            jframe.setSize(300, 300);
            JLabel stat = new JLabel();
            ITwitterVisitor visitor = new CountUsersVisitor();
            IUserGroup root = RootGroup.getInstance();
            stat.setText("Total users: " + String.valueOf(root.accept(visitor)));
            stat.setLocation(75,150);
            jframe.add(stat);
            jframe.setVisible(true);
        });
        groupTotalButton.addActionListener(e -> {
            JFrame jframe = new JFrame();
            jframe.setSize(300, 300);
            JLabel stat = new JLabel();
            ITwitterVisitor visitor = new CountGroupsVisitor();
            IUserGroup root = RootGroup.getInstance();
            stat.setText("Total groups: " + String.valueOf(root.accept(visitor)));
            stat.setLocation(75,150);
            jframe.add(stat);
            jframe.setVisible(true);
        });
        messagesTotalButton.addActionListener(e -> {
            JFrame jframe = new JFrame();
            jframe.setSize(300, 300);
            JLabel stat = new JLabel();
            ITwitterVisitor visitor = new CountMessagesVisitor();
            IUserGroup root = RootGroup.getInstance();
            stat.setText("Total messages: " + String.valueOf(root.accept(visitor)));
            stat.setLocation(75,150);
            jframe.add(stat);
            jframe.setVisible(true);
        });
        positivePercentageButton.addActionListener(e -> {
            JFrame jframe = new JFrame();
            jframe.setSize(300, 300);
            JLabel stat = new JLabel();
            ITwitterVisitor positiveVisitor = new CountPositiveMessagesVisitor();
            ITwitterVisitor totalMsgsVisitor = new CountMessagesVisitor();
            IUserGroup root = RootGroup.getInstance();
            double totalTweets = root.accept(totalMsgsVisitor);
            double goodTweets =  root.accept(positiveVisitor);
            if (totalTweets != 0) {
                stat.setText("Positive tweet percentage: " + ((goodTweets/totalTweets)*100) + "%");
            }
            else {
                stat.setText("Positive tweet percentage: 0%");
            }
            stat.setLocation(75,150);
            jframe.add(stat);
            jframe.setVisible(true);
        });
        verifyValidUserAndGroupNameButton.addActionListener(e -> {
            JFrame jframe = new JFrame();
            jframe.setSize(300, 300);
            JLabel stat = new JLabel();
            ITwitterVisitor verifyVisitor = new VerifyValidUserAndGroupVisitor();
            IUserGroup root = RootGroup.getInstance();
            int verify = root.accept(verifyVisitor);
            if (verify < 0) {
                stat.setText("There contains INVALID users/usergroups");
            }
            else {
                stat.setText("All users/usergroups are VALID");
            }
            stat.setLocation(75,150);
            jframe.add(stat);
            jframe.setVisible(true);
        });
        showLastUpdatedUserButton.addActionListener(e -> {
            System.out.println("eeee");
            JFrame jframe = new JFrame();
            jframe.setSize(300, 300);
            JLabel stat = new JLabel();
            GetLastUpdatedUserVisitor lastUpdatedVisitor = new GetLastUpdatedUserVisitor();
            IUserGroup root = RootGroup.getInstance();
            String lastUpdatedUser = root.accept(lastUpdatedVisitor);
            stat.setText("Last updated user: " + lastUpdatedUser);
            stat.setLocation(75,150);
            jframe.add(stat);
            jframe.setVisible(true);
            
        });
    }
}
