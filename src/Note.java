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
		gravarArq.printf("Nome da Anota��o : "+nome+"%n");
		gravarArq.printf("Data de cria��o : "+creationDate+"%n");
		gravarArq.printf("Data da �ltima modifica��o : "+lastModification+"%n");
		gravarArq.printf(conteudo+"%n");
		arq.close();
	}
	
	public String getConteudo(){
		return conteudo;
	}
	
	public String exibeTodasAsNotas() throws FileNotFoundException, IOException{
		BufferedReader buffRead = new BufferedReader(new FileReader("Todas_anotacoes.txt"));
		String linha = "";
		String result = "";
		while (true) {
			if (linha != null) {
				System.out.println(linha);
				result+=("%s"+linha);
			} else break; 
			linha = buffRead.readLine(); 
		} 
		buffRead.close();
		return result;
	}
}
