package views;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.LayoutManager;
import java.lang.instrument.UnmodifiableModuleException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.text.FlowView;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import java.awt.FlowLayout;

import composite.IUserCluster;
import composite.IUserGroup;
import singleton.RootGroup;

public class AdminView extends JFrame{
    
    private JTextField userIDTextField;
    private JTextField userGroupIDTextField;
    private JButton userIDButton;
    private JButton userGroupIDButton;
    private JPanel buttonPanel;
    private JPanel treePanel;
    private JLabel userIDLabel;
    private JLabel userGroupIDLabel;
    private JTree tree;
    //private JLabel test;

    public AdminView(){
        
        RootGroup root = (RootGroup) RootGroup.getInstance();
        TwitterTreeNode rootNode = new TwitterTreeNode(root.getID(), true);
        root.setRootNode(rootNode);
        TwitterTreeNode n1 = new TwitterTreeNode("test", true);
        TwitterTreeNode n2 = new TwitterTreeNode("testing", true);
        rootNode.add(n1);
        n1.add(n2);
    
        
        //renderer.setOpenIcon();
        
        buttonPanel = new JPanel();
        treePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        tree = new JTree(rootNode);
        

        this.setSize(800,800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        userIDTextField = new JTextField();
        userIDButton = new JButton("Save UserID");
        userIDLabel = new JLabel("UserID");
        userGroupIDTextField = new JTextField();
        userGroupIDButton = new JButton("Save User Group ID");
        userGroupIDLabel = new JLabel("User GroupID");
        //test = new JLabel("testttt");

        userIDTextField.setSize(80, 25);
        userIDButton.setSize(150, 100);
        userIDLabel.setSize(80,25);
        userGroupIDTextField.setSize(80, 25);
        userGroupIDButton.setSize(150, 100);
        userGroupIDLabel.setSize(80,25);
        buttonPanel.setSize(400, 800);
        treePanel.setSize(400, 800);
        //test.setSize(80,25);

        userIDButton.setLocation(225,100);
        userIDTextField.setLocation(125, 100);
        userIDLabel.setLocation(0,100);
        userGroupIDButton.setLocation(225,200);
        userGroupIDTextField.setLocation(125, 200);
        userGroupIDLabel.setLocation(0,200);
        buttonPanel.setBackground(Color.gray);
        buttonPanel.setLocation(400, 0);
        treePanel.setLocation(0, 0);
        tree.setLocation(50,00);
        treePanel.setBackground(Color.LIGHT_GRAY);
        final Font currentFont = tree.getFont();
        final Font bigFont = new Font(currentFont.getName(), currentFont.getStyle(), currentFont.getSize() + 10);
        tree.setFont(bigFont);
        //test.setLocation(400, );

        this.setLayout(null);
        buttonPanel.setLayout(null);
        //treePanel.setLayout();

        this.add(buttonPanel);
        this.add(treePanel);
        buttonPanel.add(userIDButton);
        buttonPanel.add(userIDTextField);
        buttonPanel.add(userIDLabel);
        buttonPanel.add(userGroupIDButton);
        buttonPanel.add(userGroupIDTextField);
        buttonPanel.add(userGroupIDLabel);
        treePanel.add(tree);
        //treePanel.add(test);
        //treePanel.setAlignmentX(Component.RIGHT_ALIGNMENT);

    }


    public JButton getUserIDButton() { return userIDButton; }

    public JTextField getUserIDTextField() { return userIDTextField; }

    public JButton getUserGroupIDButton() { return userGroupIDButton; }

    public JTextField getUserGroupIDTextField() { return userGroupIDTextField; }

    public JTree getTree() { return tree; }
}
