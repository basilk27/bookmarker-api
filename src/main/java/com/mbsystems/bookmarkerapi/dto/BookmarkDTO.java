package com.mbsystems.bookmarkerapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

@AllArgsConstructor
@Builder
@Getter
public class BookmarkDTO {

    private final Long id;
    private final String title;
    private final String url;
    private final Instant createdAt;
}
