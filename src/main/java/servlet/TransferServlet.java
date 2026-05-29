package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import model.User;
import service.AccountService;

@WebServlet("/TransferServlet")
public class TransferServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        User loginUser = (User) session.getAttribute("loginUser");

        if (loginUser == null) {
            response.sendRedirect(request.getContextPath() + "/Login.jsp");
            return;
        }

        String toAccountIdStr = request.getParameter("toAccountId");
        String amountStr = request.getParameter("amount");

        try {
            int toAccountId = Integer.parseInt(toAccountIdStr);
            int amount = Integer.parseInt(amountStr);

            AccountService accountService = new AccountService();
            boolean result = accountService.transfer(loginUser.getUserId(), toAccountId, amount);

            if (result) {
                request.setAttribute("message", "送金が完了しました。送金額：" + amount + "円");
            } else {
                request.setAttribute("error", "送金に失敗しました。送金先口座、金額、残高を確認してください。");
            }

        } catch (NumberFormatException e) {
            request.setAttribute("error", "送金先口座IDと送金額は数字で入力してください。");
        }

        request.getRequestDispatcher("/money_transfer.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.sendRedirect(request.getContextPath() + "/money_transfer.jsp");
    }
}