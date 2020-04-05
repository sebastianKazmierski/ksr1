package changeSettings;

public class ChangeSplitData implements ChangeSettings {
    @Override
    public ChangeSettingsType getChange() {
        return ChangeSettingsType.DATA_SPLIT;
    }

    @Override
    public String description() {
        return "Zmień podział na zbiór uczący i testowy";
    }
}
