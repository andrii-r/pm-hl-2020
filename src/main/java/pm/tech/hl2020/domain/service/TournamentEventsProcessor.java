package pm.tech.hl2020.domain.service;

import org.springframework.stereotype.Service;
import pm.tech.hl2020.domain.model.Leaderboard;
import pm.tech.hl2020.domain.model.Tournament;
import pm.tech.hl2020.domain.model.event.TournamentCreated;
import pm.tech.hl2020.domain.model.event.TournamentFinished;
import pm.tech.hl2020.domain.model.event.TournamentStarted;
import pm.tech.hl2020.domain.model.event.TournamentUpdated;

import java.util.HashMap;

import static pm.tech.hl2020.domain.model.TournamentState.ACTIVE;
import static pm.tech.hl2020.domain.model.TournamentState.FINISHED;
import static pm.tech.hl2020.domain.model.TournamentState.PENDING;

@Service
public class TournamentEventsProcessor {
  private TournamentDao tournamentDao;

  public TournamentEventsProcessor(TournamentDao tournamentDao) {
    this.tournamentDao = tournamentDao;
  }

  public String handleTournamentCreated(TournamentCreated event) {
    Leaderboard newLeaderBoard = new Leaderboard(event.getWinningPlaces(), new HashMap<>());
    Tournament newTournament = new Tournament(event.getGameIds(), newLeaderBoard, PENDING);
    return tournamentDao.createNewTournament(newTournament);
  }

  public void handleTournamentUpdated(TournamentUpdated event) {
    Leaderboard newLeaderBoard = new Leaderboard(event.getWinningPlaces(), new HashMap<>());

    Tournament tournament = tournamentDao.getTournamentById(event.getTournamentId());
    tournament.setGameIds(event.getGameIds());
    tournament.setLeaderboard(newLeaderBoard);

    tournamentDao.updateTournament(event.getTournamentId(), tournament);
  }

  public void handleTournamentStarted(TournamentStarted event) {
    Tournament tournament = tournamentDao.getTournamentById(event.getTournamentId());
    tournament.setState(ACTIVE);

    tournamentDao.updateTournament(event.getTournamentId(), tournament);
  }

  public void handleTournamentFinished(TournamentFinished event) {
    Tournament tournament = tournamentDao.getTournamentById(event.getTournamentId());
    tournament.setState(FINISHED);

    tournamentDao.updateTournament(event.getTournamentId(), tournament);
  }
}
