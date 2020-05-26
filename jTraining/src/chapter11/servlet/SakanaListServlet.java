package chapter11.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import chapter11.beans.ListBean;
import chapter11.dao.DAOException;
import chapter11.dao.ListDAO;

@WebServlet("/SakanaListServlet")
public class SakanaListServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			ListDAO dao = new ListDAO();
			List<ListBean> list = dao.findAll();
			request.setAttribute("songs",list);
			RequestDispatcher rd = request.getRequestDispatcher("/showList.jsp");
			rd.forward(request, response);
		}catch(DAOException e) {
			e.printStackTrace();
			request.setAttribute("message", "内部エラーが発生しました。");
			RequestDispatcher rd =
					request.getRequestDispatcher("/errInternal.jsp");
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
