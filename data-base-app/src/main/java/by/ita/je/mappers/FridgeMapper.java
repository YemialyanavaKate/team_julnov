package by.ita.je.mappers;

import by.ita.je.dto.FridgeDto;
import by.ita.je.models.Fridge;
import org.springframework.stereotype.Component;

@Component
public class FridgeMapper {

    public FridgeDto toDTO(Fridge fridge) {
        return new FridgeDto(
                fridge.getType(),
                fridge.getDescription(),
                fridge.getDiscount(),
                fridge.getDefect(),
                fridge.getPrice(),
                fridge.getNumber()
        );
    }
        public Fridge toEntity(FridgeDto fridgeDto){
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
