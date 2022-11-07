package composite;

import visitor.TwitterVisitor;

public interface IUserCluster {

    String getID();

    void accept(TwitterVisitor visitor);
    
}
