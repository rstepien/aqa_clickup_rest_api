package pl.aqaclickuprs.properties;

import java.util.ResourceBundle;

import static java.lang.System.getProperty;

public class ClickupProperties {

    private static final String TOKEN = "clickup.token";
    private static final String TEAM_ID = "clickup.team.id";

    public static String getToken() {
        if (getProperty(TOKEN).isEmpty() || getProperty(TOKEN).startsWith("your")) {
            return System.getProperty("TOKEN");
        } else {
            return getProperty(TOKEN);
        }
    }

    public static String getTeamId() {
        if (getProperty(TEAM_ID).isEmpty() || getProperty(TEAM_ID).startsWith("your")) {
            return System.getProperty("TEAM_ID");
        } else {
            return getProperty(TEAM_ID);
        }
    }

    private static String getProperty(String key) {
        return ResourceBundle.getBundle("clickup").getString(key);
    }
}