package gamemakerstudio_.entities.experimental;

import gamemakerstudio_.misc.entitystuff.ID;
import gamemakerstudio_.misc.audiostuff.jzoom.streamplayer.Status;
import gamemakerstudio_.misc.audiostuff.jzoom.streamplayer.StreamPlayer;
import gamemakerstudio_.misc.audiostuff.jzoom.streamplayer.StreamPlayerEvent;
import gamemakerstudio_.misc.audiostuff.jzoom.streamplayer.StreamPlayerListener;
import gamemakerstudio_.misc.audiostuff.jzoom.visualizer.Oscilloscope;
import gamemakerstudio_.misc.entitystuff.gameobject_;

import java.awt.*;
import java.util.Map;

public class osc_ extends gameobject_ implements StreamPlayerListener {
    // FIXED: only get the info about how rendering the analyzer work, strip the audio player
    Oscilloscope oscilloscope = new Oscilloscope();
    // new audio engine
//    StreamPlayer streamPlayer = audioplayer_.streamPlayer;
    StreamPlayer streamPlayer;

    // test data line here

    public osc_(float x, float y, ID id) {
        super(x, y, id);
        streamPlayer.addStreamPlayerListener(this);
        /*audioplayer_.MusicTest.playSong(new File("resources_\\music_\\fireflies remix.ogg"));*/
    }

    // capture pcmData or something similar
    byte[] pcmDataTest;

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        /*System.out.println("\nleft (" + oscilloscope.leftTest.length + "): " + Arrays.toString(oscilloscope.leftTest));
        System.out.println("right (" + oscilloscope.rightTest.length + "): " + Arrays.toString(oscilloscope.rightTest));
        System.out.println("pcmData (" + pcmDataTest.length + "): " + Arrays.toString(pcmDataTest));*/
        oscilloscope.drawOscilloscope(g);
    }

    @Override
    public Rectangle getBounds() {
        return null;
    }

    @Override
    public void opened(Object dataSource, Map<String, Object> properties) {

    }

    public static String audioTime = "";
    public static float audioTimeInFloat = 0.0f;
    public static String audioProgress = "";

    // delay fix vars
    double storeDelayTime;
    boolean startDelayTime = true;

    @Override
    public void progress(int nEncodedBytes, long microsecondPosition, byte[] pcmData, Map<String, Object> properties) {

        // write the data to the visualizer
        oscilloscope.writeDSP(pcmData);

        pcmDataTest = pcmData; // for testing purposes

        long totalBytes = streamPlayer.getTotalBytes();

        // Calculate the progress until now
        double progress = (nEncodedBytes > 0 && totalBytes > 0) ? (nEncodedBytes * 1.0f / totalBytes * 1.0f)
                : -1.0f;

        // info stuff
        audioTime = tellTimeMod((microsecondPosition));
        audioTimeInFloat = microsecondPosition / 1000000f;
        audioProgress = (int)(progress * 100) + " %";

        // System.out.println(progress*100+"%");

        /*System.out.println("Seconds  : " + (int) (microsecondPosition / 1000000) + " s " + "Progress: [ "
                + progress * 100 + " ] %");*/

        // delay fix here pls i beg you fix this shit
        // YOW I ALMOST DID IT!
        // TODO: YOW LATENCY ISSUES POPS OUT AGAIN!, IT AFFECTS PAUSING AND STARTING
        // levels_.isPlaying = true;
    }
    @Override
    public void statusUpdated(StreamPlayerEvent event) {
//        System.out.println("Player Status is:" + streamPlayer.getStatus());
        // player is opened
        if (event.getPlayerStatus() == Status.OPENED && streamPlayer.getSourceDataLine() != null) {
            oscilloscope.setupDSP(streamPlayer.getSourceDataLine());
            oscilloscope.startDSP(streamPlayer.getSourceDataLine());
        }
        // player is stopped
        else if (event.getPlayerStatus() == Status.STOPPED) {
            oscilloscope.stopDSP();
        }
    }

    // the worst implementation of tellTime
    public String tellTimeMod(long var){
        long centi = (var % 1000000) / 10000, sec = (var / 1000000) % 60, min = (var / 1000000) / 60;
        String tempCenti, tempSec, tempMin;

        if (sec < 10) tempSec = "0" + sec;
        else tempSec = String.valueOf(sec);

        if (min < 10) tempMin = "0" + min;
        else tempMin = String.valueOf(min);

        if (centi < 10) tempCenti = "0" + centi;
        else tempCenti = String.valueOf(centi);

        return tempMin + ":" + tempSec + ":" + tempCenti;
    }

}
