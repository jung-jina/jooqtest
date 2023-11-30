CREATE TABLE book (
                                  id INT NOT NULL,
                                  author_id INT NOT NULL,
                                  title VARCHAR(400) NOT NULL,

                                  CONSTRAINT pk_book PRIMARY KEY (id),
                                  CONSTRAINT fk_book_author_id FOREIGN KEY (author_id) REFERENCES public.author(id)
);
