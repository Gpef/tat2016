package homework.task04.exceptions;

/**
 * Exception thrown when discriminant lower then zero
 * (equation has only complex roots)
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 03-10-2016
 */
public class ComplexRootsOnlyException extends Exception{
    public ComplexRootsOnlyException(){
        super("This equation has only complex roots");
    }

}
