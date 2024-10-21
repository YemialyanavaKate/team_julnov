package by.ita.je.mappers;

import by.ita.je.models.Country;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class BrandConverter implements AttributeConverter<Country.CountryEnum, Integer> {
    @Override
    public Integer convertToDatabaseColumn(Country.CountryEnum brand) {
        return brand != null ? brand.getCode() : null;
    }

    @Override
    public Country.CountryEnum convertToEntityAttribute(Integer dbData) {
        for (Country.CountryEnum brand : Country.CountryEnum.values()) {
            if (brand.getCode().equals(dbData)) return brand;
        }
        throw new IllegalArgumentException("Неизвестный брэнд:" + dbData);
    }
}
