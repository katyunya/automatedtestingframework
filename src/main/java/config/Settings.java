package config;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.kohsuke.args4j.Option;

public class Settings {
    String testNGPath;
    String url;

    @Option(name = "--testNG", usage = "set path to testNG xml", required = true)
    public void setTestNGPath(String testNGPath) {
        this.testNGPath = testNGPath;
    }

    public String getTestNGPath() {
        return testNGPath;
    }

    @Option(name = "--url", usage = "set path to mailbox", required = true)
    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SIMPLE_STYLE);
    }
}
