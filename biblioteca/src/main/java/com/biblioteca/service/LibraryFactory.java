// package com.biblioteca.service;

// import com.biblioteca.model.Book;
// import com.biblioteca.model.Member;

// public class LibraryFactory {
//     public static Book createBook(String title, String author) {
//         return new Book(title, author);
//     }

//     public static Member createMember(String name) {
//         return new Member(name);
//     }
// }

package com.biblioteca.service;

import com.biblioteca.model.Author;
import com.biblioteca.model.Book;
import com.biblioteca.model.Member;
import com.biblioteca.model.Publisher;

public class LibraryFactory {

    public static Book createBook(String title, Author author, Publisher publisher) {
        return new Book(title, author, publisher);
    }

    public static Author createAuthor(String name) {
        return new Author(name);
    }

    public static Publisher createPublisher(String name) {
        return new Publisher(name);
    }

    public static Member createMember(String name) {
        return new Member(name);
    }
}
