package ro.ubb.movieapp.web.converter;

import org.springframework.stereotype.Component;
import ro.ubb.movieapp.core.model.Actor;
import ro.ubb.movieapp.web.dto.ActorDto;

@Component
public class ActorConverter extends BaseConverter<Actor, ActorDto>{
    @Override
    public Actor convertDtoToModel(ActorDto dto) {
        Actor actor = Actor.builder()
                .name(dto.getName())
                .rating(dto.getRating())
                .build();
        actor.setId(dto.getId());
        return actor;
    }

    @Override
    public ActorDto convertModelToDto(Actor actor) {
        ActorDto dto = ActorDto.builder()
                .name(actor.getName())
                .rating(actor.getRating())
                .build();
        dto.setId(actor.getId());
        return dto;
    }
}
