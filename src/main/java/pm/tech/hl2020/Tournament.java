package pm.tech.hl2020;

import lombok.AllArgsConstructor;
import lombok.Data;
import pm.tech.hl2020.messages.PlayerEvent;

import java.util.Set;

@Data
@AllArgsConstructor
public class Tournament {
    private Set<String> gameIds;
    private Leaderboard leaderboard;
    private Boolean active;

    // TODO: move to interface
    public void apply(PlayerEvent playerEvent) {
        leaderboard.apply(playerEvent);
    }
}
