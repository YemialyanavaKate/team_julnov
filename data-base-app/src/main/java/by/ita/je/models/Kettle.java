package by.ita.je.models;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Kettle {
    private String type;
    private String color;
    private Boolean isElectric;
    private Boolean isInduction;
    private BigDecimal price;
    @Id
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(
            name = "sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "fridge_sequence"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "5"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    private Integer number;
    private Character energy;
    private ZonedDateTime registered;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "kettle")
    private List<Fridge> fridges;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(
            name = "kttl_tv",
            joinColumns = @JoinColumn(name = "kttl_number"),
                    //, referencedColumnName = "number")},
            inverseJoinColumns = @JoinColumn(name = "tv_number")
                    //,referencedColumnName = "number")}
    )
    private List<TV> listTV;
}
