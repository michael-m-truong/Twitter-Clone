package views;

import java.awt.Color;
import java.awt.Font;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTree;

import java.awt.FlowLayout;

import singleton.RootGroup;

public class AdminView extends JFrame{
    
    private JTextField userIDTextField;
    private JTextField userGroupIDTextField;
    private JButton userIDButton;
    private JButton userGroupIDButton;
    private JButton openUserViewButton;
    private JPanel buttonPanel;
    private JPanel treePanel;
    private JLabel userIDLabel;
    private JLabel userGroupIDLabel;
    private JTree tree;
    private JButton showUserTotalButton;
    private JButton showGroupTotalButton;
    private JButton showMessagesTotalButton;
    private JButton showPositivePercentageButton;


    public AdminView(){
        
        RootGroup root = RootGroup.getInstance();
        TwitterTreeNode rootNode = new TwitterTreeNode(root.getID(), true);
        root.setRootNode(rootNode);
        
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
        openUserViewButton = new JButton("Open User View");
        showUserTotalButton = new JButton("Show User Total");
        showGroupTotalButton = new JButton("Show Group Total");
        showMessagesTotalButton = new JButton("Show Messages Total");
        showPositivePercentageButton = new JButton("Show Positive Percentage");

        userIDTextField.setSize(80, 25);
        userIDButton.setSize(150, 100);
        userIDLabel.setSize(80,25);
        userGroupIDTextField.setSize(80, 25);
        userGroupIDButton.setSize(150, 100);
        userGroupIDLabel.setSize(80,25);
        buttonPanel.setSize(400, 800);
        treePanel.setSize(400, 800);
        openUserViewButton.setSize(300,60);
        showUserTotalButton.setSize(190, 50);
        showGroupTotalButton.setSize(190, 50);
        showMessagesTotalButton.setSize(190, 50);
        showPositivePercentageButton.setSize(190, 50);

        userIDButton.setLocation(225,100);
        userIDTextField.setLocation(125, 100);
        userIDLabel.setLocation(0,100);
        userGroupIDButton.setLocation(225,200);
        userGroupIDTextField.setLocation(125, 200);
        userGroupIDLabel.setLocation(0,200);
        buttonPanel.setBackground(new Color(29,161,242));
        buttonPanel.setLocation(400, 0);
        treePanel.setLocation(0, 0);
        tree.setLocation(50,00);
        openUserViewButton.setLocation(50,350);
        treePanel.setBackground(Color.LIGHT_GRAY);
        showUserTotalButton.setLocation(10, 500);
        showGroupTotalButton.setLocation(200, 500);
        showMessagesTotalButton.setLocation(10, 650);
        showPositivePercentageButton.setLocation(200, 650);
        final Font currentFont = tree.getFont();
        final Font bigFont = new Font(currentFont.getName(), currentFont.getStyle(), currentFont.getSize() + 10);
        tree.setFont(bigFont);

        this.setLayout(null);
        buttonPanel.setLayout(null);

        this.add(buttonPanel);
        this.add(treePanel);
        buttonPanel.add(userIDButton);
        buttonPanel.add(userIDTextField);
        buttonPanel.add(userIDLabel);
        buttonPanel.add(userGroupIDButton);
        buttonPanel.add(userGroupIDTextField);
        buttonPanel.add(userGroupIDLabel);
        buttonPanel.add(openUserViewButton);
        buttonPanel.add(showUserTotalButton);
        buttonPanel.add(showGroupTotalButton);
        buttonPanel.add(showMessagesTotalButton);
        buttonPanel.add(showPositivePercentageButton);
        treePanel.add(tree);

    }


    public JButton getUserIDButton() { return userIDButton; }

    public JTextField getUserIDTextField() { return userIDTextField; }

    public JButton getUserGroupIDButton() { return userGroupIDButton; }

    public JTextField getUserGroupIDTextField() { return userGroupIDTextField; }

    public JButton getOpenUserViewButton() { return openUserViewButton; }

    public JTree getTree() { return tree; }

    public JButton getShowUserTotalButton() { return showUserTotalButton; }

    public JButton getShowGroupTotalButton() {return showGroupTotalButton; }

    public JButton getShowMessageTotalButton() {return showMessagesTotalButton; }

    public JButton getShowPositivePercentageButton() {return showPositivePercentageButton; }


}
