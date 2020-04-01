package changeSettings;

public class ChangeAllSettings implements ChangeSettings {
    @Override
    public String description() {
        return "Zmień wszystkie ustawienia";
    }

    @Override
    public ChangeSettingsType getChange() {
        return ChangeSettingsType.ALL_SETTINGS;
    }
}
