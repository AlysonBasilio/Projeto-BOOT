import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class InterfaceGrafica implements ActionListener {
	static int MAX = 100;
	private static int cont;
	final static boolean shouldFill = true;
	final static boolean shouldWeightX = true;
	final static boolean RIGHT_TO_LEFT = false;
	static Note[] note;
	JFrame interfaceFrame;
	JPanel interfacePanel;
	JTextArea nota;
	JTextArea mostraNota;
	JButton addNotaButton, mostraNotaButton, apagaNotaButton ;
	JScrollPane scrollPane;
	
	public InterfaceGrafica() {
		//Cria e organiza janela.
		interfaceFrame = new JFrame("Notepad ZM");
		interfaceFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		interfaceFrame.setSize(new Dimension(500, 400));

		//Cria e constroi o painel.
		interfacePanel = new JPanel(new GridBagLayout());
				
		//Adiciona o widgets.
		adicionaWidgets();

		//Marca padrao do botao
		interfaceFrame.getRootPane().setDefaultButton(addNotaButton);

		//Adiciona o painel na janela
		interfaceFrame.getContentPane().add(interfacePanel, BorderLayout.CENTER);

		//Exibe a janela.
		interfaceFrame.pack();
		interfaceFrame.setVisible(true);
	}

	/**
	 * Cria e adciona o widgets.
	 */
	private void adicionaWidgets() {
				
		GridBagConstraints c = new GridBagConstraints();
		if (shouldFill) {
			//altura natural, largura maxima.
			c.fill = GridBagConstraints.HORIZONTAL;
		}
		
		//Adiciona widgets para container.
		nota = new JTextArea(5, 30);
		if (shouldWeightX) {
			c.weightx = 0.5;
		}
		c.gridx = 0;
		c.gridy = 0;
		nota.setBorder(new LineBorder(Color.BLUE));
		nota.setLineWrap(true);
		interfacePanel.add(nota, c);
		
		addNotaButton = new JButton("Adicionar Nota");
		c.gridx = 1;
		c.gridy = 0;
		interfacePanel.add(addNotaButton, c);
		addNotaButton.addActionListener(this);
		
		mostraNotaButton = new JButton("Mostrar todas as notas");
		c.gridx = 2;
		c.gridy = 0;
		interfacePanel.add(mostraNotaButton,c);
		mostraNotaButton.addActionListener(this);
				
		mostraNota = new JTextArea();
		scrollPane = new JScrollPane(mostraNota);
		c.ipady = 40; //faz o componente no alto.
		c.weightx = 0.0;
		c.gridwidth = 3;
		c.gridx = 0;
		c.gridy = 1;
		mostraNota.setLineWrap(true);
		mostraNota.setEditable(false);
		interfacePanel.add(scrollPane,c);
	}

	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == addNotaButton){
			try {
				note[cont] = new Note(""+cont, nota.getText());
				note[cont++].saveNote();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(event.getSource() == mostraNotaButton){
			try {
				mostraNota.setText(note[0].exibeTodasAsNotas());
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * Cria um GUI e o exibe. Para thread safety,
	 * este metodo podera invocar para uma thread 
	 * de disparo de evento(event-dispatching thread).
	 */
	private static void criaExibeGUI() {
		//Faz com se tenha janelas com decoracoes agradaveis.
		JFrame.setDefaultLookAndFeelDecorated(true);

		InterfaceGrafica gui = new InterfaceGrafica();
	}

	public static void main(String[] args) {
		//Agenda um trabalho para o event-dispatching thread:
		//cria e exibe uma aplicacao GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				cont=0;
				note = new Note[MAX];
				criaExibeGUI();
			}
		});
	}
}