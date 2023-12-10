package com.jjin.book;

import com.jjin.jooqtest.Tables;
import com.jjin.jooqtest.tables.JAuthor;
import com.jjin.jooqtest.tables.JBook;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BookJooqRepository {
    @Autowired
    private DSLContext dslContext;

    public Book getBook() {
        JAuthor jAuthor = Tables.AUTHOR.as("author");
        JBook jBook = Tables.BOOK.as("book");

        return dslContext.select(jBook.ID,
                        jBook.TITLE,
                        jAuthor.ID.as("author.id"),
                        jAuthor.FIRST_NAME.as("author.firstName"),
                        jAuthor.LAST_NAME.as("author.lastName"))
                .from(Tables.BOOK)
                .join(Tables.AUTHOR)
                .on(Tables.BOOK.AUTHOR_ID.eq(Tables.AUTHOR.ID))
                .fetchSingleInto(Book.class);
    }
}
