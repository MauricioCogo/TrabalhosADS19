import java.util.Iterator;

public class Calculadora {

	private int num1;
	private int num2;
	private String operacao;
	
	public void setNum1(int num1) {
		this.num1 = num1;
	}
	public void setNum2(int num2) {
		this.num2 = num2;
	}
	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}
	
	public int getNum1() {
		return num1;
	}
	public int getNum2() {
		return num2;
	}
	public String getOperacao() {
		return operacao;
	}
	
	public int soma() {
		return num1+num2;
	}
	public int subtracao() {
		return num1-num2;
	}
	public int multi() {
		return num1*num2;
	}
	public int divisao() {
		return num1/num2;
	}
	public int potencia() {
	    int count = 1;
	    for (int i = 0; i < num2; i++) {
	        count *= num1;
	    }
	    return count;
	}
	
}