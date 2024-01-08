package com.mbsystems.bookmarkerapi.dto;

import com.mbsystems.bookmarkerapi.entity.Bookmark;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
@AllArgsConstructor
public class BookmarksDTO {

    private final List<BookmarkDTO> bookmarkList;
    private final Long totalElements;
    private final Integer totalPages;
    private final Integer currentPage;
    private final Boolean isFirstPage;
    private final Boolean isLastPage;
    private final Boolean hasNextPage;
    private final Boolean hasPreviousPage;
}
