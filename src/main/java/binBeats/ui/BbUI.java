package main.java.binBeats.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JSlider;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JInternalFrame;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JScrollPane;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.border.CompoundBorder;

public class BbUI {

	private JFrame frmBinbeats;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmBinbeats = new JFrame();
		frmBinbeats.setTitle("BinBeats");
		frmBinbeats.setBounds(100, 100, 476, 307);
		frmBinbeats.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frmBinbeats.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panelPlayer = new JPanel();
		tabbedPane.addTab("Simple Player", null, panelPlayer, null);
		panelPlayer.setLayout(new MigLayout("", "[][][grow][grow][]", "[][][][][][][grow]"));
		
		JLabel lblPlayerPreset = new JLabel("Preset");
		panelPlayer.add(lblPlayerPreset, "cell 1 0,alignx trailing");
		
		String[] mockupPlayerAtmosphereSelection = {"<new>", "Reasoning - Beta", "Relaxation - Alpha", "Meditation - Theta", "Deep Sleep - Delta"};
		JComboBox comboBoxPlayerPresetSelection = new JComboBox(mockupPlayerAtmosphereSelection);
		panelPlayer.add(comboBoxPlayerPresetSelection, "flowx,cell 2 0,growx");
		
		JButton btnSave_2 = new JButton("Save");
		panelPlayer.add(btnSave_2, "flowx,cell 3 0");
		
		JButton btnPlay_1 = new JButton("Play");
		panelPlayer.add(btnPlay_1, "cell 3 0");
		
		JLabel lblBackground_1 = new JLabel("Background");
		panelPlayer.add(lblBackground_1, "cell 1 1,alignx trailing");
		
		// TODO: Parse from Background Audio List
		String[] bgSelectionMockup = {"(none)", "Pink Noise", "White Noise", "Stream", "Rain"};
		JComboBox comboBoxPlayerBgSelection = new JComboBox(bgSelectionMockup);
		panelPlayer.add(comboBoxPlayerBgSelection, "cell 2 1,growx");
		
		JLabel lblCarrierFrequency = new JLabel("Carrier Frequency");
		panelPlayer.add(lblCarrierFrequency, "cell 1 2,alignx trailing");
		
		JFormattedTextField formattedTextFieldPlayerCarrier = new JFormattedTextField();
		formattedTextFieldPlayerCarrier.setColumns(4);
		panelPlayer.add(formattedTextFieldPlayerCarrier, "flowx,cell 2 2,alignx left");
		
		JLabel lblHz_2 = new JLabel("Hz");
		panelPlayer.add(lblHz_2, "cell 2 2");
		
		JLabel label_1 = new JLabel("20");
		panelPlayer.add(label_1, "cell 0 3,alignx right");
		
		JSlider slider = new JSlider();
		slider.setMaximum(1500);
		slider.setMinorTickSpacing(1);
		slider.setMajorTickSpacing(10);
		slider.setValue(300);
		slider.setMinimum(20);
		panelPlayer.add(slider, "cell 1 3 3 1,growx");
		
		JLabel label_2 = new JLabel("1500");
		panelPlayer.add(label_2, "cell 4 3,alignx left");
		
		JLabel lblBeatFrequency = new JLabel("Beat Frequency");
		panelPlayer.add(lblBeatFrequency, "cell 1 4,alignx trailing");
		
		JFormattedTextField formattedTextField_2 = new JFormattedTextField();
		formattedTextField_2.setColumns(4);
		panelPlayer.add(formattedTextField_2, "flowx,cell 2 4,alignx left");
		
		JLabel lblHz_3 = new JLabel("Hz");
		panelPlayer.add(lblHz_3, "cell 2 4");
		
		JLabel label_3 = new JLabel("0.5");
		panelPlayer.add(label_3, "cell 0 5,alignx right");
		
		JSlider slider_1 = new JSlider();
		slider_1.setMajorTickSpacing(10);
		slider_1.setMinorTickSpacing(1);
		slider_1.setMaximum(300);
		slider_1.setMinimum(5);
		panelPlayer.add(slider_1, "cell 1 5 3 1,growx");
		
		JLabel label_4 = new JLabel("30");
		panelPlayer.add(label_4, "cell 4 5,alignx left");
		
		JPanel panel = new JPanel();
		panel.setBorder(null);
		panelPlayer.add(panel, "cell 0 6 5 1,grow");
		panel.setLayout(new MigLayout("", "[][grow][][][grow]", "[][]"));
		
		JLabel lblBackgroundVolume_1 = new JLabel("Background Volume");
		panel.add(lblBackgroundVolume_1, "cell 0 0,alignx trailing");
		
		JFormattedTextField formattedTextFieldPlayerBgVol = new JFormattedTextField();
		formattedTextFieldPlayerBgVol.setColumns(4);
		panel.add(formattedTextFieldPlayerBgVol, "flowx,cell 1 0,alignx left");
		
		JLabel label_5 = new JLabel("%");
		panel.add(label_5, "cell 1 0");
		
		JLabel lblBeatVolume_1 = new JLabel("Beat Volume");
		panel.add(lblBeatVolume_1, "cell 3 0,alignx trailing");
		
		JFormattedTextField formattedTextFieldPlayerBeatVol = new JFormattedTextField();
		formattedTextFieldPlayerBeatVol.setColumns(4);
		panel.add(formattedTextFieldPlayerBeatVol, "flowx,cell 4 0,alignx left");
		
		JLabel label_6 = new JLabel("%");
		panel.add(label_6, "cell 4 0");
		
		JSlider sliderPlayerBgVol = new JSlider();
		sliderPlayerBgVol.setMajorTickSpacing(10);
		sliderPlayerBgVol.setMinorTickSpacing(1);
		panel.add(sliderPlayerBgVol, "cell 0 1 2 1,growx");
		
		JSlider sliderPlayerBeatVol = new JSlider();
		panel.add(sliderPlayerBeatVol, "cell 3 1 2 1,growx");
		
		JPanel panelSession = new JPanel();
		tabbedPane.addTab("Session", null, panelSession, null);
		panelSession.setLayout(new MigLayout("", "[][grow][][][][]", "[][][][][grow]"));
		
		JLabel lblSession = new JLabel("Session");
		panelSession.add(lblSession, "cell 0 0,alignx trailing");
		
		// TODO: Parse from sessions
		String[] sessionMockup = {"<new>", "Meditation", "Sleep Aid", "Wakeup"};
		JComboBox comboBoxSessionSelection = new JComboBox(sessionMockup);
		panelSession.add(comboBoxSessionSelection, "cell 1 0,growx");
		
		JButton btnSave = new JButton("Save");
		panelSession.add(btnSave, "cell 2 0,alignx right");
		
		JButton btnPlay = new JButton("Play");
		panelSession.add(btnPlay, "cell 3 0,alignx center");
		
		JButton btnExport = new JButton("Export");
		panelSession.add(btnExport, "cell 4 0,alignx left");
		
		JLabel lblBackground = new JLabel("Background");
		panelSession.add(lblBackground, "cell 0 1,alignx trailing");
		
		JComboBox comboBoxBgSelection = new JComboBox(bgSelectionMockup);
		panelSession.add(comboBoxBgSelection, "cell 1 1,growx");
		
		JLabel lblBackgroundVolume = new JLabel("Background Volume");
		panelSession.add(lblBackgroundVolume, "cell 0 2,alignx trailing");
		
		JFormattedTextField bogVolField = new JFormattedTextField();
		bogVolField.setHorizontalAlignment(SwingConstants.LEFT);
		lblBackgroundVolume.setLabelFor(bogVolField);
		bogVolField.setText("50");
		bogVolField.setColumns(3);
		panelSession.add(bogVolField, "flowx,cell 1 2,alignx left");
		
		Component horizontalGlue = Box.createHorizontalGlue();
		panelSession.add(horizontalGlue, "cell 4 2");
		
		JLabel lblBeatVolume = new JLabel("Beat Volume");
		panelSession.add(lblBeatVolume, "cell 3 2,alignx trailing");
		
		JFormattedTextField beatVolField = new JFormattedTextField();
		beatVolField.setHorizontalAlignment(SwingConstants.LEFT);
		lblBeatVolume.setLabelFor(beatVolField);
		beatVolField.setText("50");
		beatVolField.setColumns(3);
		panelSession.add(beatVolField, "flowx,cell 4 2,alignx left");
		
		JLabel label = new JLabel("%");
		panelSession.add(label, "cell 4 2");
		
		JSlider bgVolSlider = new JSlider();
		panelSession.add(bgVolSlider, "cell 0 3 2 1");
		
		JSlider beatVolSlider = new JSlider();
		panelSession.add(beatVolSlider, "cell 3 3 2 1");
		
		JLabel lblPercent = new JLabel("%");
		panelSession.add(lblPercent, "cell 1 2");
		
		JPanel panelDatapoints = new JPanel();
		panelDatapoints.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Datapoints", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelSession.add(panelDatapoints, "cell 0 4 5 1,grow");
		panelDatapoints.setLayout(new MigLayout("", "[][][][][][][]", "[][grow]"));
		
		JLabel lblCarrier = new JLabel("Carrier");
		panelDatapoints.add(lblCarrier, "cell 0 0,alignx left");
		
		JFormattedTextField formattedTextField = new JFormattedTextField();
		formattedTextField.setHorizontalAlignment(SwingConstants.LEFT);
		formattedTextField.setColumns(5);
		panelDatapoints.add(formattedTextField, "flowx,cell 1 0");
		
		JLabel lblHz = new JLabel("Hz");
		panelDatapoints.add(lblHz, "cell 1 0");
		
		// TODO: parse from real datapoint list
		String[] mockupDatapointsList = {"P1 - C 150 - B 12 - D 300", "P2 - C 150 - B 7 - D 300", "P3 - C 130 - B 0.5 - D 300", "P4 - C 130 - B 0.5 - D 300", "<new>"};
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		panelDatapoints.add(horizontalStrut, "flowx,cell 2 0");
		
		JLabel lblBeat = new JLabel("Beat");
		panelDatapoints.add(lblBeat, "cell 2 0,alignx right");
		
		JFormattedTextField formattedTextField_1 = new JFormattedTextField();
		formattedTextField_1.setHorizontalAlignment(SwingConstants.LEFT);
		formattedTextField_1.setColumns(5);
		panelDatapoints.add(formattedTextField_1, "flowx,cell 3 0");
		
		JLabel lblDuration = new JLabel("Duration");
		panelDatapoints.add(lblDuration, "cell 4 0,alignx trailing");
		
		JFormattedTextField formattedTextFieldDuration = new JFormattedTextField();
		formattedTextFieldDuration.setColumns(5);
		formattedTextFieldDuration.setHorizontalAlignment(SwingConstants.LEFT);
		panelDatapoints.add(formattedTextFieldDuration, "flowx,cell 5 0");
		
		JScrollPane scrollPane = new JScrollPane();
		panelDatapoints.add(scrollPane, "cell 0 1 3 1,grow");
		JList list = new JList(mockupDatapointsList);
		scrollPane.setViewportView(list);
		list.setValueIsAdjusting(true);
		list.setSelectedIndex(4);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setBorder(null);
		list.setVisibleRowCount(4);
		
		JButton btnSave_1 = new JButton("Save");
		panelDatapoints.add(btnSave_1, "cell 3 1,alignx center,aligny bottom");
		
		JButton btnDelete = new JButton("Delete");
		panelDatapoints.add(btnDelete, "cell 4 1,alignx center,aligny bottom");
		
		JLabel lblHz_1 = new JLabel("Hz");
		panelDatapoints.add(lblHz_1, "cell 3 0");
		
	
		JLabel lblS = new JLabel("s");
		panelDatapoints.add(lblS, "cell 5 0");
		
		
		
	}

}
