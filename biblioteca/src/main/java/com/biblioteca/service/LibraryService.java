// package com.biblioteca.service;

// import java.time.LocalDate;
// import java.time.temporal.ChronoUnit;
// import java.util.Collections;
// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;
// import java.util.stream.Collectors;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import com.biblioteca.dao.BookDAO;
// import com.biblioteca.dao.MemberDAO;
// import com.biblioteca.model.Book;
// import com.biblioteca.model.Member;
// import com.biblioteca.observer.BookIssueReturnSubject;
// import com.biblioteca.observer.Librarian;

// @Service
// public class LibraryService {

//     @Autowired
//     private BookDAO bookDAO;

//     @Autowired
//     private MemberDAO memberDAO;

//     private BookIssueReturnSubject subject = new BookIssueReturnSubject();

//     public LibraryService() {
//         subject.addObserver(new Librarian("Bibliotecário"));
//     }

//     public List<Book> getAllBooks() {
//         return bookDAO.findAll();
//     }

//     public List<Member> getAllMembers() {
//         return memberDAO.findAll();
//     }

//     public boolean issueBook(Long bookId, Long memberId) {
//         Book book = bookDAO.findById(bookId);
//         Member member = memberDAO.findById(memberId);

//         if (book.isAvailable()) {
//             book.setAvailable(false);
//             book.setIssueDate(LocalDate.now());
//             book.setIssuedTo(member);
//             book.setDueDate(calculateDueDate(member)); // Set the member who issued the book
//             bookDAO.save(book);
//             subject.notifyObservers("Book issued: " + book.getTitle() + " to " + member.getName() + " due on " + book.getDueDate());
//             return true;
//         } else {
//             return false;
//         }
//     }

//     private LocalDate calculateDueDate(Member member) {
//         List<Book> issuedBooks = bookDAO.findAll().stream().
//                                     filter(book -> member.equals(book.getIssuedTo())).
//                                     collect(Collectors.toList());
//         LocalDate maxDueDate = LocalDate.now().plusDays(7);

//         if (issuedBooks.size() >= 3) {
//             int additionalDays = (issuedBooks.size() -2) * 2;
//             maxDueDate = maxDueDate.plusDays(additionalDays);
//         }
//         return maxDueDate;
//     }

//     public void returnBook(Long bookId, Long memberId) {
//         Book book = bookDAO.findById(bookId);
//         Member member = memberDAO.findById(memberId);//orElseThrow(() -> new IllegalArgumentException("Member not found"));
//         book.setAvailable(true);
//         book.setDueDate(LocalDate.now());
//         book.setIssuedTo(null); // Clear the issued member
//         bookDAO.save(book);
//         subject.notifyObservers("Book returned: " + book.getTitle() + " by " + member.getName());
//     }

//     public Map<String, Long> checkFines(Long memberId) {
//         Member member = memberDAO.findById(memberId);
//         List<Book> books = bookDAO.findAll().stream()
//                                   .filter(book -> member.equals(book.getIssuedTo()))
//                                   .collect(Collectors.toList());
    
//         Map<String, Long> fines = new HashMap<>();
//         for (Book book : books) {
//             if (book.getDueDate() != null && book.getIssueDate() != null) {
//                 long daysBetween = ChronoUnit.DAYS.between(book.getIssueDate(), book.getDueDate());
//                 if (daysBetween > 7) { // Assuming a 14-day loan period
//                     long fine = (daysBetween - 7) * 10; // Assuming a fine of 10 units per day
//                     fines.put(book.getTitle(), fine);
//                 }
//             }
//         }
//         return fines;
//     }
    

//     public List<Book> trackIssuedBooks(Long memberId) {
//         // Optional<Member> optionalMember = Optional.of(memberDAO.findById(memberId));
//         // if (optionalMember.isPresent()) {
//         //     Member member = optionalMember.get();
//         //     return bookDAO.findAll().stream()
//         //                   .filter(book -> member.equals(book.getIssuedTo()))
//         //                   .collect(Collectors.toList());
//         // } else {
//         //     // Handle the case where the member is not found
//         //     return Collections.emptyList();
//         // }
//         Member member = memberDAO.findById(memberId);
//         if (member == null) {
//             return Collections.emptyList();
//         }
//         return bookDAO.findAll().stream()
//                       .filter(book -> member.equals(book.getIssuedTo()))
//                       .collect(Collectors.toList());
//     }

//     public void addMember(String name) {
//         Member member = new Member(name);
//         memberDAO.save(member);
//     }

//     public void removeMember(Long memberId) {
//         memberDAO.delete(memberId);
//     }

//     public void addBook(String title, String author) {
//         Book book = new Book(title, author);
//         bookDAO.save(book);
//     }

//     public void deleteBook(Long bookId) {
//         bookDAO.delete(bookId);
//     }

//     public void updateBookAvailability(Long bookId, boolean isAvailable) {
//         Book book = bookDAO.findById(bookId);
//         book.setAvailable(isAvailable);
//         bookDAO.save(book);
//     }
// }


package com.biblioteca.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteca.dao.AuthorDAO;
import com.biblioteca.dao.BookDAO;
import com.biblioteca.dao.FinesDAO;
import com.biblioteca.dao.ItemLoanDAO;
import com.biblioteca.dao.LoanDAO;
import com.biblioteca.dao.MemberDAO;
import com.biblioteca.dao.PublisherDAO;
import com.biblioteca.model.Author;
import com.biblioteca.model.Book;
import com.biblioteca.model.ItemLoan;
import com.biblioteca.model.Loan;
import com.biblioteca.model.Member;
import com.biblioteca.model.Publisher;
import com.biblioteca.observer.BookIssueReturnSubject;
import com.biblioteca.observer.Librarian;
import com.biblioteca.repository.AuthorRepository;
import com.biblioteca.repository.BookRepository;
import com.biblioteca.repository.MemberRepository;
import com.biblioteca.repository.PublisherRepository;

@Service
public class LibraryService {
    @Autowired
    private BookDAO bookDAO;
    @Autowired
    private MemberDAO memberDAO;
    @Autowired
    private AuthorDAO authorDAO;
    @Autowired
    private PublisherDAO publisherDAO;
    @Autowired
    private LoanDAO loanDAO;
    @Autowired
    private ItemLoanDAO itemLoanDAO;
    @Autowired
    private FinesDAO finesDAO;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private PublisherRepository publisherRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private MemberRepository memberRepository;
    
    private BookIssueReturnSubject subject = new BookIssueReturnSubject();

    public LibraryService() {
        subject.addObserver(new Librarian("Bibliotecário"));
    }

    public List<Book> getAllBooks() {
        return bookDAO.findAll();
    }

    public List<Member> getAllMembers() {
        return memberDAO.findAll();
    }

    public boolean issueBook(Long bookId, Long memberId) {
        Book book = bookDAO.findById(bookId);
        Member member = memberDAO.findById(memberId);

        if (book != null && book.isAvailable() && member != null) {
            book.setAvailable(false);
            book.setIssueDate(LocalDate.now());
            book.setIssuedTo(member);
            book.setDueDate(calculateDueDate(member));
            bookDAO.save(book);

            // Create a loan and item loan
            Loan loan = new Loan(member, book.getIssueDate(), book.getDueDate());
            loanDAO.save(loan);
            ItemLoan itemLoan = new ItemLoan(loan, book);
            itemLoanDAO.save(itemLoan);
            subject.notifyObservers("Book issued: " + book.getTitle() + " to " + member.getName() + " due on " + book.getDueDate());

            return true;
        }
        return false;
    }

    private LocalDate calculateDueDate(Member member) {
        List<Loan> loans = loanDAO.findByMember(member);

        LocalDate maxDueDate = LocalDate.now().plusDays(7);

        if (loans.size() >= 3) {
            int additionalDays = (loans.size() - 2) * 2;
            maxDueDate = maxDueDate.plusDays(additionalDays);
        }

        return maxDueDate;
    }

    public void returnBook(Long bookId, Long memberId) {
        Book book = bookDAO.findById(bookId);
        Member member = memberDAO.findById(memberId);
        if (book != null) {
            book.setAvailable(true);
            book.setIssuedTo(null);
            book.setDueDate(LocalDate.now());
            bookDAO.save(book);
            subject.notifyObservers("Book returned: " + book.getTitle() + " by " + member.getName());
        }
    }

    public Map<String, Long> checkFines(Long memberId) {
        Member member = memberDAO.findById(memberId);
        if (member == null) {
            return Collections.emptyMap();
        }

        List<Loan> loans = loanDAO.findByMember(member);

        Map<String, Long> fines = new HashMap<>();
        for (Loan loan : loans) {
            for (ItemLoan itemLoan : loan.getItems()) {
                Book book = itemLoan.getBook();
                if (book.getDueDate() != null && book.getIssueDate() != null) {
                    long daysOverdue = ChronoUnit.DAYS.between(book.getDueDate(), LocalDate.now());
                    if (daysOverdue > 0) {
                        long fine = daysOverdue * 10;
                        fines.put(book.getTitle(), fine);
                    }
                }
            }
        }
        return fines;
    }

    public List<Book> trackIssuedBooks(Long memberId) {
        Member member = memberDAO.findById(memberId);
        if (member == null) {
            return Collections.emptyList();
        }
        return bookDAO.findAll().stream()
                .filter(book -> member.equals(book.getIssuedTo()))
                .collect(Collectors.toList());
    }

    public void addBook(String title, Author author, Publisher publisher) {
        Book book = new Book(title, author, publisher);
        bookDAO.save(book);
        subject.notifyObservers("Book created: " + book.getTitle() + " by " + book.getAuthor().getName() + ", published by " + book.getPublisher().getName()); 

    }

    public void addMember(String name) {
        Member member = new Member(name);
        memberDAO.save(member);
        subject.notifyObservers("Member created: " + member.getName());
    }
        

    public void removeMember(Long memberId) {
        memberDAO.deleteById(memberId);
    }

    public void deleteBook(Long bookId) {
        bookDAO.deleteById(bookId);
    }

    public void updateBookAvailability(Long bookId, boolean isAvailable) {
        Book book = bookDAO.findById(bookId);
        if (book != null) {
            book.setAvailable(isAvailable);
            bookDAO.save(book);
        }
    }

    public List<Author> getAllAuthors() { 
        return authorDAO.findAll(); 
    } 
    
    public List<Publisher> getAllPublishers() { 
        return publisherDAO.findAll(); 
    } 
    
    public Author getAuthorById(Long id) { 
        return authorRepository.findById(id).orElse(null); 
    } 
    
    public Publisher getPublisherById(Long id) { 
        return publisherRepository.findById(id).orElse(null); 
    } 
    
    public void addAuthor(String name) { 
        Author author = new Author(name); 
        authorDAO.save(author);
        subject.notifyObservers("Author created: " + author.getName()); 
    } 
    
    public void addPublisher(String name) { 
        Publisher publisher = new Publisher(name); 
        publisherDAO.save(publisher);
        subject.notifyObservers("Publisher created: " + publisher.getName()); 
    }
}