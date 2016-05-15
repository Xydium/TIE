package engine.utility;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import engine.utility.Log.LogLevel;

public class LogWindow extends JFrame {

	private static final long serialVersionUID = -7439835121174490802L;
	private JPanel contentPane;
	private JTextArea textArea;
	private JLabel lblLogLevel;
	private JSlider slider;

	/**
	 * Create the frame.
	 */
	public LogWindow() {
		super();
		setTitle("Log");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		lblLogLevel = new JLabel("Log Level: " + Log.getLogLevel());
		contentPane.add(lblLogLevel, BorderLayout.NORTH);

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane);

		textArea = new JTextArea();
		textArea.setRows(20);
		scrollPane.setViewportView(textArea);

		slider = new JSlider();
		scrollPane.setColumnHeaderView(slider);
		int sliderSize = slider.getMaximum() - slider.getMinimum();
		int numStates = Log.LogLevel.values().length + 1;

		double sectionSize = sliderSize/numStates;
		System.out.println("Section size " + sectionSize);
		slider.setValue((int)(sectionSize * Log.getLogLevel().ordinal()));
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int sliderSize = slider.getMaximum() - slider.getMinimum();
				int numStates = Log.LogLevel.values().length;
				double sectionSize = sliderSize/numStates;
				int state = (int) (slider.getValue() / sectionSize);
				try {
					Log.setLogLevel(LogLevel.values()[state]);
				} catch (Exception e) {
				}

				lblLogLevel.setText("Log Level: " + Log.getLogLevel().name());
			}
		});
		setVisible(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
	}

	public void updateConsole(LogLevel level, String text) {
		if(level.ordinal() <= Log.getLogLevel().ordinal()) {
			textArea.append(level.tag + " " + text + System.lineSeparator());
		}
	}
}
