package by.ita.je.mappers;

import by.ita.je.dto.KettleDto;
import by.ita.je.models.Kettle;

public class KettleMapper {

    public static KettleDto toDTO(Kettle kettle){
        return new KettleDto(
                kettle.getType(),
                kettle.getColor(),
                kettle.getIsElectric(),
                kettle.getIsInduction(),
                kettle.getPrice(),
                kettle.getNumber()
        );
    }

    public static Kettle toEntity(KettleDto kettleDto){
        return new Kettle(
                kettleDto.getType(),
                kettleDto.getColor(),
                kettleDto.getIsElectric(),
                kettleDto.getIsInduction(),
                kettleDto.getPrice(),
                kettleDto.getNumber(),
                null,
                null
        );
    }
}
