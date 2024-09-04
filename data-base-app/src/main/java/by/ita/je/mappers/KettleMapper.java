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
}
