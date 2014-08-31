package pl.magazyn.exceptions;

public class DbException extends Exception {

    /***/
    private static final long serialVersionUID = 1482861916310102379L;

    public DbException(String msg) {
        super(msg);
    }

    public DbException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
