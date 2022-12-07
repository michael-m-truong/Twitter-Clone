package singleton;

import composite.UserGroup;
import views.TwitterTreeNode;

public class RootGroup extends UserGroup  {
    private static RootGroup root;
    private TwitterTreeNode rootNode;

    private RootGroup(){ 
        super("Root", System.currentTimeMillis());
    }

    public static RootGroup getInstance() {
    
        if (root == null) {
            root = new RootGroup();
        }

        return root;
    }

    public TwitterTreeNode getRootNode() {
        return rootNode;
    }

    public void setRootNode(TwitterTreeNode node) {
        this.rootNode = node;
    }

    

}
