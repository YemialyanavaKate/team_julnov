package by.ita.je.mappers;

import by.ita.je.dto.TVDto;
import by.ita.je.models.TV;

public class TVMapper {

    public static TVDto toDTO(TV tv){
        return new TVDto(
                tv.getType(),
                tv.getBrand(),
                tv.getDiscount(),
                tv.getDiagonal(),
                tv.getPrice(),
                tv.getNumber()
        );
    }

    public static TV toEntity(TVDto tvDto){
        return new TV(
                tvDto.getType(),
                tvDto.getBrand(),
                tvDto.getDiscount(),
                tvDto.getDiagonal(),
                tvDto.getPrice(),
                tvDto.getNumber(),
                null,
                null
        );
    }
}
