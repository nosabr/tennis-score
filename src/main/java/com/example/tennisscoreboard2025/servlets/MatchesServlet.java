package com.example.tennisscoreboard2025.servlets;

import com.example.tennisscoreboard2025.dao.MatchDAO;
import com.example.tennisscoreboard2025.models.Match;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/matches")
public class MatchesServlet extends HttpServlet {
    private static final int MATCHES_PER_PAGE = 10;
    private final MatchDAO matchDAO = new MatchDAO();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pageParam = req.getParameter("page");
        String name = req.getParameter("filter_by_player_name");
        int currentpage = 1;
        if(pageParam != null && !pageParam.isEmpty()){
            currentpage = Integer.parseInt(pageParam);
            if (currentpage < 1) currentpage = 1;
        }

        List<Match> matches;
        int totalMatches;
        if(name != null && !name.trim().isEmpty()){
            matches = matchDAO.findByName(name.trim(), currentpage,MATCHES_PER_PAGE);
            totalMatches = matchDAO.countByName(name); // почему бы просто не измерять длину листа
        } else {
            matches = matchDAO.findAll(currentpage,MATCHES_PER_PAGE);
            totalMatches = matchDAO.count();
        }
        int totalPages = (int) Math.ceil((double)totalMatches / MATCHES_PER_PAGE);
        req.setAttribute("matches", matches);
        req.setAttribute("currentPage", currentpage);
        req.setAttribute("totalPages", totalPages);
        req.setAttribute("totalMatches", totalMatches);
        req.setAttribute("filterByPlayerName", name);

        req.getRequestDispatcher("/views/matches.jsp").forward(req, resp);
    }
}
