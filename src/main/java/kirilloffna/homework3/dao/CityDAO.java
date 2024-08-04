package kirilloffna.homework3.dao;

import kirilloffna.homework3.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Data Access Object (DAO) для управления сущностями {@link City}. Предоставляет методы для
 * выполнения операций с городами в базе данных.
 */
@Repository
public interface CityDAO extends JpaRepository<City, Long> {

}
