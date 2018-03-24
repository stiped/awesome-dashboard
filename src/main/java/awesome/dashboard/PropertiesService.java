package awesome.dashboard;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class PropertiesService {

    private static Properties properties;


    public static void init() {
        String propertyPath = System.getProperty("dashboard.properties");
        if (propertyPath == null) {
            System.out.println("Mangler oppstartsparameter: -Ddashboard.properties=/path/to/dashboard.properties");
            System.exit(1);
        }

        try {
            properties = new Properties();
            properties.load(new InputStreamReader(new FileInputStream(propertyPath), "UTF-8"));
        } catch (Exception e) {
            System.out.println("Sorry, klarte ikke laste dashboard.properties .. :(");
            System.exit(1);
        }
    }

    public static String get(String property) {
        if (properties == null) {
            throw new IllegalStateException("PropertiesService er ikke instansiert");
        }
        return properties.getProperty(property);
    }
}
