package ui.webdriver;

import exceptions.UnknownDriverTypeException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class Driver {
    private static final String DEFAULT_WEB_DRIVER = "DEFAULT_WEB_DRIVER";
    private static DriverTypes defaultDriverType = DriverTypes.CHROME;

    private static HashMap<String, RemoteWebDriver> instances;

    static {
        instances = new HashMap<String, RemoteWebDriver>();
    }

    public static RemoteWebDriver getWebDriverInstance(String name, DriverTypes driverType) throws MalformedURLException {
        RemoteWebDriver remoteWebDriver;
        DesiredCapabilities capabilities;
        if (!instances.containsKey(name)) {
            switch (driverType) {
                case CHROME:
                    capabilities = DesiredCapabilities.chrome();
                    remoteWebDriver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
                    break;
                case FIREFOX:
                    capabilities = DesiredCapabilities.firefox();
                    remoteWebDriver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
                    break;
                default:
                    throw new UnknownDriverTypeException("UnknownDriverTypeException: " + driverType.getDriverName());
            }
            remoteWebDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            instances.put(name, remoteWebDriver);
        } else {
            remoteWebDriver = instances.get(name);
        }
        return remoteWebDriver;
    }

    public static RemoteWebDriver getWebDriverInstance(String name) throws MalformedURLException {
        return getWebDriverInstance(name, defaultDriverType);
    }

    public static RemoteWebDriver getWebDriverInstance() throws MalformedURLException {
        return getWebDriverInstance(DEFAULT_WEB_DRIVER, defaultDriverType);
    }
}
