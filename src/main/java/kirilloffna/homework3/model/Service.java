package kirilloffna.homework3.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Сущность, представляющая услугу. Хранит информацию об услуге, включая ее название, описание и
 * связанные с ней достопримечательности.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Service {

  /**
   * Идентификатор услуги. Генерируется автоматически.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String description;

  /**
   * Список достопримечательностей, связанных с услугой.
   */
  @ManyToMany(mappedBy = "services")
  @JsonBackReference
  private List<Attraction> attractions;
}
