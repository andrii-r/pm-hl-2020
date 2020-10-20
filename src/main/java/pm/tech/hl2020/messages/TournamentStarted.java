package pm.tech.hl2020.messages;

import lombok.Data;

import java.util.Set;

@Data
public class TournamentStarted {
    String tournamentId;
    Set<String> gameIds;
}
