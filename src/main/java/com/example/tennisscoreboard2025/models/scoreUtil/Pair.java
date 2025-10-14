package com.example.tennisscoreboard2025.models.scoreUtil;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pair {
    int first;
    int second;

    public Pair(int first, int second) {
        this.first = first;
        this.second = second;
    }
}