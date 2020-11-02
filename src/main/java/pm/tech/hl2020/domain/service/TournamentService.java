package pm.tech.hl2020.domain.service;

import org.springframework.stereotype.Service;
import pm.tech.hl2020.domain.model.Leaderboard;
import pm.tech.hl2020.domain.model.Tournament;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import static java.util.stream.Collectors.toMap;

@Service
public class TournamentService {
  private TournamentDao tournamentDao;

  public TournamentService(TournamentDao tournamentDao) {
    this.tournamentDao = tournamentDao;
  }

  public Map<String, Tournament> getTournamentsActiveForPlayer(String playerId) {
    return tournamentDao.getActiveTournaments().entrySet().stream()
        .filter(entry -> isGameValidForPlayer(playerId, entry.getValue()))
        .collect(toMap(Entry::getKey, Entry::getValue));
  }

  private boolean isGameValidForPlayer(String playerId, Tournament tournament) {
    return tournament.getLeaderboard().getPoints().containsKey(playerId);
  }

  public Map<String, Integer> getLeaderboardTable(String tournamentId) {
    Leaderboard leaderboard = tournamentDao.getTournamentById(tournamentId).getLeaderboard();

    return leaderboard.getPoints().entrySet().stream()
        .sorted(Entry.comparingByValue()).limit(leaderboard.getWinningPlaces())
        .collect(toMap(Entry::getKey, Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
  }

}
