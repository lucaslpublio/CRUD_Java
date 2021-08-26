import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Usuario implements Registro {
	protected int idUsuario;
	protected String CPF;
	protected char sexo;
	protected String nome;


	public Usuario(String cpf, char sx, String nome) throws Exception {
		this.idUsuario = -1;
		if(cpf.getBytes().length != 13)
			throw new Exception("CPF inválido!");
		this.CPF = cpf;
		this.sexo = sx;
		this.nome = nome;
	}
	
	public Usuario() {
		this.idUsuario = -1;
		this.CPF = "";
		this.sexo = '0';
		this.nome = "";
	}
	
	public void setID(int id) {
		this.idUsuario = id;
	}
	
	public int getID() {
		return this.idUsuario;
	}
	
	public void setCPF(String cpf) {
		this.CPF = cpf;
	}
	
	public String getCPF() {
		return this.CPF;
	}
	
	public void setSexo(char sx) {
		this.sexo = sx;
	}
	
	public char getSexo() {
		return this.sexo;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public byte[] toByteArray() throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);
		dos.writeInt(this.idUsuario);
		dos.write(this.CPF.getBytes());
		dos.writeChar(this.sexo);
		dos.writeUTF(this.nome);
		return baos.toByteArray();
	}
	
	 public void fromByteArray(byte[] ba) throws IOException {
		 ByteArrayInputStream bais = new ByteArrayInputStream(ba);
		 DataInputStream dis = new DataInputStream(bais);
		 byte[] CPFaux = new byte[13];
		 this.idUsuario = dis.readInt();
		 dis.read(CPFaux);
		 this.CPF = new String(CPFaux);
		 this.sexo = dis.readChar();
		 this.nome = dis.readUTF();
	 }
	 
	 public String toString() {
		 return"\nID: "+this.idUsuario+
				  "\nNome: "+this.nome+
				     "\nCPF: "+this.CPF+
				     "\nSexo: "+this.sexo;
	 }
	 
}
