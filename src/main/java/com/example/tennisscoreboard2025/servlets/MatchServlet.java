package com.example.tennisscoreboard2025.servlets;

import com.example.tennisscoreboard2025.models.Match;
import com.example.tennisscoreboard2025.services.OngoingMatchService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;
import java.util.UUID;

@WebServlet("/match")
public class MatchServlet extends HttpServlet {
    OngoingMatchService ongoingMatchService = OngoingMatchService.getInstance();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uuid =  req.getParameter("uuid");
        if(uuid==null || uuid.isEmpty()){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing uuid parameter");
        }
        Optional<Match> match = ongoingMatchService.getMatch(UUID.fromString(uuid));
        if(match.isEmpty()){
            resp.sendError(404, "Match not found");
        } else {
            req.setAttribute("match", match.get());
            req.getRequestDispatcher("/views/match.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uuid = req.getParameter("uuid");
        Optional<Match> match = ongoingMatchService.getMatch(UUID.fromString(uuid));
        if (match.isEmpty()){
            resp.sendError(502, "Match not found");
        } else {
            Match matchObj = match.get();

        }
    }
}
