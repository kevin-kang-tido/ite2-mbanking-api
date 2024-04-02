package co.istad.ite2mbanking.feature.media.dto;

import lombok.Builder;

@Builder
public record MediaResponse(
        // Unique
        String name,// file name
        String contentType,// pdf jpg..
        String extension,
        String uri,
        Long size
) {
}
