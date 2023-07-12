package estudoJavaAtividadeFuncionalLambda.programa;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import estudoJavaAtividadeFuncionalLambda.entidades.Funcionario;

public class Programa {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner scan = new Scanner(System.in);

		System.out.print("Digite o caminho do arquivo : ");
		String path = scan.nextLine();

		try (BufferedReader buffer = new BufferedReader(new FileReader(path))) {

			List<Funcionario> lista = new ArrayList<>();

			String linha = buffer.readLine();
			while (linha != null) {
				String[] fields = linha.split(",");
				lista.add(new Funcionario(fields[0], fields[1], Double.parseDouble(fields[2])));
				linha = buffer.readLine();
			}
			// lista.forEach(System.out::print);
			System.out.print("Digite o valor do salario : ");
			double salario = scan.nextDouble();

			List<String> email = lista.stream().filter(x -> x.getSalario() > salario).map(x -> x.getEmail()).sorted()
					.collect(Collectors.toList());

			System.out.println("Email dos funcionarios com salario acima de : " + String.format("%.2f", salario));
			email.forEach(System.out::println);

			double soma = lista.stream().filter(x -> x.getNome().charAt(0) == 'M').map(x -> x.getSalario()).reduce(0.0,
					(x, y) -> x + y);

			System.out.print("A soma dos funcionarios com email iniciado com 'M'  : " + String.format("%.2f", soma));

		} catch (IOException e) {
			System.out.println("Error :" + e.getMessage());
		}

		scan.close();
	}

}
