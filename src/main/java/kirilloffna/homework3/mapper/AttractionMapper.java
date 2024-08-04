package kirilloffna.homework3.mapper;

import kirilloffna.homework3.dto.AttractionDTO;
import kirilloffna.homework3.model.Attraction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

/**
 * Интерфейс маппера для преобразования объектов Attraction в объект AttractionDTO. Использует
 * библиотеку MapStruct для автоматической генерации реализации маппера.
 */
@Component
@Mapper(componentModel = "spring")
public interface AttractionMapper {

  /**
   * Преобразует объект Attraction в объект AttractionDTO.
   *
   * @param attraction Сущность {@link Attraction}, которую необходимо преобразовать.
   * @return DTO {@link AttractionDTO}, соответствующий сущности.
   */
  @Mapping(source = "name", target = "name")
  AttractionDTO toDto(Attraction attraction);

  /**
   * Преобразует объект CityDTO в объект City.
   *
   * @param attractionDTO Сущность {@link AttractionDTO}, которую необходимо преобразовать.
   * @return Сущность {@link Attraction}, соответствующий сущности.
   */
  @Mapping(source = "name", target = "name")
  Attraction toEntity(AttractionDTO attractionDTO);
}
