package com.jjin.author;

import com.jjin.book.Book;
import com.jjin.jooqtest.Tables;
import com.jjin.jooqtest.tables.JAuthor;
import com.jjin.jooqtest.tables.JBook;
import com.jjin.jooqtest.tables.records.JAuthorRecord;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class AuthorJooqRepository {
    @Autowired
    private DSLContext dslContext;

    public List<Author> getList(Integer size) {
        return dslContext.select(JAuthor.AUTHOR)
                .from(JAuthor.AUTHOR)
                .orderBy(JAuthor.AUTHOR.ID.desc())
                .limit(size)
                .fetchInto(Author.class);
    }

    public void bulkInsert(List<Author> list) {
        List<JAuthorRecord> collect = list.stream()
                .map(x -> dslContext.newRecord(JAuthor.AUTHOR, x))
                .collect(Collectors.toList());
        dslContext.batchInsert(collect).execute();
    }

    public List<Book> getList() {
        JAuthor jAuthor = Tables.AUTHOR.as("author");
        JBook jBook = Tables.BOOK.as("book");
        return dslContext.select(jBook.ID.as("id"),
                        jBook.TITLE.as("title"),
                        jAuthor.FIRST_NAME.as("author.firstName"),
                        jAuthor.LAST_NAME.as("author.lastName"))
                .from(Tables.BOOK)
                .join(Tables.AUTHOR)
                .on(Tables.BOOK.AUTHOR_ID.eq(Tables.AUTHOR.ID))
                .fetchInto(Book.class);
    }

}
