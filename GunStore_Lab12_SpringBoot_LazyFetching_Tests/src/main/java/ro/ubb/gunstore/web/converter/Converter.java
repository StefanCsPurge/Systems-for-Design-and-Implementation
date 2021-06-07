package ro.ubb.gunstore.web.converter;

import ro.ubb.gunstore.core.model.BaseEntity;
import ro.ubb.gunstore.web.dto.BaseDto;

/**
 * Created by C64.
 */

public interface Converter<Model extends BaseEntity<Long>, Dto extends BaseDto> {

    Model convertDtoToModel(Dto dto);

    Dto convertModelToDto(Model model);

}

