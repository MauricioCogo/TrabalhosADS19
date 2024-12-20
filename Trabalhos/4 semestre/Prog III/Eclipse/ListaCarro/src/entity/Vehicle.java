package entity;

public class Vehicle {

	private int id;
	private String marca;
	private String modelo;
	private int ano;
	private String cor;
	private double quilometragem;
	private String estado;
	private String combustivel;
	
	
	public String getCor() {
		return cor;
	}
	public void setCor(String cor) {
		this.cor = cor;
	}
	public double getQuilometragem() {
		return quilometragem;
	}
	public void setQuilometragem(double quilometragem) {
		this.quilometragem = quilometragem;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getCombustivel() {
		return combustivel;
	}
	public void setCombustivel(String combustivel) {
		this.combustivel = combustivel;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	
	@Override
	public String toString() {
		return "Vehicle [id=" + id + ", marca=" + marca + ", modelo=" + modelo + ", ano=" + ano + ", cor=" + cor
				+ ", quilometragem=" + quilometragem + ", estado=" + estado + ", combustivel=" + combustivel + "]";
	}
	
	public Vehicle(int id, String marca, String modelo, int ano, String cor, double quilometragem, String estado,
			String combustivel) {
		super();
		this.id = id;
		this.marca = marca;
		this.modelo = modelo;
		this.ano = ano;
		this.cor = cor;
		this.quilometragem = quilometragem;
		this.estado = estado;
		this.combustivel = combustivel;
	}
	
}
