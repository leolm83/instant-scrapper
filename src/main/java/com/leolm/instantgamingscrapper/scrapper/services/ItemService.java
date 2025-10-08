package com.leolm.instantgamingscrapper.scrapper.services;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.leolm.instantgamingscrapper.scrapper.exceptions.PlatformNotFoundException;
import com.leolm.instantgamingscrapper.scrapper.filters.ItemFilter;

@Service
public class ItemService {
    private final Map<String, Set<String>> jogos;

    public ItemService(Map<String, Set<String>> jogos) {
        this.jogos = jogos;
    }

    public void inserirJogoNaLista(String nomeJogo, String plataforma) {
        if (!jogos.containsKey(plataforma)) {
            Set<String> jogosDaPlatoforma = new HashSet<>();
            jogosDaPlatoforma.add(nomeJogo);
            jogos.put(plataforma, jogosDaPlatoforma);
        } else {
            jogos.get(plataforma).add(nomeJogo);
        }
    }

    public String obterNomePlataforma(String[] tituloJogo) {
        String plataforma;
        if (tituloJogo.length > 1) {
            plataforma = tituloJogo[tituloJogo.length - 1];
        } else {
            plataforma = ItemFilter.filtrarNomePlataformaDoTituloPorRegex(tituloJogo);
            System.out.println("OPS NAO HA - NO NOME DO JOGO " + tituloJogo[0]);
        }
        return plataforma;
    }

    public void showItens() {
        for (String plataforma : jogos.keySet()) {
            System.out.println("JOGOS DA PLATAFORMA " + plataforma + "   " + jogos.get(plataforma).size());
        }
    }

    public void showByPlatformName(String platformName) {
        if (!jogos.containsKey(platformName)) {
            throw new PlatformNotFoundException("Plataforma Nao encontrada" + String.valueOf(platformName));
        }
        System.out.println("JOGOS ENCONTRADOS NA PLATAFORMA '" + platformName + "'");

        for (String jogo : jogos.get(platformName)) {
            System.out.println(jogo);
        }
    }
}
