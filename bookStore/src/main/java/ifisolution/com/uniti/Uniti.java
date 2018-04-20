package ifisolution.com.uniti;

import java.util.List;

import ifisolution.com.model.Book;

public class Uniti {
	private List<Book> listpage;
	private int page;
	
	public List<Book> getListPage(){
		return this.listpage;
	}
	public void setListPage(List<Book> listpage) {
		this.listpage=listpage;
	}
	
	public int getPage() {
		return this.page;
	}
	public void setPage(int page) {
		this.page=page;
	}
}
