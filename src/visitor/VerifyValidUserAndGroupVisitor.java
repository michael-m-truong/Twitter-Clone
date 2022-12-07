package visitor;

import java.util.HashSet;
import java.util.Set;

import composite.User;
import composite.UserGroup;

public class VerifyValidUserAndGroupVisitor implements ITwitterVisitor{

    private int count;
    private boolean valid = true;
    private Set<String> numOfUniqueUserClusters = new HashSet<>();

    /**
     * If visitor returns a negative number, there contains INVALID user/usergroups
     * If visitor returns a number > 0, all user/usergroups are VALID
     */
    public int visit(UserGroup userGroup) {
        count = 0;
        userGroup.getUserGroup().forEach(child -> { 
            if (!numOfUniqueUserClusters.contains(child.getID()) && !child.getID().contains(" ") && child.getID().length() != 0) {
                numOfUniqueUserClusters.add(child.getID());
                count+=child.accept(this);
            }
            else {
                valid = false;
                return;
            }
        });
        if (!valid) {            
            return ((-1*count) - 1);  // we do -1 in the case the first user is invalid; first userID contains space
        }
        return count;
    }

    public int visit(User user) {
        return 1;
    }
    
}
