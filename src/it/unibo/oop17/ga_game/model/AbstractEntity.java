package it.unibo.oop17.ga_game.model;

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