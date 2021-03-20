package gamemakerstudio_.misc.audiostuff.xtaudio;

import com.sun.jna.Pointer;
import gamemakerstudio_.game_;
import xt.audio.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.EnumSet;

import static gamemakerstudio_.entities.experimental.betterosc_.*;

public class XtAudio {
    // thread wait milli
    public static int milli = 1000000000;
    public static boolean run = false;

    // intermediate buffer
    public static byte[] BUFFER = new byte[2048];
    // dump to file (never do this, see below)
    static FileOutputStream fos;

    // audio streaming callback
    public static int onBuffer(XtStream stream, Structs.XtBuffer buffer, Object user) {
        XtSafeBuffer safe = XtSafeBuffer.get(stream);
        // lock buffer from native into java
        safe.lock(buffer);
        // short[] because we specified INT16 below
        // this is the captured audio data
        short[] audio = (short[])safe.getInput();
        // you want a spectrum analyzer, i dump to a file
        // but actually never dump to a file in any serious app
        // see http://www.rossbencina.com/code/real-time-audio-programming-101-time-waits-for-nothing
        processAudio(audio, buffer.frames);
        // unlock buffer from java into native
        safe.unlock(buffer);
        return 0;
    }

    static void processAudio(short[] audio, int frames) {
        // convert from short[] to byte[]
        for(int frame = 0; frame < frames; frame++) {
            // for 2 channels
            for(int channel = 0; channel < 2; channel++) {
                // 2 = channels again
                int sampleIndex = frame * 2 + channel;
                // 2 = 2 bytes for each short
                int byteIndex0 = sampleIndex * 2;
                int byteIndex1 = sampleIndex * 2 + 1;
                // probably some library method for this, somewhere
                // TODO: implement audio consumer here, see KJDSPAudioDataConsumer.java line 406
                BUFFER[byteIndex0] = (byte)((audio[sampleIndex] & 0x000000FF) >> 8);
                BUFFER[byteIndex1] = (byte)((audio[sampleIndex] & 0x0000FF00) >> 8);

                /*pLeftChannel[byteIndex0] = (float) (((int) BUFFER[sampleIndex + 1] << 8) + (BUFFER[sampleIndex] & 0xff)) / 32767.0f;
                pRightChannel[byteIndex1] = (float) (((int) BUFFER[sampleIndex + 3] << 8) + (BUFFER[sampleIndex + 2] & 0xff))
                        / 32767.0f;*/
            }
        }

        // by now BYTES contains the data you want,
        // but be sure to account for frame count
        // (i.e. not all off BYTES may contain useful data,
        // might be some unused garbage at the end)

        // compute total bytes this round
        // = frame count * 2 channels * 2 bytes per short (INT16)
        int byteCount = frames * 2 * 2;

        // write to file - again, never do this in a real app
        if (game_.recordAudio){
            try {
                fos.write(BUFFER, 0, byteCount);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public XtAudio(){
        // this initializes platform dependent stuff like COM
        try(XtPlatform platform = xt.audio.XtAudio.init(null, Pointer.NULL, null)) {
            // works on windows only, obviously
            XtService service = platform.getService(Enums.XtSystem.WASAPI);
            // list input devices (this includes loopback)
            try(XtDeviceList list = service.openDeviceList(EnumSet.of( Enums.XtEnumFlags.INPUT))) {
                for(int i = 0; i < list.getCount(); i++) {
                    String deviceId = list.getId(i);
                    EnumSet<Enums.XtDeviceCaps> caps = list.getCapabilities(deviceId);
                    // filter loopback devices
                    if(caps.contains(Enums.XtDeviceCaps.LOOPBACK)) {
                        String deviceName = list.getName(deviceId);
                        // just to check what output we're recording
                        System.out.println(deviceName);
                        // open device
                        try(XtDevice device = service.openDevice(deviceId)) {
                            // 16 bit 48khz
                            Structs.XtMix mix = new Structs.XtMix(48000, Enums.XtSample.INT16);
                            // Structs.XtMix mix = new Structs.XtMix(44100, Enums.XtSample.INT16);
                            // 2 channels input, no masking
                            Structs.XtChannels channels = new Structs.XtChannels(2, 0, 0, 0);
                            // final audio format
                            Structs.XtFormat format = new Structs.XtFormat(mix, channels);
                            // query min/max/default buffer sizes
                            Structs.XtBufferSize bufferSize = device.getBufferSize(format);
                            // true->interleaved, onBuffer->audio stream callback
                            Structs.XtStreamParams streamParams = new Structs.XtStreamParams(true, XtAudio::onBuffer, null, null);
                            // final initialization params with default buffer size
                            Structs.XtDeviceStreamParams deviceParams = new Structs.XtDeviceStreamParams(streamParams, format, bufferSize.current);
                            // run stream
                            // safe buffer allows you to get java short[] instead on jna Pointer in the callback
                            try(XtStream stream = device.openStream(deviceParams, null);
                                var safeBuffer = XtSafeBuffer.register(stream, true)) {
                                // max frames to enter onBuffer * channels * bytes per sample
                                BUFFER = new byte[stream.getFrames() * 2 * 2];
                                // DO NOT USE CODES BELOW!, THIS IS DESIGNED FOR RECORDING
                                // make filename valid
                                String fileName = deviceName.replaceAll("[\\\\/:*?\"<>|]", "");
                                try(FileOutputStream fos0 = new FileOutputStream(fileName + ".raw")) {
                                    // make filestream accessible to the callback
                                    // could also be done by passsing as userdata to openStream
                                    if (game_.recordAudio) fos = fos0;
                                    // run for 1 second
                                    stream.start();
                                    // not xt audio stuff but ok
                                    game_.doneLoadingCodes();
                                    // noob fix
                                    run = true;
                                    System.out.println("==============================================================");
                                    Thread.sleep(1000000000); // run this for 11.5740740741 days
                                    System.out.println("this wasn't supposed to happen");
                                    System.exit(1);
                                }
                            }
                        }
                    }
                }
            }
        }
        catch (Exception exception){
            exception.printStackTrace();
        }
    }
}
