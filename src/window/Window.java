package window;

import generic.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

//Class that create the window of the program
public class Window implements Listener, Speaker {

    private JFrame window;
    private JPanel contentPane;
    private JTextField textPathToFile;
    private JTextField textSubWordToSearch;
    private JPanel panelUp;
    private JLabel labelPathToFile;
    private JButton buttonChooseFile;
    private JButton buttonLoadWordsFile;
    private JButton buttonLoadStopWordsFile;
    private JPanel panelDown;
    private JLabel labelSubWordToSearch;
    private JPanel panelDownButtons;
    private JButton buttonConsultWord;
    private JButton buttonPrintDictionary;
    private String stringToPrint;
    private JButton buttonConsultSimilar;
    private JPanel panelMiddle;
    private JLabel labelWordToConsult;
    private JLabel labelDistance;
    private JTextField textWordToConsult;
    private JTextField textDistance;
    private ArrayList<Listener> listeners;

    //Window constructor
    public Window() {
        this.stringToPrint = "";
        this.listeners = new ArrayList<Listener>();
        this.window = new JFrame();

        this.window.setTitle("Trie");
        this.window.setBackground(Color.LIGHT_GRAY);
        this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.window.setBounds(100, 100, 703, 384);
        this.contentPane = new JPanel();
        this.contentPane.setBackground(Color.LIGHT_GRAY);
        this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.window.setContentPane(this.contentPane);
        this.window.setLocationRelativeTo(null);

        GridBagLayout gbl_contentPane = new GridBagLayout();
        gbl_contentPane.columnWidths = new int[]{0, 0};
        gbl_contentPane.rowHeights = new int[]{86, 50, 103, 0};
        gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
        gbl_contentPane.rowWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
        this.contentPane.setLayout(gbl_contentPane);

        panelUp = new JPanel();
        panelUp.setBackground(Color.LIGHT_GRAY);
        panelUp.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
        GridBagConstraints gbc_panelUp = new GridBagConstraints();
        gbc_panelUp.insets = new Insets(0, 0, 5, 0);
        gbc_panelUp.fill = GridBagConstraints.BOTH;
        gbc_panelUp.gridx = 0;
        gbc_panelUp.gridy = 0;
        this.contentPane.add(panelUp, gbc_panelUp);

        GridBagLayout gbl_panelUp = new GridBagLayout();
        gbl_panelUp.columnWidths = new int[]{5, 0, 0, 0, 5, 0};
        gbl_panelUp.rowHeights = new int[]{5, 0, 0, 0, 5, 0};
        gbl_panelUp.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
        gbl_panelUp.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        panelUp.setLayout(gbl_panelUp);

        labelPathToFile = new JLabel("Caminho do Arquivo");
        GridBagConstraints gbc_labelPathToFile = new GridBagConstraints();
        gbc_labelPathToFile.insets = new Insets(0, 0, 5, 5);
        gbc_labelPathToFile.gridx = 2;
        gbc_labelPathToFile.gridy = 1;
        panelUp.add(labelPathToFile, gbc_labelPathToFile);

        textPathToFile = new JTextField();
        textPathToFile.setFont(new Font("Dialog", Font.PLAIN, 16));
        GridBagConstraints gbc_textPathToFile = new GridBagConstraints();
        gbc_textPathToFile.insets = new Insets(0, 0, 5, 5);
        gbc_textPathToFile.fill = GridBagConstraints.HORIZONTAL;
        gbc_textPathToFile.gridx = 2;
        gbc_textPathToFile.gridy = 2;
        textPathToFile.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) { //Read the words file em enter is pressed
                if (e.getKeyChar() == 10 && !textPathToFile.getText().isEmpty()) {
                    readWordsFile();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) { //Set enabled or disabled the buttons loadStopWords and loadWords
                if (textPathToFile.getText().isEmpty()) {
                    buttonLoadStopWordsFile.setEnabled(false);
                    buttonLoadWordsFile.setEnabled(false);
                } else {
                    buttonLoadStopWordsFile.setEnabled(true);
                    buttonLoadWordsFile.setEnabled(true);
                }
            }
        });
        panelUp.add(textPathToFile, gbc_textPathToFile);
        textPathToFile.setColumns(10);

        buttonChooseFile = new JButton("Escolher arquivo");
        GridBagConstraints gbc_buttonChooseFile = new GridBagConstraints();
        gbc_buttonChooseFile.insets = new Insets(0, 0, 5, 5);
        gbc_buttonChooseFile.gridx = 1;
        gbc_buttonChooseFile.gridy = 3;
        buttonChooseFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) { //Open the new file selector
                selectFile();
            }
        });
        panelUp.add(buttonChooseFile, gbc_buttonChooseFile);

        buttonLoadWordsFile = new JButton("Carregar Arquivo de Palavras");
        GridBagConstraints gbc_buttonLoadWordsFile = new GridBagConstraints();
        gbc_buttonLoadWordsFile.insets = new Insets(0, 0, 5, 5);
        gbc_buttonLoadWordsFile.gridx = 2;
        gbc_buttonLoadWordsFile.gridy = 3;
        buttonLoadWordsFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) { //Read a new word file when the button is pressed
                readWordsFile();
            }
        });
        buttonLoadWordsFile.setEnabled(false);
        panelUp.add(buttonLoadWordsFile, gbc_buttonLoadWordsFile);

        buttonLoadStopWordsFile = new JButton("Carregar Stop Words");
        GridBagConstraints gbc_buttonLoadStopWordsFile = new GridBagConstraints();
        gbc_buttonLoadStopWordsFile.insets = new Insets(0, 0, 5, 5);
        gbc_buttonLoadStopWordsFile.gridx = 3;
        gbc_buttonLoadStopWordsFile.gridy = 3;
        buttonLoadStopWordsFile.setEnabled(false);
        buttonLoadStopWordsFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) { //Read a new stop words file when the button is pressed
                readStopWordsFile();
            }
        });
        panelUp.add(buttonLoadStopWordsFile, gbc_buttonLoadStopWordsFile);

        panelMiddle = new JPanel();
        panelMiddle.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
        panelMiddle.setBackground(Color.LIGHT_GRAY);
        GridBagConstraints gbc_panelMiddle = new GridBagConstraints();
        gbc_panelMiddle.insets = new Insets(0, 0, 5, 0);
        gbc_panelMiddle.fill = GridBagConstraints.BOTH;
        gbc_panelMiddle.gridx = 0;
        gbc_panelMiddle.gridy = 1;
        this.contentPane.add(panelMiddle, gbc_panelMiddle);
        GridBagLayout gbl_panelMiddle = new GridBagLayout();
        gbl_panelMiddle.columnWidths = new int[]{5, 259, 0, 193, 5, 0};
        gbl_panelMiddle.rowHeights = new int[]{5, 0, 5, 0};
        gbl_panelMiddle.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
        gbl_panelMiddle.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
        panelMiddle.setLayout(gbl_panelMiddle);

        labelWordToConsult = new JLabel("Palavra a consultar");
        GridBagConstraints gbc_labelPalavraAConsultar = new GridBagConstraints();
        gbc_labelPalavraAConsultar.insets = new Insets(0, 0, 5, 5);
        gbc_labelPalavraAConsultar.gridx = 1;
        gbc_labelPalavraAConsultar.gridy = 1;
        panelMiddle.add(labelWordToConsult, gbc_labelPalavraAConsultar);

        labelDistance = new JLabel("Distância");
        GridBagConstraints gbc_labelDistncia = new GridBagConstraints();
        gbc_labelDistncia.insets = new Insets(0, 0, 5, 5);
        gbc_labelDistncia.gridx = 2;
        gbc_labelDistncia.gridy = 1;
        panelMiddle.add(labelDistance, gbc_labelDistncia);

        textWordToConsult = new JTextField();
        GridBagConstraints gbc_textWordToConsult = new GridBagConstraints();
        gbc_textWordToConsult.insets = new Insets(0, 0, 0, 5);
        gbc_textWordToConsult.fill = GridBagConstraints.HORIZONTAL;
        gbc_textWordToConsult.gridx = 1;
        gbc_textWordToConsult.gridy = 2;
        panelMiddle.add(textWordToConsult, gbc_textWordToConsult);
        textWordToConsult.setColumns(10);

        textDistance = new JTextField();
        GridBagConstraints gbc_textDistance = new GridBagConstraints();
        gbc_textDistance.insets = new Insets(0, 0, 0, 5);
        gbc_textDistance.fill = GridBagConstraints.HORIZONTAL;
        gbc_textDistance.gridx = 2;
        gbc_textDistance.gridy = 2;
        panelMiddle.add(textDistance, gbc_textDistance);
        textDistance.setColumns(10);

        buttonConsultSimilar = new JButton("Consultar Semelhante");
        GridBagConstraints gbc_buttonConsultSimilar = new GridBagConstraints();
        gbc_buttonConsultSimilar.insets = new Insets(0, 0, 0, 5);
        gbc_buttonConsultSimilar.gridx = 3;
        gbc_buttonConsultSimilar.gridy = 2;
        panelMiddle.add(buttonConsultSimilar, gbc_buttonConsultSimilar);
        buttonConsultSimilar.setEnabled(false);
        buttonConsultSimilar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) { //Consult similar words when the button is pressed
                speak("buttonConsultSimilarWordsClicked," + textWordToConsult.getText() + "," + textDistance.getText());
            }
        });

        panelDown = new JPanel();
        panelDown.setBackground(Color.LIGHT_GRAY);
        panelDown.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
        GridBagConstraints gbc_panelDown = new GridBagConstraints();
        gbc_panelDown.fill = GridBagConstraints.BOTH;
        gbc_panelDown.gridx = 0;
        gbc_panelDown.gridy = 2;
        this.contentPane.add(panelDown, gbc_panelDown);
        GridBagLayout gbl_panelDown = new GridBagLayout();
        gbl_panelDown.columnWidths = new int[]{5, 0, 5, 0};
        gbl_panelDown.rowHeights = new int[]{5, 0, 0, 0, 5, 0};
        gbl_panelDown.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
        gbl_panelDown.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
        panelDown.setLayout(gbl_panelDown);

        labelSubWordToSearch = new JLabel("Sub-Palavra Para Pesquisar");
        GridBagConstraints gbc_labelSubWordToSearch = new GridBagConstraints();
        gbc_labelSubWordToSearch.insets = new Insets(0, 0, 5, 5);
        gbc_labelSubWordToSearch.gridx = 1;
        gbc_labelSubWordToSearch.gridy = 1;
        panelDown.add(labelSubWordToSearch, gbc_labelSubWordToSearch);

        textSubWordToSearch = new JTextField();
        textSubWordToSearch.setFont(new Font("Dialog", Font.PLAIN, 16));
        GridBagConstraints gbc_textSubWordToSearch = new GridBagConstraints();
        gbc_textSubWordToSearch.insets = new Insets(0, 0, 5, 5);
        gbc_textSubWordToSearch.fill = GridBagConstraints.HORIZONTAL;
        gbc_textSubWordToSearch.gridx = 1;
        gbc_textSubWordToSearch.gridy = 2;
        panelDown.add(textSubWordToSearch, gbc_textSubWordToSearch);
        textSubWordToSearch.setColumns(10);

        panelDownButtons = new JPanel();
        panelDownButtons.setBackground(Color.LIGHT_GRAY);
        GridBagConstraints gbc_panelDownButtons = new GridBagConstraints();
        gbc_panelDownButtons.insets = new Insets(0, 0, 5, 5);
        gbc_panelDownButtons.fill = GridBagConstraints.BOTH;
        gbc_panelDownButtons.gridx = 1;
        gbc_panelDownButtons.gridy = 3;
        panelDown.add(panelDownButtons, gbc_panelDownButtons);
        GridBagLayout gbl_panelDownButtons = new GridBagLayout();
        gbl_panelDownButtons.columnWidths = new int[]{5, 0, 0, 0, 5, 0};
        gbl_panelDownButtons.rowHeights = new int[]{5, 0, 5, 0};
        gbl_panelDownButtons.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
        gbl_panelDownButtons.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
        panelDownButtons.setLayout(gbl_panelDownButtons);

        buttonConsultWord = new JButton("Consultar Palavra");
        GridBagConstraints gbc_buttonConsultWord = new GridBagConstraints();
        gbc_buttonConsultWord.fill = GridBagConstraints.BOTH;
        gbc_buttonConsultWord.insets = new Insets(0, 0, 5, 5);
        gbc_buttonConsultWord.gridx = 1;
        gbc_buttonConsultWord.gridy = 1;
        buttonConsultWord.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) { //Consult a word when the button is pressed
                String aux = textSubWordToSearch.getText();
                if (aux.isEmpty()) {
                    aux = "NULL";
                }
                speak("buttonConsultWordClicked," + aux);
            }
        });
        buttonConsultWord.setEnabled(false);
        panelDownButtons.add(buttonConsultWord, gbc_buttonConsultWord);

        buttonPrintDictionary = new JButton("Imprimir Dicionário");
        GridBagConstraints gbc_buttonPrintDictionary = new GridBagConstraints();
        gbc_buttonPrintDictionary.fill = GridBagConstraints.BOTH;
        gbc_buttonPrintDictionary.insets = new Insets(0, 0, 5, 5);
        gbc_buttonPrintDictionary.gridx = 2;
        gbc_buttonPrintDictionary.gridy = 1;
        buttonPrintDictionary.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) { //Print the dictionary when the button is pressed
                speak("buttonPrintDictionaryClicked,");
            }
        });
        buttonPrintDictionary.setEnabled(false);
        panelDownButtons.add(buttonPrintDictionary, gbc_buttonPrintDictionary);

        this.window.setVisible(true);
    }

    /* Method that print all similar words to the window
     * Input:        None
     * Return:       None
     * Precondition: None
    */
    private void printSimilarWords() {
        String aux[] = this.stringToPrint.split("\n");
        String derivatedWords = "";
        int i = 0;
        for (String a : aux) {
            derivatedWords += (i + 1) + ":" + a + "\n";
            i++;
        }
        this.showOutPutWindow("Palavras Semelhantes", derivatedWords);
        this.stringToPrint = "";
    }

    /* Method that read a new StopWordsFile
     * Input:        None
     * Return:       None
     * Precondition: None
    */
    private void readStopWordsFile() {
        this.speak("buttonReadNewStopWordsFileClicked," + this.textPathToFile.getText());
    }
    
    /* Method that read a new WordsFile
     * Input:        None
     * Return:       None
     * Precondition: None
    */
    private void readWordsFile() {
        this.speak("buttonReadNewWordsFileClicked," + this.textPathToFile.getText());
    }
    
    /* Method that confirm a succeffuly read of a new file
     * Input:        None
     * Return:       None
     * Precondition: None
    */
    private void successfullyReadFile() {
        JOptionPane.showMessageDialog(null, "Arquivo lido com sucesso !", "Mensagem", JOptionPane.INFORMATION_MESSAGE);
        this.buttonConsultWord.setEnabled(true);
        this.buttonPrintDictionary.setEnabled(true);
        this.buttonConsultSimilar.setEnabled(true);
    }
    
    /* Method that confirm an error reading a new file
     * Input:        None
     * Return:       None
     * Precondition: None
    */
    private void errorReadingFile() {
        JOptionPane.showMessageDialog(null, "Erro ao ler arquivo !", "Mensagem", JOptionPane.ERROR_MESSAGE);
    }
    
    /* Method that print an unrecognized error in the Window
     * Input:        None
     * Return:       None
     * Precondition: None
    */
    private void printUnknownError(){
        JOptionPane.showMessageDialog(null, "Erro desconhecido !", "Mensagem", JOptionPane.ERROR_MESSAGE);
    }

    /* Method that open a file selector for the user
     * Input:        None
     * Return:       None
     * Precondition: None
    */
    private void selectFile() {
        JFileChooser j = new JFileChooser();
        String aux;
        j.showOpenDialog(null);
        try {
            aux = j.getSelectedFile().getAbsoluteFile().toString();
            this.textPathToFile.setText(aux);
            this.buttonLoadWordsFile.setEnabled(true);
            this.buttonLoadStopWordsFile.setEnabled(true);
            JOptionPane.showMessageDialog(null, "Arquivo selecionado com sucesso !", "Mensagem", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e2) {
            JOptionPane.showMessageDialog(null, "Arquivo não selecionado !", "Mensagem", JOptionPane.ERROR_MESSAGE);
        }
    }

    /* Method that show a outPut window with some text inside it
     * Input:        WindowName and message to print inside the window
     * Return:       None
     * Precondition: None
    */
    private void showOutPutWindow(String windowName, String msgToPrint) {
        JFrame showOutput = new JFrame(windowName);
        showOutput.setVisible(true);
        showOutput.setBackground(Color.LIGHT_GRAY);
        showOutput.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        showOutput.setBounds(100, 100, 500, 354);
        showOutput.setAlwaysOnTop(true);
        showOutput.setLocationRelativeTo(null);

        JPanel ContentPane = new JPanel();
        ContentPane.setBackground(Color.LIGHT_GRAY);
        ContentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        showOutput.setContentPane(ContentPane);
        GridBagLayout gbl_contentPane = new GridBagLayout();
        gbl_contentPane.columnWidths = new int[]{0, 0};
        gbl_contentPane.rowHeights = new int[]{0, 0};
        gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
        gbl_contentPane.rowWeights = new double[]{1.0, Double.MIN_VALUE};
        ContentPane.setLayout(gbl_contentPane);

        JScrollPane scrollPane = new JScrollPane();
        GridBagConstraints gbc_scrollPane = new GridBagConstraints();
        gbc_scrollPane.fill = GridBagConstraints.BOTH;
        gbc_scrollPane.gridx = 0;
        gbc_scrollPane.gridy = 0;
        ContentPane.add(scrollPane, gbc_scrollPane);

        JTextArea textOutput = new JTextArea();
        textOutput.setEditable(false);
        textOutput.setText(msgToPrint);
        scrollPane.setViewportView(textOutput);
    }

    /* Method that print a maximum of 10 derivated words to the window
     * Input:        None
     * Return:       None
     * Precondition: None
    */
    public void printDerivatedWords() {
        String aux[] = this.stringToPrint.split("\n");
        String derivatedWords = "";
        int i = 0;
        for (String a : aux) {
            if (i == 10) {
                break;
            }
            derivatedWords += (i + 1) + ":" + a + "\n";
            i++;
        }
        this.showOutPutWindow("Palavras derivadas", derivatedWords);
        this.stringToPrint = "";
    }

    /* Method that print the dictionary to the screen
     * Input:        None
     * Return:       None
     * Precondition: None
    */
    public void printDictionary() {
        String aux[] = this.stringToPrint.split("\n");
        String derivatedWords = "";
        int i = 0;
        for (String a : aux) {
            derivatedWords += (i + 1) + ":" + a + "\n";
            i++;
        }
        if(derivatedWords.isEmpty())
            derivatedWords = "";
        this.showOutPutWindow("Dicionário", derivatedWords);
        this.stringToPrint = "";
    }

    /* Method that listen a message from a speaker
     * Input:        Message listen
     * Return:       None
     * Precondition: None
     */
    @Override
    public void listen(String msg) {
        String aux[] = msg.split(",");

        if (aux[0].compareTo("newDictionaryWord") == 0) {
            if (!this.stringToPrint.contains(aux[1])) {
                this.stringToPrint += aux[1];
            }
        }
        else if (aux[0].compareTo("newDerivatedWord") == 0) {
            if (!this.stringToPrint.contains(aux[1])) {
                this.stringToPrint += aux[1];
            }
        }

        else if (aux[0].compareTo("dictionaryPrintEnded") == 0) {
            this.printDictionary();
        }

        else if (aux[0].compareTo("derivatedWordPrintEnded") == 0) {
            if (aux.length == 2 && aux[1].compareTo("Não existem paralavras similares") == 0) {
                this.showOutPutWindow("Palavras derivadas", "Não existem palavras derivadas para a entrada");
            } else {
                this.printDerivatedWords();
            }
        }

        else if (aux[0].compareTo("newWordsFileSuccessfullyRead") == 0) {
            this.successfullyReadFile();
        }

        else if (aux[0].compareTo("errorOnReadingNewWordsFile") == 0) {
            this.errorReadingFile();
        }

        else if (aux[0].compareTo("newStopWordsFileSuccessfullyRead") == 0) {
            this.successfullyReadFile();
        }

        else if (aux[0].compareTo("errorOnReadingNewStopWordsFile") == 0) {
            this.errorReadingFile();
        }

        else if (aux[0].compareTo("newSimilarWord") == 0) {
            if (!this.stringToPrint.contains(aux[1])) {
                this.stringToPrint += aux[1];
            }
        }

        else if (aux[0].compareTo("similarWordPrintEnded") == 0) {
            this.printSimilarWords();
        }
        
        else if(aux[0].compareTo("error") == 0){
            this.printUnknownError();
        }
    }

    /* Method that subscribe a listener to this class
     * Input:        Listenter to be subscribed
     * Return:       None
     * Precondition: None
     */
    @Override
    public void subscribe(Object listener) throws Exception {
        if (listener instanceof Listener) {
            this.listeners.add((Listener) listener);
        } else {
            throw new Exception("A classe não é um listener");
        }
    }

    /* Method that subscribe a ArrayList of listener to this class
     * Input:        Listenter to be subscribed
     * Return:       None
     * Precondition: None
     */
    public void subscribe(ArrayList<Listener> listener) {
        for (Listener l : listeners) {
            this.listeners.add(l);
        }
    }

    /* Method that unscribe a listener from this class
     * Input:        Listener to be unscribed
     * Return:       None
     * Precondition: None
     */
    @Override
    public void unscribe(Object listener) throws Exception {
        if (listener instanceof Listener) {
            this.listeners.remove((Listener) listener);
        } else {
            throw new Exception("A classe não é um listener");
        }
    }

    /* Method that speak to all connected listeners
     * Input:        Message to speak
     * Return:       None
     * Precondition: None
     */
    @Override
    public void speak(String msg) {
        for (Listener l : this.listeners) {
            l.listen(msg);
        }
    }
}
