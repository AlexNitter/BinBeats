package main.java.binBeats.ui;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.Action;
import javax.swing.Box;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.ToolTipManager;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;

import net.miginfocom.swing.MigLayout;

import main.java.binBeats.lib.BinBeat;
import main.java.binBeats.lib.BinBeatValidator;
import main.java.binBeats.lib.BinBeatsPlayer;

import javax.swing.event.ChangeListener;
import javax.swing.text.NumberFormatter;
import javax.swing.event.ChangeEvent;

import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.NumberFormat;
import java.util.Locale;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;

/**
 * Displays the UI for BinBeat Player only
 * @author Magnus
 *
 */
public class BbUIBasic {

	private JFrame frmBinbeats;
	private NumberFormat numberFormatEn;
	private NumberFormatter numberFormatterEn;
	
	private BinBeat playerBinBeat;
	private BinBeatsPlayer binBeatsPlayer;
	private BinBeatValidator binBeatValidator;
	
	private boolean isPlaying = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		// Set System look and feel, hide from windowbuilder parser
		// hide>>$
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			System.err.println("Error setting native system look and feel " + e);
		}
		// $hide<<
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BbUIBasic window = new BbUIBasic();
					window.frmBinbeats.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public BbUIBasic() {
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame and add functionality.
	 */
	private void initialize() {
		try {
			binBeatsPlayer = new BinBeatsPlayer();
		} catch (LineUnavailableException e1) {
			// TODO: Popup
			e1.printStackTrace();
		}
		binBeatValidator = new BinBeatValidator();
		// TODO: Demo BinBeat
		playerBinBeat = new BinBeat(432, 7);
		
		/* Format numbers in text fields to display a dot as decimal separator
		 * and to not use grouping separators for multiples of 1000.
		 * e.g. instead of 1.333,37 display 1333.37 
		 */
		numberFormatEn = NumberFormat.getNumberInstance(Locale.ENGLISH);
		numberFormatEn.setGroupingUsed(false);		
		numberFormatterEn = new NumberFormatter(numberFormatEn);
		
		// Make tooltips stay open indefinitely
		ToolTipManager.sharedInstance().setDismissDelay(Integer.MAX_VALUE);
		
		frmBinbeats = new JFrame();
		frmBinbeats.setTitle("BinBeats");
		frmBinbeats.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		// _____________________ Player View _____________________
		
		JPanel panelPlayer = new JPanel();
		panelPlayer.setLayout(new MigLayout("", "[][][grow][grow][]", "[][][][][][][]"));
		frmBinbeats.getContentPane().add(panelPlayer, BorderLayout.CENTER);
		
		JLabel lblPlayerPreset = new JLabel("Preset");
		panelPlayer.add(lblPlayerPreset, "cell 1 0,alignx trailing");
		
		String[] mockupPlayerAtmosphereSelection = {"<new>", "Reasoning - Beta", "Relaxation - Alpha", "Meditation - Theta", "Deep Sleep - Delta"};
		JComboBox comboBoxPlayerPresetSelection = new JComboBox(mockupPlayerAtmosphereSelection);
		comboBoxPlayerPresetSelection.setEditable(true);
		comboBoxPlayerPresetSelection.setToolTipText("Select binaural beat from preset or create a new one");
		panelPlayer.add(comboBoxPlayerPresetSelection, "flowx,cell 2 0,growx");
		
		//JButton btnPlayerSave = new JButton("Save");
		JButton btnPlayerSave = new JButton();
		btnPlayerSave.setIcon(new ImageIcon(BbUIBasic.class.getResource("/main/java/binBeats/ui/save.png")));
		btnPlayerSave.setToolTipText("Save preset");
		panelPlayer.add(btnPlayerSave, "flowx,cell 3 0");
		
		// initialize Play button with whitespaces to prevent component resizing during runtime
		// TODO: find more elegant solution. If found, change in Action Listener too
		JButton btnPlayerPlay = new JButton("");
		btnPlayerPlay.setIcon(new ImageIcon(BbUIBasic.class.getResource("/main/java/binBeats/ui/play.png")));
		btnPlayerPlay.setToolTipText("Play binaural beat as configured below");
		panelPlayer.add(btnPlayerPlay, "cell 3 0");
				
		JLabel lblPlayerCarrierFrequency = new JLabel("Carrier Frequency");
		panelPlayer.add(lblPlayerCarrierFrequency, "cell 1 1,alignx trailing");
		
		JFormattedTextField formattedTextFieldPlayerCarrier = new JFormattedTextField(numberFormatterEn);
		formattedTextFieldPlayerCarrier.setHorizontalAlignment(SwingConstants.RIGHT);
		formattedTextFieldPlayerCarrier.setValue(playerBinBeat.getCarrierFrequency());
		formattedTextFieldPlayerCarrier.setToolTipText("Define the pitch of the carrier tone");
		JSlider sliderPlayerCarrier = new JSlider();
		sliderPlayerCarrier.setToolTipText("Define the pitch of the carrier tone");
		
		formattedTextFieldPlayerCarrier.setColumns(6);
		panelPlayer.add(formattedTextFieldPlayerCarrier, "flowx,cell 2 1,alignx left");
		
		sliderPlayerCarrier.setMinimum(20);
		sliderPlayerCarrier.setMaximum(1500);
		sliderPlayerCarrier.setMinorTickSpacing(1);
		sliderPlayerCarrier.setMajorTickSpacing(10);
		sliderPlayerCarrier.setValue((int)this.playerBinBeat.getCarrierFrequency());
		panelPlayer.add(sliderPlayerCarrier, "cell 1 2 3 1,growx");
		
		JLabel lblPlayerCarrierHz = new JLabel("Hz");
		panelPlayer.add(lblPlayerCarrierHz, "cell 2 1");
		
		JLabel labelPlayerCarrier_20 = new JLabel("20");
		panelPlayer.add(labelPlayerCarrier_20, "cell 0 2,alignx right");
		
		JLabel labelPlayerCarrier_1500 = new JLabel("1500");
		panelPlayer.add(labelPlayerCarrier_1500, "cell 4 2,alignx left");
		
		JLabel lblPlayerBeatFrequency = new JLabel("Beat Frequency");
		panelPlayer.add(lblPlayerBeatFrequency, "cell 1 3,alignx trailing");
		
		JFormattedTextField formattedTextFieldPlayerBeatFreq = new JFormattedTextField(numberFormatterEn);
		formattedTextFieldPlayerBeatFreq.setHorizontalAlignment(SwingConstants.RIGHT);
		formattedTextFieldPlayerBeatFreq.setValue(playerBinBeat.getBeatFrequency());
		String beatFreqTooltip = "<html>Define the frequency of the binaural beat"
				+ "<ul>"
				+ "<li><strong>0.5 - 4</strong> - <em>Delta</em> - deep sleep, trance</li>"
				+ "<li><strong>4 - 6.5</strong> - <em>Low Theta</em> - falling asleep</li>"
				+ "<li><strong>6.5 - 8</strong> - <em>High Theta</em> - deep relaxation, meditation</li>"
				+ "<li><strong>8 - 13</strong> - <em>Alpha</em> - light relaxation, super learning</li>"
				+ "<li><strong>13 - 15</strong> - <em>Low Beta</em> - awareness</li>"
				+ "<li><strong>15 - 21</strong> - <em>Medium Beta</em> - concentration</li>"
				+ "<li><strong>21 - 30</strong> - <em>High Beta</em> - stress</li>"
				+ "</ul></html>";
		formattedTextFieldPlayerBeatFreq.setToolTipText(beatFreqTooltip);
		formattedTextFieldPlayerBeatFreq.setColumns(6);
		panelPlayer.add(formattedTextFieldPlayerBeatFreq, "flowx,cell 2 3,alignx left");
		
		JLabel lblPlayerBeatFreqHz = new JLabel("Hz");
		panelPlayer.add(lblPlayerBeatFreqHz, "cell 2 3");
		
		JLabel labelPlayerBeatFreq_05 = new JLabel("0.5");
		panelPlayer.add(labelPlayerBeatFreq_05, "cell 0 4,alignx right");
		
		JSlider sliderPlayerBeatFreq = new JSlider();
		sliderPlayerBeatFreq.setToolTipText(beatFreqTooltip);
		panelPlayer.add(sliderPlayerBeatFreq, "cell 1 4 3 1,growx");
		
		JLabel labelPlayerBeatFreq_30 = new JLabel("30");
		panelPlayer.add(labelPlayerBeatFreq_30, "cell 4 4,alignx left");
		
		sliderPlayerBeatFreq.setMajorTickSpacing(10);
		sliderPlayerBeatFreq.setMinorTickSpacing(1);
		sliderPlayerBeatFreq.setMaximum(300);
		sliderPlayerBeatFreq.setMinimum(5);
		sliderPlayerBeatFreq.setValue((int)this.playerBinBeat.getBeatFrequency()*10);
		
		JLabel lblPlayerBeatVolume = new JLabel("Beat Volume");
		panelPlayer.add(lblPlayerBeatVolume, "cell 1 5,alignx trailing");
		
		JFormattedTextField formattedTextFieldPlayerBeatVol = new JFormattedTextField(numberFormatterEn);
		formattedTextFieldPlayerBeatVol.setHorizontalAlignment(SwingConstants.RIGHT);
		formattedTextFieldPlayerBeatVol.setValue(playerBinBeat.getVolume());
		panelPlayer.add(formattedTextFieldPlayerBeatVol, "flowx,cell 2 5");
		formattedTextFieldPlayerBeatVol.setToolTipText("Define binaural beat volume");
		formattedTextFieldPlayerBeatVol.setColumns(6);
		
		JLabel labelPlayerBeatVolume_0 = new JLabel("0");
		panelPlayer.add(labelPlayerBeatVolume_0, "cell 0 6,alignx right");
		
		
		JSlider sliderPlayerBeatVol = new JSlider();
		panelPlayer.add(sliderPlayerBeatVol, "cell 1 6 3 1,growx");
		sliderPlayerBeatVol.setToolTipText("Define binaural beat volume");
		sliderPlayerBeatVol.setMajorTickSpacing(10);
		sliderPlayerBeatVol.setMinorTickSpacing(1);
		sliderPlayerBeatVol.setMinimum(0);
		sliderPlayerBeatVol.setMaximum(100);
		// TODO: maybe change playerBinBeat.getVolume() to int in BinBeat Class
		sliderPlayerBeatVol.setValue(Math.round(playerBinBeat.getVolume()));
		
		JLabel labelPlayerBeatVolPercent = new JLabel("%");
		panelPlayer.add(labelPlayerBeatVolPercent, "cell 2 5");
		
		JLabel labelPlayerBeatVolume_100 = new JLabel("100");
		panelPlayer.add(labelPlayerBeatVolume_100, "cell 4 6,alignx left");
		
		
		// _____________________ UI functionality for Player View _____________________
		
		// after all elements are initialized
		
		// Play button actions
		btnPlayerPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!isPlaying) {
					// collect data from UI in playerBinBeat
					// cannot use the fields' value() function because moving a slider will fill the field with int
					playerBinBeat.setCarrierFrequency(Float.parseFloat(formattedTextFieldPlayerCarrier.getText()));
					playerBinBeat.setBeatFrequency(Float.parseFloat(formattedTextFieldPlayerBeatFreq.getText()));
					playerBinBeat.setVolume(Float.parseFloat(formattedTextFieldPlayerBeatVol.getText()));
					binBeatsPlayer.setBinBeat(playerBinBeat);
					try {
						btnPlayerPlay.setIcon(new ImageIcon(BbUIBasic.class.getResource("/main/java/binBeats/ui/stop.png")));
						btnPlayerPlay.setToolTipText("Stop playback");
						binBeatsPlayer.play();
						isPlaying = true;
					} catch (LineUnavailableException e1) {
						// TODO: Popup
						e1.printStackTrace();
					}
				} else {
					// if Beat is already playing
					btnPlayerPlay.setIcon(new ImageIcon(BbUIBasic.class.getResource("/main/java/binBeats/ui/play.png")));
					btnPlayerPlay.setToolTipText("Play binaural beat as configured below");
					binBeatsPlayer.stop();
					isPlaying = false;
				}
			}
		});
		
		// Link carrier slider and text field
		formattedTextFieldPlayerCarrier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				float value = checkValidity(formattedTextFieldPlayerCarrier.getText(), "\\d+(\\.\\d*)?", 7, binBeatValidator.getCarrierFrequencyMin(), binBeatValidator.getCarrierFrequencyMax());
				if (value > -1) {
					sliderPlayerCarrier.setValue(Math.round(value));
					formattedTextFieldPlayerCarrier.setValue(value);
				} else {
					// TODO: Popup
				}
			}
		});
		sliderPlayerCarrier.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				formattedTextFieldPlayerCarrier.setValue(sliderPlayerCarrier.getValue());
			}
		});
		
		/* Link beat frequency slider and text field
		 * Note: JSlider works with integers.
		 * As we want to be able to change values by .1 we multiply by 10.
		 */
		formattedTextFieldPlayerBeatFreq.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				float value = checkValidity(formattedTextFieldPlayerBeatFreq.getText(), "\\d+(\\.\\d*)?", 5, binBeatValidator.getBeatFrequencyMin(), binBeatValidator.getBeatFrequencyMax());
				if (value > -1){
					sliderPlayerBeatFreq.setValue((int)(value*10));
					formattedTextFieldPlayerBeatFreq.setValue(value);
				} else {
					// TODO: Popup
				}
			}
		});
		sliderPlayerBeatFreq.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				formattedTextFieldPlayerBeatFreq.setValue((float)sliderPlayerBeatFreq.getValue()/10);
			}
		});
		
		// Link beat volume field with slider
		formattedTextFieldPlayerBeatVol.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// don't allow decimal fractions for volume percentage
				// float value = checkValidity(formattedTextFieldPlayerBeatVol.getText(), "\\d+", 3, binBeatValidator.getVolumeMin(), binBeatValidator.getVolumeMax());
				// allow decimal fractions for volume percentage
				float value = checkValidity(formattedTextFieldPlayerBeatVol.getText(), "\\d+(\\.\\d*)?", 5, binBeatValidator.getVolumeMin(), binBeatValidator.getVolumeMax());
				if (value > -1) {
					sliderPlayerBeatVol.setValue(Math.round(value));
					formattedTextFieldPlayerBeatVol.setValue(value);
				}
			}
		});
		sliderPlayerBeatVol.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				formattedTextFieldPlayerBeatVol.setValue(sliderPlayerBeatVol.getValue());
			}
		});
		
		// After every element is added, pack window to content size and center on screen
		frmBinbeats.pack();
		//TODO: remove btnPlayerPlay.setText("Play");
		frmBinbeats.setLocationRelativeTo(null);
		
				
	}
	

	/**
	 * Checks the value of a text field against a regular expression and upper and lower bounds.
	 * Corrects the value to its upper or lower bound if necessary.
	 * 
	 * @param typedValue the String to be checked and converted into a number
	 * @param regex the regular expression to check if typedValue is a valid number
	 * @param maxLength maximum length for the number to be valid
	 * @param lowerBound lower bound to be checked against
	 * @param upperBound upper bound to be checked against
	 * @return the checked and corrected value or -1 if not a valid number
	 */
	private float checkValidity(String typedValue, String regex, int maxLength, float lowerBound, float upperBound){
		float value = -1;
		// Check if typed value is a valid number
		if (!typedValue.matches(regex)) {
			System.out.println(typedValue + " is not a valid number.");
			return value;
		}
		try {
			value = Float.parseFloat(typedValue);
		} catch (NumberFormatException e){
			System.out.println(typedValue + " is not a parsable float.");
			return value;
		}
		// Check for correct specifications and change if necessary
		if (value < lowerBound) {
			value = lowerBound;
		} else if (value > upperBound) {
			value = upperBound;
		}
		return value;
	}
}
