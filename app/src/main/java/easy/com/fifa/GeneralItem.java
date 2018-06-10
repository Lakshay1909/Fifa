package easy.com.fifa;

public class GeneralItem extends ListItem {

    private Fixturelist fixturelist;

    public Fixturelist getFixturelist() {
        return fixturelist;
    }

    public void setFixturelist(Fixturelist fixturelist) {
        this.fixturelist = fixturelist;
    }


    @Override
    public int getType() {
        return TYPE_GENERAL;
    }
}
