package testcases;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestProperties {
    public static void main(String[] args) throws IOException {
        Properties android = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/properties/Conf.properties");
        android.load(fis);

        //capabilities.setCapability("appActivity", android.getProperty("device.name"));

        System.out.println(android.getProperty("device.name"));

    }
}
