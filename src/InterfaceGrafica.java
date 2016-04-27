import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.*;

public class InterfaceGrafica implements ActionListener {
	Note note;
	JFrame interfaceFrame;
	JPanel interfacePanel;
	JTextField nota;
	JLabel mostraNota;
	JButton addNotaButton, mostraNotaButton, apagaNotaButton ;

	public InterfaceGrafica() {
		//Cria e organiza janela.
		interfaceFrame = new JFrame("Notepad ZM");
		interfaceFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		interfaceFrame.setSize(new Dimension(500, 400));

		//Cria e constroi o painel.
		interfacePanel = new JPanel(new GridLayout(2,2));

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
		//Cria widgets.
		nota = new JTextField(2);
		mostraNotaButton = new JButton("Mostrar todas as notas");
		addNotaButton = new JButton("Adicionar Nota");
		mostraNota = new JLabel();
		
		//Listen para evento do botao Converte
		addNotaButton.addActionListener(this);
		mostraNotaButton.addActionListener(this);

		//Adiciona widgets para container.
		interfacePanel.add(nota);
		interfacePanel.add(addNotaButton);
		interfacePanel.add(mostraNotaButton);
		interfacePanel.add(mostraNota);
 
		mostraNota.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
	}

	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == addNotaButton){
			note = new Note("Teste", nota.getText());
			try {
				note.saveNote();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(event.getSource() == mostraNotaButton){
			try {
				mostraNota.setText(note.exibeTodasAsNotas());
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
				criaExibeGUI();
			}
		});
	}
}