package creitive.com.workouttimer.model;

/**
 * Created by Creitive 31 on 13-Sep-17.
 */

public class Settings {

    private boolean soundOn;
    private boolean vibrationOn;
    private boolean screenRotationOn;
    private boolean lockModeOn;

    public Settings(boolean soundOn, boolean vibrationOn, boolean screenRotationOn, boolean lockModeOn) {
        this.soundOn = soundOn;
        this.vibrationOn = vibrationOn;
        this.screenRotationOn = screenRotationOn;
        this.lockModeOn = lockModeOn;
    }

    public boolean isSoundOn() {
        return soundOn;
    }

    public void setSoundOn(boolean soundOn) {
        this.soundOn = soundOn;
    }

    public boolean isVibrationOn() {
        return vibrationOn;
    }

    public void setVibrationOn(boolean vibrationOn) {
        this.vibrationOn = vibrationOn;
    }

    public boolean isScreenRotationOn() {
        return screenRotationOn;
    }

    public void setScreenRotationOn(boolean screenRotationOn) {
        this.screenRotationOn = screenRotationOn;
    }

    public boolean isLockModeOn() {
        return lockModeOn;
    }

    public void setLockModeOn(boolean lockModeOn) {
        this.lockModeOn = lockModeOn;
    }
}
