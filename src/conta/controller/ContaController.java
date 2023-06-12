package conta.controller;

import java.util.ArrayList;

import conta.model.Conta;
import conta.repository.ContaRepository;

public class ContaController implements ContaRepository {  //Palavra reservada implements, implementamos a Interface ContaRepository na Classe Conta Controller

	private ArrayList<Conta> listaContas = new ArrayList<Conta>(); //Criamos uma Collection ArrayList, do tipo Conta (Classe Abstrata), chamada listaContas.
	int numero = 0; //armazenará o numero da última conta que foi criada.
		
	@Override
	public void procurarPorNumero(int numero) {  //na assinatura do Método foi inserido como parâmetro uma variável do tipo int, chamada numero, que receberá o número da conta que se deseja localizar.
		var conta = buscarNaCollection(numero);  // a variável local conta, utilizada para receber o Objeto Conta, que foi encontrado na Collection listaContas, foi criada através da palavra reservada var. Por inferência, a palavra reservada var entende que a variável conta, deve ser um Objeto da Classe Conta, porque receberá o retorno do Método buscarNaCollection.
		
		if(conta != null)  //Linhas 16 a 19: Através do Laço Condicional, verifica se o Objeto Conta foi encontrado.
			conta.visualizar();
		else
			System.out.println("\nA Conta número: " + numero + " não foi encontrada!");
		
	}

	@Override
	public void listarTodas() { //na assinatura do Método não foi inserido nenhum parâmetro, porque o Método exibirá todos os Objetos da Classe Conta, armazenados na Collection listaContas.
		for (var conta : listaContas) { //Através do Laço de Repetição for...each, percorremos toda a Collection listaContas.
			conta.visualizar(); //a variável local conta, utilizada para receber um Objeto da Classe Conta a cada iteração da Collection, definida pelo Laço de repetição, foi criada através da palavra reservada var.
		}
		
	}

	@Override
	public void cadastrar(Conta conta) {  //na assinatura do Método, foi inserido como parâmetro um Objeto da Classe Conta, chamado conta. Este Objeto será adicionado na Collection listaContas.
		listaContas.add(conta); //Para inserir um novo Objeto da Classe Conta, utilizamos o Método add(), da Collection ArrayList, passando como parâmetro o Objeto da Classe Conta, chamado conta.
		System.out.println("\nA Conta número: " + conta.getNumero()+ " foi criada com sucesso");  //Para identificar a conta que foi criada, utilizamos o Método getNumero(), da Classe Model Conta, que retornará o número da conta.
		
	}

	@Override
	public void atualizar(Conta conta) {    //na assinatura do Método, foi inserido como parâmetro um Objeto da Classe Conta, chamado conta. Este Objeto terá os seus dados atualizados na Collection listaContas.
		var buscaConta = buscarNaCollection(conta.getNumero());  //Antes de atualizar o Objeto Conta na Collection listaContas, precisamos verificar se o Objeto conta existe. Observe que foi criada a variável local buscaConta, para receber o Objeto da Classe Conta, que foi encontrado na Collection listaContas.
		
		if(buscaConta != null) {   //Linhas 43 a 45: Através do Laço Condicional, verifica se o Objeto Conta foi encontrado.
			listaContas.set(listaContas.lastIndexOf(buscaConta), conta);
			System.out.println("\nA Conta numero: " + conta.getNumero() + "não foi encontrada!");
			
		}
		
	}

	@Override
	public void deletar(int numero) {   // na assinatura do Método foi inserido como parâmetro uma variável do tipo int, chamada numero, que receberá o número da conta que se deseja deletar.
		var conta = buscarNaCollection(numero); //Antes de deletar o Objeto Conta na Collection listaContas, precisamos verificar se o Objeto conta existe.
	
	if(conta != null) {  //Através do Laço Condicional, verifica se o Objeto Conta foi encontrado.
		if(listaContas.remove(conta)== true) 
			System.out.println("\nA Conta Número: " + numero + " foi deletada com sucesso!");
		}else 
			System.out.println("\nA Conta Número: " + numero + " não foi encontrada!");
	
		}
	@Override
	public void sacar(int numero, float valor) {  //Foram inseridos 2 parâmetros - numero: Número da conta que será efetuada a operação de Saque. e valor: valor que será debitado da conta.
		var conta = buscarNaCollection(numero);   //Antes de Efetuar o saque na conta, precisamos verificar se a conta existe. Observe que foi criada a variável local buscaConta, para receber o Objeto da Classe Conta, que foi encontrado na Collection listaContas.
	if(conta != null) {                  //Através do Laço Condicional, verifica se o Objeto Conta foi encontrado
		if(conta.sacar(valor)== true)
			System.out.println("\nO Saque na Conta Corrente " +numero + " foi efetuado com sucesso!");
	}else
		System.out.println("\nA Conta numero: " + numero + " não foi encontrada!");
		
	}

	@Override
	public void depositar(int numero, float valor) { //Antes de Efetuar o depósito na conta, precisamos verificar se a conta existe. Observe que foi criada a variável local conta, para receber o Objeto da Classe Conta, que foi encontrado na Collection listaContas. Esse Objeto foi criado através da palavra reservada var. Por inferência, a palavra reservada var entende que a variável conta, deve ser um Objeto da Classe Conta, porque receberá o retorno do Método buscarNaCollection.
		var conta = buscarNaCollection(numero);
		
	if(conta != null) { //Através do Laço Condicional, verifica se o Objeto Conta foi encontrado 
		conta.depositar(valor);
		System.out.println("\nO Depósito na Conta Número: " + numero + " feoi efetuada com sucesso!");
	}else
		System.out.println("\n A Conta Número: " + numero + " não foi encontrada ou Conta destino não é uma Conta Corrente!");
		
	}

	@Override
	public void transferir(int numeroOrigem, int numeroDestino, float valor) {
		var contaOrigem = buscarNaCollection(numeroDestino);
		var contaDestino = buscarNaCollection(numeroDestino);
		
	if (contaOrigem != null && contaDestino != null) {
		
	if (contaOrigem.sacar(valor)== true) {	
		contaDestino.depositar(valor);
		System.out.println("\nA Transferência foi Efetuada com Sucesso!");
  }
	} else
		System.out.println("\nConta de Origem e/ou Destino não foram encontradas!");	
}

public int gerarNumero() {   //Para gerar automaticamente o número da conta, vamos criar o Método auxiliar gerarNumero(), no final da Classe ContaController, através do código abaixo:
	return ++ numero;
}
	public Conta buscarNaCollection(int numero) {   // na assinatura do Método foi inserido como parâmetro uma variável do tipo int, chamada numero, que receberá o número da conta que se deseja localizar.
		for(var conta : listaContas) {               // Através do Laço de Repetição for...each, percorremos toda a Collection listaContas.
			if(conta.getNumero()== numero) {     //a variável local conta, utilizada para receber um Objeto Conta a cada iteração da Collection, definida pelo Laço de repetição, foi criada através da palavra reservada var.
				return conta;          //Se os números forem iguais, o Método devolve o Objeto Conta encontrado e finaliza o Laço de repetição.
			}
		}
		return null;  //Se o número não for encontrado na Collection listaContas, o Método devolve um Objeto null.
	}
	
	
	public int retornaTipo(int numero) {  //na assinatura do Método foi inserido como parâmetro uma variável do tipo int, chamada numero, que receberá o número da conta que se deseja localizar o tipo.
		for(var conta: listaContas) {    //Através do Laço de Repetição for...each, percorremos toda a Collection listaContas. Observe que a variável local conta, utilizada para receber um Objeto Conta a cada iteração da Collection, definida pelo Laço de repetição, foi criada através da palavra reservada var.
			if(conta.getNumero()== numero) {  //Verifica se o Atributo numero (obtido através do Método getNumero()), do Objeto da Classe Conta armazenado na variável conta é o mesmo que foi enviado na variável numero.
				return conta.getTipo();    //Se os números forem iguais, o Método devolve o valor do Atributo tipo do Objeto Conta encontrado, através do Método getTipo() e finaliza o Laço de repetição.
			}
		}
		return 0;  //Se o número não for encontrado na Collection listaContas, o Método devolve 0 (zero).
	}
}
