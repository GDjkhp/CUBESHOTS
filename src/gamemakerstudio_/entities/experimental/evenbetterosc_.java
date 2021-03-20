package gamemakerstudio_.entities.experimental;

import gamemakerstudio_.game_;
import gamemakerstudio_.misc.ID;
import gamemakerstudio_.misc.gameobject_;

import java.awt.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

public class evenbetterosc_ extends gameobject_ {
    // TODO: this must convert ogg to pcm for visualization

    int colorIndex = 0;
    int colorSize = 360;

    int	halfCanvasHeight = game_.HEIGHT/2;
    int canvasWidth = game_.WIDTH;

    // intermediate buffer
//    public static byte[] BUFFER;

    public evenbetterosc_(float x, float y, ID id) {
        super(x, y, id);
    }

    int nBytesRead = 0;
    int audioDataLength = 1024 * 4;
    ByteBuffer audioDataBuffer = ByteBuffer.allocate(audioDataLength);
    {
        audioDataBuffer.order(ByteOrder.LITTLE_ENDIAN);
    }
    static byte[] BUFFER = new byte[1024*4];

    @Override
    public void tick() {
        /*if (audioInputStream != null){
            try {
                int toRead = audioDataLength;
                int totalRead = 0;
                // Reads up a specified maximum number of bytes
                // from audio stream
                while (toRead > 0 && (nBytesRead = audioInputStream.read(audioDataBuffer.array(), totalRead,
                        toRead)) != -1) {
                    totalRead += nBytesRead;
                    toRead -= nBytesRead;
                }

                if (totalRead > 0) {
                    BUFFER = audioDataBuffer.array();
                    if (totalRead < BUFFER.length) {
                        BUFFER = new byte[totalRead];
                        //Copies an array from the specified source array, beginning at the specified position, to the specified position of the destination array
                        // The number of components copied is equal to the length argument.
                        System.arraycopy(audioDataBuffer.array(), 0, BUFFER, 0, totalRead);
                    }
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }*/
    }

    @Override
    public void render(Graphics gc) {
        System.out.println(Arrays.toString(BUFFER));

        /*boolean stereo = true;

        float[] pSample1 = bytesToFloats(BUFFER, 0); // FIXME: must be left channel

        // use this if problem above was fixed
        gc.setColor(Color.WHITE);

        colorIndex = (colorIndex == colorSize - 1) ? 0 : colorIndex + 1;
        gc.setColor(Color.getHSBColor((float) colorIndex / 360f, 1.0f, 1.0f));

        int yLast1 = (int) (pSample1[0] * (float) halfCanvasHeight)
                + halfCanvasHeight;
        int samIncrement1 = 1;
        for (int a = samIncrement1, c = 0; c < canvasWidth && a < pSample1.length; a += samIncrement1, c+=2) {
            int yNow = (int) (pSample1[a] * (float) halfCanvasHeight)
                    + halfCanvasHeight;
            gc.drawLine(c, yLast1, c + 1, yNow);
            yLast1 = yNow;
        }

        if (stereo) {
            colorIndex = (colorIndex == colorSize - 1) ? 0 : colorIndex + 1;
            gc.setColor(Color.getHSBColor((float) colorIndex / 360f, 1.0f, 1.0f));
//			System.out.println(Color.getHSBColor((float) colorIndex / 360f, 1.0f, 1.0f) + ", colorIndex: " + colorIndex);

            float[] pSample2 = bytesToFloats(BUFFER, 1); // FIXME: must be right channel

            int yLast2 = (int) (pSample2[0] * (float) halfCanvasHeight)
                    + halfCanvasHeight;
            int samIncrement2 = 1;
            for (int a = samIncrement2, c = 0; c < canvasWidth && a < pSample2.length; a += samIncrement2, c+=2) {
                int yNow = (int) (pSample2[a] * (float) halfCanvasHeight)
                        + halfCanvasHeight;
                gc.drawLine(c, yLast2, c + 1, yNow);
                yLast2 = yNow;
            }
        }*/
    }

    public static float[] bytesToFloats(byte[] bytes, int channel){
        float[] floats = new float[bytes.length / 2];
        for (int i = 0; i < bytes.length; i += 2){
            floats[i/2] = (float)(bytes[i] | (bytes[i+1] << 8)) / 32767.0f;
        }

        int limit = 480;
        float[] betterFloats = new float[floats.length / 2];
        float[] clampToLimit = new float[limit];
        if (channel == 0){
            for (int i = 0, j = 0; i < floats.length; i+=2, j++){
                betterFloats[j] = floats[i];
            }
        } else if (channel == 1){
            for (int i = 1, j = 0; i < floats.length; i+=2, j++){
                betterFloats[j] = floats[i];
            }
        }
        for (int i = 0; i < limit; i++){
            clampToLimit[i] = betterFloats[i];
        }

        // find two zero
        /*for (int i = 0; i < betterFloats.length; i++){
            if (i != 0 && betterFloats[i - 1] != 0 && betterFloats[i] == 0 && betterFloats[i + 1] == 0){
                System.out.println("index " + i + " afterwards fucks up the array");
            }
        }*/

        return clampToLimit; // TODO: must return channel
    }

    @Override
    public Rectangle getBounds() {
        return null;
    }
}
