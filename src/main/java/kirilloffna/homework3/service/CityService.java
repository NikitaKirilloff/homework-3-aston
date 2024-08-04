package kirilloffna.homework3.service;

import kirilloffna.homework3.dto.CityDTO;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

/**
 * Сервис для управления городами. Предоставляет методы для создания и обновления городов.
 */
public interface CityService {

  /**
   * Сохраняет новый город в базу данных.
   *
   * @param cityDTO Объект {@link CityDTO}, содержащий информацию о городе, который необходимо
   * создать.
   */
  void saveCity(CityDTO cityDTO);

  /**
   * Обновляет существующий город.
   *
   * @param cityDTO Объект {@link CityDTO}, содержащий обновленную информацию о городе.
   * @throws NotFoundException Если город с указанным идентификатором не найден.
   */
  void updateCity(CityDTO cityDTO) throws NotFoundException;
}
