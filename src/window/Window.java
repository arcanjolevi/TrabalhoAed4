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
import generic.Listener;
import generic.Speaker;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Window extends JFrame implements Listener,Speaker{
    
    private JPanel contentPane;
    private JTextField pathToFileTextField;
    private JTextField textFieldPalavraAConsultar;
    private JPanel superiorPane;
    private JButton buttonLoadStopWords;
    private JButton buttonLoadWords;
    private JPanel pathToFilePane;
    private JLabel labelPathToFile;
    private JButton buttonChooseFile;
    private JPanel inferiorPane;
    private JPanel screenPane;
    private JScrollPane scrollPane;
    private JTextArea screenTextArea;
    private JPanel buttonsPane;
    private JLabel labelPalavraAConsultar;
    private JPanel functionButtonsPane;
    private JButton buttonConsultar;
    private JButton buttonImprimirDicionrio;
    private JButton buttonConsultarSemelhante;
    private String stringToPrint;
    private ArrayList<Listener> listeners;
	
    public void startWindow(Object listener) {
        try {
            Window frame = new Window();
            frame.subscribe(listener);
            frame.setVisible(true);
        } catch (Exception e) {}
    }

	//Window constructor
	public Window() {
                this.stringToPrint = "";
                this.listeners     = new ArrayList<Listener>();
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
		
		superiorPane = new JPanel();
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
		
		buttonLoadStopWords = new JButton("Carregar StopWords");
		GridBagConstraints gbc_buttonLoadStopWords = new GridBagConstraints();
		gbc_buttonLoadStopWords.insets = new Insets(0, 0, 5, 5);
		gbc_buttonLoadStopWords.gridx = 1;
		gbc_buttonLoadStopWords.gridy = 1;
		superiorPane.add(buttonLoadStopWords, gbc_buttonLoadStopWords);
		
		buttonLoadWords = new JButton("Carregar Palavras");
		GridBagConstraints gbc_buttonLoadWords = new GridBagConstraints();
		gbc_buttonLoadWords.insets = new Insets(0, 0, 5, 5);
		gbc_buttonLoadWords.gridx = 1;
		gbc_buttonLoadWords.gridy = 2;
		superiorPane.add(buttonLoadWords, gbc_buttonLoadWords);
		
		pathToFilePane = new JPanel();
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
		
		labelPathToFile = new JLabel("Caminho do Arquivo");
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
		
		buttonChooseFile = new JButton("Escolher Arquivo");
		GridBagConstraints gbc_buttonChooseFile = new GridBagConstraints();
		gbc_buttonChooseFile.insets = new Insets(0, 0, 5, 5);
		gbc_buttonChooseFile.gridx = 2;
		gbc_buttonChooseFile.gridy = 2;
		pathToFilePane.add(buttonChooseFile, gbc_buttonChooseFile);
		
		inferiorPane = new JPanel();
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
		
		screenPane = new JPanel();
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
		
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		screenPane.add(scrollPane, gbc_scrollPane);
		
		screenTextArea = new JTextArea();
		screenTextArea.setEditable(false);
		scrollPane.setViewportView(screenTextArea);
		
		buttonsPane = new JPanel();
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
		
		labelPalavraAConsultar = new JLabel("Palavra a consultar");
		GridBagConstraints gbc_labelPalavraAConsultar = new GridBagConstraints();
		gbc_labelPalavraAConsultar.insets = new Insets(0, 0, 5, 0);
		gbc_labelPalavraAConsultar.gridx = 0;
		gbc_labelPalavraAConsultar.gridy = 0;
		buttonsPane.add(labelPalavraAConsultar, gbc_labelPalavraAConsultar);
		
		textFieldPalavraAConsultar = new JTextField();
		GridBagConstraints gbc_textFieldPalavraAConsultar = new GridBagConstraints();
		gbc_textFieldPalavraAConsultar.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldPalavraAConsultar.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldPalavraAConsultar.gridx = 0;
		gbc_textFieldPalavraAConsultar.gridy = 1;
		buttonsPane.add(textFieldPalavraAConsultar, gbc_textFieldPalavraAConsultar);
		textFieldPalavraAConsultar.setColumns(10);
		
		functionButtonsPane = new JPanel();
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
		
		buttonConsultar = new JButton("Consultar");
		GridBagConstraints gbc_buttonConsultar = new GridBagConstraints();
		gbc_buttonConsultar.fill = GridBagConstraints.HORIZONTAL;
		gbc_buttonConsultar.insets = new Insets(0, 0, 5, 5);
		gbc_buttonConsultar.gridx = 1;
		gbc_buttonConsultar.gridy = 1;
                buttonConsultar.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent arg0) {
                        speak("consultButtonPressed,"+textFieldPalavraAConsultar.getText());
                    }
                });
		functionButtonsPane.add(buttonConsultar, gbc_buttonConsultar);
		
		buttonImprimirDicionrio = new JButton("Imprimir Dicionário");
		GridBagConstraints gbc_buttonImprimirDicionrio = new GridBagConstraints();
		gbc_buttonImprimirDicionrio.fill = GridBagConstraints.HORIZONTAL;
		gbc_buttonImprimirDicionrio.insets = new Insets(0, 0, 5, 5);
		gbc_buttonImprimirDicionrio.gridx = 2;
		gbc_buttonImprimirDicionrio.gridy = 1;
                buttonImprimirDicionrio.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent arg0) {
                        speak("printButtonPressed,");
                    }
                });
		functionButtonsPane.add(buttonImprimirDicionrio, gbc_buttonImprimirDicionrio);
		
		buttonConsultarSemelhante = new JButton("Consultar Semelhante");
		GridBagConstraints gbc_buttonConsultarSemelhante = new GridBagConstraints();
		gbc_buttonConsultarSemelhante.fill = GridBagConstraints.HORIZONTAL;
                gbc_buttonConsultarSemelhante.insets = new Insets(0, 0, 5, 5);
            gbc_buttonConsultarSemelhante.gridx = 3;
            gbc_buttonConsultarSemelhante.gridy = 1;
            functionButtonsPane.add(buttonConsultarSemelhante, gbc_buttonConsultarSemelhante);
	}
        
    public void printDerivatedWords(String subWord){
        String aux1[] = this.stringToPrint.split("\n");
        String aux2 = "";
        int i = 0;
        for(String a:aux1){
            if(i == 10)
                break;
            aux2 += (i+1) + ":" + a + "\n";
            i++;
        }
        
        JFrame aux = new JFrame("Palavras derivadas de "+subWord);
        aux.setSize(640, 430);
        aux.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        aux.setLocationRelativeTo(null);
        
        scrollPane = new JScrollPane();
        aux.add(scrollPane, BorderLayout.CENTER);
        
        screenTextArea = new JTextArea();
        screenTextArea.setEditable(false);
        screenTextArea.setText(aux2);
        scrollPane.setViewportView(screenTextArea);
        
        aux.setVisible(true);
        aux.setEnabled(true);
        
        stringToPrint = "";
    }
        
    public void printDictionary(){
        JOptionPane.showInternalMessageDialog(null, this.stringToPrint);
        JFrame aux = new JFrame("Dicionário");
        aux.setSize(640, 430);
        aux.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        aux.setLocationRelativeTo(null);
        
        scrollPane = new JScrollPane();
        aux.add(scrollPane, BorderLayout.CENTER);
        
        screenTextArea = new JTextArea();
        screenTextArea.setEditable(false);
        screenTextArea.setText(stringToPrint);
        scrollPane.setViewportView(screenTextArea);
        
        aux.setVisible(true);
        aux.setEnabled(true);
        
        stringToPrint = "";
    }

    @Override
    public void listen(String msg) {
        String aux[] = msg.split(",");
        
        if(aux[0].compareTo("newWord") == 0){
            if(!this.stringToPrint.contains(aux[1])){
                this.stringToPrint += aux[1];
            }
        }
        
        if(aux[0].compareTo("newDerivatedWord") == 0){
            if(!this.stringToPrint.contains(aux[1])){
                this.stringToPrint += aux[1];
            }
        }
        
        if(aux[0].compareTo("dictionaryEnd") == 0){
            this.printDictionary();
        }
        
        if(aux[0].compareTo("derivatedWordEnd") == 0){
            this.printDerivatedWords(aux[1]);
        }
    }

    /* Method that subscribe a listener to this class
     * Input:        Listenter to be subscribed
     * Return:       None
     * Precondition: None
    */
    @Override
    public void subscribe(Object listener) throws Exception {
        if(listener instanceof Listener){
            this.listeners.add((Listener) listener);
        }else{
            throw new Exception("A classe não é um listener");
        }
    }

    @Override
    public void unscribe(Object listener) throws Exception {
        if(listener instanceof Listener){
            this.listeners.remove((Listener) listener);
        }else{
            throw new Exception("A classe não é um listener");
        }
    }

    @Override
    public void speak(String msg) {
        for(Listener l:this.listeners){
            l.listen(msg);
        }
    }
}
