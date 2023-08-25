package com.ti2cc;

import java.util.Scanner;

public class Principal {

	public static void main(String[] args) {
		// Declaracao de variaveis
		Scanner in = new Scanner(System.in);
		int cod = 0; // codigo
		String log = ""; // loigin
		String sen = ""; // senha
		char sex = ' '; // sexo

		// Declaracao do DAO
		DAO dao = new DAO();

		// Conexao com BD
		dao.conectar();

		// Vetor de users
		Usuario[] usuarios = dao.getUsuarios();

		// User default
		Usuario usuario = new Usuario(cod, log, sen, sex);

		// Condiçao do while
		int key = 0;

		while (key != 5) {
			// Instruções
			System.out.println("1- Listar todos os usuários");
			System.out.println("2- Inserir um usuário");
			System.out.println("3- Atualizar um usuário");
			System.out.println("4- Excluir um usuário");
			System.out.println("5- Sair");

			// Scanner da key
			key = in.nextInt();

			switch (key) {

				// Lista todos os users na tela
				case 1:
					usuarios = dao.getUsuarios();

					System.out.println("==== Mostrar usuários === ");
					for (int i = 0; i < usuarios.length; i++) {
						System.out.println(usuarios[i].toString());
					}
					break;

				// Entrada de user
				case 2:
					System.out.println("Digite o código, login, senha e sexo do usuário: ");
				
					// Entrada de codigo
					cod = in.nextInt();
					usuario.setCodigo(cod);

					// Clear buffer
					in.nextLine();

					// Entrada de login
					log = in.nextLine();
					usuario.setLogin(log);

					// Entrada de senha
					sen = in.nextLine();
					usuario.setSenha(sen);

					// Clear buffer
					in.nextLine();

					// Entrada de sexo
					sex = in.next().charAt(0);
					usuario.setSexo(sex);

					if (dao.inserirUsuario(usuario) == true) {
						System.out.println("Inserção com sucesso -> " + usuario.toString());
					}
					break;

				// Atualiza as informaçoes do user
				case 3:
					System.out.println("Informe o codigo do usuario a ser atualizado: ");
					cod = in.nextInt();

					// Clear buffer
					in.nextLine();

					// Entrada de login
					System.out.println("Digite o login do usuário: ");
					log = in.nextLine();

					// Entrada de senha
					System.out.println("Digite a senha do usuário: ");
					sen = in.nextLine();

					// Clear buffer
					in.nextLine();
					System.out.println("Digite o sexo do usuário");

					sex = in.next().charAt(0);
					Usuario aux = new Usuario(cod, log, sen, sex);

					dao.atualizarUsuario(aux);
					break;

				// Exclui user
				case 4:
					System.out.println("Informe o codigo do usuario a ser excluido: ");
					cod = in.nextInt();
					
					dao.excluirUsuario(cod);
					break;

				// Sair do menu
				case 5:
					key = 5;
					break;

				default:
					break;
			}
		}

		// Fechamentos
		in.close();
		dao.close();
	}
}