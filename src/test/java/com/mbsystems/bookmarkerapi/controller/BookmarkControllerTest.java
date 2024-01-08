package com.mbsystems.bookmarkerapi.controller;

import com.mbsystems.bookmarkerapi.entity.Bookmark;
import com.mbsystems.bookmarkerapi.repository.BookmarkRepository;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource(properties = {
        "spring.datasource.url=jdbc:tc:postgresql:14-alpine:///testDemo"
})
class BookmarkControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookmarkRepository bookmarkRepository;

    private List<Bookmark> bookmarkList;

    @BeforeEach
    void setUp() {
        this.bookmarkRepository.deleteAllInBatch();

        this.bookmarkList = List.of(
                Bookmark.builder()
                        .title("SivaLabs")
                        .url("https://sivalabs.io")
                        .createdAt(Instant.now())
                        .build(),
                Bookmark.builder()
                        .title("SpringBlog")
                        .url("https://spring.io/blog")
                        .createdAt(Instant.now())
                        .build(),
                Bookmark.builder()
                        .title("JOOQ")
                        .url("https://www.jooq.org")
                        .createdAt(Instant.now())
                        .build()
        );

        this.bookmarkRepository.saveAll(bookmarkList);
    }

    @Test
    void getAllBookmarks() throws Exception {
        this.mockMvc.perform(get("/api/v1/bookmarks/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalElements", CoreMatchers.equalTo(3)))
                .andExpect(jsonPath("$.totalPages", CoreMatchers.equalTo(1)))
                .andExpect(jsonPath("$.isFirstPage", CoreMatchers.equalTo(true)));
    }

    @ParameterizedTest
    @CsvSource(
            "1, 3, 1, true"
    )
    void getAllBookmarksParameterized(int pageNo, int totalElements, int totalPages, boolean isFirstPage) throws Exception {
        this.mockMvc.perform(get("/api/v1/bookmarks/all?page"+pageNo))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalElements", CoreMatchers.equalTo(totalElements)))
                .andExpect(jsonPath("$.totalPages", CoreMatchers.equalTo(totalPages)))
                .andExpect(jsonPath("$.isFirstPage", CoreMatchers.equalTo(isFirstPage)));
    }
}