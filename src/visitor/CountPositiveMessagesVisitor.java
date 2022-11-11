package visitor;

import java.util.List;

import composite.User;
import composite.UserGroup;

public class CountPositiveMessagesVisitor implements ITwitterVisitor{

    private int count;
    private String[] positiveWords = {"good", "great", "amazing"};

    public int visit(UserGroup userGroup) {
        count = 0;
        userGroup.getUserGroup().forEach(child -> {
            count+=child.accept(this);
        });
        return count;
    }

    public int visit(User user) {
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
