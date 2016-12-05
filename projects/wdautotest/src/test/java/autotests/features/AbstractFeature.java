package autotests.features;

import autotests.utils.config.Config;

/**
 * @author Oleg Baslak
 * @version 1.0
 * @since 03.12.2016
 */
public abstract class AbstractFeature {
    static {
        Config.getConfig();
    }
}
