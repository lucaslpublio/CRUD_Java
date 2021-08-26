import java.io.RandomAccessFile;
import java.io.IOException;

public class Arquivo<T extends Registro> {
	RandomAccessFile arq;
	
	Arquivo<T> construtor; 
	 
	
	public Arquivo(String nomeArq, Arquivo<T> construtor) throws Exception {
		arq = new RandomAccessFile(nomeArq+".db","rw");
		this.construtor = construtor;
	}
	
	public void create(T general) throws Exception {
		arq.seek(0);
		int ultimoId = arq.readInt();
		int proxId = ultimoId + 1;
		arq.seek(0);
		arq.writeInt(proxId);
		
		arq.seek(arq.length());
		general.setID(proxId);
		byte[] dados = general.toByteArray();
		arq.write(' ');
		arq.writeInt(dados.length);
		arq.write(dados);
	}
	
	public T read(int id) throws IOException {
		T obj = this.construtor.newInstance();
		arq.seek(4);
		int tam = 0;
		char lapide = ' ';
		while(true) {
			lapide = arq.readChar();
			tam = arq.readInt();	//ler tamanho do registro
			byte[] dados = new byte[tam];
			arq.read(dados);
			try {
				if(lapide != '*') {
					obj.fromByteArray(dados);
					if(id == obj.getID()) {
						return obj;
					}
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}
}
