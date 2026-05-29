package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import model.User;
import service.AccountService;

@WebServlet("/DepositServlet")
public class DepositServlet extends HttpServlet {
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

        String amountStr = request.getParameter("amount");

        try {
            int amount = Integer.parseInt(amountStr);

            AccountService service = new AccountService();
            boolean result = service.deposit(loginUser.getUserId(), amount);

            if (result) {
                request.setAttribute("message", "入金が完了しました。入金額：" + amount + "円");
            } else {
                request.setAttribute("error", "入金に失敗しました。口座がない、または金額が正しくありません。");
            }
        } catch (NumberFormatException e) {
            request.setAttribute("error", "金額は数字で入力してください。");
        }

        request.getRequestDispatcher("/deposit.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.sendRedirect(request.getContextPath() + "/deposit.jsp");
    }
}
    