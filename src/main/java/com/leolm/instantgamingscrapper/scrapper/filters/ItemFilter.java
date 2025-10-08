package com.leolm.instantgamingscrapper.scrapper.filters;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ItemFilter {
    public static String filtrarNomePlataformaDoTituloPorRegex(String[] tituloJogo) {
        Pattern pattern = Pattern.compile("\\([^)]*\\)");
        Matcher matcher = pattern.matcher(tituloJogo[0]);

        List<String> matches = new ArrayList<>();
        while (matcher.find()) {
            matches.add(matcher.group());
        }
        if (matches.isEmpty()) {
            return "PLATAFORMA NAO ENCONTRADA";
        }
        String plataforma = matches.getLast();
        return plataforma;
    }
}
