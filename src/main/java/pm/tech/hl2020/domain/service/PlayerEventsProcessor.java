package pm.tech.hl2020.domain.service;

import org.springframework.stereotype.Service;
import pm.tech.hl2020.domain.model.Tournament;
import pm.tech.hl2020.domain.model.event.PlayerEvent;

import java.util.Map;

@Service
public class PlayerEventsProcessor {
  private TournamentDao tournamentDao;

  public PlayerEventsProcessor(TournamentDao tournamentDao) {
    this.tournamentDao = tournamentDao;
  }

  public void handlePlayerEvent(PlayerEvent event) {
    tournamentDao.getActiveTournaments().entrySet().stream()
        .filter(entry -> isGameValidForTournament(event.getGameId(), entry.getValue()))
        .forEach(entry -> {
          applyPlayersPointsToTournament(event.getPlayerId(), event.getPoints(), entry.getValue());
          updateTournament(entry.getKey(), entry.getValue());
        });
  }

  private boolean isGameValidForTournament(String gameId, Tournament tournament) {
    return tournament.getGameIds().contains(gameId);
  }

  private void applyPlayersPointsToTournament(String playerId, Integer newPoints, Tournament tournament) {
    Map<String, Integer> leaderboardPoints = tournament.getLeaderboard().getPoints();

    Integer currentPlayersPoints = leaderboardPoints.getOrDefault(playerId, 0);
    leaderboardPoints.put(playerId, currentPlayersPoints + newPoints);
  }

  private void updateTournament(String tournamentId, Tournament tournament) {
    tournamentDao.updateTournament(tournamentId, tournament);

  }

}
