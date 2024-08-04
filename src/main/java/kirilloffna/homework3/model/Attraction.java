package kirilloffna.homework3.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Сущность, представляющая достопримечательность. Хранит информацию о достопримечательности,
 * включая ее название, дату создания, описание, тип, город и услуги, связанные с ней.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Attraction {

  /**
   * Идентификатор достопримечательности. Генерируется автоматически.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String name;
  @Column(name = "date_of_creation", nullable = false)
  private String dateOfCreation;
  private String description;

  @Enumerated(EnumType.STRING)
  private TypeOfAttraction type;

  /**
   * Город, в котором расположена достопримечательность.
   */
  @ManyToOne
  @JoinColumn(name = "city_id")
  @JsonBackReference
  private City city;

  /**
   * Список услуг, связанных с достопримечательностью.
   */
  @ManyToMany
  @JoinTable(
      name = "attraction_service",
      joinColumns = @JoinColumn(name = "attraction_id"),
      inverseJoinColumns = @JoinColumn(name = "service_id"))
  private List<Service> services;
}
