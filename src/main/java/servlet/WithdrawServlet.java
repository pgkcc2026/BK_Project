package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import model.User;
import service.AccountService;


@WebServlet("/WithdrawServlet")
public class WithdrawServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        User loginUser = (User) session.getAttribute("loginUser");

        // 未ログインの場合はログイン画面へ戻す
        if (loginUser == null) {
            response.sendRedirect(request.getContextPath() + "/Login.jsp");
            return;
        }

        String amountStr = request.getParameter("amount");

        try {
            int amount = Integer.parseInt(amountStr);

            AccountService accountService = new AccountService();
            boolean result = accountService.withdraw(loginUser.getUserId(), amount);

            if (result) {
                request.setAttribute("message", "出金が完了しました。出金額：" + amount + "円");
            } else {
                request.setAttribute("error", "出金に失敗しました。金額が正しくない、口座が存在しない、または残高不足です。");
            }

        } catch (NumberFormatException e) {
            request.setAttribute("error", "金額は数字で入力してください。");
        }

        request.getRequestDispatcher("/withdraw.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.sendRedirect(request.getContextPath() + "/withdraw.jsp");
    }
}