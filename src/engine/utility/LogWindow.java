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
import javax.swing.text.DefaultCaret;

import engine.utility.Log.LogLevel;

/**
 * Window that displays output from the log
 * 
 * @author Lenny Litvak
 * @author Chris Jerrett
 *
 */
public class LogWindow extends JFrame
{
	private static final long serialVersionUID = -7439835121174490802L;
	
	private JTextArea textArea;

	/**
	 * Creates a new Log Window to display output
	 * from the Log
	 */
	public LogWindow()
	{
		super("Output Log");
		setBounds(100, 100, 700, 500);
		setResizable(false);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		JPanel contentPane = (JPanel)getContentPane();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		final JLabel lblLogLevel = new JLabel("Log Level: " + Log.getLogLevel());
		contentPane.add(lblLogLevel, BorderLayout.NORTH);

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane);

		textArea = new JTextArea();
		DefaultCaret caret = (DefaultCaret)textArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		textArea.setRows(30);
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);

		final JSlider slider = new JSlider();
		scrollPane.setColumnHeaderView(slider);
		slider.setMinimum(0);
		slider.setMaximum(Log.LogLevel.values().length - 1);
		slider.setValue(Log.getLogLevel().ordinal());
		
		slider.addChangeListener(new ChangeListener()
		{
			public void stateChanged(ChangeEvent arg0)
			{
				try
				{
					Log.setLogLevel(LogLevel.values()[slider.getValue()]);
				} 
				catch (Exception e)
				{
				}

				lblLogLevel.setText("Log Level: " + Log.getLogLevel().name());
			}
		});
		
		setVisible(true);
	}

	/**
	 * Writes a line of text to the console
	 * 
	 * @param text the text to write
	 */
	public void writeLine(String text)
	{
		textArea.append(" " + text + "\n");
	}
}
