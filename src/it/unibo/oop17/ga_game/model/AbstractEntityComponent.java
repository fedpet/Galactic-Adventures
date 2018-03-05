package it.unibo.oop17.ga_game.model;

import java.util.Optional;

public abstract class AbstractEntityComponent implements EntityComponent {
    private Optional<Entity> owner = Optional.empty();

    @Override
    public final Optional<Entity> getOwner() {
        return owner;
    }

    @Override
    public final void attach(final Entity owner) throws IllegalStateException {
        this.owner.ifPresent(e -> {
            throw new IllegalStateException();
        });
        this.owner = Optional.of(owner);
    }
}