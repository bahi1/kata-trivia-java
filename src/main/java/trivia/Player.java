package trivia;

public class Player {
    private final String name;
    private int place;
    private int purse;
    private boolean penalized;
    private boolean gettingOutOfPenaltyBox;

    public Player(String name) {
        this.name = name;
    }


    void penalize() {
        this.penalized = true;
    }

    void getOutOfPenaltyBox(boolean isGettingOutOfPenaltyBox) {
        this.gettingOutOfPenaltyBox = isGettingOutOfPenaltyBox;
    }

    void getNewPlace(int newPlace) {

        this.place = this.place + newPlace;
        if (this.place > 11) this.place = this.place - 12;
    }

    public void winPurse() {
        this.purse++;
    }

    public String getName() {
        return name;
    }

    public int getPlace() {
        return place;
    }

    public int getPurse() {
        return purse;
    }

    public boolean isPenalized() {
        return penalized;
    }

    public boolean isGettingOutOfPenaltyBox() {
        return gettingOutOfPenaltyBox;
    }
}
