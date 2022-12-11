package com.ddd.onetwothree.entity.converter

import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

@Converter(autoApply = true)
class BooleanConverter: AttributeConverter<Boolean, String> {
    override fun convertToDatabaseColumn(attribute: Boolean): String {
        return when (attribute) {
            true -> "Y"
            false -> "N"
        }
    }

    override fun convertToEntityAttribute(dbData: String): Boolean {
        return "Y" == dbData
    }
}