package surviveGame;

import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {
   private static Vector<String> v = new Vector<String>();
   private static Clip clip;
   static void loadAudio(String pathName) {
         v.add(pathName);
      try {
         clip = AudioSystem.getClip();
         File audioFile = new File(pathName);
         AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
         clip.open(audioStream);
         clip.start();
         clip.loop(Clip.LOOP_CONTINUOUSLY);
      }
      catch(LineUnavailableException e) {e.printStackTrace();}
      catch(UnsupportedAudioFileException e) {e.printStackTrace();}
      catch(IOException e) {e.printStackTrace();}
   }
   static void unloadAudio() {
      clip.stop();
      String s = v.get(0);
      System.out.println(s);
   }
   static void startAudio() {
      clip.start();
   }
   static void pauseAudio() {
      clip.stop();
   }
   static void restartAudio() {
      clip.setFramePosition(0);
      clip.start();
   }
}