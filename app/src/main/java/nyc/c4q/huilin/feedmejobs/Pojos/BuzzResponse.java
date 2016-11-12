package nyc.c4q.huilin.feedmejobs.Pojos;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Akasha on 11/11/16.
 */

public class BuzzResponse {

    private List<BuzzArticles> buzzArticles = new ArrayList<BuzzArticles>();
    private String sortBy;
    private String source;
    private String status;

    //    public List<BuzzArticles> getBuzzArticles() {
//        return buzzArticles;
//    }

    public List<BuzzArticles> getBuzzArticles() {
        return buzzArticles;
    }


    public void setBuzzArticles(List<BuzzArticles> buzzArticles) {
        this.buzzArticles = buzzArticles;
    }

    public String getSortBy () {
        return sortBy;
    }

    public void setSortBy (String sortBy) {
        this.sortBy = sortBy;
    }

    public String getSource () {
        return source;
    }

    public void setSource (String source) {
        this.source = source;
    }

    public String getStatus () {
        return status;
    }

    public void setStatus (String status) {
        this.status = status;
    }


}
