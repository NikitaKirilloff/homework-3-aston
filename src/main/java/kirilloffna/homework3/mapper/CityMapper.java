package kirilloffna.homework3.mapper;

import kirilloffna.homework3.dto.CityDTO;
import kirilloffna.homework3.model.City;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

/**
 * Интерфейс маппера для преобразования объектов City в объект CityDTO. Использует библиотеку
 * MapStruct для автоматической генерации реализации маппера.
 */
@Component
@Mapper(componentModel = "spring")
public interface CityMapper {

  /**
   * Преобразует объект City в объект CityDTO.
   *
   * @param city Сущность {@link City}, которую необходимо преобразовать.
   * @return DTO {@link CityDTO}, соответствующий сущности.
   */
  @Mapping(source = "name", target = "name")
  CityDTO toDto(City city);

  /**
   * Преобразует объект CityDTO в объект City.
   *
   * @param cityDTO Сущность {@link CityDTO}, которую необходимо преобразовать.
   * @return Сущность {@link City}, соответствующий сущности.
   */
  @Mapping(source = "name", target = "name")
  City toEntity(CityDTO cityDTO);
}
