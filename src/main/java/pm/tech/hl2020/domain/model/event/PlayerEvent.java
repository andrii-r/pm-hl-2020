package pm.tech.hl2020.domain.model.event;

import lombok.Value;

@Value
public class PlayerEvent {
  String playerId;
  String gameId;
  Integer points;
}
