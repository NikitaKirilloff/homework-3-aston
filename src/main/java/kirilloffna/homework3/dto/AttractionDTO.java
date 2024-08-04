package kirilloffna.homework3.dto;

import java.util.List;
import kirilloffna.homework3.model.City;
import kirilloffna.homework3.model.Service;
import kirilloffna.homework3.model.TypeOfAttraction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) для представления информации о достопримечательности. Используется для
 * передачи данных между слоями приложения.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttractionDTO {

  private long id;
  private String name;
  private String dateOfCreation;
  private String description;
  private TypeOfAttraction type;
  private City city;
  private List<Service> services;
}
