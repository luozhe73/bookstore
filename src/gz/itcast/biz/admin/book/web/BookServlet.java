package gz.itcast.biz.admin.book.web;



import gz.itcast.biz.admin.book.service.BookService;
import gz.itcast.biz.admin.book.service.BookServiceImpl;
import gz.itcast.biz.admin.type.service.TypeService;
import gz.itcast.biz.admin.type.service.TypeServiceImpl;
import gz.itcast.entity.Book;
import gz.itcast.entity.Types;
import gz.itcast.util.BaseServlet;
import gz.itcast.util.WebUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;

public class BookServlet extends BaseServlet {
	
	private TypeService service = new TypeServiceImpl();
	private BookService bookService = new BookServiceImpl();
	//查看分类
	public void toAdd(HttpServletRequest req, HttpServletResponse resp)
			throws Exception {
		//查所有分类
		List<Types> types = service.queryTypes();
		//放到request
		req.setAttribute("types",types);
		//转发到添加图书的页面
		req.getRequestDispatcher("/jsp/admin/book/add.jsp").forward(req, resp);
		
	}
	
	//保存图书
	public void save(HttpServletRequest req, HttpServletResponse resp) 
			throws Exception{
		String path = getServletContext().getRealPath("/imgs");
		
		File uploadPath = new File("e:/temps");
		if(!uploadPath.exists()){
			uploadPath.mkdir();
		}
		DiskFileItemFactory factory = new DiskFileItemFactory(10*1024,uploadPath);
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		List<FileItem> list = upload.parseRequest(req);
		
		Book book = new Book();
		
		String fileName = "";
		List<String> types = null;
		for(FileItem item: list){
			if(item.isFormField()){
				if(item.getFieldName().equals("name")){
					book.setName(item.getString("utf-8"));
				}
				if(item.getFieldName().equals("price")){
					book.setPrice(Double.parseDouble(item.getString("utf-8")));
				}
				if(item.getFieldName().equals("auth")){
					book.setAuth(item.getString("utf-8"));
				}
				if(item.getFieldName().equals("rebate")){
					book.setRebate(Double.parseDouble(item.getString("utf-8")));
				}
				if(item.getFieldName().equals("size")){
					book.setSize(Integer.parseInt(item.getString("utf-8")));
				}
				if(item.getFieldName().equals("stock")){
					book.setStock(Integer.parseInt(item.getString("utf-8")));
				}
				if(item.getFieldName().equals("publisher")){
					book.setPublisher(item.getString("utf-8"));
				}
				if(item.getFieldName().equals("publishdate")){
					book.setPublishdate(item.getString("utf-8"));
				}
				if(item.getFieldName().equals("pages")){
					book.setPages(Integer.parseInt(item.getString("utf-8")));
				}
				if(item.getFieldName().equals("printtimes")){
					book.setPrinttimes(Integer.parseInt(item.getString("utf-8")));
				}
				if(item.getFieldName().equals("versions")){
					book.setVersions(Integer.parseInt(item.getString("utf-8")));
				}
				if(item.getFieldName().equals("brief")){
					book.setBrief(item.getString("utf-8"));
				}
				if(item.getFieldName().equals("content")){
					book.setContent(item.getString("utf-8"));
				}
				if(types==null){
					types = new ArrayList<String>();
				}else{
					if(item.getFieldName().equals("types")){
						types.add(item.getString("utf-8"));
					}
				}	
			}else{
				//保存文件
				fileName = item.getName();
				String uuid = UUID.randomUUID().toString().replace("-", "");
				fileName = uuid+fileName.substring(fileName.lastIndexOf("."));
				
				FileUtils.copyInputStreamToFile(item.getInputStream(), new File(path+"/"+fileName));
				item.delete();
			}
		}
		book.setId(UUID.randomUUID().toString().replace("-", ""));
		book.setOnlinetime(WebUtil.getDateStr());
		//图片路径
		book.setImg(fileName);
		
		int size = types.size();
		String[] typesArr = new String[size];
		for(int i=0;i<size;i++){
			typesArr[i]=types.get(i);
		}
		book.setTypes(typesArr);
		//组成book信息
		bookService.save(book);
		
		resp.getWriter().write("保存成功！");
	}

}

