package pm.tech.hl2020.domain.model.event;

import lombok.Value;

@Value
public class TournamentStarted implements TournamentEvent {
  String tournamentId;
}
