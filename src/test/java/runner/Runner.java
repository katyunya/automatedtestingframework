package runner;

import runner.config.Settings;
import exceptions.TestNGRunException;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.testng.ITestNGListener;
import org.testng.TestNG;
import org.testng.xml.Parser;
import org.testng.xml.XmlSuite;
import org.uncommons.reportng.HTMLReporter;
import org.xml.sax.SAXException;
import util.ScreenshotListener;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Runner {
    protected TestNG testNG = new TestNG();
    private String testNGConfig;
    private static String url;

    public static void main(String[] args) {
        new Runner(args).run();
    }

    public Runner(String[] args) {
        Settings settings = new Settings();
        CmdLineParser parser = new CmdLineParser(settings);
        try {
            parser.parseArgument(args);
            testNGConfig = settings.getTestNGPath();
            url = settings.getUrl();
        } catch (CmdLineException e) {
            e.printStackTrace();
            parser.printUsage(System.out);
        }
    }

    public static String getUrl() {
        return url;
    }

    public void run() {
        try {
            XmlSuite xmlSuite = new Parser(testNGConfig).parseToList().get(0);
            this.testNG.setCommandLineSuite(xmlSuite);
            List<Class<? extends ITestNGListener>> listeners = new ArrayList<>();
            listeners.add(HTMLReporter.class);
            listeners.add(ScreenshotListener.class);
            this.testNG.setListenerClasses(listeners);
            this.testNG.run();
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new TestNGRunException("Error running TestNG suite: " + e.getMessage());
        }
    }
}
