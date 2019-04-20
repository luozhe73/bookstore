package gz.itcast.biz.admin.type.web;

import gz.itcast.biz.admin.type.service.TypeService;
import gz.itcast.biz.admin.type.service.TypeServiceImpl;
import gz.itcast.entity.Types;
import gz.itcast.util.BaseServlet;
import gz.itcast.util.WebUtil;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TypeServlet extends BaseServlet {
	TypeService service = new TypeServiceImpl();
	
	public void queryTypes(HttpServletRequest req, HttpServletResponse resp)
			throws Exception {
		// 查询出所有图书分类，然后放到request中去
		List<Types> types = service.queryTypes();
		req.setAttribute("types", types);
		req.getRequestDispatcher("/jsp/admin/type/list.jsp").forward(req, resp);
	}

	/**
	 * 保存方法
	 */
	public void save(HttpServletRequest req, HttpServletResponse resp)
			throws Exception {
		Types types = WebUtil.copyRequestToBean(req, Types.class);
		types = service.add(types);
		req.setAttribute("type", types);
		req.getRequestDispatcher("/jsp/admin/type/addback.jsp").forward(req, resp);
	}
}
