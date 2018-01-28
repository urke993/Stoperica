package creitive.com.workouttimer.util;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import java.util.HashMap;

public class SoundManager
{
    private AudioManager mAudioManager;
    private Context mContext;
    private SoundPool mSoundPool;
    private HashMap<Integer, Integer> mSoundPoolMap;

    public void addSound(int paramInt1, int paramInt2)
    {
        this.mSoundPoolMap.put(Integer.valueOf(paramInt1), Integer.valueOf(this.mSoundPool.load(this.mContext, paramInt2, 1)));
    }

    public void initSounds(Context paramContext)
    {
        this.mContext = paramContext;
        this.mSoundPool = new SoundPool(4, 3, 0);
        this.mSoundPoolMap = new HashMap();
        //this.mAudioManager = ((AudioManager)this.mContext.getSystemService("audio"));
    }

    public void playLoopedSound(int paramInt)
    {
        float f = this.mAudioManager.getStreamVolume(3);
        this.mSoundPool.play(((Integer)this.mSoundPoolMap.get(Integer.valueOf(paramInt))).intValue(), f, f, 1, 0, 1.0F);
    }

    public void playSound(int paramInt)
    {
        float f = this.mAudioManager.getStreamVolume(3);
        this.mSoundPool.play(((Integer)this.mSoundPoolMap.get(Integer.valueOf(paramInt))).intValue(), f, f, 1, 0, 1.0F);
    }
}
