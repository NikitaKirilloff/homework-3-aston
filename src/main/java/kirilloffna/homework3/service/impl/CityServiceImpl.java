package kirilloffna.homework3.service.impl;

import static kirilloffna.homework3.service.ServiceLogMessages.CITY_SERVICE_CITY_NOT_FOUND;
import static kirilloffna.homework3.service.ServiceLogMessages.CITY_SERVICE_SAVE_CITY;
import static kirilloffna.homework3.service.ServiceLogMessages.CITY_SERVICE_SAVE_CITY_DONE;
import static kirilloffna.homework3.service.ServiceLogMessages.CITY_SERVICE_UPDATE_CITY;
import static kirilloffna.homework3.service.ServiceLogMessages.CITY_SERVICE_UPDATE_CITY_DONE;

import kirilloffna.homework3.dao.CityDAO;
import kirilloffna.homework3.dto.CityDTO;
import kirilloffna.homework3.mapper.CityMapper;
import kirilloffna.homework3.model.City;
import kirilloffna.homework3.service.CityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

/**
 * Реализация сервиса для работы с городами.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

  private final CityDAO cityDAO;
  private final CityMapper cityMapper;

  @Override
  public void saveCity(CityDTO cityDTO) {
    log.info(CITY_SERVICE_SAVE_CITY, cityDTO);
    City city = cityMapper.toEntity(cityDTO);
    cityDAO.save(city);
    log.info(CITY_SERVICE_SAVE_CITY_DONE, cityDTO.getId());
  }

  @Override
  public void updateCity(CityDTO cityDTO) throws NotFoundException {
    log.info(CITY_SERVICE_UPDATE_CITY, cityDTO);
    City city = cityDAO.findById(cityDTO.getId())
        .orElseThrow(() -> {
          log.warn(CITY_SERVICE_CITY_NOT_FOUND, cityDTO.getId());
          return new NotFoundException();
        });
    city.setPopulation(cityDTO.getPopulation());
    city.setHasMetro(cityDTO.isHasMetro());
    cityDAO.save(city);
    log.info(CITY_SERVICE_UPDATE_CITY_DONE + "id : {}", cityDTO.getId());
  }
}
