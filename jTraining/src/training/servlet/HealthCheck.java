package training.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import training.model.Health;
import training.model.HealthCheckLogic;

@WebServlet("/HealthCheck")
public class HealthCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,HttpServletResponse response)
			throws ServletException, IOException {
		// フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/healthCheck.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request,HttpServletResponse response)
			throws ServletException, IOException {

		// リクエストパラメータを取得
		// 身長
		String weight = request.getParameter("weight");
		 // 体重
		String height = request.getParameter("height");

		// 入力値をプロパティに設定
		Health health = new Health();
		health.setHeight(Double.parseDouble(height));
		health.setWeight(Double.parseDouble(weight));
		// 健康診断を実行し結果を設定
		HealthCheckLogic healthCheckLogic = new HealthCheckLogic();
		healthCheckLogic.execute(health);

		// リクエストスコープに保存
		request.setAttribute("health", health);

		// フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/healthCheckResult.jsp");
		dispatcher.forward(request, response);
	}
}
