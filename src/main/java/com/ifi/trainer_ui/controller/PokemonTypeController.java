package com.ifi.trainer_ui.controller;

import com.ifi.trainer_ui.pokemonTypes.bo.PokemonType;
import com.ifi.trainer_ui.pokemonTypes.service.PokemonTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PokemonTypeController {

    private PokemonTypeService pokemonTypeService;

    @GetMapping("/pokedex")
    public ModelAndView pokedex(){
        var modelAndView = new ModelAndView("pokedex");
        List<PokemonType> pokemon = pokemonTypeService.listPokemonsTypes();
        if(pokemon == null){
            pokemon = new ArrayList<>();
        }
        modelAndView.addObject("pokemonTypes", pokemon);
        return modelAndView;
    }
    /*@GetMapping("/pokedex", )
    public ModelAndView pokedexTypes(String types){

    }*/
    @Autowired
    public void setPokemonTypeService(PokemonTypeService pokemonTypeService) {
        this.pokemonTypeService = pokemonTypeService;
    }
}