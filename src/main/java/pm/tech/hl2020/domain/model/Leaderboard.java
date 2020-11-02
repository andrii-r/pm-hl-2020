package pm.tech.hl2020.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;
import pm.tech.hl2020.domain.model.event.PlayerEvent;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class Leaderboard {
    Integer winningPlaces;
    Map<String, Integer> points;
}
