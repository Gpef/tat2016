package command;

import org.testng.annotations.DataProvider;

import java.util.HashMap;

/**
 * @author Oleg Baslak
 * @version 1.0
 * @since 21.11.2016
 */
public class CommandBuildDataProviders {

    // CheckLinkPresentByHref
    @DataProvider(name = "truePropertiesCheckLinkPresentByHref")
    public static Object[][] getTruePropertiesCheckLinkPresentByHrefData() throws Exception {
        HashMap<String, String> trueProperties1 = new HashMap<>();
        trueProperties1.put("command", "checkLinkPresentByHref");
        trueProperties1.put("href", "http//test.href");

        HashMap<String, String> trueProperties2 = new HashMap<>();
        trueProperties2.put("command", "checkLinkPresentByHref");
        trueProperties2.put("href", "http//test.href");
        trueProperties2.put("title", "property must be ignored");

        return new Object[][]{
                {trueProperties1},
                {trueProperties2}
        };
    }

    @DataProvider(name = "falsePropertiesCheckLinkPresentByHref")
    public static Object[][] getFalsePropertiesCheckLinkPresentByHrefData() throws Exception {
        HashMap<String, String> falseProperties1 = new HashMap<>();
        falseProperties1.put("command", "checkLink");
        falseProperties1.put("href", "http//test.href");

        HashMap<String, String> falseProperties2 = new HashMap<>();
        falseProperties2.put("command", "checkLinkPresentByHref");
        falseProperties2.put("title", "http//test.href");

        HashMap<String, String> falseProperties3 = new HashMap<>();
        falseProperties3.put("command", "checkLinkPresentByHref");

        HashMap<String, String> falseProperties4 = new HashMap<>();
        falseProperties4.put("href", "http//test.href");

        HashMap<String, String> nullProperties1 =new HashMap<>();
        nullProperties1.put("command", null);
        nullProperties1.put("href", "test text");

        HashMap<String, String> nullProperties2 =new HashMap<>();
        nullProperties2.put("command", "checkLinkPresentByHref");
        nullProperties2.put("href", null);

        return new Object[][]{
                {falseProperties1},
                {falseProperties2},
                {falseProperties3},
                {falseProperties4},
                {nullProperties1},
                {nullProperties2},
                {null}
        };
    }


    // CheckLinkPresentByName
    @DataProvider(name = "truePropertiesCheckLinkPresentByName")
    public static Object[][] getTruePropertiesCheckLinkPresentByNameData() throws Exception {
        HashMap<String, String> trueProperties1 = new HashMap<>();
        trueProperties1.put("command", "checkLinkPresentByName");
        trueProperties1.put("link_name", "test link name");

        HashMap<String, String> trueProperties2 = new HashMap<>();
        trueProperties2.put("command", "checkLinkPresentByName");
        trueProperties2.put("link_name", "test link name");
        trueProperties2.put("title", "property must be ignored");

        return new Object[][]{
                {trueProperties1},
                {trueProperties2}
        };
    }

    @DataProvider(name = "falsePropertiesCheckLinkPresentByName")
    public static Object[][] getFalsePropertiesCheckLinkPresentByNameData() throws Exception {
        HashMap<String, String> falseProperties1 = new HashMap<>();
        falseProperties1.put("command", "checkLink");
        falseProperties1.put("link_name", "test link name");

        HashMap<String, String> falseProperties2 = new HashMap<>();
        falseProperties2.put("command", "checkLinkPresentByName");
        falseProperties2.put("title", "test title");

        HashMap<String, String> falseProperties3 = new HashMap<>();
        falseProperties3.put("command", "checkLinkPresentByName");

        HashMap<String, String> falseProperties4 = new HashMap<>();
        falseProperties4.put("link_name", "test link name");

        HashMap<String, String> nullProperties1 =new HashMap<>();
        nullProperties1.put("command", null);
        nullProperties1.put("link_name", "test text");

        HashMap<String, String> nullProperties2 =new HashMap<>();
        nullProperties2.put("command", "checkLinkPresentByName");
        nullProperties2.put("link_name", null);

        return new Object[][]{
                {falseProperties1},
                {falseProperties2},
                {falseProperties3},
                {falseProperties4},
                {nullProperties1},
                {nullProperties2},
                {null}
        };
    }

    // CheckPageContains
    @DataProvider(name = "truePropertiesCheckPageContains")
    public static Object[][] getTruePropertiesCheckPageContainsData() throws Exception {
        HashMap<String, String> trueProperties1 = new HashMap<>();
        trueProperties1.put("command", "checkPageContains");
        trueProperties1.put("text", "test text");

        HashMap<String, String> trueProperties2 = new HashMap<>();
        trueProperties2.put("command", "checkPageContains");
        trueProperties2.put("text", "test text");
        trueProperties2.put("title", "property must be ignored");

        return new Object[][]{
                {trueProperties1},
                {trueProperties2}
        };
    }

    @DataProvider(name = "falsePropertiesCheckPageContains")
    public static Object[][] getFalsePropertiesCheckPageContainsData() throws Exception {
        HashMap<String, String> falseProperties1 = new HashMap<>();
        falseProperties1.put("command", "checkPage");
        falseProperties1.put("text", "test text");

        HashMap<String, String> falseProperties2 = new HashMap<>();
        falseProperties2.put("command", "checkPageContains");
        falseProperties2.put("title", "test title");

        HashMap<String, String> falseProperties3 = new HashMap<>();
        falseProperties3.put("command", "checkPageContains");

        HashMap<String, String> falseProperties4 = new HashMap<>();
        falseProperties4.put("text", "test text");

        HashMap<String, String> nullProperties1 =new HashMap<>();
        nullProperties1.put("command", null);
        nullProperties1.put("text", "test text");

        HashMap<String, String> nullProperties2 =new HashMap<>();
        nullProperties2.put("command", "checkPageContains");
        nullProperties2.put("text", null);

        return new Object[][]{
                {falseProperties1},
                {falseProperties2},
                {falseProperties3},
                {falseProperties4},
                {nullProperties1},
                {nullProperties2},
                {null}
        };
    }

    // CheckPageTitle
    @DataProvider(name = "truePropertiesCheckPageTitle")
    public static Object[][] getTruePropertiesCheckPageTitleData() throws Exception {
        HashMap<String, String> trueProperties1 = new HashMap<>();
        trueProperties1.put("command", "checkPageTitle");
        trueProperties1.put("title", "test title");

        HashMap<String, String> trueProperties2 = new HashMap<>();
        trueProperties2.put("command", "checkPageTitle");
        trueProperties2.put("text", "property must be ignored");
        trueProperties2.put("title", "test title");

        return new Object[][]{
                {trueProperties1},
                {trueProperties2}
        };
    }

    @DataProvider(name = "falsePropertiesCheckPageTitle")
    public static Object[][] getFalsePropertiesCheckPageTitleData() throws Exception {
        HashMap<String, String> falseProperties1 = new HashMap<>();
        falseProperties1.put("command", "checkPage");
        falseProperties1.put("title", "test title");

        HashMap<String, String> falseProperties2 = new HashMap<>();
        falseProperties2.put("command", "checkPageTitle");
        falseProperties2.put("text", "test text");

        HashMap<String, String> falseProperties3 = new HashMap<>();
        falseProperties3.put("command", "checkPageTitle");

        HashMap<String, String> falseProperties4 = new HashMap<>();
        falseProperties4.put("title", "test title");

        HashMap<String, String> nullProperties1 =new HashMap<>();
        nullProperties1.put("command", null);
        nullProperties1.put("title", "test title");

        HashMap<String, String> nullProperties2 =new HashMap<>();
        nullProperties2.put("command", "checkPageTitle");
        nullProperties2.put("title", null);

        return new Object[][]{
                {falseProperties1},
                {falseProperties2},
                {falseProperties3},
                {falseProperties4},
                {nullProperties1},
                {nullProperties2},
                {null}
        };
    }

    // Open
    @DataProvider(name = "truePropertiesOpen")
    public static Object[][] getTruePropertiesOpenData() throws Exception {
        HashMap<String, String> trueProperties1 = new HashMap<>();
        trueProperties1.put("command", "open");
        trueProperties1.put("url", "http://test.url");
        trueProperties1.put("timeout", "3000");

        HashMap<String, String> trueProperties2 = new HashMap<>();
        trueProperties2.put("command", "open");
        trueProperties2.put("url", "http://test.url");
        trueProperties2.put("timeout", "3000");
        trueProperties2.put("title", "property must be ignored");

        return new Object[][]{
                {trueProperties1},
                {trueProperties2}
        };
    }

    @DataProvider(name = "falsePropertiesOpen")
    public static Object[][] getFalsePropertiesOpenData() throws Exception {
        HashMap<String, String> falseProperties1 = new HashMap<>();
        falseProperties1.put("command", "open");
        falseProperties1.put("url", "http://test.url");

        HashMap<String, String> falseProperties2 = new HashMap<>();
        falseProperties2.put("command", "open");
        falseProperties2.put("timeout", "3000");

        HashMap<String, String> falseProperties3 = new HashMap<>();
        falseProperties3.put("command", "open");

        HashMap<String, String> falseProperties4 = new HashMap<>();
        falseProperties4.put("title", "test title");

        HashMap<String, String> falseProperties5 = new HashMap<>();
        falseProperties5.put("command", "opn");
        falseProperties5.put("url", "http://test.url");
        falseProperties5.put("timeout", "3000");

        HashMap<String, String> falseProperties6 = new HashMap<>();
        falseProperties6.put("command", "opn");
        falseProperties6.put("url", "http://test.url");
        falseProperties6.put("timeout", "timeout");

        HashMap<String, String> nullProperties1 =new HashMap<>();
        nullProperties1.put("command", null);
        nullProperties1.put("url", "http://test.url");
        nullProperties1.put("timeout", "timeout");

        HashMap<String, String> nullProperties2 =new HashMap<>();
        nullProperties2.put("command", "open");
        nullProperties2.put("url", null);
        nullProperties2.put("timeout", "timeout");

        HashMap<String, String> nullProperties3 =new HashMap<>();
        nullProperties3.put("command", "open");
        nullProperties3.put("url", "http://test.url");
        nullProperties3.put("timeout", null);

        return new Object[][]{
                {falseProperties1},
                {falseProperties2},
                {falseProperties3},
                {falseProperties4},
                {falseProperties5},
                {falseProperties6},
                {nullProperties1},
                {nullProperties2},
                {nullProperties3},
                {null}
        };
    }
}
