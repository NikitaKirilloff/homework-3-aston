package kirilloffna.homework3.service.impl;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import kirilloffna.homework3.dao.CityDAO;
import kirilloffna.homework3.dto.CityDTO;
import kirilloffna.homework3.mapper.CityMapper;
import kirilloffna.homework3.model.City;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

/**
 * Тестовый класс для CityServiceImpl. Тестирует методы сервиса для сохранения и обновления
 * информации о городах.
 */
@ExtendWith(MockitoExtension.class)
class CityServiceImplTest {

  @Mock
  private CityDAO cityDAO;

  @Mock
  private CityMapper cityMapper;

  @InjectMocks
  private CityServiceImpl cityService;

  private City city;
  private CityDTO cityDTO;

  /**
   * Настройка данных для тестов.
   */
  @BeforeEach
  void setUp() {
    city = new City();
    city.setId(1L);
    city.setPopulation(1000000);
    city.setHasMetro(true);

    cityDTO = new CityDTO();
    cityDTO.setId(1L);
    cityDTO.setPopulation(1000000);
    cityDTO.setHasMetro(true);
  }

  /**
   * Тест для метода saveCity. Проверяет, что город сохраняется в базе данных.
   */
  @Test
  void saveCity_ShouldSaveCity() {
    when(cityMapper.toEntity(cityDTO)).thenReturn(city);

    cityService.saveCity(cityDTO);

    verify(cityDAO).save(city);
  }

  /**
   * Тест для метода updateCity. Проверяет, что информация о городе обновляется в базе данных.
   *
   * @throws NotFoundException если город не найден
   */
  @Test
  void updateCity_ShouldUpdateCity() throws NotFoundException {
    when(cityDAO.findById(1L)).thenReturn(Optional.of(city));

    cityService.updateCity(cityDTO);

    verify(cityDAO).save(city);
  }

  /**
   * Тест для метода updateCity. Проверяет, что выбрасывается исключение NotFoundException, если
   * город не найден.
   */
  @Test
  void updateCity_ShouldThrowNotFoundException() {
    when(cityDAO.findById(1L)).thenReturn(Optional.empty());

    assertThrows(NotFoundException.class, () -> cityService.updateCity(cityDTO));
    verify(cityDAO).findById(1L);
  }
}