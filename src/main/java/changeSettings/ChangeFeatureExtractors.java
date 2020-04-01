package changeSettings;

public class ChangeFeatureExtractors implements ChangeSettings {
    @Override
    public String description() {
        return "Zmień cechy";
    }

    @Override
    public ChangeSettingsType getChange() {
        return ChangeSettingsType.FEATURE_EXTRACTORS_SETTINGS;
    }
}
