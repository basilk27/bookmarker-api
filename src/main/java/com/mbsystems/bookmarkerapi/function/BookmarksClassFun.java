package com.mbsystems.bookmarkerapi.function;

import com.mbsystems.bookmarkerapi.dto.BookmarkDTO;
import com.mbsystems.bookmarkerapi.dto.BookmarksDTO;
import com.mbsystems.bookmarkerapi.entity.Bookmark;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@NoArgsConstructor
@Getter
@Component
public class BookmarksClassFun {

    private final Function<Page<BookmarkDTO>, BookmarksDTO> pageBookmarksDTOFunction = bookmarksPage ->
            BookmarksDTO.builder()
                    .bookmarkList(bookmarksPage.getContent())
                    .totalElements(bookmarksPage.getTotalElements())
                    .totalPages(bookmarksPage.getTotalPages())
                    .currentPage(bookmarksPage.getNumber() + 1)
                    .isFirstPage(bookmarksPage.isFirst())
                    .isLastPage(bookmarksPage.isLast())
                    .hasNextPage(bookmarksPage.hasNext())
                    .hasPreviousPage(bookmarksPage.hasPrevious())
                    .build();
}
