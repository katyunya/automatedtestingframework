package ui.webdriver;

public enum DriverTypes {
    CHROME("chrome"), FIREFOX("firefox");

    private String driverName;

    private DriverTypes(String driverName){
        this.driverName = driverName;
    }

    public String getDriverName() {
        return driverName;
    }
}
