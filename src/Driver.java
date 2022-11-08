import java.awt.Font;
import java.util.List;

import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import composite.IUserCluster;
import composite.IUserGroup;
import composite.User;
import composite.UserGroup;
import controllers.AdminController;
import singleton.RootGroup;
import views.AdminView;
import visitor.CountUsersVisitor;
import visitor.ITwitterVisitor;

public class Driver {
    public static void main(String[] args) {
        IUserGroup root = RootGroup.getInstance();
        /*IUserCluster p1 = new User("Michael");
        IUserCluster p2 = new User("Nick");
        IUserGroup g1 = new UserGroup("School Friends");
        IUserGroup g2 = new UserGroup("Basketball");
        IUserCluster p3 = new User("Abdullah");
        root.addUserCluster(p1);
        root.addUserCluster(p2);
        root.addUserCluster(g1);
        g1.addUserCluster(g2);
        g2.addUserCluster(p3); */
        ITwitterVisitor ElonMusk = new CountUsersVisitor();
        System.out.println(root.accept(ElonMusk));
        // List<IUserCluster> users = root.getUserGroup();
        // for (int i = 0; i < users.size(); i++) {
        //     System.out.println(users.get(i).getID());
        // }
        AdminController adminController = new AdminController(new AdminView());
        adminController.display();
        //gui_test();
    }

    public static void gui_test() {
        JFrame jframe = new JFrame();
        jframe.setSize(400,400);
        DefaultMutableTreeNode school = new DefaultMutableTreeNode("school");
        DefaultMutableTreeNode n1 = new DefaultMutableTreeNode("nick");
        DefaultMutableTreeNode n2 = new DefaultMutableTreeNode("uzi");
        DefaultMutableTreeNode n3 = new DefaultMutableTreeNode("andrew");
        DefaultMutableTreeNode n4 = new DefaultMutableTreeNode("ryan");
        DefaultMutableTreeNode n5 = new DefaultMutableTreeNode("basketball");
        DefaultMutableTreeNode n6 = new DefaultMutableTreeNode("abdullah");
        n5.add(n6);
        school.add(n1);
        school.add(n2);
        school.add(n3);
        school.add(n4);
        school.add(n5);
        JTree tree = new JTree(school);
        
        jframe.add(tree);
        jframe.setVisible(true);
        final Font currentFont = tree.getFont();
        final Font bigFont = new Font(currentFont.getName(), currentFont.getStyle(), currentFont.getSize() + 10);
        tree.setFont(bigFont);
    }
}
