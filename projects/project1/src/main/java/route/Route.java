package route;

import exceptions.Messages;
import exceptions.RouteException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents route as array of checkpoints with their coordinates.
 * Can be created by reading checkpoint coordinate from file.
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 25.10.2016
 */
public class Route {

    private ArrayList<Checkpoint> checkpoints;

    /**
     * Constructor is private to prevent creating empty routes
     * avoiding file reading.
     */
    private Route() {
    }

    public Route(ArrayList<Checkpoint> _checkpoints) {
        checkpoints = _checkpoints;
    }

    /**
     * Reads file and parses it into list of checkpoints.
     * If there is at least 2 checkpoints, new route will be
     * created.
     *
     * @param routeFile file to find checkpoints
     * @return new {@code Route} from checkpoints in file
     * @throws IOException    if problem with route file
     *                        existing or reading occurred
     * @throws RouteException if 0 or 1 checkpoints were
     *                        found in file
     */
    public static Route readFromFile(File routeFile) throws IOException, RouteException {
        return RouteReader.read(routeFile);
    }

    /**
     * Class for getting route from sources like files and etc.
     */
    private static class RouteReader {

        /**
         * Gets route presented in file as lines of pairs of double
         * values (x and y coordinates).
         *
         * @param routeFile file to get coordinates of checkpoints from
         * @return route read from file
         * @throws IOException    if error with file existence or availability
         *                        occurred
         * @throws RouteException if 0 or 1 checkpoints were found, or first
         *                        and last checkpoints are equals (they can't
         *                        be equal by the program's logic)
         */
        public static Route read(File routeFile) throws IOException, RouteException {
            ArrayList<Checkpoint> checkpoints = new ArrayList<Checkpoint>();
            BufferedReader reader = new BufferedReader(new FileReader(routeFile));
            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                try {
                    String[] splitLine = currentLine.split("\\s");
                    if (splitLine.length != 2) {
                        throw new Exception(Messages.INFO + " Can't found checkpoints in line \"" + currentLine + "\", skipped");
                    }
                    checkpoints.add(new Checkpoint(
                            Double.parseDouble(splitLine[0]),
                            Double.parseDouble(splitLine[1])));
                } catch (NumberFormatException e) {
                    System.out.println(Messages.INFO + " " + e.getMessage() + " can't parse to number, skipped");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }

            if (checkpoints.size() == 0) {
                throw new RouteException(Messages.ERROR + " No checkpoints found in file");
            }
            if (checkpoints.size() == 1) {
                throw new RouteException(Messages.ERROR + " Route can't contain only one checkpoint");
            }
            if (checkpoints.get(0).equals(checkpoints.get(checkpoints.size() - 1))) {
                throw new RouteException(Messages.ERROR + " First and last checkpoints of route can't be equals");
            }

            return new Route(checkpoints);
        }
    }

    public ArrayList<Checkpoint> getCheckpoints() {
        return checkpoints;
    }
}
