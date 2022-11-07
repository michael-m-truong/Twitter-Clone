package visitor;

import composite.IUserCluster;

public class TwitterVisitor {
    
    public void visit(IUserCluster userCluster) {
        System.out.println(userCluster.getID());
    }
}
