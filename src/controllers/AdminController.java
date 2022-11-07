package controllers;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import composite.IUserGroup;
import singleton.RootGroup;

import java.util.ArrayList;
import java.util.Vector;

import views.AdminView;
import views.TwitterTreeNode;

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
    }

    public void display() {
        view.getTree().addTreeSelectionListener(e-> {
            selectedNode = (TwitterTreeNode) view.getTree().getLastSelectedPathComponent();
        });
        //DefaultTreeModel model = (DefaultTreeModel) view.getTree().getModel();
        view.setVisible(true);
    }

    public void saveUserID() {
        JButton button = view.getUserIDButton();

        button.addActionListener(e -> {
            //System.out.println("test");
            //System.out.println(view.getUserIDTextField().getText());
            String userID = view.getUserIDTextField().getText();
            if (selectedNode == null) {
                RootGroup root =  (RootGroup) RootGroup.getInstance();
                selectedNode = root.getRootNode();
                System.out.println("eeeeeee");
            }
            System.out.println(selectedNode.isLeaf());
            //selectedNode.add(new TwitterTreeNode(userID, false));
            DefaultTreeModel model = (DefaultTreeModel) view.getTree().getModel();
            model.insertNodeInto(new TwitterTreeNode(userID, false), selectedNode, selectedNode.getChildCount()); // change index to 0 if wanted
            System.out.println(selectedNode.getPath());
            for (int i = 0; i<selectedNode.getPath().length; i++) {
                System.out.println(selectedNode.getPath()[i]);
            }
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
                System.out.println("eeeeeee");
            }
            System.out.println(selectedNode.isLeaf());
            //selectedNode.add(new TwitterTreeNode(userID, false));
            DefaultTreeModel model = (DefaultTreeModel) view.getTree().getModel();
            model.insertNodeInto(new TwitterTreeNode(userGroupID, true), selectedNode, selectedNode.getChildCount()); // change index to 0 if wanted
            System.out.println(selectedNode.getPath());
            for (int i = 0; i<selectedNode.getPath().length; i++) {
                System.out.println(selectedNode.getPath()[i]);
            }
        });
    }
}
