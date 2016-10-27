package route.reader;

import exceptions.RouteReaderException;
import route.Route;


/**
 * @author Oleg Baslak
 * @version 1.0
 * @since 27.10.2016
 */
public abstract class RouteReader {

    /**
     * Gets route from source that extends this class.
     *
     * @return read route
     */
    public abstract Route read() throws RouteReaderException;
}
