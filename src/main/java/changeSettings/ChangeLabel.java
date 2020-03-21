package changeSettings;

public class ChangeLabel implements ChangeSettings {
    @Override
    public ChangeSettingsType getChange() {
        return ChangeSettingsType.LABEL_SETTINGS;
    }

    @Override
    public String description() {
        return "Zmień etykietę";
    }
}
