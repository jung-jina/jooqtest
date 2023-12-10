package com.jjin.book;

import com.jjin.jooqtest.Tables;
import com.jjin.jooqtest.tables.JAuthor;
import com.jjin.jooqtest.tables.JBook;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.DefaultDSLContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.simpleflatmapper.jooq.JooqMapperFactory;
import org.simpleflatmapper.jooq.SelectQueryMapper;
import org.simpleflatmapper.jooq.SelectQueryMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jooq.JooqTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.util.List;

@JooqTest(properties = {"spring.test.database.replace=none",
        "spring.datasource.url=jdbc:postgresql://localhost:5432/flyway_test"
})
class SFMJooqRepoTest {
    @TestConfiguration
    public static class TestConfig {
        @Bean
        public DefaultConfiguration configuration(@Autowired DataSource dataSource) {
            DefaultConfiguration jooqConfiguration = new DefaultConfiguration();
            jooqConfiguration.set(SQLDialect.POSTGRES);
            jooqConfiguration.set(dataSource);
            jooqConfiguration.set(JooqMapperFactory.newInstance().ignorePropertyNotFound().newRecordMapperProvider());
            return jooqConfiguration;
        }

        @Bean
        public DSLContext dslContext(@Autowired DefaultConfiguration configuration) {
            return new DefaultDSLContext(configuration);
        }
    }

    @Autowired
    private DSLContext dslContext;

    @Test
    void name() {
        JAuthor jAuthor = Tables.AUTHOR.as("author");
        JBook jBook = Tables.BOOK.as("book");
        SelectQueryMapper<Book> mapper = SelectQueryMapperFactory.newInstance().newMapper(Book.class);

        List<Book> selectField = mapper.asList(dslContext.select(jAuthor.ID, jAuthor.FIRST_NAME, jAuthor.LAST_NAME,
                        jBook.ID)
                .from(jBook)
                .join(jAuthor)
                .on(jBook.AUTHOR_ID.eq(jAuthor.ID))
        );
        Assertions.assertNotNull(selectField.get(0));
        Assertions.assertNotNull(selectField.get(0).getAuthor().getId());
        Assertions.assertNull(selectField.get(0).getAuthor().getDatetime());

        List<Book> selectAll = mapper.asList(dslContext.select()
                .from(Tables.BOOK)
                .join(Tables.AUTHOR)
                .on(Tables.BOOK.AUTHOR_ID.eq(Tables.AUTHOR.ID)));

        Assertions.assertNotNull(selectAll.get(0));
        Assertions.assertNotNull(selectAll.get(0).getAuthor().getId());
    }
}
