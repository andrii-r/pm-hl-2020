package pm.tech.hl2020.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import pm.tech.hl2020.domain.model.event.PlayerEvent;

import java.util.Set;

@Data
@AllArgsConstructor
public class Tournament {
    private Set<String> gameIds;
    private Leaderboard leaderboard;
    private TournamentState state;
}
