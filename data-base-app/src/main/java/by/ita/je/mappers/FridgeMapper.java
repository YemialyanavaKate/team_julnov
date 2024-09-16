package by.ita.je.mappers;

import by.ita.je.dto.FridgeDto;
import by.ita.je.models.Fridge;

public class FridgeMapper {

    public static FridgeDto toDTO(Fridge fridge) {
        return new FridgeDto(
                fridge.getType(),
                fridge.getDescription(),
                fridge.getDiscount(),
                fridge.getDefect(),
                fridge.getPrice(),
                fridge.getNumber()
        );
    }
        public static Fridge toEntity(FridgeDto fridgeDto){
            return new Fridge(
                    fridgeDto.getType(),
                    fridgeDto.getDescription(),
                    fridgeDto.getDiscount(),
                    fridgeDto.getDefect(),
                    fridgeDto.getPrice(),
                    fridgeDto.getNumber(),
                    null,
                    null
            );
        }
}
