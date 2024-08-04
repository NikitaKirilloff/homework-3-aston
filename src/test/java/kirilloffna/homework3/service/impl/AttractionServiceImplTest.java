package kirilloffna.homework3.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import kirilloffna.homework3.dao.AttractionDAO;
import kirilloffna.homework3.dto.AttractionDTO;
import kirilloffna.homework3.mapper.AttractionMapper;
import kirilloffna.homework3.model.Attraction;
import kirilloffna.homework3.model.TypeOfAttraction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

/**
 * Тестовый класс для AttractionServiceImpl. Тестирует методы сервиса для работы с
 * достопримечательностями.
 */
@ExtendWith(MockitoExtension.class)
class AttractionServiceImplTest {

  @Mock
  private AttractionDAO attractionDAO;

  @Mock
  private AttractionMapper attractionMapper;

  @InjectMocks
  private AttractionServiceImpl attractionService;

  private Attraction attraction;
  private AttractionDTO attractionDTO;

  /**
   * Настройка данных для тестов.
   */
  @BeforeEach
  void setUp() {
    attraction = new Attraction();
    attraction.setId(1L);
    attraction.setName("Test Attraction");

    attractionDTO = new AttractionDTO();
    attractionDTO.setId(1L);
    attractionDTO.setName("Test Attraction");
    attractionDTO.setType(TypeOfAttraction.PARK);
  }

  /**
   * Тест для метода getAttractionById. Проверяет, что достопримечательность возвращается по ID.
   *
   * @throws NotFoundException если достопримечательность не найдена
   */
  @Test
  void getAttractionById_ShouldReturnAttraction() throws NotFoundException {
    when(attractionDAO.findById(1L)).thenReturn(Optional.of(attraction));
    when(attractionMapper.toDto(attraction)).thenReturn(attractionDTO);

    AttractionDTO result = attractionService.getAttractionById(1L);

    verify(attractionDAO).findById(1L);
    verify(attractionMapper).toDto(attraction);
    assertEquals(attractionDTO, result);
  }

  /**
   * Тест для метода getAttractionById. Проверяет, что выбрасывается исключение NotFoundException,
   * если достопримечательность не найдена.
   */
  @Test
  void getAttractionById_ShouldThrowNotFoundException() {
    when(attractionDAO.findById(1L)).thenReturn(Optional.empty());

    assertThrows(NotFoundException.class, () -> attractionService.getAttractionById(1L));
    verify(attractionDAO).findById(1L);
  }

  /**
   * Тест для метода getAttractionsByCityId. Проверяет, что возвращается список
   * достопримечательностей по ID города.
   *
   * @throws NotFoundException если достопримечательности не найдены
   */
  @Test
  void getAttractionsByCityId_ShouldReturnListOfAttractions() throws NotFoundException {
    when(attractionDAO.findAllByCityId(1L)).thenReturn(Optional.of(List.of(attraction)));
    when(attractionMapper.toDto(attraction)).thenReturn(attractionDTO);

    List<AttractionDTO> result = attractionService.getAttractionsByCityId(1L);

    verify(attractionDAO).findAllByCityId(1L);
    assertEquals(1, result.size());
    assertEquals(attractionDTO, result.getFirst());
  }

  /**
   * Тест для метода getAttractionsByCityId. Проверяет, что выбрасывается исключение
   * NotFoundException, если достопримечательности не найдены.
   */
  @Test
  void getAttractionsByCityId_ShouldThrowNotFoundException() {
    when(attractionDAO.findAllByCityId(1L)).thenReturn(Optional.empty());

    assertThrows(NotFoundException.class, () -> attractionService.getAttractionsByCityId(1L));
    verify(attractionDAO).findAllByCityId(1L);
  }

  /**
   * Тест для метода getAllAttractions. Проверяет, что возвращается список всех
   * достопримечательностей.
   */
  @Test
  void getAllAttractions_ShouldReturnListOfAttractions() {
    when(attractionDAO.findAll()).thenReturn(List.of(attraction));
    when(attractionMapper.toDto(attraction)).thenReturn(attractionDTO);

    List<AttractionDTO> result = attractionService.getAllAttractions(false, null);

    verify(attractionDAO).findAll();
    assertEquals(1, result.size());
    assertEquals(attractionDTO, result.getFirst());
  }

  /**
   * Тест для метода saveAttraction. Проверяет, что достопримечательность сохраняется в базе
   * данных.
   */
  @Test
  void saveAttraction_ShouldSaveAttraction() {
    when(attractionMapper.toEntity(attractionDTO)).thenReturn(attraction);

    attractionService.saveAttraction(attractionDTO);

    verify(attractionDAO).save(attraction);
  }

  /**
   * Тест для метода updateAttraction. Проверяет, что информация о достопримечательности обновляется
   * в базе данных.
   *
   * @throws NotFoundException если достопримечательность не найдена
   */
  @Test
  void updateAttraction_ShouldUpdateAttraction() throws NotFoundException {
    when(attractionDAO.findById(1L)).thenReturn(Optional.of(attraction));

    attractionService.updateAttraction(attractionDTO);

    verify(attractionDAO).save(attraction);
  }

  /**
   * Тест для метода updateAttraction. Проверяет, что выбрасывается исключение NotFoundException,
   * если достопримечательность не найдена.
   */
  @Test
  void updateAttraction_ShouldThrowNotFoundException() {
    when(attractionDAO.findById(1L)).thenReturn(Optional.empty());

    assertThrows(NotFoundException.class, () -> attractionService.updateAttraction(attractionDTO));
    verify(attractionDAO).findById(1L);
  }

  /**
   * Тест для метода deleteAttraction. Проверяет, что достопримечательность удаляется из базы
   * данных.
   *
   * @throws NotFoundException если достопримечательность не найдена
   */
  @Test
  void deleteAttraction_ShouldDeleteAttraction() throws NotFoundException {
    when(attractionDAO.findById(1L)).thenReturn(Optional.of(attraction));

    attractionService.deleteAttraction(1L);

    verify(attractionDAO).delete(attraction);
  }

  /**
   * Тест для метода deleteAttraction. Проверяет, что выбрасывается исключение NotFoundException,
   * если достопримечательность не найдена.
   */
  @Test
  void deleteAttraction_ShouldThrowNotFoundException() {
    when(attractionDAO.findById(1L)).thenReturn(Optional.empty());

    assertThrows(NotFoundException.class, () -> attractionService.deleteAttraction(1L));
    verify(attractionDAO).findById(1L);
  }
}