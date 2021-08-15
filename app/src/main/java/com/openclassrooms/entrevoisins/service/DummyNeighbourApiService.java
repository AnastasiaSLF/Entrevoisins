package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
    }


    /**
     * {@inheritDoc}
     *
     * @param neighbour
     */
    @Override
    public void createNeighbour(Neighbour neighbour) {
        neighbours.add(neighbour);
    }


    @Override
    public void addNeighbourFavorite(Neighbour neighbour) {
        neighbours.get(neighbours.indexOf(neighbour)).setFavorite(true);
    }


    @Override
    public Boolean isFavorite(Neighbour neighbour) {
        return neighbours.get(neighbours.indexOf(neighbour)).getFavorite();
    }



    @Override
    public void removeFavoriteNeighbour(Neighbour neighbour) {
        neighbours.get(neighbours.indexOf(neighbour)).setFavorite(false);
    }

    @Override
    public Neighbour getNeighbour(long id) {
        Neighbour neighbour = null;
        for(Neighbour i : neighbours) {
            if(i.getId() == id){
                neighbour = i;
                break;
            }
        }
        return neighbour;
    }

    @Override
    public List<Neighbour> getFavoriteNeighbours() {
        List<Neighbour> favoriteNeighbourList = new ArrayList<>();
        for (Neighbour a: neighbours) {
            if (a.getFavorite()){
                favoriteNeighbourList.add(a);
            }
        }
        return favoriteNeighbourList;
    }
}
