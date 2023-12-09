package com.KUAlchemists.backend.services;

import com.KUAlchemists.backend.models.Ingredient;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class IngredientService {
    private final List<Ingredient> ingredients; // This should be replaced with a real data source

    public IngredientService() {
        this.ingredients = new List<>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator<Ingredient> iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray(T[] a) {
                return null;
            }

            @Override
            public boolean add(Ingredient ingredient) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends Ingredient> c) {
                return false;
            }

            @Override
            public boolean addAll(int index, Collection<? extends Ingredient> c) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public Ingredient get(int index) {
                return null;
            }

            @Override
            public Ingredient set(int index, Ingredient element) {
                return null;
            }

            @Override
            public void add(int index, Ingredient element) {

            }

            @Override
            public Ingredient remove(int index) {
                return null;
            }

            @Override
            public int indexOf(Object o) {
                return 0;
            }

            @Override
            public int lastIndexOf(Object o) {
                return 0;
            }

            @Override
            public ListIterator<Ingredient> listIterator() {
                return null;
            }

            @Override
            public ListIterator<Ingredient> listIterator(int index) {
                return null;
            }

            @Override
            public List<Ingredient> subList(int fromIndex, int toIndex) {
                return null;
            }
        };
    }

    /**
     * Finds an Ingredient by its name.
     *
     * @param ingredientName The name of the ingredient to find.
     * @return The Ingredient object if found, or null if not found.
     */
    public Ingredient findIngredientByName(String ingredientName) {
        return ingredients.stream()
                .filter(ingredient -> ingredient.getName().equalsIgnoreCase(ingredientName))
                .findFirst()
                .orElse(null); // Returns null if ingredient is not found
    }
}
