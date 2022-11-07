package views;

import javax.swing.tree.DefaultMutableTreeNode;

public class TwitterTreeNode extends DefaultMutableTreeNode{

    /**
     * This class is used to override this isLeaf function in the parent class DefaultMutableTreeNode
     * The isLeaf method determines whether or not the folder icon or the file icon shows
     * Leafs are rendered with the file icon
     * To make empty userGroups possible, I overrided the isLeaf function so that it is not a leaf it it doesn't allow children
    */

    public TwitterTreeNode(String nodeID, boolean allowsChildren) {
        super(nodeID, allowsChildren);
    }

    @Override
    public boolean isLeaf() {
        // TODO Auto-generated method stub
        return !super.allowsChildren;
    }
}
