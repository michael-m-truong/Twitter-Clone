package visitor;

import java.util.List;

import composite.User;
import composite.UserGroup;

public class CountPositiveMessagesVisitor implements ITwitterVisitor{

    private int count;
    private String[] positiveWords = {"good", "great", "amazing"};

    public int visit(UserGroup userGroup) {
        System.out.println(userGroup.getID());
        userGroup.getUserGroup().forEach(child -> {
            count+=child.accept(this);
            //System.out.println("eeeee");
        });
        //state+=count;
        return count;
    }

    public int visit(User user) {
        System.out.println(user.getID());
        count = 0;
        List<String> totalTweets = user.getUserTweets();
        for (String tweet: totalTweets) {
            for (String word: positiveWords) {
                if (tweet.toLowerCase().contains(word)) {
                    count+=1;
                    break;
                }
            }
        }
        return count;
    }
    
}
