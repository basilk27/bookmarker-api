package com.mbsystems.bookmarkerapi.service;

import com.mbsystems.bookmarkerapi.dto.BookmarkDTO;
import com.mbsystems.bookmarkerapi.dto.BookmarksDTO;
import com.mbsystems.bookmarkerapi.function.BookmarksClassFun;
import com.mbsystems.bookmarkerapi.repository.BookmarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class BookmarkService {

    private final BookmarkRepository bookmarkRepository;
    private final BookmarksClassFun bookmarksClassFun;

    @Transactional(readOnly = true)
    public BookmarksDTO getAllBookmarks(Integer page) {
        int pageNum = page < 1 ? 0 : page - 1;

        Pageable pageable = PageRequest.of(pageNum, 10, Sort.Direction.DESC, "createdAt");

        Page<BookmarkDTO> bookmarkDTOPage = this.bookmarkRepository.findBookmarks(pageable);

        return bookmarksClassFun.getPageBookmarksDTOFunction()
                .apply(bookmarkDTOPage);
    }
}
