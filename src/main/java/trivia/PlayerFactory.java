package trivia;

import java.util.ArrayList;
import java.util.List;

public class PlayerFactory {

    private final List<Player> players = new ArrayList<>();

    private int index = 0 ;

    boolean with(Player player) {
        return players.add(player);
    }

    void next() {
        index++;
        index = index == players.size() ? 0 : index;
    }

    Player extract() {
        return players.get(index);
    }

    int totalPlayers() {
        return players.size();
    }

}
