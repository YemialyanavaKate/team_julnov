package by.ita.je.mappers;

import by.ita.je.dto.KettleDto;
import by.ita.je.models.Kettle;
import org.springframework.stereotype.Component;

@Component
public class KettleMapperToDto {

    public KettleDto toDTO(Kettle kettle) {
        return new KettleDto(
                kettle.getType(),
                kettle.getColor(),
                kettle.getIsElectric(),
                kettle.getIsInduction(),
                kettle.getPrice(),
                kettle.getNumber()
        );
    }
}
