package pm.tech.hl2020.domain.model.event;

import lombok.Value;

import java.util.Set;

@Value
public class TournamentCreated implements TournamentEvent {
  Integer winningPlaces;
  Set<String> gameIds;
}
