package ru.netology.dto;

import lombok.Data;

@Data
public class ExceptionDescription {
    private final String errorMessage;
    private final int id;

    public ExceptionDescription(String errorMessage) {
        this.errorMessage = errorMessage;
        this.id = countId++;
    }

    private static int countId = 1;
}
