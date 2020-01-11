package game;

import java.util.Arrays;

public class GameOperationResult<T> {

    private final T result;

    private final boolean movementHappened;

    public GameOperationResult(T result, boolean movementHappened) {
        this.result = result;
        this.movementHappened = movementHappened;
    }

    public T getResult() {
        return result;
    }

    public boolean isMovementHappened() {
        return movementHappened;
    }

}
