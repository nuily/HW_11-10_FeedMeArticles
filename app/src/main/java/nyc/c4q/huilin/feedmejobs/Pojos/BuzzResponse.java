package nyc.c4q.huilin.feedmejobs.Pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Akasha on 11/11/16.
 */

public class BuzzResponse {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("source")
    @Expose
    private String source;
    @SerializedName("sortBy")
    @Expose
    private String sortBy;
    @SerializedName("articles")
    @Expose
    private List<BuzzArticles> buzzArticlesList = new ArrayList<BuzzArticles>();


    public List<BuzzArticles> getBuzzArticles() {
        return buzzArticlesList;
    }

    public void setBuzzArticles(List<BuzzArticles> buzzArticles) {
        this.buzzArticlesList = buzzArticles;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
