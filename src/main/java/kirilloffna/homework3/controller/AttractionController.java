package kirilloffna.homework3.controller;

import java.util.List;
import kirilloffna.homework3.dto.AttractionDTO;
import org.springframework.http.ResponseEntity;

/**
 * Интерфейс контроллера для создания сущностей достопримечательностей. Предоставляет REST API для
 * создания, обновления, удаления и получения информации о достопримечательностях.
 */
public interface AttractionController {

  /**
   * Получает достопримечательность по ее идентификатору.
   *
   * @param id Идентификатор достопримечательности, которую необходимо получить.
   * @return ResponseEntity с объектом {@link AttractionDTO}, представляющим достопримечательность.
   * Возвращает статус 200 (OK) при успешном получении достопримечательности. В случае, если
   * достопримечательность не найдена, возвращает статус 404 (NOT_FOUND) и пустой
   * {@link AttractionDTO}. В случае ошибки возвращает статус 503 (SERVICE_UNAVAILABLE) и пустой
   * {@link AttractionDTO}.
   */
  ResponseEntity<AttractionDTO> getAttractionById(Long id);

  /**
   * Получает список всех достопримечательностей с возможностью фильтрации и сортировки.
   *
   * @param sortByName   Параметр для сортировки достопримечательностей по имени (необязательный).
   * @param filterByType Параметр для фильтрации достопримечательностей по типу (необязательный).
   * @return ResponseEntity со списком {@link AttractionDTO} достопримечательностей. Возвращает
   * статус 200 (OK) при успешном получении списка достопримечательностей. В случае ошибки
   * возвращает статус 503 (SERVICE_UNAVAILABLE) и пустой список.
   */
  ResponseEntity<List<AttractionDTO>> getAttractions(boolean sortByName, String filterByType);

  /**
   * Получает список достопримечательностей по идентификатору города.
   *
   * @param id Идентификатор города, для которого необходимо получить достопримечательности.
   * @return ResponseEntity со списком {@link AttractionDTO}, представляющих достопримечательности
   * данного города. Возвращает статус 200 (OK) при успешном получении списка
   * достопримечательностей. В случае, если город не найден, возвращает статус 404 (NOT_FOUND) и
   * пустой список. В случае ошибки возвращает статус 503 (SERVICE_UNAVAILABLE) и пустой список.
   */
  ResponseEntity<List<AttractionDTO>> getAttractionsByCityId(Long id);

  /**
   * Создает новую достопримечательность.
   *
   * @param attractionDTO Объект {@link AttractionDTO}, содержащий информацию о
   *                      достопримечательности, которую необходимо создать.
   * @return ResponseEntity с сообщением о статусе выполнения операции. Возвращает статус 200 (OK)
   * при успешном создании достопримечательности. В случае ошибки возвращает статус 503
   * (SERVICE_UNAVAILABLE).
   */
  ResponseEntity<String> createAttraction(AttractionDTO attractionDTO);

  /**
   * Обновляет существующую достопримечательность.
   *
   * @param attractionDTO Объект {@link AttractionDTO}, содержащий обновленную информацию о
   *                      достопримечательности.
   * @return ResponseEntity с сообщением о статусе выполнения операции. Возвращает статус 200 (OK)
   * при успешном обновлении достопримечательности. В случае, если достопримечательность не найдена,
   * возвращает статус 404 (NOT_FOUND) с сообщением о том, что достопримечательность не найдена. В
   * случае ошибки возвращает статус 503 (SERVICE_UNAVAILABLE).
   */
  ResponseEntity<String> updateAttraction(AttractionDTO attractionDTO);

  /**
   * Удаляет достопримечательность по идентификатору.
   *
   * @param id Идентификатор достопримечательности, которую необходимо удалить.
   * @return ResponseEntity с сообщением о статусе выполнения операции. Возвращает статус 200 (OK)
   * при успешном удалении достопримечательности. В случае, если достопримечательность не найдена,
   * возвращает статус 404 (NOT_FOUND) с сообщением о том, что достопримечательность не найдена. В
   * случае ошибки возвращает статус 503 (SERVICE_UNAVAILABLE).
   */
  ResponseEntity<String> deleteAttraction(Long id);
}