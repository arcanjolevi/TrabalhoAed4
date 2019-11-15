package window;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class Window extends JFrame {

	private JPanel contentPane;
	private JTextField pathToFileTextField;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window frame = new Window();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Window() {
		setBackground(Color.LIGHT_GRAY);
		setTitle("Árvore Trie");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 730, 460);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{5, 0, 5, 0};
		gbl_contentPane.rowHeights = new int[]{5, 146, 301, 5, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JPanel superiorPane = new JPanel();
		superiorPane.setBackground(Color.LIGHT_GRAY);
		superiorPane.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GridBagConstraints gbc_superiorPane = new GridBagConstraints();
		gbc_superiorPane.insets = new Insets(0, 0, 5, 5);
		gbc_superiorPane.fill = GridBagConstraints.HORIZONTAL;
		gbc_superiorPane.gridx = 1;
		gbc_superiorPane.gridy = 1;
		contentPane.add(superiorPane, gbc_superiorPane);
		GridBagLayout gbl_superiorPane = new GridBagLayout();
		gbl_superiorPane.columnWidths = new int[]{5, 0, 5, 0};
		gbl_superiorPane.rowHeights = new int[]{5, 0, 0, 5, 0, 0};
		gbl_superiorPane.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_superiorPane.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		superiorPane.setLayout(gbl_superiorPane);
		
		JButton buttonLoadStopWords = new JButton("Carregar StopWords");
		GridBagConstraints gbc_buttonLoadStopWords = new GridBagConstraints();
		gbc_buttonLoadStopWords.insets = new Insets(0, 0, 5, 5);
		gbc_buttonLoadStopWords.gridx = 1;
		gbc_buttonLoadStopWords.gridy = 1;
		superiorPane.add(buttonLoadStopWords, gbc_buttonLoadStopWords);
		
		JButton buttonLoadWords = new JButton("Carregar Palavras");
		GridBagConstraints gbc_buttonLoadWords = new GridBagConstraints();
		gbc_buttonLoadWords.insets = new Insets(0, 0, 5, 5);
		gbc_buttonLoadWords.gridx = 1;
		gbc_buttonLoadWords.gridy = 2;
		superiorPane.add(buttonLoadWords, gbc_buttonLoadWords);
		
		JPanel pathToFilePane = new JPanel();
		pathToFilePane.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_pathToFilePane = new GridBagConstraints();
		gbc_pathToFilePane.insets = new Insets(0, 0, 5, 5);
		gbc_pathToFilePane.fill = GridBagConstraints.BOTH;
		gbc_pathToFilePane.gridx = 1;
		gbc_pathToFilePane.gridy = 3;
		superiorPane.add(pathToFilePane, gbc_pathToFilePane);
		GridBagLayout gbl_pathToFilePane = new GridBagLayout();
		gbl_pathToFilePane.columnWidths = new int[]{5, 0, 0, 5, 0};
		gbl_pathToFilePane.rowHeights = new int[]{5, 0, 0, 5, 0};
		gbl_pathToFilePane.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_pathToFilePane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		pathToFilePane.setLayout(gbl_pathToFilePane);
		
		JLabel labelPathToFile = new JLabel("Caminho do Arquivo");
		GridBagConstraints gbc_labelPathToFile = new GridBagConstraints();
		gbc_labelPathToFile.fill = GridBagConstraints.HORIZONTAL;
		gbc_labelPathToFile.insets = new Insets(0, 0, 5, 5);
		gbc_labelPathToFile.gridx = 1;
		gbc_labelPathToFile.gridy = 1;
		pathToFilePane.add(labelPathToFile, gbc_labelPathToFile);
		
		pathToFileTextField = new JTextField();
		GridBagConstraints gbc_pathToFileTextField = new GridBagConstraints();
		gbc_pathToFileTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_pathToFileTextField.insets = new Insets(0, 0, 5, 5);
		gbc_pathToFileTextField.gridx = 1;
		gbc_pathToFileTextField.gridy = 2;
		pathToFilePane.add(pathToFileTextField, gbc_pathToFileTextField);
		pathToFileTextField.setColumns(10);
		
		JButton buttonChooseFile = new JButton("Escolher Arquivo");
		GridBagConstraints gbc_buttonChooseFile = new GridBagConstraints();
		gbc_buttonChooseFile.insets = new Insets(0, 0, 5, 5);
		gbc_buttonChooseFile.gridx = 2;
		gbc_buttonChooseFile.gridy = 2;
		pathToFilePane.add(buttonChooseFile, gbc_buttonChooseFile);
		
		JPanel inferiorPane = new JPanel();
		inferiorPane.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_inferiorPane = new GridBagConstraints();
		gbc_inferiorPane.insets = new Insets(0, 0, 5, 5);
		gbc_inferiorPane.fill = GridBagConstraints.BOTH;
		gbc_inferiorPane.gridx = 1;
		gbc_inferiorPane.gridy = 2;
		contentPane.add(inferiorPane, gbc_inferiorPane);
		GridBagLayout gbl_inferiorPane = new GridBagLayout();
		gbl_inferiorPane.columnWidths = new int[]{0, 0};
		gbl_inferiorPane.rowHeights = new int[]{132, 51, 0};
		gbl_inferiorPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_inferiorPane.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		inferiorPane.setLayout(gbl_inferiorPane);
		
		JPanel screenPane = new JPanel();
		screenPane.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		screenPane.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_screenPane = new GridBagConstraints();
		gbc_screenPane.insets = new Insets(0, 0, 5, 0);
		gbc_screenPane.fill = GridBagConstraints.BOTH;
		gbc_screenPane.gridx = 0;
		gbc_screenPane.gridy = 0;
		inferiorPane.add(screenPane, gbc_screenPane);
		GridBagLayout gbl_screenPane = new GridBagLayout();
		gbl_screenPane.columnWidths = new int[]{0, 0};
		gbl_screenPane.rowHeights = new int[]{0, 0};
		gbl_screenPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_screenPane.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		screenPane.setLayout(gbl_screenPane);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		screenPane.add(scrollPane, gbc_scrollPane);
		
		JTextArea screenTextArea = new JTextArea();
		screenTextArea.setEditable(false);
		scrollPane.setViewportView(screenTextArea);
		
		JPanel buttonsPane = new JPanel();
		buttonsPane.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		buttonsPane.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_buttonsPane = new GridBagConstraints();
		gbc_buttonsPane.fill = GridBagConstraints.BOTH;
		gbc_buttonsPane.gridx = 0;
		gbc_buttonsPane.gridy = 1;
		inferiorPane.add(buttonsPane, gbc_buttonsPane);
		GridBagLayout gbl_buttonsPane = new GridBagLayout();
		gbl_buttonsPane.columnWidths = new int[]{0, 0};
		gbl_buttonsPane.rowHeights = new int[]{0, -16, 0, 0};
		gbl_buttonsPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_buttonsPane.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		buttonsPane.setLayout(gbl_buttonsPane);
		
		JLabel lblPalavraAConsultar = new JLabel("Palavra a consultar");
		GridBagConstraints gbc_lblPalavraAConsultar = new GridBagConstraints();
		gbc_lblPalavraAConsultar.insets = new Insets(0, 0, 5, 0);
		gbc_lblPalavraAConsultar.gridx = 0;
		gbc_lblPalavraAConsultar.gridy = 0;
		buttonsPane.add(lblPalavraAConsultar, gbc_lblPalavraAConsultar);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 0;
		gbc_textField.gridy = 1;
		buttonsPane.add(textField, gbc_textField);
		textField.setColumns(10);
		
		JPanel functionButtonsPane = new JPanel();
		functionButtonsPane.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_functionButtonsPane = new GridBagConstraints();
		gbc_functionButtonsPane.fill = GridBagConstraints.BOTH;
		gbc_functionButtonsPane.gridx = 0;
		gbc_functionButtonsPane.gridy = 2;
		buttonsPane.add(functionButtonsPane, gbc_functionButtonsPane);
		GridBagLayout gbl_functionButtonsPane = new GridBagLayout();
		gbl_functionButtonsPane.columnWidths = new int[]{5, 0, 0, 0, 5, 0};
		gbl_functionButtonsPane.rowHeights = new int[]{5, 0, 5, 0};
		gbl_functionButtonsPane.columnWeights = new double[]{0.0, 1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_functionButtonsPane.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		functionButtonsPane.setLayout(gbl_functionButtonsPane);
		
		JButton btnConsultar = new JButton("Consultar");
		GridBagConstraints gbc_btnConsultar = new GridBagConstraints();
		gbc_btnConsultar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnConsultar.insets = new Insets(0, 0, 5, 5);
		gbc_btnConsultar.gridx = 1;
		gbc_btnConsultar.gridy = 1;
		functionButtonsPane.add(btnConsultar, gbc_btnConsultar);
		
		JButton btnImprimirDicionrio = new JButton("Imprimir Dicionário");
		GridBagConstraints gbc_btnImprimirDicionrio = new GridBagConstraints();
		gbc_btnImprimirDicionrio.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnImprimirDicionrio.insets = new Insets(0, 0, 5, 5);
		gbc_btnImprimirDicionrio.gridx = 2;
		gbc_btnImprimirDicionrio.gridy = 1;
		functionButtonsPane.add(btnImprimirDicionrio, gbc_btnImprimirDicionrio);
		
		JButton btnConsultarSemelhante = new JButton("Consultar Semelhante");
		GridBagConstraints gbc_btnConsultarSemelhante = new GridBagConstraints();
		gbc_btnConsultarSemelhante.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnConsultarSemelhante.insets = new Insets(0, 0, 5, 5);
		gbc_btnConsultarSemelhante.gridx = 3;
		gbc_btnConsultarSemelhante.gridy = 1;
		functionButtonsPane.add(btnConsultarSemelhante, gbc_btnConsultarSemelhante);
	}

}
