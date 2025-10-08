package com.example.tennisscoreboard2025.servlets;

import com.example.tennisscoreboard2025.models.Match;
import com.example.tennisscoreboard2025.services.OngoingMatchService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@WebServlet("/match")
public class MatchServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();

        String uuid = request.getParameter("uuid");
        Optional<Match> match = OngoingMatchService.getMatch(uuid);
        if(match.isPresent()){
            out.print("Match found");
            out.print(match.get().getPlayer1().getName());
            out.print(match.get().getPlayer2().getName());
            out.print(uuid);
        } else {
            out.print("No match found");
        }
    }
}
