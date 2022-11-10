package com.codebyte.userservice.converter;

import com.github.f4b6a3.ulid.Ulid;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class ULIDConverter implements AttributeConverter<String, byte[]> {

    @Override
    public byte[] convertToDatabaseColumn(String ulid) {
        if (null == ulid) {
            return null;
        }
        return Ulid.from(ulid).toBytes();
    }

    @Override
    public String convertToEntityAttribute(byte[] ulidBytes) {
        if (null == ulidBytes) {
            return null;
        }
        return Ulid.from(ulidBytes).toString();
    }
}
