package main.java.binBeats.ui;

import java.awt.EventQueue;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
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
import javax.swing.event.ChangeListener;
import javax.swing.text.NumberFormatter;
import javax.swing.event.ChangeEvent;

import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.NumberFormat;
import java.util.Locale;
import java.awt.event.ActionListener;

/**
 * Displays the UI for BinBeat Player and -Session Manager
 * @author Magnus
 *
 */
public class BbUI {

	private JFrame frmBinbeats;
	private NumberFormat numberFormatEn;
	private NumberFormatter numberFormatterEn;
	
	private BinBeat playerBinBeat;

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
					BbUI window = new BbUI();
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
	public BbUI() {
		initialize();
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
	private int checkValidity(String typedValue, String regex, int maxLength, int lowerBound, int upperBound){
		// Check if typed value is a valid number
		if (!typedValue.matches(regex) || typedValue.length() > maxLength) {
			return -1;
		}
		int value = Integer.parseInt(typedValue);
		// Check for correct specifications and change if necessary
		if (value < lowerBound) {
			value = lowerBound;
		} else if (value > upperBound) {
			value = upperBound;
		}
		return value;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		// TODO: Demo BinBeat
		playerBinBeat = new BinBeat(432, 7);
		
		/* Format numbers in text fields to display a dot as decimal separator
		 * and to not use grouping separators for multiples of 1000.
		 * e.g. instead of 1.333,37 use 1333.37 
		 */
		numberFormatEn = NumberFormat.getNumberInstance(Locale.ENGLISH);
		numberFormatEn.setGroupingUsed(false);		
		numberFormatterEn = new NumberFormatter(numberFormatEn);
		
		// Make tooltips stay open indefinitely
		ToolTipManager.sharedInstance().setDismissDelay(Integer.MAX_VALUE);
		
		frmBinbeats = new JFrame();
		frmBinbeats.setTitle("BinBeats");
		frmBinbeats.setBounds(100, 100, 476, 307);
		frmBinbeats.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frmBinbeats.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		
		// _____________________ Player View _____________________
		// TODO: connect volume fields with sliders, fix international display of decimal values (0,1 should be displayed as 0.1)
		
		JPanel panelPlayer = new JPanel();
		tabbedPane.addTab("Simple Player", null, panelPlayer, null);
		panelPlayer.setLayout(new MigLayout("", "[][][grow][grow][]", "[][][][][][][grow]"));
		
		JLabel lblPlayerPreset = new JLabel("Preset");
		panelPlayer.add(lblPlayerPreset, "cell 1 0,alignx trailing");
		
		String[] mockupPlayerAtmosphereSelection = {"<new>", "Reasoning - Beta", "Relaxation - Alpha", "Meditation - Theta", "Deep Sleep - Delta"};
		JComboBox comboBoxPlayerPresetSelection = new JComboBox(mockupPlayerAtmosphereSelection);
		comboBoxPlayerPresetSelection.setToolTipText("Select binaural beat from preset or create a new one");
		panelPlayer.add(comboBoxPlayerPresetSelection, "flowx,cell 2 0,growx");
		
		JButton btnPlayerSave = new JButton("Save");
		btnPlayerSave.setToolTipText("Save preset");
		panelPlayer.add(btnPlayerSave, "flowx,cell 3 0");
		
		JButton btnPlayerPlay = new JButton("Play");
		btnPlayerPlay.setToolTipText("Play binaural beat as configured below");
		panelPlayer.add(btnPlayerPlay, "cell 3 0");
		
		JLabel lblPlayerBackground = new JLabel("Background");
		panelPlayer.add(lblPlayerBackground, "cell 1 1,alignx trailing");
		
		// TODO: Parse from Background Audio List
		String[] bgSelectionMockup = {"(none)", "Pink Noise", "White Noise", "Stream", "Rain"};
		JComboBox comboBoxPlayerBgSelection = new JComboBox(bgSelectionMockup);
		comboBoxPlayerBgSelection.setToolTipText("Select background noise");
		panelPlayer.add(comboBoxPlayerBgSelection, "cell 2 1,growx");
		
		JLabel lblPlayerCarrierFrequency = new JLabel("Carrier Frequency");
		panelPlayer.add(lblPlayerCarrierFrequency, "cell 1 2,alignx trailing");
		
		JFormattedTextField formattedTextFieldPlayerCarrier = new JFormattedTextField(numberFormatterEn);
		formattedTextFieldPlayerCarrier.setToolTipText("Define the pitch of the carrier tone");
		JSlider sliderPlayerCarrier = new JSlider();
		sliderPlayerCarrier.setToolTipText("Define the pitch of the carrier tone");
		
		// Link carrier slider and text field
		formattedTextFieldPlayerCarrier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int value = checkValidity(formattedTextFieldPlayerCarrier.getText(), "\\d+", 4, 20, 1500);
				if (value > -1) {
					formattedTextFieldPlayerCarrier.setValue(value);
					sliderPlayerCarrier.setValue(value);
				}
			}
		});
		sliderPlayerCarrier.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				formattedTextFieldPlayerCarrier.setValue(sliderPlayerCarrier.getValue());
			}
		});
		
		formattedTextFieldPlayerCarrier.setColumns(4);
		panelPlayer.add(formattedTextFieldPlayerCarrier, "flowx,cell 2 2,alignx left");
		
		sliderPlayerCarrier.setMinimum(20);
		sliderPlayerCarrier.setMaximum(1500);
		sliderPlayerCarrier.setMinorTickSpacing(1);
		sliderPlayerCarrier.setMajorTickSpacing(10);
		sliderPlayerCarrier.setValue((int)this.playerBinBeat.getCarrierFrequency());
		panelPlayer.add(sliderPlayerCarrier, "cell 1 3 3 1,growx");
		
		JLabel lblPlayerCarrierHz = new JLabel("Hz");
		panelPlayer.add(lblPlayerCarrierHz, "cell 2 2");
		
		JLabel labelPlayerCarrier_20 = new JLabel("20");
		panelPlayer.add(labelPlayerCarrier_20, "cell 0 3,alignx right");
		
		JLabel labelPlayerCarrier_1500 = new JLabel("1500");
		panelPlayer.add(labelPlayerCarrier_1500, "cell 4 3,alignx left");
		
		JLabel lblPlayerBeatFrequency = new JLabel("Beat Frequency");
		panelPlayer.add(lblPlayerBeatFrequency, "cell 1 4,alignx trailing");
		
		JFormattedTextField formattedTextFieldPlayerBeatFreq = new JFormattedTextField(numberFormatterEn);
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
		formattedTextFieldPlayerBeatFreq.setColumns(4);
		panelPlayer.add(formattedTextFieldPlayerBeatFreq, "flowx,cell 2 4,alignx left");
		
		JLabel lblPlayerBeatFreqHz = new JLabel("Hz");
		panelPlayer.add(lblPlayerBeatFreqHz, "cell 2 4");
		
		JLabel labelPlayerBeatFreq_05 = new JLabel("0.5");
		panelPlayer.add(labelPlayerBeatFreq_05, "cell 0 5,alignx right");
		
		JSlider sliderPlayerBeatFreq = new JSlider();
		sliderPlayerBeatFreq.setToolTipText(beatFreqTooltip);
		panelPlayer.add(sliderPlayerBeatFreq, "cell 1 5 3 1,growx");
		
		JLabel labelPlayerBeatFreq_30 = new JLabel("30");
		panelPlayer.add(labelPlayerBeatFreq_30, "cell 4 5,alignx left");
		
		/* Link beat slider and text field
		 * Note: JSlider works with integers.
		 * As we want to be able to change values by .1 we divide by 10.
		 */
		formattedTextFieldPlayerBeatFreq.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String typedBeatFreq = formattedTextFieldPlayerBeatFreq.getText();
				if(!typedBeatFreq.matches("\\d+(\\.\\d?)?")) {
					return;
				}
				float fieldValue = Float.parseFloat(typedBeatFreq);
				int sliderValue = (int)(fieldValue*10);
				// Check for correct BinBeat specifications and change if necessary
				if (sliderValue < 5) {
					sliderValue = 5;
					fieldValue = 0.5f;
				} else if (sliderValue > 300) {
					sliderValue = 300;
					fieldValue = 30;
				}
				formattedTextFieldPlayerBeatFreq.setValue(fieldValue);
				sliderPlayerBeatFreq.setValue(sliderValue);
			}
		});
		sliderPlayerBeatFreq.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				formattedTextFieldPlayerBeatFreq.setValue((float)sliderPlayerBeatFreq.getValue()/10);
			}
		});
		
		sliderPlayerBeatFreq.setMajorTickSpacing(10);
		sliderPlayerBeatFreq.setMinorTickSpacing(1);
		sliderPlayerBeatFreq.setMaximum(300);
		sliderPlayerBeatFreq.setMinimum(5);
		sliderPlayerBeatFreq.setValue((int)this.playerBinBeat.getBeatFrequency()*10);
		
		JPanel panelPlayerVolumeControl = new JPanel();
		panelPlayerVolumeControl.setBorder(null);
		panelPlayer.add(panelPlayerVolumeControl, "cell 0 6 5 1,grow");
		panelPlayerVolumeControl.setLayout(new MigLayout("", "[][grow][][][grow]", "[][]"));
		
		JLabel lblPlayerBackgroundVolume = new JLabel("Background Volume");
		panelPlayerVolumeControl.add(lblPlayerBackgroundVolume, "cell 0 0,alignx trailing");
		
		JFormattedTextField formattedTextFieldPlayerBgVol = new JFormattedTextField();
		formattedTextFieldPlayerBgVol.setToolTipText("Define background noise volume");
		formattedTextFieldPlayerBgVol.setColumns(4);
		panelPlayerVolumeControl.add(formattedTextFieldPlayerBgVol, "flowx,cell 1 0,alignx left");
		
		JSlider sliderPlayerBgVol = new JSlider();
		sliderPlayerBgVol.setToolTipText("Define background noise volume");
		sliderPlayerBgVol.setMajorTickSpacing(10);
		sliderPlayerBgVol.setMinorTickSpacing(1);
		sliderPlayerBgVol.setMinimum(0);
		sliderPlayerBgVol.setMaximum(100);
		panelPlayerVolumeControl.add(sliderPlayerBgVol, "cell 0 1 2 1,growx");
		
		// Link background volume field with slider
		formattedTextFieldPlayerBgVol.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {			
				int value = checkValidity(formattedTextFieldPlayerBgVol.getText(), "\\d+", 3, 0, 100);
				if (value > -1) {
					formattedTextFieldPlayerBgVol.setValue(value);
					sliderPlayerBgVol.setValue(value);
				}
			}
		});
		sliderPlayerBgVol.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				formattedTextFieldPlayerBgVol.setValue(sliderPlayerBgVol.getValue());
			}
		});
		
		JLabel labelPlayerBgvolPercent = new JLabel("%");
		panelPlayerVolumeControl.add(labelPlayerBgvolPercent, "cell 1 0");
		
		JLabel lblPlayerBeatVolume = new JLabel("Beat Volume");
		panelPlayerVolumeControl.add(lblPlayerBeatVolume, "cell 3 0,alignx trailing");
		
		JFormattedTextField formattedTextFieldPlayerBeatVol = new JFormattedTextField();
		formattedTextFieldPlayerBeatVol.setToolTipText("Define binaural beat volume");
		formattedTextFieldPlayerBeatVol.setColumns(4);
		panelPlayerVolumeControl.add(formattedTextFieldPlayerBeatVol, "flowx,cell 4 0,alignx left");
		
		JLabel labelPlayerBeatVolPercent = new JLabel("%");
		panelPlayerVolumeControl.add(labelPlayerBeatVolPercent, "cell 4 0");
		
		JSlider sliderPlayerBeatVol = new JSlider();
		sliderPlayerBeatVol.setToolTipText("Define binaural beat volume");
		sliderPlayerBeatVol.setMajorTickSpacing(10);
		sliderPlayerBeatVol.setMinorTickSpacing(1);
		sliderPlayerBeatVol.setMinimum(0);
		sliderPlayerBeatVol.setMaximum(100);
		// TODO: initialize volume slider with volume stored in this.playerBinBeat
		panelPlayerVolumeControl.add(sliderPlayerBeatVol, "cell 3 1 2 1,growx");
		
		// Link beat volume field with slider
		formattedTextFieldPlayerBeatVol.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int value = checkValidity(formattedTextFieldPlayerBeatVol.getText(), "\\d+", 3, 0, 100);
				if (value > -1) {
					formattedTextFieldPlayerBeatVol.setValue(value);
					sliderPlayerBeatVol.setValue(value);
				}
			}
		});
		sliderPlayerBeatVol.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				formattedTextFieldPlayerBeatVol.setValue(sliderPlayerBeatVol.getValue());
			}
		});
		
		
		
		
		// _____________________ Session View _____________________
		
		JPanel panelSession = new JPanel();
		tabbedPane.addTab("Session", null, panelSession, null);
		panelSession.setLayout(new MigLayout("", "[][grow][][][][]", "[][][][][grow]"));
		
		JLabel lblSessionPreset = new JLabel("Preset");
		panelSession.add(lblSessionPreset, "cell 0 0,alignx trailing");
		
		// TODO: Parse from sessions
		String[] sessionMockup = {"<new>", "Meditation", "Sleep Aid", "Wakeup"};
		JComboBox comboBoxSessionPresetSelection = new JComboBox(sessionMockup);
		panelSession.add(comboBoxSessionPresetSelection, "cell 1 0,growx");
		
		JButton btnSessionSave = new JButton("Save");
		panelSession.add(btnSessionSave, "cell 2 0,alignx right");
		
		JButton btnSessionPlay = new JButton("Play");
		panelSession.add(btnSessionPlay, "cell 3 0,alignx center");
		
		JButton btnSessionExport = new JButton("Export");
		panelSession.add(btnSessionExport, "cell 4 0,alignx left");
		
		JLabel lblSessionBackground = new JLabel("Background");
		panelSession.add(lblSessionBackground, "cell 0 1,alignx trailing");
		
		JComboBox comboBoxSessionBgSelection = new JComboBox(bgSelectionMockup);
		panelSession.add(comboBoxSessionBgSelection, "cell 1 1,growx");
		
		JLabel lblSessionBackgroundVolume = new JLabel("Background Volume");
		panelSession.add(lblSessionBackgroundVolume, "cell 0 2,alignx trailing");
		
		JFormattedTextField formattedTextFieldSessionBgVol = new JFormattedTextField();
		formattedTextFieldSessionBgVol.setHorizontalAlignment(SwingConstants.LEFT);
		lblSessionBackgroundVolume.setLabelFor(formattedTextFieldSessionBgVol);
		formattedTextFieldSessionBgVol.setText("50");
		formattedTextFieldSessionBgVol.setColumns(3);
		panelSession.add(formattedTextFieldSessionBgVol, "flowx,cell 1 2,alignx left");
		
		Component horizontalGlue = Box.createHorizontalGlue();
		panelSession.add(horizontalGlue, "cell 4 2");
		
		JLabel lblSessionBeatVolume = new JLabel("Beat Volume");
		panelSession.add(lblSessionBeatVolume, "cell 3 2,alignx trailing");
		
		JFormattedTextField formattedTextFieldSessionBeatVol = new JFormattedTextField();
		formattedTextFieldSessionBeatVol.setHorizontalAlignment(SwingConstants.LEFT);
		lblSessionBeatVolume.setLabelFor(formattedTextFieldSessionBeatVol);
		formattedTextFieldSessionBeatVol.setText("50");
		formattedTextFieldSessionBeatVol.setColumns(3);
		panelSession.add(formattedTextFieldSessionBeatVol, "flowx,cell 4 2,alignx left");
		
		JLabel lblSessionBeatVolPercent = new JLabel("%");
		panelSession.add(lblSessionBeatVolPercent, "cell 4 2");
		
		JSlider sliderSessionBgVol = new JSlider();
		panelSession.add(sliderSessionBgVol, "cell 0 3 2 1");
		
		JSlider sliderSessionBeatVol = new JSlider();
		panelSession.add(sliderSessionBeatVol, "cell 3 3 2 1");
		
		JLabel lblSessionBgVolPercent = new JLabel("%");
		panelSession.add(lblSessionBgVolPercent, "cell 1 2");
		
		JPanel panelSessionDatapoints = new JPanel();
		panelSessionDatapoints.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Datapoints", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelSession.add(panelSessionDatapoints, "cell 0 4 5 1,grow");
		panelSessionDatapoints.setLayout(new MigLayout("", "[][][][][][][]", "[][grow]"));
		
		JLabel lblSessionCarrier = new JLabel("Carrier");
		panelSessionDatapoints.add(lblSessionCarrier, "cell 0 0,alignx left");
		
		JFormattedTextField formattedTextFieldSessionCarrierFreq = new JFormattedTextField();
		formattedTextFieldSessionCarrierFreq.setHorizontalAlignment(SwingConstants.LEFT);
		formattedTextFieldSessionCarrierFreq.setColumns(5);
		panelSessionDatapoints.add(formattedTextFieldSessionCarrierFreq, "flowx,cell 1 0");
		
		JLabel lblSessionCarrierHz = new JLabel("Hz");
		panelSessionDatapoints.add(lblSessionCarrierHz, "cell 1 0");
		
		// TODO: parse from real datapoint list
		String[] mockupDatapointsList = {"P1 - C 150 - B 12 - D 300", "P2 - C 150 - B 7 - D 300", "P3 - C 130 - B 0.5 - D 300", "P4 - C 130 - B 0.5 - D 300", "<new>"};
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		panelSessionDatapoints.add(horizontalStrut, "flowx,cell 2 0");
		
		JLabel lblSessionBeat = new JLabel("Beat");
		panelSessionDatapoints.add(lblSessionBeat, "cell 2 0,alignx right");
		
		JFormattedTextField formattedTextFieldSessionBeatFreq = new JFormattedTextField();
		formattedTextFieldSessionBeatFreq.setHorizontalAlignment(SwingConstants.LEFT);
		formattedTextFieldSessionBeatFreq.setColumns(5);
		panelSessionDatapoints.add(formattedTextFieldSessionBeatFreq, "flowx,cell 3 0");
		
		JLabel lblSessionDuration = new JLabel("Duration");
		panelSessionDatapoints.add(lblSessionDuration, "cell 4 0,alignx trailing");
		
		JFormattedTextField formattedTextFieldSessionBeatDuration = new JFormattedTextField();
		formattedTextFieldSessionBeatDuration.setColumns(5);
		formattedTextFieldSessionBeatDuration.setHorizontalAlignment(SwingConstants.LEFT);
		panelSessionDatapoints.add(formattedTextFieldSessionBeatDuration, "flowx,cell 5 0");
		
		JScrollPane scrollPaneSessionDatapoints = new JScrollPane();
		panelSessionDatapoints.add(scrollPaneSessionDatapoints, "cell 0 1 3 1,grow");
		JList listSessionDatapoints = new JList(mockupDatapointsList);
		scrollPaneSessionDatapoints.setViewportView(listSessionDatapoints);
		listSessionDatapoints.setValueIsAdjusting(true);
		listSessionDatapoints.setSelectedIndex(4);
		listSessionDatapoints.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listSessionDatapoints.setBorder(null);
		listSessionDatapoints.setVisibleRowCount(4);
		
		JButton btnSessionDatapointsSave = new JButton("Save");
		panelSessionDatapoints.add(btnSessionDatapointsSave, "cell 3 1,alignx center,aligny bottom");
		
		JButton btnSessionDatapointsDelete = new JButton("Delete");
		panelSessionDatapoints.add(btnSessionDatapointsDelete, "cell 4 1,alignx center,aligny bottom");
		
		JLabel lblSessionBeatHz = new JLabel("Hz");
		panelSessionDatapoints.add(lblSessionBeatHz, "cell 3 0");
		
		JLabel lblSessionDurationS = new JLabel("s");
		panelSessionDatapoints.add(lblSessionDurationS, "cell 5 0");
				
	}
}
