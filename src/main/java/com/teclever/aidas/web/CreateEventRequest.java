package com.teclever.aidas.web;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateEventRequest(
        @NotBlank @Size(max = 255) String type,
        @NotBlank @Size(max = 4000) String payload) {
}
