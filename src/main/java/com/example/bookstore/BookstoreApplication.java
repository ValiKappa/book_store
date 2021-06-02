package com.example.bookstore;
import com.example.bookstore.model.Author;
import com.example.bookstore.model.Books;
import com.example.bookstore.model.User;
import com.example.bookstore.service.AuthorService;
import com.example.bookstore.service.BooksService;
import com.example.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import javax.annotation.PostConstruct;
import java.util.Set;

@SpringBootApplication
public class BookstoreApplication {

	@Autowired
	private AuthorService authorService;
	@Autowired
	private BooksService booksService;

	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@PostConstruct
	public void execute() {
		authorService.save(Author.builder().authorName("Stephan King").build());
		authorService.save(Author.builder().authorName("Michael Connelly").build());
		authorService.save(Author.builder().authorName("Nora Roberts").build());
		authorService.save(Author.builder().authorName("Dan Brown").build());
		authorService.save(Author.builder().authorName("Dean Kunz").build());

		Set<Author> authors = authorService.findAll();
		authors.forEach(author -> System.out.println(author.toString()));

		Books romanceBook = Books.builder().name("Rose").genre("Romance").year(2000).build();
		Author romanceAuthor = authorService.findByAuthorName("Nora Roberts");
		booksService.save(romanceBook, romanceAuthor);
		Books horrorBook = Books.builder().name("It").genre("Horror").year(1985).build();
		Author horrorAuthor = authorService.findByAuthorName("Stephan King");
		booksService.save(horrorBook, horrorAuthor);
		Books mysteryBook = Books.builder().name("The Da Vinci Code").genre("Mystery").year(2003).build();
		Author mysteryAuthor = authorService.findByAuthorName("Dan Brown");
		booksService.save(mysteryBook, mysteryAuthor);

		User firstUser = User.builder().username("Peter_91").email("Peter_91@gmail.com").build();
		userService.save(firstUser);
		userService.saveBook(horrorBook, firstUser);
		User secondUser = User.builder().username("Ivelina34").email("Iva_Georgieva@gmail.com").build();
		userService.save(secondUser);
		userService.saveBook(mysteryBook, secondUser);
		User thirdUser = User.builder().username("Kiril_Nedev").email("Kiril_Nedev@gmail.com").build();
		userService.save(thirdUser);
		userService.saveBook(romanceBook, thirdUser);





		//authorService.deleteByAuthorName("Stephan King");
//		Author author = authorService.findByAuthorName("Dean Kunz");
//		System.out.println(author.toString());

	}
}
