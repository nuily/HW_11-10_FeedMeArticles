package nyc.c4q.huilin.feedmejobs;

/**
 * Created by huilin on 10/30/16.
 */

public class Data {

    private String drinkName;
    private String alcoholType;

    public Data(String drinkName, String alcoholType) {
        this.drinkName = drinkName;
        this.alcoholType = alcoholType;
    }


    public String getDrinkName() {
        return drinkName;
    }

    public void setDrinkName(String drinkName) {
        this.drinkName = drinkName;
    }

    public String getAlcoholType() {
        return alcoholType;
    }

    public void setAlcoholType(String alcoholType) {
        this.alcoholType = alcoholType;
    }
