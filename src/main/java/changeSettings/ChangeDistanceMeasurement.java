package changeSettings;

public class ChangeDistanceMeasurement implements ChangeSettings {
    @Override
    public String description() {
        return "Zmień metrykę";
    }

    @Override
    public ChangeSettingsType getChange() {
        return ChangeSettingsType.DISTANCE_MEASUREMENT_SETTINGS;
    }
}
