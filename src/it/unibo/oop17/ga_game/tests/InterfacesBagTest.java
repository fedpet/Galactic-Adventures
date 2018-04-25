package it.unibo.oop17.ga_game.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.junit.Test;

import it.unibo.oop17.ga_game.utils.InterfacesBag;
import it.unibo.oop17.ga_game.utils.InterfacesBagImpl;

interface Parent {
}

interface ChildA extends Parent {
}

interface ChildB extends Parent {
}

interface ChildofAB extends ChildA, ChildB {
}

class ConcreteParent implements Parent {
}

class ConcreteChildA implements ChildA {
}

/**
 * Test for {@link InterfacesBagImpl}.
 */
public class InterfacesBagTest {

    /**
     * It must not be possible to add the parent interface to the bag. It would make no sense and it would "fill" the
     * whole bag.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCannotAddParent() {
        final InterfacesBag<Parent> bag = new InterfacesBagImpl<>(Parent.class);

        bag.put(new Parent() {
        });
    }

    /**
     * Basic test for put and get.
     */
    @Test
    public void testPut() {
        final InterfacesBag<Parent> bag = new InterfacesBagImpl<>(Parent.class);
        final Parent a = new ChildA() {
        };
        final Parent b = new ChildB() {
        };
        bag.put(a);
        bag.put(b);

        assertTrue(bag.get(ChildA.class).isPresent());
        assertEquals(a, bag.get(ChildA.class).get());
        assertTrue(bag.get(ChildB.class).isPresent());
        assertEquals(b, bag.get(ChildB.class).get());
    }

    /**
     * Must not be possible as it would make no sense.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testParentCannotBeConcreteClass() {
        new InterfacesBagImpl<>(ConcreteParent.class);
    }

    /**
     * Must not be possible since this is a bag of Interfaces.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCannotGetByConcreteClass() {
        final InterfacesBag<Parent> bag = new InterfacesBagImpl<>(Parent.class);
        final Parent a = new ConcreteChildA();
        final Parent b = new ChildB() {
        };
        bag.put(a);
        bag.put(b);

        bag.get(ConcreteChildA.class);
    }

    /**
     * It must not be possible to get elements by the parent interface.
     * (What element would we return?)
     */
    @Test
    public void testParentNotPresent() {
        final InterfacesBag<Parent> bag = new InterfacesBagImpl<>(Parent.class);

        bag.put(new ChildA() {
        });

        assertFalse(bag.get(Parent.class).isPresent());
    }

    /**
     * Remove a specific element.
     */
    @Test
    public void testRemoveByElement() {
        final InterfacesBag<Parent> bag = new InterfacesBagImpl<>(Parent.class);
        final ChildA a = new ChildA() {
        };
        final ChildB b = new ChildB() {
        };
        bag.put(b);
        bag.put(a);
        bag.remove(a);

        assertTrue(bag.get(ChildB.class).isPresent());
        assertFalse(bag.get(ChildA.class).isPresent());
    }

    /**
     * Remove elements by the interface.
     */
    @Test
    public void testRemoveByInterface() {
        final InterfacesBag<Parent> bag = new InterfacesBagImpl<>(Parent.class);
        final ChildA a = new ChildA() {
        };
        final ChildB b = new ChildB() {
        };
        bag.put(b);
        bag.put(a);
        bag.remove(ChildA.class);

        assertTrue(bag.get(ChildB.class).isPresent());
        assertFalse(bag.get(ChildA.class).isPresent());
    }

    /**
     * Here we test adding an element implementing multiple interfaces.
     */
    @Test
    public void testSubInterfaces() {
        final InterfacesBag<Parent> bag = new InterfacesBagImpl<>(Parent.class);
        final ChildofAB ab = new ChildofAB() {
        };

        bag.put(ab);

        assertTrue(bag.get(ChildA.class).isPresent());
        assertEquals(ab, bag.get(ChildA.class).get());
        assertTrue(bag.get(ChildB.class).isPresent());
        assertEquals(ab, bag.get(ChildB.class).get());
        assertTrue(bag.get(ChildofAB.class).isPresent());
        assertEquals(ab, bag.get(ChildofAB.class).get());
    }

    /**
     * Cannot add the same interface more than once.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testInterfacesClash() {
        final InterfacesBag<Parent> bag = new InterfacesBagImpl<>(Parent.class);
        final ChildA a = new ChildA() {
        };
        final ChildofAB ab = new ChildofAB() {
        };

        bag.put(ab);
        bag.put(a);
    }

    /**
     * Here we test the stream method.
     */
    @Test
    public void testStream() {
        final InterfacesBag<Parent> bag = new InterfacesBagImpl<>(Parent.class);
        final ChildA a = new ChildA() {
        };
        final ChildB b = new ChildB() {
        };

        bag.put(a);
        bag.put(b);

        assertEquals(bag.stream().collect(Collectors.toList()), Arrays.asList(a, b));
    }
}
