package co.istad.ite2mbanking.feature.media.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

@Builder
public record MediaResponse(
        // Unique
        String name,// file name
        String contentType,// pdf jpg..
        String extension,
        String uri,
        @JsonInclude(JsonInclude.Include.NON_NULL)// if size is null not show in postman
        Long size
) {
}
