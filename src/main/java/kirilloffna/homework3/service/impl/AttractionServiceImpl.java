package kirilloffna.homework3.service.impl;

import static kirilloffna.homework3.service.ServiceLogMessages.ATT_SERVICE_ATTRACTION_NOT_FOUND;
import static kirilloffna.homework3.service.ServiceLogMessages.ATT_SERVICE_DELETE_ATTRACTION;
import static kirilloffna.homework3.service.ServiceLogMessages.ATT_SERVICE_DELETE_ATTRACTION_DONE;
import static kirilloffna.homework3.service.ServiceLogMessages.ATT_SERVICE_FOR_CITY_WITH_ID_NOT_FOUND;
import static kirilloffna.homework3.service.ServiceLogMessages.ATT_SERVICE_GET_ALL;
import static kirilloffna.homework3.service.ServiceLogMessages.ATT_SERVICE_GET_ALL_WITH_FILTER;
import static kirilloffna.homework3.service.ServiceLogMessages.ATT_SERVICE_GET_ALL_WITH_SORT;
import static kirilloffna.homework3.service.ServiceLogMessages.ATT_SERVICE_GET_ALL_WITH_SORT_AND_FILTER;
import static kirilloffna.homework3.service.ServiceLogMessages.ATT_SERVICE_GET_BY_CITY_ID;
import static kirilloffna.homework3.service.ServiceLogMessages.ATT_SERVICE_GET_BY_ID;
import static kirilloffna.homework3.service.ServiceLogMessages.ATT_SERVICE_SAVE_ATTRACTION;
import static kirilloffna.homework3.service.ServiceLogMessages.ATT_SERVICE_SAVE_ATTRACTION_DONE;
import static kirilloffna.homework3.service.ServiceLogMessages.ATT_SERVICE_UPDATE_ATTRACTION;
import static kirilloffna.homework3.service.ServiceLogMessages.ATT_SERVICE_UPDATE_ATTRACTION_DONE;
import static kirilloffna.homework3.service.ServiceLogMessages.ATT_SERVICE_WITH_ID_NOT_FOUND;

import java.util.Comparator;
import java.util.List;
import kirilloffna.homework3.dao.AttractionDAO;
import kirilloffna.homework3.dto.AttractionDTO;
import kirilloffna.homework3.mapper.AttractionMapper;
import kirilloffna.homework3.model.Attraction;
import kirilloffna.homework3.model.TypeOfAttraction;
import kirilloffna.homework3.service.AttractionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

/**
 * Реализация сервиса для работы с достопримечательностями.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AttractionServiceImpl implements AttractionService {

  private final AttractionDAO attractionDAO;
  private final AttractionMapper attractionMapper;

  @Override
  public AttractionDTO getAttractionById(Long id) throws NotFoundException {
    log.info(ATT_SERVICE_GET_BY_ID, id);
    Attraction attraction = attractionDAO.findById(id)
        .orElseThrow(() -> {
          log.warn(ATT_SERVICE_WITH_ID_NOT_FOUND, id);
          return new NotFoundException();
        });

    return attractionMapper.toDto(attraction);
  }

  @Override
  public List<AttractionDTO> getAttractionsByCityId(Long id) throws NotFoundException {
    log.info(ATT_SERVICE_GET_BY_CITY_ID, id);
    List<Attraction> attractions = attractionDAO.findAllByCityId(id)
        .orElseThrow(() -> {
          log.warn(ATT_SERVICE_FOR_CITY_WITH_ID_NOT_FOUND, id);
          return new NotFoundException();
        });

    return attractions.stream()
        .map(attractionMapper::toDto)
        .toList();
  }

  @Override
  public List<AttractionDTO> getAllAttractions(boolean sortByName, String filterByType) {
    log.info(ATT_SERVICE_GET_ALL_WITH_SORT_AND_FILTER, sortByName, filterByType);
    List<Attraction> attractions;

    if (filterByType != null) {
      log.info(ATT_SERVICE_GET_ALL_WITH_FILTER, filterByType);
      attractions = attractionDAO.findByType(TypeOfAttraction.valueOf(filterByType.toUpperCase()));
    } else {
      log.info(ATT_SERVICE_GET_ALL);
      attractions = attractionDAO.findAll();
    }

    if (sortByName) {
      log.info(ATT_SERVICE_GET_ALL_WITH_SORT);
      attractions = attractions.stream()
          .sorted(Comparator.comparing(Attraction::getName))
          .toList();
    }

    return attractions.stream().map(attractionMapper::toDto).toList();
  }

  @Override
  public void saveAttraction(AttractionDTO attractionDTO) {
    log.info(ATT_SERVICE_SAVE_ATTRACTION, attractionDTO);
    Attraction attraction = attractionMapper.toEntity(attractionDTO);
    attractionDAO.save(attraction);
    log.info(ATT_SERVICE_SAVE_ATTRACTION_DONE, attractionDTO.getId());
  }

  @Override
  public void updateAttraction(AttractionDTO attractionDTO) throws NotFoundException {
    log.info(ATT_SERVICE_UPDATE_ATTRACTION, attractionDTO.getId());
    Attraction attraction = attractionDAO.findById(attractionDTO.getId())
        .orElseThrow(() -> {
          log.warn(ATT_SERVICE_ATTRACTION_NOT_FOUND, attractionDTO.getId());
          return new NotFoundException();
        });
    attraction.setName(attractionDTO.getName());
    attraction.setDateOfCreation(attractionDTO.getDateOfCreation());
    attraction.setDescription(attractionDTO.getDescription());
    attraction.setType(TypeOfAttraction.valueOf(String.valueOf(attractionDTO.getType())));
    attractionDAO.save(attraction);
    log.info(ATT_SERVICE_UPDATE_ATTRACTION_DONE, attractionDTO.getId());
  }

  @Override
  public void deleteAttraction(Long id) throws NotFoundException {
    log.info(ATT_SERVICE_DELETE_ATTRACTION, id);
    Attraction attraction = attractionDAO.findById(id)
        .orElseThrow(() -> {
          log.warn(ATT_SERVICE_ATTRACTION_NOT_FOUND, id);
          return new NotFoundException();
        });
    attractionDAO.delete(attraction);
    log.info(ATT_SERVICE_DELETE_ATTRACTION_DONE, id);
  }
}
