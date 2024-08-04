package kirilloffna.homework3.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Сущность, представляющая город. Хранит информацию о городе, включая его название, население,
 * наличие метро и связанные с ним достопримечательности.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class City {

  /**
   * Идентификатор города. Генерируется автоматически.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String name;
  private long population;
  private boolean hasMetro;

  /**
   * Список достопримечательностей, связанных с городом.
   */
  @OneToMany(mappedBy = "city")
  @JsonIgnore
  private List<Attraction> attractions;
}

