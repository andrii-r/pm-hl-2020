package pm.tech.hl2020.domain.model.event;

import lombok.Value;

@Value
public class TournamentFinished implements TournamentEvent{
  String tournamentId;
}
