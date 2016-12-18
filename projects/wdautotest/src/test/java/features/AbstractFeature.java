package features;

import utils.config.DatabaseConfig;
import utils.config.WordpressConfig;

/**
 * @author Oleg Baslak
 * @version 1.0
 * @since 03.12.2016
 */
public abstract class AbstractFeature {
    protected DatabaseConfig dbConfig = DatabaseConfig.getConfig();
    protected WordpressConfig wpConfig = WordpressConfig.getConfig();
}
