package com.example.tennisscoreboard2025.servlets;

import com.example.tennisscoreboard2025.models.Match;
import com.example.tennisscoreboard2025.services.MatchService;
import com.example.tennisscoreboard2025.services.OngoingMatchService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@WebServlet("/match")
public class MatchServlet extends HttpServlet {
    OngoingMatchService ongoingMatchService = OngoingMatchService.getInstance();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uuid = req.getParameter("uuid");
        if (uuid == null || uuid.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing uuid parameter");
        }
        Optional<Match> match = ongoingMatchService.getMatch(UUID.fromString(uuid));
        if (match.isEmpty()) {
            req.getRequestDispatcher("views/home.jsp").forward(req,resp);
        } else {
            req.setAttribute("match", match.get());
            req.getRequestDispatcher("/views/match.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MatchService matchService = new MatchService();
        String uuid = req.getParameter("uuid");
        Optional<Match> matchOpt = ongoingMatchService.getMatch(UUID.fromString(uuid));
        Match match;
        int scoredPlayerNumber = Integer.parseInt(req.getParameter("player"));
        if (matchOpt.isPresent()) {
            match = matchOpt.get();
        } else {
            throw new RuntimeException("no match in uuid: " + uuid);
        }
        if(!match.isMatchFinished()){
            boolean wasOngoing = !match.isMatchFinished();
            matchService.handlePost(uuid, scoredPlayerNumber);
            if(wasOngoing &&  match.isMatchFinished()){
                req.setAttribute("match", match);
                req.setAttribute("matchJustFinished", true);
                req.getRequestDispatcher("/views/match.jsp").forward(req,resp);
                return;
            }
            resp.sendRedirect(req.getContextPath() + "/match?uuid=" + uuid);
        }
    }
}
