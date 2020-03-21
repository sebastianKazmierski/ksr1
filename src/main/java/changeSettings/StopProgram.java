package changeSettings;

public class StopProgram implements ChangeSettings {
    @Override
    public ChangeSettingsType getChange() {
        return ChangeSettingsType.STOP_PROGRAM;
    }

    @Override
    public String description() {
        return "Zakoń program";
    }
}
