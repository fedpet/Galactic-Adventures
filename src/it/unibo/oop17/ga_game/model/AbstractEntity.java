package it.unibo.oop17.ga_game.model;

/* TODO: 
 * Questo design mi fa schifo e penso dovremmo cambiarlo.
 * Ho pensato che ogni EntityBody possa sapere quando è onGround (quindi non ci sarebbe bisogno di un GroundEntityBody).
 * Ad un nemico volante semplicemente non gliene frega nulla se è onGround e non lo controlla.
 * 
 * Potremmo rifattorizzare il controllo del movimento con dei "BodyDriver"
 * ad esempio WalkingBodyDriver, AlienBodyDriver, FlyingBodyDriver
 * che prendono un body come argomento ed espongono metodi come walk, fly, jump
 * in modo da usare "Composition over inheritance" ed eliminare la WalkingEntity
 * 
 */
public abstract class AbstractEntity implements Entity {
    private final EntityBody body;
    private final Brain brain;

    public AbstractEntity(final EntityBody body, final Brain brain) {
        this.body = body;
        this.brain = brain;
        body.attach(this);
        brain.attach(this);
    }

    @Override
    public final EntityBody getBody() {
        return body;
    }

    @Override
    public final Brain getBrain() {
        return brain;
    }

}