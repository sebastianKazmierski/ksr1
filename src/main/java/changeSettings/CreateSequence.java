package changeSettings;

public class CreateSequence implements ChangeSettings {
    @Override
    public ChangeSettingsType getChange() {
        return ChangeSettingsType.CREATE_SEQUENCE;
    }

    @Override
    public String description() {
        return "Stwórz sekwencję";
    }
}
