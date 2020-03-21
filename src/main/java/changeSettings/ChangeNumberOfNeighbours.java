package changeSettings;

public class ChangeNumberOfNeighbours implements ChangeSettings {
    @Override
    public String description() {
        return "Zmień liczbę sąsiadów";
    }

    @Override
    public ChangeSettingsType getChange() {
        return ChangeSettingsType.NUMBER_OF_NEIGHBOURS_SETTINGS;
    }
}
