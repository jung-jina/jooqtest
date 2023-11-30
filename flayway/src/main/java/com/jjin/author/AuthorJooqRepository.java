package com.jjin.author;

import com.jjin.jooqtest.tables.JAuthor;
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
}
