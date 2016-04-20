import java.util.*;
import java.io.*;

public class Note {
	private String nome;
	private Date creationDate, lastModification;
	private String conteudo;
	private String[] metatags;
	
	Note(String nome1, String conteudo1){
		nome = nome1;
		Calendar c = Calendar.getInstance();
		creationDate = c.getTime();
		lastModification = creationDate;
		conteudo = conteudo1;
	}
	
	public void edit(String novo_conteudo){
		conteudo=novo_conteudo;
		Calendar c = Calendar.getInstance();
		lastModification = c.getTime();
	}
	
	public void saveNote() throws IOException{
		FileWriter arq = new FileWriter("Todas_anotacoes.txt", true);
		PrintWriter gravarArq = new PrintWriter(arq);
		gravarArq.printf("Nome da Anotação : "+nome+"%n");
		gravarArq.printf("Data de criação : "+creationDate+"%n");
		gravarArq.printf("Data da última modificação : "+lastModification+"%n");
		gravarArq.printf(conteudo+"%n");
		arq.close();
	}
}
