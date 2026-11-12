package com.bnp.kata.rover.model.enums;

import com.bnp.kata.rover.exceptions.InvalidInstructionsFormatException;

import java.util.Arrays;
import java.util.Objects;

public interface CodeEnum {

    char getCode();

    static <E extends Enum<E> & CodeEnum> E of(Class<E> enumType, char code) {

        Objects.requireNonNull(enumType, "enumType must not be null");

        Character[] allowedValues = Arrays.stream(enumType.getEnumConstants())
                .map(CodeEnum::getCode)
                .toArray(Character[]::new);

        String errorMessage = "Invalid input format unknown code [" + code + "] for enum "
                + enumType.getSimpleName()
                + ". Allowed values "  + Arrays.toString(allowedValues);

        return Arrays.stream(enumType.getEnumConstants())
                .filter(value -> value.getCode() == code)
                .findFirst()
                .orElseThrow(() -> new InvalidInstructionsFormatException(errorMessage));
    }
}