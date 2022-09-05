package classes;
import java.io.IOException;
import java.util.Objects;

import javax.sound.sampled.*;

public class AudioPlayer{


	AudioInputStream audioStream;
	Clip clip;
	String file;
	/**
	 * 
	 * @param file String name of the .wav file the player should play
	 * @throws UnsupportedAudioFileException
	 * @throws IOException
	 * @throws LineUnavailableException
	 */
	public AudioPlayer(String file) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		this.file = file;
		audioStream = AudioSystem.getAudioInputStream(AudioPlayer.class.getResource(file));
		clip = AudioSystem.getClip();
		clip.open(audioStream);
	}
	
	/**
	 * plays the selected audio file once
	 */
	public void play() {
		FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);        
    gainControl.setValue(20f * (float) Math.log10(0.1));
		clip.start();
	}
	
	/**
	 * loops the selected audio file until stopped.
	 */
	public void playLoop(){
		FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);        
    gainControl.setValue(20f * (float) Math.log10(0.1));  

		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	/**
	 * Creates a new instance of the clip that can be played again at a later time 
	 * @throws LineUnavailableException
	 * @throws IOException
	 * @throws UnsupportedAudioFileException
	 */
	public void stop() throws LineUnavailableException, IOException, UnsupportedAudioFileException {
		audioStream = AudioSystem.getAudioInputStream(AudioPlayer.class.getResource(file));
		clip = AudioSystem.getClip();
		clip.open(audioStream);
		return;
	}

	
	
}
