package com.jjin;

import com.jjin.author.Author;
import com.jjin.author.AuthorJooqRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jooq.JooqTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@JooqTest(properties = {"spring.test.database.replace=none",
        "spring.datasource.url=jdbc:postgresql://localhost:5432/flyway_test"
})
@Import(AuthorJooqRepositoryTest.TestConfig.class)
class AuthorJooqRepositoryTest {
    @TestConfiguration
    public static class TestConfig {
        @Bean
        public AuthorJooqRepository authorJooqRepository() {
            return new AuthorJooqRepository();
        }
    }

    @Autowired
    private AuthorJooqRepository authorJooqRepository;

    @Test
    void bulkInsertWithSelectTest() {
        List<Author> authors = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Author author = new Author();
            author.setId(i);
            author.setFirstName("first" + i);
            author.setLastName("last" + i);
            author.setDatetime(LocalDateTime.now());
            authors.add(author);
        }
        authorJooqRepository.bulkInsert(authors);
        List<Author> list = authorJooqRepository.getList(10);
        Assertions.assertEquals(10, list.size());
    }
}