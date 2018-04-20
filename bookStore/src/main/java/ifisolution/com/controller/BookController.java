package ifisolution.com.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import ifisolution.com.dao.BookDAO;
import ifisolution.com.model.Book;
import ifisolution.com.uniti.Uniti;

@RestController
@RequestMapping("app/book")
public class BookController {
	@Autowired
	private BookDAO bookDAO;
	//get all book
	@RequestMapping(value = "/getAllBook", method = RequestMethod.GET)
	public ResponseEntity<?> getAllBook() {
		List<Book> result=bookDAO.getAllBook();
		if(result.isEmpty())
			return new ResponseEntity<>("not found", HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<Book>>(result,HttpStatus.FOUND);
	}
	//save book
	@RequestMapping(value = "/saveBook", method = RequestMethod.POST)
	public ResponseEntity<?> saveBook(@RequestBody Book obj) {
		if(bookDAO.saveBook(obj))
			return new ResponseEntity<Book>(obj,HttpStatus.OK);
		return new ResponseEntity<>("not save",HttpStatus.NOT_FOUND);
	}
	//update book
	@RequestMapping(value="/updateBook/{idBook}",method=RequestMethod.PUT)
	public ResponseEntity<?> updateBook(@PathVariable("idBook") int idBook,@RequestBody Book obj){
		obj.setIdbook(idBook);
		if(bookDAO.updateBook(obj))
			return new ResponseEntity<Book>(obj,HttpStatus.OK);
		return new ResponseEntity<>("not update",HttpStatus.NOT_FOUND);
	}
	//delete book
	@RequestMapping(value = "/deleteBook/{idBook}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> deleteBook(@PathVariable("idBook") int idBook) {
		if(bookDAO.deleteBook(idBook)) {
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		}
		return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
	}
	//get catergory
	@RequestMapping(value = "/getCatergory", method = RequestMethod.GET)
	public ResponseEntity<?> getCatergory(@RequestParam Integer page,
			@RequestParam Integer pageSize, @RequestParam String catergory,@RequestParam(required = false) String sortedColumn,
			@RequestParam(required = false) Boolean desc) {
			List<Book> alldata = bookDAO.getlistCatergory(catergory, page, pageSize, sortedColumn, desc);
			if(alldata.isEmpty()==true)
				System.err.println("loi");
			Uniti un=new Uniti();
			un.setListPage(alldata);
			if(alldata.isEmpty())
				return new ResponseEntity<>("not fuond catergory",HttpStatus.NOT_FOUND);
			return new ResponseEntity<Uniti>(un, HttpStatus.OK); 
	}
	//get id book
	@RequestMapping(value = "/getIbBook/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getIbBook(@PathVariable("id") int id) {
			Book currBook=bookDAO.getIdBook(id); 
			if(currBook.equals(null))
				return new ResponseEntity<>("not fuond idBook",HttpStatus.NOT_FOUND);
			return new ResponseEntity<Book>(currBook, HttpStatus.OK); 
	}
	//get list name
	@RequestMapping(value = "/getName", method = RequestMethod.GET)
	public ResponseEntity<?> getName(@RequestParam Integer page,
			@RequestParam Integer pageSize, @RequestParam String name,@RequestParam(required = false) String sortedColumn,
			@RequestParam(required = false) Boolean desc) {
			List<Book> alldata = bookDAO.getListName(name, page, pageSize, sortedColumn, desc);
			if(alldata.isEmpty())
				return new ResponseEntity<>("not fuond catergory",HttpStatus.NOT_FOUND);
			return new ResponseEntity<List<Book>>(alldata, HttpStatus.OK); 
	}
	//get list author
	@RequestMapping(value = "/getAuthor", method = RequestMethod.GET)
	public ResponseEntity<?> getAuthor(@RequestParam Integer page,
			@RequestParam Integer pageSize, @RequestParam String author,@RequestParam(required = false) String sortedColumn,
			@RequestParam(required = false) Boolean desc) {
			List<Book> alldata = bookDAO.getListAuthor(author, page, pageSize, sortedColumn, desc);
			if(alldata.isEmpty())
				return new ResponseEntity<>("not fuond catergory",HttpStatus.NOT_FOUND);
			return new ResponseEntity<List<Book>>(alldata, HttpStatus.OK); 
	}
	// get list price
	@RequestMapping(value = "/getPrice", method = RequestMethod.GET)
	public ResponseEntity<?> getPrice(@RequestParam Integer page,
			@RequestParam Integer pageSize, @RequestParam float price,@RequestParam(required = false) String sortedColumn,
			@RequestParam(required = false) Boolean desc) {
			List<Book> alldata = bookDAO.getListPrice(price, page, pageSize, sortedColumn, desc);
			if(alldata.isEmpty())
				return new ResponseEntity<>("not fuond catergory",HttpStatus.NOT_FOUND);
			return new ResponseEntity<List<Book>>(alldata, HttpStatus.OK); 
	}
}
