package pm.tech.hl2020.messages;

import lombok.Data;

@Data
public class PlayerEvent {
    String playerId;
    String gameId;
    Integer points;
}
