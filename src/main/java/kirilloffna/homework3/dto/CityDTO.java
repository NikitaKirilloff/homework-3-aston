package kirilloffna.homework3.dto;

import java.util.List;
import kirilloffna.homework3.model.Attraction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) для представления информации о городе. Используется для передачи
 * данных между слоями приложения.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CityDTO {

  private long id;
  private String name;
  private long population;
  private boolean hasMetro;
  private List<Attraction> attractions;
}
