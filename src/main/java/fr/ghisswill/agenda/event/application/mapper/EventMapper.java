package fr.ghisswill.agenda.event.application.mapper;

import fr.ghisswill.agenda.event.application.dto.EventResponse;
import fr.ghisswill.agenda.event.domain.model.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface EventMapper {

    EventMapper INSTANCE = Mappers.getMapper(EventMapper.class);

    @Mapping(target = "userId", expression = "java(getUserId(entity))")
    EventResponse toDto(Event entity);

    default UUID getUserId(Event entity) {
        return entity.getUser() != null ? entity.getUser().getId() : null;
    }
}
