package easy.com.fifa.Database;

/**
 * Created by Hp on 6/10/2018.
 */

public class Formresponse {
    int id;
    String timestamp;
    String name;
    String prediction;
    int position;
    int score;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Formresponse(int id, String timestamp, String name, String prediction) {
        this.id = id;
        this.timestamp = timestamp;
        this.name = name;
        this.prediction = prediction;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrediction() {
        return prediction;
    }

    public void setPrediction(String prediction) {
        this.prediction = prediction;
    }
}
