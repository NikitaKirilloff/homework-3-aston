package kirilloffna.homework3.dao;

import java.util.List;
import java.util.Optional;
import kirilloffna.homework3.model.Attraction;
import kirilloffna.homework3.model.TypeOfAttraction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Data Access Object (DAO) для управления сущностями {@link Attraction}. Предоставляет методы для
 * выполнения операций с достопримечательностями в базе данных.
 */
@Repository
public interface AttractionDAO extends JpaRepository<Attraction, Long> {

  /**
   * Метод для поиска всех достопримечательностей по идентификатору города.
   *
   * @param id Идентификатор города, для которого необходимо найти достопримечательности.
   * @return Опциональный список достопримечательностей, связанных с указанным городом.
   */
  Optional<List<Attraction>> findAllByCityId(Long id);

  /**
   * Метод для поиска всех достопримечательностей по типу.
   *
   * @param type Тип достопримечательности, по которому необходимо выполнить поиск.
   * @return Список достопримечательностей, соответствующих указанному типу.
   */
  List<Attraction> findByType(TypeOfAttraction type);
}
