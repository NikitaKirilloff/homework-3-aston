package kirilloffna.homework3.controller;

import kirilloffna.homework3.dto.CityDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Интерфейс контроллера для создания сущностей городов. Предоставляет REST API для создания и
 * обновления информации о городах.
 */
public interface CityController {

  /**
   * Создает новый город.
   *
   * @param cityDTO Объект {@link CityDTO}, содержащий информацию о городе, который необходимо
   * создать.
   * @return ResponseEntity с сообщением о статусе выполнения операции. Возвращает статус 200 (OK)
   * при успешном создании города. В случае ошибки возвращает статус 503 (SERVICE_UNAVAILABLE).
   */
  ResponseEntity<String> createCity(@RequestBody CityDTO cityDTO);

  /**
   * Обновляет существующий город.
   *
   * @param cityDTO Объект {@link CityDTO}, содержащий обновленную информацию о городе.
   * @return ResponseEntity с сообщением о статусе выполнения операции. Возвращает статус 200 (OK)
   * при успешном обновлении города. В случае, если город не найден, возвращает статус 404
   * (NOT_FOUND) с сообщением о том, что город не найден. В случае ошибки возвращает статус 503
   * (SERVICE_UNAVAILABLE).
   */
  ResponseEntity<String> updateCity(@RequestBody CityDTO cityDTO);
}
