package ca.ualberta.cmput301.t03;

/**
 * Created by ross on 15-10-29.
 */
public interface Observer<T extends Observable> {
    void update(T observable);
}
