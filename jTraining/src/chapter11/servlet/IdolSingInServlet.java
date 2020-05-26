package chapter11.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/IdolSingInServlet")
public class IdolSingInServlet extends HttpServlet {

    //doGetメソッド
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//tryブロック
		try {
			//変数actionをリクエストパラメータで取得
			String action = request.getParameter("action");
			//インスタンス化
//			IdolUserSignUpDAO dao = new IdolUserSignUpDAO();
			//もしactionがnullもしくはactionの要素数が0の時
			if(action == null || action.length() == 0) {
				//リクエストスコープに値を登録する属性名:users,中身:action
				request.setAttribute("users",action);
				//jspに値を送信
				gotoPage(request,response,"/idolUser.jsp");
			//値追加
			}else if(action.equals("add")) {
				String name = request.getParameter("name");
				int age = Integer.parseInt(request.getParameter("age"));
				String password = request.getParameter("password");
				request.setAttribute("users",action);
				gotoPage(request,response,"idolUser.jsp");
//				daoのaddUser(?)に値を追加(?)

            //例外処理が行われた場合最初のページに戻る
			}else {
				request.setAttribute("message","正しく操作してください");
				gotoPage(request,response,"IdolSignUp.html");
			}
		//例外処理が行われた場合最初のページに戻る
		}catch(Exception e) {
			e.printStackTrace();
			request.setAttribute("message","内部エラーが発生しています");
			gotoPage(request,response,"IdolSignUp.html");
		}
	}
	//forward処理を行う
    protected void gotoPage(HttpServletRequest request,
		  HttpServletResponse response,String page)throws ServletException,
          IOException{
	  RequestDispatcher rd = request.getRequestDispatcher(page);
	  rd.forward(request, response);

      }

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
