package it.unibo.oop17.ga_game.model.entities.components;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * A bag of interfaces.
 * It works like a Map<Interface -> Implementation> where Interface is an extension of the Parent interface specified in
 * the generic type.
 * 
 * It is different from Guava's @ClassToInstanceMap as this works with interfaces only and we account for super
 * interfaces and multiple interfaces:
 * E.g. class A implements B,C where B extends P then
 * bag.put(new A())
 * bag.get(B.class) .get(C.class) .get(P.class) are all valid uses and must return the instance of A.
 * 
 * @param <T>
 *            Parent interface of all the interfaces this bag can contain.
 */
public interface InterfacesBag<T> {
    /**
     * Gets an element by its Interface.
     * Remember to work with interfaces and not concrete types!
     * 
     * @param <C>
     *            the Interface type.
     * @param interf
     *            the interface class
     * @throws IllegalArgumentException
     *             if interf is not an interface
     * @return the element found
     */
    <C extends T> Optional<C> get(Class<C> interf) throws IllegalArgumentException;

    /**
     * Puts an element in the bag.
     * 
     * @param element
     *            the element
     * @throws IllegalArgumentException
     *             - if the element or its interfaces are already present in the bag
     *             - if you try to insert an element implementing the parent interface only
     */
    void put(T element) throws IllegalArgumentException;

    /**
     * Removes an element by its type.
     * 
     * @param <C>
     *            the type
     * @param type
     *            the type
     */
    <C extends T> void remove(Class<C> type);

    /**
     * Removes the element from the bag.
     * 
     * @param element
     *            the element.
     */
    void remove(T element);

    /**
     * Clears the bag removing all elements.
     */
    void clear();

    /**
     * @return A stream of elements.
     */
    Stream<T> stream();

    /**
     * Convenience method.
     * 
     * @param action
     *            an action to be executed on each element.
     */
    default void forEach(final Consumer<T> action) {
        stream().forEach(action);
    }
}
