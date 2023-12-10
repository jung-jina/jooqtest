package com.jjin.book;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jooq.JooqTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@JooqTest(properties = {"spring.test.database.replace=none",
        "spring.datasource.url=jdbc:postgresql://localhost:5432/flyway_test"})
class BookJooqRepositoryTest {
    @TestConfiguration
    public static class TestConfig {
        @Bean
        public BookJooqRepository bookJooqRepository() {
            return new BookJooqRepository();
        }
    }

    @Autowired
    private BookJooqRepository bookJooqRepository;

    @Test
    void joinSelectTest() {
        Book book = bookJooqRepository.getBook();
        Assertions.assertNotNull(book);
        Assertions.assertNotNull(book.getAuthor());
    }
}