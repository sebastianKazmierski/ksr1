package changeSettings;

import interfaceModule.ElementSelectedByUser;

public interface ChangeSettings extends ElementSelectedByUser {
    @Override
    default String wordToInsertIntoQuestionAboutThisObject() {
        return "które z obecnych ustawień chcesz zmienić";
    }

    ChangeSettingsType getChange();
}
