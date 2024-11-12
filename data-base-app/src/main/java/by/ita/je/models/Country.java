package by.ita.je.models;

import by.ita.je.mappers.BrandConverter;
import lombok.*;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Convert(converter = BrandConverter.class)
    private CountryEnum country;

    @Getter
    public enum CountryEnum {
        BELARUS(1),
        USA(2),
        CYPRUS(3),
        INDIA(4);

        private Integer code;

        CountryEnum(Integer code) {
            this.code = code;
        }
    }

    @OneToOne(cascade = CascadeType.ALL)
    private TV tv;
}
