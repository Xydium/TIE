package engine.utility;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import engine.utility.Log.LogLevel;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class LogWindow extends JFrame {

	private static final long serialVersionUID = -7439835121174490802L;
	private JPanel contentPane;
	private JTextArea textArea;

	/**
	 * Create the frame.
	 */
	public LogWindow() {

		setTitle("Log");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		textArea = new JTextArea();
		textArea.setRows(20);
		scrollPane.setViewportView(textArea);
		setVisible(true);
	}

	public void updateConsole(LogLevel level, String text) {
		if(level.ordinal() >= Log.getLogLevel().ordinal()) {
			textArea.append(level.tag + " " + text + System.lineSeparator());
		}
	}
}
