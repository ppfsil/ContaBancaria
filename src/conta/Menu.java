package conta;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import conta.controller.ContaController;
import conta.model.ContaCorrente;
import conta.model.ContaPoupanca;
import conta.util.Cores;

public class Menu {
	
	public static void main(String[] args) {
		ContaController contas = new ContaController();  //Cria um Objeto da Classe ContaController, para armazenar os dados das contas na Collection listaContas e executar os Métodos do CRUD e os Métodos Bancários.

		
		
		
		int opcao , numero , agencia , tipo , aniversario;
		String titular;
		float saldo , limite;
		
		System.out.println("\nCriar Contas \n");
		
		ContaCorrente cc1 = new ContaCorrente(contas.gerarNumero(), 123, 1, "João da Silva", 1000f, 100.0f);
		contas.cadastrar(cc1);
		
		ContaCorrente cc2 = new ContaCorrente(contas.gerarNumero(), 124, 1, "Maria da Silva", 2000f, 100.0f);
		contas.cadastrar(cc2);
		
		ContaPoupanca cp1 = new ContaPoupanca(contas.gerarNumero(), 125, 2, "Mariana dos Santos", 4000f, 12);
		contas.cadastrar(cp1);
		
		ContaPoupanca cp2 = new ContaPoupanca(contas.gerarNumero(), 125, 1, "Juliana Ramos", 8000f, 15);
		contas.cadastrar(cp2);
		
		contas.listarTodas();
		
		while (true) {

			System.out.println(Cores.TEXT_YELLOW + Cores.ANSI_BLACK_BACKGROUND
					        + "*****************************************************");
			System.out.println("                                                     ");
			System.out.println("               BANCO DO BRAZIL COM Z                 ");
			System.out.println("                                                     ");
			System.out.println("*****************************************************");
			System.out.println("                                                     ");
			System.out.println("               1 - Criar Conta                       ");
			System.out.println("               2 - Listar todas as Contas            ");
			System.out.println("               3 - Buscar Conta por Numero           ");
			System.out.println("               4 - Atualizar Dados da Conta          ");
			System.out.println("               5 - Apagar Cont                       ");
			System.out.println("               6 - Sacar                             ");
			System.out.println("               7 - Depositar                         ");
			System.out.println("               8 - Transferir valores entre Contas   ");
			System.out.println("               9 - Sair                              ");
			System.out.println("                                                     ");
			System.out.println("*****************************************************");
			System.out.println("Entre com a opção desejada:                          ");
			System.out.println("                                                     " + Cores.TEXT_RESET);

		
			Scanner leia = new Scanner(System.in);
			
			try {
					opcao = leia.nextInt();
			}catch(InputMismatchException e) {
					System.out.println("\nDigite Valores Inteiros!");
					leia.nextLine();
					opcao = 0;
			}		
			if (opcao == 9) {
				System.out.println(Cores.TEXT_WHITE_BOLD + "\nBanco do Brazil  - O seu Futuro começa aqui!");
				leia.close();
				System.exit(0);
			}

			switch (opcao) {
			case 1:
				System.out.println(Cores.TEXT_WHITE_BOLD + "Criar Conta\n\n"); //Linha 63: Observe que foi adicionado o comando skip da Classe Scanner. O Método skip ignora a entrada que corresponde ao padrão especificado, ignorando os delimitadores. Em nosso projeto, estamos ignorando \r (quebra de linha), para permitir que o comando nextLine() leia palavras compostas. Lembre-se que o titular possui pelo menos nome e um sobrenome.
				
				System.out.println("Digite o número da Agência: "); //Linhas 65 a 69: Foram criadas as entradas de dados via teclado para as variáveis agencia e titular.
				agencia = leia.nextInt();
				System.out.println("Digite o nome do Titular: ");
				leia.skip("\\R?");
				titular = leia.nextLine();
								
			do {                                                    //Linhas 71 a 74: Foi criado um Laço de repetição do...while, para garantir que a variável tipo receba via teclado somente os números 1 - *Conta Corrente ou 2 - Conta Poupança.
				System.out.println("Digite o Tipo de Conta (1-CC ou 2-CP): ");
				tipo = leia.nextInt();
			}while(tipo < 1&& tipo > 2);
			
				System.out.println("Digite o Saldo da Conta (R$): ");      //Linhas 76 a 77: Foi criada a entrada de dados via teclado para a variável saldo.
				saldo = leia.nextFloat();
				
		switch (tipo) {                                                  //Linhas 79 a 90: Foi criado um Laço condicional do tipo Switch Expression, que checará qual o tipo da conta.
			 case 1 -> {
			  	System.out.println("Digite o Limite de Crédito (R$): ");
			 	limite = leia.nextFloat();
			 	contas.cadastrar(new ContaCorrente(contas.gerarNumero(), agencia , tipo , titular , saldo , limite));   //Linha 83: Observe que dentro do Método cadastrar foi criado um Objeto da Classe ContaCorrente, composto pelos valores das variáveis auxiliares, que receberam dados via teclado. O Atributo numero, foi preenchido com o Método auxiliar gerarNumero(), criado na Classe ContaController.
		}
		case 2 -> {
				System.out.println("Digite o dia do Aniversário da Conta: ");
				aniversario = leia.nextInt();
				contas.cadastrar(new ContaPoupanca(contas.gerarNumero(), agencia, tipo, titular, saldo, aniversario));   //Linha 88: Observe que dentro do Método cadastrar foi criado um Objeto da Classe ContaPoupanca, composto pelos valores das variáveis auxiliares, que receberam dados via teclado. O Atributo numero, foi preenchido com o Método auxiliar gerarNumero(), criado na Classe ContaController.
		}	
	}
	
		keyPress();
		break;
		
			case 2:
				System.out.println(Cores.TEXT_WHITE_BOLD + "Listar todas as Contas\n\n");
				contas.listarTodas();
				keyPress();
				break;

			case 3:
				System.out.println(Cores.TEXT_WHITE_BOLD + "Consultar dados da Conta - por número\n\n");
				
				System.out.println("Digite o número da conta: ");  // Foram criada a entrada de dados via teclado para a variável numero, que receberá o numero da conta que deverá ser procurada na Collection listaContas.
				numero = leia.nextInt();
				
				contas.procurarPorNumero(numero);  //Executa o Método procurarPorNumero(int numero), da Classe ContaController, passando como parâmetro a variável numero, que contém o numero da conta (recebido via teclado), que deverá ser procurada na Collection listaContas.
				
				keyPress();
				break;

			case 4:
				System.out.println(Cores.TEXT_WHITE_BOLD + "Atualizar dados da Conta\n\n");
				System.out.println("Digite o número da conta: ");   //Foi criada a entrada de dados via teclado para a variável numero, que receberá o numero da conta que será atualizada na Collection listaContas.
				numero = leia.nextInt();
			
			if (contas.buscarNaCollection(numero) != null) {   //Através do Laço Condicional, verifica se o Objeto Conta foi encontrado.
				
				System.out.println("Digite o Número da Agência: "); //Linha 137 a 144: Foram criadas as entradas de dados via teclado para as variáveis agencia, titular e saldo.
				agencia = leia.nextInt();
				System.out.println("Digite o Nome do Titular: ");
				leia.skip("\\R?"); //Observe que foi adicionado o comando skip da Classe Scanner. O Método skip ignora a entrada que corresponde ao padrão especificado, ignorando os delimitadores. Em nosso projeto, estamos ignorando \r (quebra de linha), para permitir que o comando nextLine() leia palavras compostas. Lembre-se que o titualr possui pelo menos nome e um sobrenome.
				titular = leia.nextLine();
				
				System.out.println("Digite o Saldo da Conta (R$): ");
				saldo = leia.nextFloat();
				
				tipo = contas.retornaTipo(numero);  //Executa o Método retornaTipo(numero), da Classe ContaController, que checará a partir do numero da conta, qual é o tipo da conta: 1 - Conta Corrente ou 2 - Conta Poupança. Esta informação, será armazenada na variável auxiliar tipo.
				
			switch (tipo) { //Linhas 148 a 163: Foi criado um Laço condicional do tipo Switch Expression, que checará qual o tipo da conta.
			case 1 -> {
				System.out.println("Digite o Limite de Crédito (R$): ");
				limite = leia.nextFloat();
				contas.atualizar(new ContaCorrente(numero , agencia , tipo, titular, saldo, limite));  //dentro do Método atualizar foi criado um Objeto da Classe ContaCorrente, composto pelos valores das variáveis auxiliares, que receberam dados via teclado.
		}
			case 2 -> {
				System.out.println("Digite o dia do Aniversário da Conta: ");
				aniversario = leia.nextInt();
				contas.atualizar(new ContaPoupanca(numero, agencia, tipo, titular, saldo, aniversario)); //dentro do Método atualizar foi criado um Objeto da Classe ContaPoupanca, composto pelos valores das variáveis auxiliares, que receberam dados via teclado.
	   }
			default -> {
				System.out.println("Tipo de conta inválida!: ");
				
			}
			}
			}else
				System.out.println("\nConta não Encontrada!: ");
			
				keyPress();
				break;

			case 5:
				System.out.println(Cores.TEXT_WHITE_BOLD + "Apagar a Conta\n\n");
				
				System.out.println("Digite o Número da Conta: "); //Foi criada a entrada de dados via teclado para a variável numero, que receberá o numero da conta que será excluída da Collection listaContas.
				numero = leia.nextInt();
				
				contas.deletar(numero); //Executa o Método deletar(int numero), da Classe ContaController, passando como parâmetro a variável numero, que contém o numero da conta (recebido via teclado), que será excluída da Collection listaContas.
				
				keyPress();
				break;

			case 6:
				System.out.println(Cores.TEXT_WHITE_BOLD + "Saque\n\n");
				keyPress();
				break;

			case 7:
				System.out.println(Cores.TEXT_WHITE_BOLD + "Depósito\n\n");
				keyPress();
				break;

			case 8:
				System.out.println(Cores.TEXT_WHITE_BOLD + "Transferência entre Contas\n\n");
				keyPress();
				break;
			default:
				System.out.println(Cores.TEXT_RED_BOLD + "Opção Inválida!\n");
				keyPress();
				break;
			}

		}

	}
		public static void keyPress() {
			
		try {
				System.out.println(Cores.TEXT_RESET + "\n Pressione Enter para Continuar... ");
				System.in.read();
		}catch(IOException e) {
				System.out.println("Você pressionou uma tecla diferente de Enter! ");

		}
	}
		
}
