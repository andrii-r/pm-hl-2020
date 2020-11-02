package pm.tech.hl2020.domain.service;

import org.springframework.stereotype.Repository;
import pm.tech.hl2020.domain.model.Tournament;

import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.stream.Collectors.toMap;
import static pm.tech.hl2020.domain.model.TournamentState.ACTIVE;

@Repository
public class TournamentDao {
  private Map<String, Tournament> tournaments = new ConcurrentHashMap<>();

  public Map<String, Tournament> getActiveTournaments() {
    return tournaments.entrySet().stream()
        .filter(entry -> entry.getValue().getState() == ACTIVE)
        .collect(toMap(Entry::getKey, Entry::getValue));
  }

  public String createNewTournament(Tournament tournament) {
    String newId = UUID.randomUUID().toString();

    tournaments.put(newId, tournament);

    return newId;
  }

  public Tournament getTournamentById(String id) {
    return tournaments.get(id);
  }

  public void updateTournament(String tournamentId, Tournament tournament) {
    tournaments.put(tournamentId, tournament);
  }

}
