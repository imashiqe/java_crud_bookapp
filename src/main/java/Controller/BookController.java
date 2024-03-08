package Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    private List<Book> books = new ArrayList<>().reversed();
    private Long nextId = 1L;

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(books);
    }

    @PostMapping
    public ResponseEntity<Object> addBook(@RequestBody Book book) {
        book.setId(nextId++);
        books.add(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(book);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeBook(@PathVariable Long id) {
        books.removeIf(book -> book.getId().equals(id));
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getBook(@PathVariable Long id) {
        for (Book book : books) {
            if (book.getId().equals(id)) {
                return ResponseEntity.ok(book);
            }
        }
        return ResponseEntity.notFound().build();
    }
}
