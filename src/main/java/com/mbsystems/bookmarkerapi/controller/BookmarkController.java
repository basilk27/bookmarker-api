package com.mbsystems.bookmarkerapi.controller;

import com.mbsystems.bookmarkerapi.dto.BookmarksDTO;
import com.mbsystems.bookmarkerapi.service.BookmarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/bookmarks")
public class BookmarkController {

    private final BookmarkService bookmarkService;

    @GetMapping(path = "/all")
    public BookmarksDTO getAllBookmarks(@RequestParam(name = "page", defaultValue = "1") Integer page) {
        return this.bookmarkService.getAllBookmarks(page);
    }
}
