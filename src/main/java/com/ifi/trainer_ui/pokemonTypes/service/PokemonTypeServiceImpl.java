package com.ifi.trainer_ui.pokemonTypes.service;

import com.ifi.trainer_ui.pokemonTypes.bo.PokemonType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class PokemonTypeServiceImpl implements PokemonTypeService {
    private RestTemplate template;
    private String pokemonUrl;

    public List<PokemonType> listPokemonsTypes() {
        List<PokemonType> pokemons = null;
        PokemonType[] pokemonsResponse = template.getForObject(pokemonUrl, PokemonType[].class);
        if (pokemonsResponse != null) {
            pokemons = Arrays.asList(pokemonsResponse);

        }
        return pokemons;
    }

    public List<PokemonType> listPokemonsTypesByTypes(List<String> types){
        List<PokemonType> pokemons = null;
        PokemonType[] pokemonsResponse = template.getForObject(pokemonUrl + "?type", PokemonType[].class);
        if (pokemonsResponse != null) {
            pokemons = Arrays.asList(pokemonsResponse);

        }
        return pokemons;
    }
    @Autowired
    void setRestTemplate(RestTemplate restTemplate) {
        template = restTemplate;
    }
    @Value("${pokemonType.service.url}")
    void setPokemonTypeServiceUrl(String pokemonServiceUrl) {
        pokemonUrl = pokemonServiceUrl + "/pokemon-types/";
    }
}
