package kirilloffna.homework3.service;

import java.util.List;
import kirilloffna.homework3.dto.AttractionDTO;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

/**
 * Сервис для управления достопримечательностями. Предоставляет методы для получения, создания,
 * обновления и удаления достопримечательностей.
 */
public interface AttractionService {

  /**
   * Получает достопримечательность по ее идентификатору.
   *
   * @param id Идентификатор достопримечательности, которую необходимо получить.
   * @return Объект {@link AttractionDTO}, представляющий достопримечательность.
   * @throws NotFoundException Если достопримечательность с указанным идентификатором не найдена.
   */
  AttractionDTO getAttractionById(Long id) throws NotFoundException;

  /**
   * Получает список достопримечательностей по идентификатору города.
   *
   * @param id Идентификатор города, для которого необходимо получить достопримечательности.
   * @return Список {@link AttractionDTO}, представляющих достопримечательности данного города.
   * @throws NotFoundException Если город с указанным идентификатором не найден.
   */
  List<AttractionDTO> getAttractionsByCityId(Long id) throws NotFoundException;

  /**
   * Получает список всех достопримечательностей с возможностью фильтрации и сортировки.
   *
   * @param sortByName   Параметр для сортировки достопримечательностей по имени (необязательный).
   * @param filterByType Параметр для фильтрации достопримечательностей по типу (необязательный).
   * @return Список {@link AttractionDTO}, представляющих все достопримечательности.
   */
  List<AttractionDTO> getAllAttractions(boolean sortByName, String filterByType);

  /**
   * Сохраняет новую достопримечательность в базу данных.
   *
   * @param attractionDTO Объект {@link AttractionDTO}, содержащий информацию о
   *                      достопримечательности, которую необходимо создать.
   */
  void saveAttraction(AttractionDTO attractionDTO);

  /**
   * Обновляет существующую достопримечательность.
   *
   * @param attractionDTO Объект {@link AttractionDTO}, содержащий обновленную информацию о
   * достопримечательности.
   * @throws NotFoundException Если достопримечательность с указанным идентификатором не найдена.
   */
  void updateAttraction(AttractionDTO attractionDTO) throws NotFoundException;

  /**
   * Удаляет достопримечательность по идентификатору из базы данных.
   *
   * @param id Идентификатор достопримечательности, которую необходимо удалить.
   * @throws NotFoundException Если достопримечательность с указанным идентификатором не найдена.
   */
  void deleteAttraction(Long id) throws NotFoundException;
}
