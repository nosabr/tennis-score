package com.example.tennisscoreboard2025.servlets;

import com.example.tennisscoreboard2025.models.Match;
import com.example.tennisscoreboard2025.services.MatchGenerationService;
import com.example.tennisscoreboard2025.services.OngoingMatchService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/new-match")
public class NewMatchServlet extends HttpServlet {
    MatchGenerationService matchGenerationService = new MatchGenerationService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/new-match.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        OngoingMatchService ongoingMatchService = OngoingMatchService.getInstance();
        String firstPlayerName = req.getParameter("p1");
        String secondPlayerName = req.getParameter("p2");
        Match match = matchGenerationService.generateNewMatch(firstPlayerName,secondPlayerName);
        UUID uuid = ongoingMatchService.putMatch(match);
        resp.sendRedirect(req.getContextPath()+"/match?uuid="+uuid);
    }
}
