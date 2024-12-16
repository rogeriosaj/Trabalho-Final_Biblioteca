// package com.biblioteca.controller;

// import java.util.List;
// import java.util.Map;
// import java.util.stream.Collectors;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.ModelAttribute;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;

// import com.biblioteca.model.Author;
// import com.biblioteca.model.Book;
// import com.biblioteca.model.Member;
// import com.biblioteca.model.Publisher;
// import com.biblioteca.service.LibraryService;


// @Controller
// @RequestMapping("/library")
// public class LibraryController {
//     @Autowired
//     private LibraryService libraryService;

//     @GetMapping
//     public String index() {
//         return "index";
//     }

//     @GetMapping("/books")
//     public String getBooks(Model model) {
//         List<Book> books = libraryService.getAllBooks();
//         model.addAttribute("books", books);
//         return "books";
//     }

//     @GetMapping("/members")
//     public String getMembers(Model model) {
//         List<Member> members = libraryService.getAllMembers();
//         model.addAttribute("members", members);
//         return "members";
//     }

//     @GetMapping("/add-book")
//     public String addBookForm(Model model) {
//         model.addAttribute("book", new Book());
//         return "add-book";
//     }

//     @PostMapping("/add-book")
//     public String addBook(@ModelAttribute Book book) {
//         libraryService.addBook(book.getTitle(), book.getAuthor());
//         return "redirect:/library/books";
//     }

//     @GetMapping("/add-member")
//     public String addMemberForm(Model model) {
//         model.addAttribute("member", new Member());
//         return "add-member";
//     }

//     @PostMapping("/add-member")
//     public String addMember(@ModelAttribute Member member) {
//         libraryService.addMember(member.getName());
//         return "redirect:/library/members";
//     }

//     @GetMapping("/issue-book") 
//     public String issueBookForm(Model model) { 
//         model.addAttribute("books", libraryService.getAllBooks().stream()
//                 .filter(Book::isAvailable)
//                 .collect(Collectors.toList()));
//         return "issue-book"; 
//     } 
    
//     @PostMapping("/issue-book") 
//     public String issueBook(@RequestParam Long bookId, @RequestParam Long memberId, Model model) { 
//         //libraryService.issueBook(bookId, memberId); 
//         //return "redirect:/library/books"; 
//         boolean issued = libraryService.issueBook(bookId, memberId);
//         if (issued){
//             return "redirect:/library/books";
//         } else {
//             model.addAttribute("message", "Book not available");
//             return "issue-book";
//         }
//     } 
    
//     @GetMapping("/return-book") 
//     public String returnBookForm() { 
//         return "return-book"; 
//     } 
    
//     @PostMapping("/return-book") 
//     public String returnBook(@RequestParam Long bookId, @RequestParam Long memberId) { 
//         libraryService.returnBook(bookId, memberId); 
//         return "redirect:/library/books"; 
//     }

//     @GetMapping("/check-fines") 
//     public String checkFinesForm() { 
//         return "check-fines"; 
//     } 
    
//     @PostMapping("/check-fines") 
//     public String checkFines(@RequestParam Long memberId, Model model) { 
//         Map<String, Long> fines = libraryService.checkFines(memberId); 
//         model.addAttribute("fines", fines); 
//         return "fines"; 
//     }

//     @GetMapping("/track-issued-books")
//     public String trackIssuedBooksForm() {
//         return "track-issued-books";
//     }

//     @PostMapping("/track-issued-books")
//     public String trackIssuedBooks(@RequestParam Long memberId, Model model) {
//         List<Book> books = libraryService.trackIssuedBooks(memberId);
//         if(books.isEmpty()){
//             model.addAttribute("warning", "Colocar ID do aluno");
//         } else {
//             model.addAttribute("books", books);
//         }
//         return "issued-books";
//     }
    
// }


package com.biblioteca.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.biblioteca.model.Author;
import com.biblioteca.model.Book;
import com.biblioteca.model.Member;
import com.biblioteca.model.Publisher;
import com.biblioteca.service.LibraryService;

@Controller
@RequestMapping("/library")
public class LibraryController {
    @Autowired
    private LibraryService libraryService;

    @GetMapping
    public String index() {
        return "index";
    }

    @GetMapping("/books")
    public String getBooks(Model model) {
        List<Book> books = libraryService.getAllBooks();
        model.addAttribute("books", books);
        return "books";
    }

    @GetMapping("/members")
    public String getMembers(Model model) {
        List<Member> members = libraryService.getAllMembers();
        model.addAttribute("members", members);
        return "members";
    }

    @GetMapping("/add-book")
    public String addBookForm(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("authors", libraryService.getAllAuthors());
        model.addAttribute("publishers", libraryService.getAllPublishers());
        return "add-book";
    }

    @PostMapping("/add-book")
    public String addBook(@RequestParam String title, @RequestParam Long authorId, @RequestParam Long publisherId) {
        Author author = libraryService.getAuthorById(authorId);
        Publisher publisher = libraryService.getPublisherById(publisherId);
        libraryService.addBook(title, author, publisher);
        return "redirect:/library/books";
    }

    @GetMapping("/add-member")
    public String addMemberForm(Model model) {
        model.addAttribute("member", new Member());
        return "add-member";
    }

    @PostMapping("/add-member")
    public String addMember(@ModelAttribute Member member) {
        libraryService.addMember(member.getName());
        return "redirect:/library/members";
    }

    @GetMapping("/issue-book")
    public String issueBookForm(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("members", libraryService.getAllMembers());
        return "issue-book";
    }

    @PostMapping("/issue-book")
    public String issueBook(@RequestParam Long bookId, @RequestParam Long memberId, Model model) {
        boolean success = libraryService.issueBook(bookId, memberId);
        if (!success) {
            model.addAttribute("error", "Book is already issued to another member.");
        }
        model.addAttribute("members", libraryService.getAllMembers());
        return "issue-book";
    }

    @GetMapping("/return-book")
    public String returnBookForm() {
        return "return-book";
    }

    @PostMapping("/return-book")
    public String returnBook(@RequestParam Long bookId, @RequestParam Long memberId) {
        libraryService.returnBook(bookId, memberId);
        return "redirect:/library/books";
    }

    @GetMapping("/check-fines")
    public String checkFinesForm() {
        return "check-fines";
    }

    @PostMapping("/check-fines")
    public String checkFines(@RequestParam Long memberId, Model model) {
        Map<String, Long> fines = libraryService.checkFines(memberId);
        model.addAttribute("fines", fines);
        return "fines";
    }

    @GetMapping("/track-issued-books")
    public String trackIssuedBooksForm() {
        return "track-issued-books";
    }

    @PostMapping("/track-issued-books")
    public String trackIssuedBooks(@RequestParam Long memberId, Model model) {
        List<Book> books = libraryService.trackIssuedBooks(memberId);
        if (books.isEmpty()) {
            model.addAttribute("warning", "Colocar ID do aluno");
        } else {
            model.addAttribute("books", books);
        }
        return "issued-books";
    }

    @GetMapping("/add-author")
    public String addAuthorForm(Model model) {
        model.addAttribute("author", new Author());
        return "add-author";
    }

    @PostMapping("/add-author")
    public String addAuthor(@RequestParam String name) {
        libraryService.addAuthor(name);
        return "redirect:/library/authors";
    }

    @GetMapping("/add-publisher")
    public String addPublisherForm(Model model) {
        model.addAttribute("publisher", new Publisher());
        return "add-publisher";
    }

    @PostMapping("/add-publisher")
    public String addPublisher(@RequestParam String name) {
        libraryService.addPublisher(name);
        return "redirect:/library/publishers";
    }

    @GetMapping("/authors")
    public String getAuthors(Model model) {
        List<Author> authors = libraryService.getAllAuthors();
        model.addAttribute("authors", authors);
        return "authors";
    }

    @GetMapping("/publishers")
    public String getPublishers(Model model) {
        List<Publisher> publishers = libraryService.getAllPublishers();
        model.addAttribute("publishers", publishers);
        return "publishers";
    }
}