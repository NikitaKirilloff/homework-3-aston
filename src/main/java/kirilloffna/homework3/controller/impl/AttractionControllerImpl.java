package kirilloffna.homework3.controller.impl;

import static kirilloffna.homework3.controller.ControllerMessages.ATTRACTION_ADDED;
import static kirilloffna.homework3.controller.ControllerMessages.ATTRACTION_DELETED;
import static kirilloffna.homework3.controller.ControllerMessages.ATTRACTION_NOT_FOUND;
import static kirilloffna.homework3.controller.ControllerMessages.ATTRACTION_UPDATED;
import static kirilloffna.homework3.controller.ControllerMessages.ATTRACTION_WITH_CITY_ID_NOT_FOUND;
import static kirilloffna.homework3.controller.ControllerMessages.ATT_CONT_DELETE_ATTRACTION;
import static kirilloffna.homework3.controller.ControllerMessages.ATT_CONT_DELETE_ATTRACTION_ERROR;
import static kirilloffna.homework3.controller.ControllerMessages.ATT_CONT_GET_ATTRACTION_BY_ID;
import static kirilloffna.homework3.controller.ControllerMessages.ATT_CONT_GET_ATTRACTION_BY_ID_ERROR;
import static kirilloffna.homework3.controller.ControllerMessages.ATT_CONT_GET_ATTRACTION_FOR_CITY_ID;
import static kirilloffna.homework3.controller.ControllerMessages.ATT_CONT_GET_ATTRACTION_FOR_CITY_ID_ERROR;
import static kirilloffna.homework3.controller.ControllerMessages.ATT_CONT_GET_ATTRACTION_FOR_CITY_ID_NOT_FOUND;
import static kirilloffna.homework3.controller.ControllerMessages.ATT_CONT_GET_ATTRACTION_SORT_BY_NAME;
import static kirilloffna.homework3.controller.ControllerMessages.ATT_CONT_GET_ATTRACTION_SORT_BY_NAME_ERROR;
import static kirilloffna.homework3.controller.ControllerMessages.ATT_CONT_SAVE_ATTRACTION;
import static kirilloffna.homework3.controller.ControllerMessages.ATT_CONT_SAVE_ATTRACTION_ERROR;
import static kirilloffna.homework3.controller.ControllerMessages.ATT_CONT_UPDATE_ATTRACTION;
import static kirilloffna.homework3.controller.ControllerMessages.ATT_CONT_UPDATE_ATTRACTION_ERROR;
import static kirilloffna.homework3.controller.ControllerMessages.SERVICE_UNAVAILABLE;

import java.util.List;
import kirilloffna.homework3.controller.AttractionController;
import kirilloffna.homework3.dto.AttractionDTO;
import kirilloffna.homework3.service.AttractionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для создания сущностей достопримечательностей. Предоставляет REST API для создания,
 * обновления, удаления и получения информации о достопримечательностях.
 */
@Slf4j
@RestController
@RequestMapping("/attractions")
@RequiredArgsConstructor
public class AttractionControllerImpl implements AttractionController {

  private final AttractionService attractionService;

  @GetMapping("/{id}")
  public ResponseEntity<AttractionDTO> getAttractionById(@PathVariable("id") Long id) {
    log.info(ATT_CONT_GET_ATTRACTION_BY_ID, id);
    try {
      return ResponseEntity.ok(attractionService.getAttractionById(id));
    } catch (NotFoundException e) {
      log.warn(ATTRACTION_WITH_CITY_ID_NOT_FOUND, id);
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body(new AttractionDTO());
    } catch (Exception e) {
      log.error(ATT_CONT_GET_ATTRACTION_BY_ID_ERROR, id, e);
      return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
          .body(new AttractionDTO());
    }
  }

  @GetMapping()
  public ResponseEntity<List<AttractionDTO>> getAttractions(
      @RequestParam(required = false, defaultValue = "false") boolean sortByName,
      @RequestParam(required = false) String filterByType) {
    log.info(ATT_CONT_GET_ATTRACTION_SORT_BY_NAME, sortByName, filterByType);
    try {
      return ResponseEntity.ok(attractionService.getAllAttractions(sortByName, filterByType));
    } catch (Exception e) {
      log.error(ATT_CONT_GET_ATTRACTION_SORT_BY_NAME_ERROR, e);
      return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
          .body(List.of());
    }
  }

  @GetMapping("/city/{id}")
  public ResponseEntity<List<AttractionDTO>> getAttractionsByCityId(@PathVariable Long id) {
    log.info(ATT_CONT_GET_ATTRACTION_FOR_CITY_ID, id);
    try {
      return ResponseEntity.ok(attractionService.getAttractionsByCityId(id));
    } catch (NotFoundException e) {
      log.warn(ATT_CONT_GET_ATTRACTION_FOR_CITY_ID_NOT_FOUND, id);
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body(List.of());
    } catch (Exception e) {
      log.error(ATT_CONT_GET_ATTRACTION_FOR_CITY_ID_ERROR, id, e);
      return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
          .body(List.of());
    }
  }

  @PostMapping()
  public ResponseEntity<String> createAttraction(@RequestBody AttractionDTO attractionDTO) {
    log.info(ATT_CONT_SAVE_ATTRACTION, attractionDTO);
    try {
      attractionService.saveAttraction(attractionDTO);
      return ResponseEntity.ok(ATTRACTION_ADDED);
    } catch (Exception e) {
      log.error(ATT_CONT_SAVE_ATTRACTION_ERROR, e);
      return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
          .body(SERVICE_UNAVAILABLE);
    }
  }

  @PutMapping
  public ResponseEntity<String> updateAttraction(@RequestBody AttractionDTO attractionDTO) {
    log.info(ATT_CONT_UPDATE_ATTRACTION, attractionDTO.getId());
    try {
      attractionService.updateAttraction(attractionDTO);
      return ResponseEntity.ok(ATTRACTION_UPDATED);
    } catch (NotFoundException e) {
      log.warn(ATTRACTION_WITH_CITY_ID_NOT_FOUND, attractionDTO.getId());
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body(ATTRACTION_NOT_FOUND + attractionDTO.getId());
    } catch (Exception e) {
      log.error(ATT_CONT_UPDATE_ATTRACTION_ERROR, attractionDTO.getId(), e);
      return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
          .body(SERVICE_UNAVAILABLE);
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteAttraction(@PathVariable Long id) {
    log.info(ATT_CONT_DELETE_ATTRACTION, id);
    try {
      attractionService.deleteAttraction(id);
      return ResponseEntity.ok(ATTRACTION_DELETED);
    } catch (NotFoundException e) {
      log.warn(ATTRACTION_WITH_CITY_ID_NOT_FOUND, id);
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body(ATTRACTION_NOT_FOUND + id);
    } catch (Exception e) {
      log.error(ATT_CONT_DELETE_ATTRACTION_ERROR, id, e);
      return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
          .body(SERVICE_UNAVAILABLE);
    }
  }
}
