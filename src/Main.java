import java.io.RandomAccessFile;

public class Main {

	public static void main(String[] args) {
		RandomAccessFile arq;
		byte[] ba;
		
		
		try {
			
			Usuario u1 = new Usuario("8975472085639", 'M', "Joao");
			Usuario u2 = new Usuario("0463956344599", 'F', "Maria");
			
			Arquivo<Usuario> arqUsuarios = new Arquivo<>("usuarios", Usuario.class.getConstructor());
			
			//Escrita	
			arq = new RandomAccessFile("usuarios.db", "rw");
			
			arqUsuarios.create(u1);
			arqUsuarios.create(u2);
			
			long pos1 = arq.getFilePointer();
			ba = u1.toByteArray();
			arq.writeInt(ba.length);
			arq.write(ba);
			
			long pos2 = arq.getFilePointer();
			ba = u2.toByteArray();
			arq.writeInt(ba.length);
			arq.write(ba);
			
			//Leitura
			Usuario u3 = new Usuario();
			Usuario u4 = new Usuario();
			int tam;
			
			arq.seek(pos2);
			tam = arq.readInt();
			ba = new byte[tam];
			arq.read(ba);
			u3.fromByteArray(ba);
			
			arq.seek(pos1);
			tam = arq.readInt();
			ba = new byte[tam];
			arq.read(ba);
			u4.fromByteArray(ba);
			
			System.out.println(u3.toString());
			System.out.println(u4.toString());
					
			arq.close();
			
		}catch(Exception e) {
			System.out.println("Erro: "+e);
		}
	}

}
