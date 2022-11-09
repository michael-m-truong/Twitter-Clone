package controllers;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import composite.IUser;
import composite.IUserCluster;
import composite.IUserGroup;
import composite.User;
import composite.UserGroup;
import singleton.RootGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import views.AdminView;
import views.TwitterTreeNode;
import views.UserView;
import visitor.CountUsersVisitor;
import visitor.GetUserVisitor;
import visitor.ITwitterVisitor;

public class AdminController {
    private AdminView view;
    private DefaultMutableTreeNode selectedNode;

    public AdminController(AdminView view) {
        this.view = view;
        initController();
    }

    public void initController() {
        saveUserID();
        saveUserGroupID();
        displayUserView();
    }

    public void display() {
        view.getTree().addTreeSelectionListener(e-> {
            selectedNode = (TwitterTreeNode) view.getTree().getLastSelectedPathComponent();
        });
        //DefaultTreeModel model = (DefaultTreeModel) view.getTree().getModel();
        view.setVisible(true);
    }

    public void displayUserView() {
        JButton button = view.getOpenUserViewButton();

        button.addActionListener(e -> {
            IUserGroup rootGroup = RootGroup.getInstance();
            List<IUserCluster> selectedUserGroup = rootGroup.getUserGroup();
            IUser selectedUser = null;
            for (int i = 1; i<selectedNode.getPath().length; i++) {
                System.out.println(selectedNode.getPath().length + "long");
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
            System.out.println(selectedUser.getID() + "IDDd");
            UserView userView = new UserView(selectedUser.getID());
            UserController userController = new UserController(userView, selectedUser);
            userController.display();
        });
    }

    public void saveUserID() {
        JButton button = view.getUserIDButton();

        button.addActionListener(e -> {
            //System.out.println("test");
            //System.out.println(view.getUserIDTextField().getText());
            String userID = view.getUserIDTextField().getText();
            if (selectedNode == null) {
                RootGroup root =  RootGroup.getInstance();
                selectedNode = root.getRootNode();
                //System.out.println("eeeeeee");
            }
            //System.out.println(selectedNode.isLeaf());
            //selectedNode.add(new TwitterTreeNode(userID, false));
            DefaultTreeModel model = (DefaultTreeModel) view.getTree().getModel();
            model.insertNodeInto(new TwitterTreeNode(userID, false), selectedNode, selectedNode.getChildCount()); // change index to 0 if wanted
            System.out.println(selectedNode.getPath());
            IUserGroup rootGroup = RootGroup.getInstance();
            List<IUserCluster> selectedUserGroup = rootGroup.getUserGroup();
            for (int i = 1; i<selectedNode.getPath().length; i++) {
                // for (int j = 0; j< selectedUserGroup.size(); j++) {
                //     if (selectedUserGroup.get(j).getID().equals(selectedNode.getPath()[i].toString())) {
                //         IUserGroup userGroup = (IUserGroup) selectedUserGroup.get(j);
                        
                //         break;
                //     }
                // }
                //System.out.println(selectedNode.getPath()[i]);
                //System.out.println(selectedNode.getPath()[i].getParent().getIndex(selectedNode.getPath()[i]));
                //System.out.println(selectedNode.getPath()[i]);
                IUserGroup tempSelectedGroup = (IUserGroup) selectedUserGroup.get(selectedNode.getPath()[i].getParent().getIndex(selectedNode.getPath()[i]));
                selectedUserGroup = tempSelectedGroup.getUserGroup();
                //System.out.println(selectedNode.getIndex(selectedNode.getPath()[i]));
                //System.out.println(selectedNode.getPath()[i].getParent().getIndex(selectedNode));
                //System.out.println(selectedNode.getParent().getIndex(selectedNode));
            }
            IUserCluster newUser = new User(userID);
            selectedUserGroup.add(newUser);
            ITwitterVisitor ElonMusk = new CountUsersVisitor();
            System.out.println(rootGroup.accept(ElonMusk));
            System.out.println(rootGroup.findUser(new GetUserVisitor(userID)).getID());
            System.out.println("-----------------------");
        });
    }

    public void saveUserGroupID() {
        JButton button = view.getUserGroupIDButton();

        button.addActionListener(e -> {
            //System.out.println("test");
            //System.out.println(view.getUserIDTextField().getText());
            String userGroupID = view.getUserGroupIDTextField().getText();
            if (selectedNode == null) {
                RootGroup root =  (RootGroup) RootGroup.getInstance();
                selectedNode = root.getRootNode();
                //System.out.println("eeeeeee");
            }
            //System.out.println(selectedNode.isLeaf());
            //selectedNode.add(new TwitterTreeNode(userID, false));
            DefaultTreeModel model = (DefaultTreeModel) view.getTree().getModel();
            model.insertNodeInto(new TwitterTreeNode(userGroupID, true), selectedNode, selectedNode.getChildCount()); // change index to 0 if wanted
            System.out.println(selectedNode.getPath());
            IUserGroup rootGroup = RootGroup.getInstance();
            List<IUserCluster> selectedUserGroup = rootGroup.getUserGroup();
            for (int i = 1; i<selectedNode.getPath().length; i++) {
                //System.out.println(selectedNode.getPath()[i]);
                IUserGroup tempSelectedGroup = (IUserGroup) selectedUserGroup.get(selectedNode.getPath()[i].getParent().getIndex(selectedNode.getPath()[i]));
                selectedUserGroup = tempSelectedGroup.getUserGroup();
            }
            IUserCluster newUserGroup = new UserGroup(userGroupID);
            selectedUserGroup.add(newUserGroup);
        });
    }
}
