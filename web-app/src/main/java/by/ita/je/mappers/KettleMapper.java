package by.ita.je.mappers;

import by.ita.je.webDto.KettleWebDto;
import by.ita.je.models.Kettle;
import org.springframework.stereotype.Component;

@Component
public class KettleMapper {

    public KettleWebDto toWebDTO(Kettle kettle) {
        if (kettle == null) {
            return null;
        }
        return KettleWebDto.builder()
                .type(kettle.getType())
                .color(kettle.getColor())
                .isElectric(kettle.getIsElectric())
                .isInduction(kettle.getIsInduction())
                .price(kettle.getPrice())
                .number(kettle.getNumber())
                .build();
    }
    public Kettle toEntity(KettleWebDto kettleWebDto) {
        if (kettleWebDto == null) {
            return null;
        }
        return Kettle.builder()
                .type(kettleWebDto.getType())
                .color(kettleWebDto.getColor())
                .isElectric(kettleWebDto.getIsElectric())
                .isInduction(kettleWebDto.getIsInduction())
                .price(kettleWebDto.getPrice())
                .number(kettleWebDto.getNumber())
                .build();
    }
}
