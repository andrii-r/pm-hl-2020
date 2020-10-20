package pm.tech.hl2020;

import pm.tech.hl2020.messages.PlayerEvent;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Leaderboard {
    Integer winningPlaces;
    Map<String, Integer> points;

    // TODO: move to interface
    public void apply(PlayerEvent playerEvent) {
        points.putIfAbsent(playerEvent.getPlayerId(), 0);
        Integer playerPoints = points.get(playerEvent.getPlayerId());
        points.put(playerEvent.getPlayerId(), playerPoints + playerEvent.getPoints());
    }

    // TODO: move to interface
    public Map<String, Integer> getLeaderboardTable() {
        return points.entrySet().stream().sorted(Map.Entry.comparingByValue()).limit(winningPlaces).collect(
            Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1,e2) -> e1, LinkedHashMap::new));
    }
}
