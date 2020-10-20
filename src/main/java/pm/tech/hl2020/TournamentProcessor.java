package pm.tech.hl2020;

import pm.tech.hl2020.messages.PlayerEvent;
import pm.tech.hl2020.messages.TournamentFinished;
import pm.tech.hl2020.messages.TournamentStarted;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class TournamentProcessor {
    private Map<String, Tournament> tournaments;
    private Map<String, List<Tournament>> gamesToTournaments;

    // TODO: move to interface
    void apply(TournamentStarted message) {
        Tournament newTournament = new Tournament(message.getGameIds(), new Leaderboard(), true);
        tournaments.put(message.getTournamentId(),
            newTournament);
        gamesToTournaments.putIfAbsent(message.getTournamentId(), Collections.emptyList());
        gamesToTournaments.get(message.getTournamentId()).add(newTournament);
    }

    // TODO: move to interface
    void apply(TournamentFinished message) {
        tournaments.get(message.getTournamentId()).setActive(false);
    }

    // TODO: move to interface
    void apply(PlayerEvent message) {
        gamesToTournaments.get(message.getGameId()).forEach( t -> t.apply(message));
    }
}
