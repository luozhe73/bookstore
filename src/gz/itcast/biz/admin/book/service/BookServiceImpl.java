package gz.itcast.biz.admin.book.service;

import gz.itcast.biz.admin.book.dao.BookDao;
import gz.itcast.biz.admin.book.dao.BookDaoImpl;
import gz.itcast.entity.Book;

public class BookServiceImpl implements BookService {
	
	private BookDao dao = new BookDaoImpl();
	public void save(Book book){
		dao.save(book);
	}
}
