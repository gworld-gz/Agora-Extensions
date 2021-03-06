package io.agora.demo.streaming.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;

import io.agora.rtc.Constants;
import io.agora.rtc.video.VideoEncoderConfiguration;
import io.agora.streaming.AudioStreamConfiguration;
import io.agora.streaming.StreamingKit;
import io.agora.streaming.VideoStreamConfiguration;


public class PrefManager {
    public static final boolean IS_DEV_DEBUG = true;
    public static final boolean IS_SIMUL_TEST = false;
    private static final String DEFAULT_RTMP_URL = "rtmp://ks-push.broadcastapp.agoraio.cn/live/yutest";
    private static final String DEFAULT_VIDEO_LOCAL_STORE_PATH = "";

    public static final String PREF_FILE_NAME = "io.agora.demo.streaming";

    public static final String PREF_RTMP_URL = "pref_rtmp_url";
    public static final String PREF_VIDEO_DIMENSIONS_INDEX = "pref_video_dimensions_index";
    public static final String PREF_VIDEO_FRAMERATE_INDEX = "pref_video_framerate_index";
    public static final String PREF_VIDEO_BITRATE_INDEX = "pref_video_bitrate_index";
    public static final String PREF_VIDEO_ORIENTATION_MODE_INDEX = "pref_video_orientation_mode_index";
    public static final String PREF_SCREEN_ORIENTATION_INDEX = "pref_screen_orientation_index";
    public static final String PREF_AUDIO_SAMPLE_RATE_INDEX = "pref_audio_sample_rate_index";
    public static final String PREF_AUDIO_TYPE_INDEX = "pref_audio_type_index";
    public static final String PREF_AUDIO_BITRATE_INDEX = "pref_audio_bitrate_index";
    public static final String PREF_LOG_PATH = "pref_log_path";
    public static final String PREF_LOG_FILTER_INDEX = "pref_log_filter_index";
    public static final String PREF_LOG_FILE_SIZE = "pref_log_file_size";
    public static final String PREF_STREAM_TYPE_INDEX = "pref_stream_type_index";
    public static final String PREF_ENABLE_STATS = "pref_enable_stats";
    public static final String PREF_MIRROR_LOCAL = "pref_mirror_local";
    public static final String PREF_MIRROR_REMOTE = "pref_mirror_remote";
    public static final String PREF_SCREEN_RATION = "pref_screen_ration";
    public static final String PREF_VIDEO_LOCAL_STORE = "pref_video_local_store";
    public static final String PREF_VIDEO_RATIO_INDEX = "pref_video_ratio_index";
    public static final String PREF_VIDEO_DEFINITION_INDEX = "pref_video_definition_index";

    /************************************** video settings **************************************/
    // video dimensions
    public static final VideoEncoderConfiguration.VideoDimensions[] VIDEO_DIMENSIONS =
        new VideoEncoderConfiguration.VideoDimensions[]{
            VideoEncoderConfiguration.VD_320x240,
            VideoEncoderConfiguration.VD_480x360,
            VideoEncoderConfiguration.VD_640x360,
            VideoEncoderConfiguration.VD_640x480,
            new VideoEncoderConfiguration.VideoDimensions(960, 540),
            VideoEncoderConfiguration.VD_1280x720
    };
    private static final int DEFAULT_VIDEO_DIMENSIONS_INDEX = 2;

    // video frame rates
    public static final VideoEncoderConfiguration.FRAME_RATE[] VIDEO_FRAMERATES =
        new VideoEncoderConfiguration.FRAME_RATE[] {
        VideoEncoderConfiguration.FRAME_RATE.FRAME_RATE_FPS_7,
        VideoEncoderConfiguration.FRAME_RATE.FRAME_RATE_FPS_15,
        VideoEncoderConfiguration.FRAME_RATE.FRAME_RATE_FPS_24,
        VideoEncoderConfiguration.FRAME_RATE.FRAME_RATE_FPS_30,
    };
    private static final int DEFAULT_VIDEO_FRAMERATE_INDEX = 1;

    // video bitrates
    public static final int[] VIDEO_BITRATES = new int[] {
        VideoEncoderConfiguration.STANDARD_BITRATE, 400, 640, 800, 1000, 2260, 3420,
    };
    private static final int DEFAULT_VIDEO_BITRATE_INDEX = 0;

    // video orientation modes
    public static final VideoStreamConfiguration.ORIENTATION_MODE[] VIDEO_ORIENTATION_MODES =
        new VideoStreamConfiguration.ORIENTATION_MODE[] {
            VideoStreamConfiguration.ORIENTATION_MODE.ORIENTATION_MODE_FIXED_PORTRAIT,
            VideoStreamConfiguration.ORIENTATION_MODE.ORIENTATION_MODE_FIXED_LANDSCAPE,
        };
    public static final String[] VIDEO_ORIENTATION_MODE_STRINGS = new String[] {
        "Portrait", "Landscape"
    };
    private static final int DEFAULT_VIDEO_ORIENTATION_MODE_INDEX = 0;

    // screen orientation modes
    public static final int[] SCREEN_ORIENTATIONS = new int[] {
        Configuration.ORIENTATION_PORTRAIT,
        Configuration.ORIENTATION_LANDSCAPE,
        Configuration.ORIENTATION_UNDEFINED,
    };
    public static final String[] SCREEN_ORIENTATION_STRINGS = new String[] {
        "Portrait", "Landscape", "Dynamic"
    };
    private static final int DEFAULT_SCREEN_ORIENTATION_INDEX = 0;

    // video mirror modes
    public static final int[] VIDEO_MIRROR_MODES = new int[] {
        Constants.VIDEO_MIRROR_MODE_AUTO,
        Constants.VIDEO_MIRROR_MODE_ENABLED,
        Constants.VIDEO_MIRROR_MODE_DISABLED,
    };
    public static final String[] VIDEO_MIRROR_MODE_STRINGS = new String[] {
        "Auto", "Enabled", "Disabled"
    };
    private static final int DEFAULT_LOCAL_VIDEO_MIRROR_INDEX = 0;
    private static final int DEFAULT_REMOTE_VIDEO_MIRROR_INDEX = 0;

    //screen ration
    public static String[] SCREEN_RATION_MODE_STRINGS = new String[] {
        "1:1", "16:9", "4:3"
    };
    public static final int DEFAULT_SCREEN_RATION_INDEX = 1;

    //screen ration
    public static String[] VIDEO_RATIO_MODE_STRINGS = new String[] {
            "1:1", "16:9", "4:3", "Custom"
    };
    public static final int DEFAULT_VIDEO_RATIO_INDEX = 1;

    //screen definition
    public static String[] VIDEO_DEFINITION_MODE_STRINGS = new String[] {
        "Low", "Middle", "High", "Custom"
    };
    public static final int DEFAULT_VIDEO_DEFINITION_INDEX = 1;

    /************************************** audio settings **************************************/
    // audio sample rates
    @AudioStreamConfiguration.SoundRate
    public static final int[] AUDIO_SAMPLE_RATES = new int[] {
        AudioStreamConfiguration.SoundRate.SAMPLE_RATE_11000,
        AudioStreamConfiguration.SoundRate.SAMPLE_RATE_22000,
        AudioStreamConfiguration.SoundRate.SAMPLE_RATE_44100,
    };
    public static final String[] AUDIO_SAMPLE_RATE_STRINGS = new String[] {
        "11KHz", "22KHz", "44KHz"
    };
    private static final int DEFAULT_AUDIO_SAMPLE_RATE_INDEX = 2;

    // audio types
    @AudioStreamConfiguration.SoundType
    public static final int[] AUDIO_TYPES = new int[] {
        AudioStreamConfiguration.SoundType.TYPE_MONO,
        AudioStreamConfiguration.SoundType.TYPE_STEREO,
    };
    public static final String[] AUDIO_TYPE_STRINGS = new String[] {
        "Mono", "Stereo"
    };
    private static final int DEFAULT_AUDIO_TYPE_INDEX = 0;

    // audio bitrates
    public static final int[] AUDIO_BITRATES = new int[] {
        0, 12, 18, 36, 48, 56, 92, 112, 192
    };
    private static final int DEFAULT_AUDIO_BITRATE_INDEX = 4;

    /************************************** log settings **************************************/
    private static final int DEFAULT_LOG_FILE_SIZE = 512; // KB

    // log filter
    public static final int[] LOG_FILTERS = new int[] {
        StreamingKit.LogFilter.LOG_FILTER_OFF,
        StreamingKit.LogFilter.LOG_FILTER_DEBUG,
        StreamingKit.LogFilter.LOG_FILTER_INFO,
        StreamingKit.LogFilter.LOG_FILTER_WARN,
        StreamingKit.LogFilter.LOG_FILTER_ERROR,
        StreamingKit.LogFilter.LOG_FILTER_CRITICAL,
    };
    public static final String[] LOG_FILTER_STRINGS = new String[] {
        "OFF", "DEBUG", "INFO", "WARN", "ERROR", "CRITICAL"
    };
    private static final int DEFAULT_LOG_FILTER_INDEX = 1;

    /************************************** other settings **************************************/
    // stream type
    public @interface StreamType {
        int TYPE_AUDIO_AND_VIDEO = 0;
        int TYPE_AUDIO_ONLY = 1;
        int TYPE_VIDEO_ONLY = 2;
    }
    public static final @StreamType int[] STREAM_TYPES = new int[] {
        StreamType.TYPE_AUDIO_AND_VIDEO,
        StreamType.TYPE_AUDIO_ONLY,
        StreamType.TYPE_VIDEO_ONLY,
    };
    public static final String[] STREAM_TYPES_STRINGS = new String[] {
        "Audio and Video", "Audio Only", "Video Only"
    };
    private static final int DEFAULT_STREAM_TYPE_INDEX = 0;

    private static SharedPreferences mPref;

    public static synchronized SharedPreferences getPreferences(Context context) {
        if (mPref == null) {
            mPref = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        }
        return mPref;
    }

    public static String getRtmpUrl(Context context) {
        return getPreferences(context).getString(PREF_RTMP_URL, DEFAULT_RTMP_URL);
    }

    public static int getVideoDimensionsIndex(Context context) {
        return getPreferences(context).getInt(PREF_VIDEO_DIMENSIONS_INDEX, DEFAULT_VIDEO_DIMENSIONS_INDEX);
    }

    public static VideoEncoderConfiguration.VideoDimensions getVideoDimensions(Context context) {
     return VIDEO_DIMENSIONS[getVideoDimensionsIndex(context)];
    }

    public static int getVideoFramerateIndex(Context context) {
        return getPreferences(context).getInt(PREF_VIDEO_FRAMERATE_INDEX, DEFAULT_VIDEO_FRAMERATE_INDEX);
    }

    public static int getVideoBitrateIndex(Context context) {
        return getPreferences(context).getInt(PREF_VIDEO_BITRATE_INDEX, DEFAULT_VIDEO_BITRATE_INDEX);
    }

    public static int getVideoOrientationModeIndex(Context context) {
        return getPreferences(context).getInt(PREF_VIDEO_ORIENTATION_MODE_INDEX, DEFAULT_VIDEO_ORIENTATION_MODE_INDEX);
    }

    public static int getScreenOrientationIndex(Context context) {
        return getPreferences(context).getInt(PREF_SCREEN_ORIENTATION_INDEX, DEFAULT_SCREEN_ORIENTATION_INDEX);
    }

    public static int getMirrorLocalIndex(Context context) {
        return getPreferences(context).getInt(PREF_MIRROR_LOCAL, DEFAULT_LOCAL_VIDEO_MIRROR_INDEX);
    }

    public static int getScreenRationIndex(Context context) {
        return getPreferences(context).getInt(PREF_SCREEN_RATION, DEFAULT_SCREEN_RATION_INDEX);
    }

    public static int getVideoRatioIndex(Context context) {
        return getPreferences(context).getInt(PREF_VIDEO_RATIO_INDEX, DEFAULT_VIDEO_RATIO_INDEX);
    }

    public static int getVideoDefinitionIndex(Context context) {
        return getPreferences(context).getInt(PREF_VIDEO_DEFINITION_INDEX, DEFAULT_VIDEO_DEFINITION_INDEX);
    }
    public static void setVideoRatioIndex(Context context, int index) {
        getPreferences(context).edit().putInt(PREF_VIDEO_RATIO_INDEX, index).apply();
    }

    public static void setVideoDefinitionIndex(Context context, int index) {
        getPreferences(context).edit().putInt(PREF_VIDEO_DEFINITION_INDEX, index).apply();
    }


    public static int getMirrorLocalMode(Context context) {
        return VIDEO_MIRROR_MODES[getMirrorLocalIndex(context)];
    }

    public static int getMirrorRemoteIndex(Context context) {
        return getPreferences(context).getInt(PREF_MIRROR_REMOTE, DEFAULT_REMOTE_VIDEO_MIRROR_INDEX);
    }

    public static int getMirrorRemoteMode(Context context) {
        return VIDEO_MIRROR_MODES[getMirrorRemoteIndex(context)];
    }

    public static int getAudioSampleRateIndex(Context context) {
        return getPreferences(context).getInt(PREF_AUDIO_SAMPLE_RATE_INDEX, DEFAULT_AUDIO_SAMPLE_RATE_INDEX);
    }

    public static int getAudioSampleRate(Context context) {
        return AUDIO_SAMPLE_RATES[getAudioSampleRateIndex(context)];
    }

    public static int getAudioTypeIndex(Context context) {
        return getPreferences(context).getInt(PREF_AUDIO_TYPE_INDEX, DEFAULT_AUDIO_TYPE_INDEX);
    }

    public static int getAudioType(Context context) {
        return AUDIO_TYPES[getAudioTypeIndex(context)];
    }

    public static int getAudioBitrateIndex(Context context) {
        return getPreferences(context).getInt(PREF_AUDIO_BITRATE_INDEX, DEFAULT_AUDIO_BITRATE_INDEX);
    }

    public static String getDefaultLogPath(Context context) {
        return FileUtil.getLogFilePath(context, "streaming-kit.log");
    }

    public static String getDefaultVideoPath(Context context){
        return FileUtil.geMediaFilePath(context, "streaming-kit.mp4");
    }

    public static String getVideoPath(Context context){
        return getPreferences(context).getString(PREF_VIDEO_LOCAL_STORE, getDefaultVideoPath(context));
    }

    public static String getLogPath(Context context) {
        return getPreferences(context).getString(PREF_LOG_PATH, getDefaultLogPath(context));
    }

    public static int getLogFilterIndex(Context context) {
        return getPreferences(context).getInt(PREF_LOG_FILTER_INDEX, DEFAULT_LOG_FILTER_INDEX);
    }

    public static int getLogFileSize(Context context) {
        return getPreferences(context).getInt(PREF_LOG_FILE_SIZE, DEFAULT_LOG_FILE_SIZE);
    }

    public static int getStreamTypeIndex(Context context) {
        return getPreferences(context).getInt(PREF_STREAM_TYPE_INDEX, DEFAULT_STREAM_TYPE_INDEX);
    }

    public static boolean isStatsEnabled(Context context) {
        return getPreferences(context).getBoolean(PREF_ENABLE_STATS, false);
    }
    /*
     * 360 360
     * 560 560
     * 720 720
     *
     * 16:9
     * 1280 720
     * 960 540
     * 640 320
     *
     * 4:3
     * 960 720
     * 640 480
     * 320 240
     */
    public static int getResolutionWidth(Context context) {
        int ratio = PrefManager.getVideoRatioIndex(context);
        int definition = PrefManager.getVideoDefinitionIndex(context);
        if(ratio == 0){//1:1
            if(definition == 0){
                return 360;//loq
            }else if(definition == 1){
                return 560;
            }else if(definition == 2){
                return 720;
            }else{
                return 720;
            }
        }else if(ratio == 1){//16:9
            if(definition == 0){
                return 640;
            }else if(definition == 1){
                return 960;
            }else if(definition == 2){
                return 1280;
            }else{
                return 1280;
            }
        }else if(ratio == 2){//4:3
            if(definition == 0){
                return 320;
            }else if(definition == 1){
                return 640;
            }else if(definition == 2){
                return 960;
            }else{
                return 960;
            }
        }
        return 0;
    }

    public static int getResolutionHeight(Context context) {
        int ratio = PrefManager.getVideoRatioIndex(context);
        int definition = PrefManager.getVideoDefinitionIndex(context);
        if(ratio == 0){//1:1
            if(definition == 0){
                return 360;//loq
            }else if(definition == 1){
                return 560;
            }else if(definition == 2){
                return 720;
            }else{
                return 720;
            }
        }else if(ratio == 1){//16:9
            if(definition == 0){
                return 320;
            }else if(definition == 1){
                return 540;
            }else if(definition == 2){
                return 720;
            }else{
                return 720;
            }
        }else if(ratio == 2){//4:3
            if(definition == 0){
                return 240;
            }else if(definition == 1){
                return 480;
            }else if(definition == 2){
                return 720;
            }else{
                return 720;
            }
        }
        return 0;
    }

}
