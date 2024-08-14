package org.example;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SeleniumScrap seleniumScrap = new SeleniumScrap();
        List<Player> players = seleniumScrap.ScrapAllPlayersStats();
        PlayerMethods playerMethods = new PlayerMethods(players);
        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            System.out.println("\nMenu:");
            System.out.println("1. Buscar jogador por nome");
            System.out.println("2. Exibir TOP 5 jogadores por rounds");
            System.out.println("3. Exibir TOP 5 jogadores por sucesso");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");

            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    System.out.print("\n Digite o nome do jogador: ");
                    String name = scanner.nextLine();
                    Player player = playerMethods.searchPlayerByName(name);
                    if (player != null) {
                        System.out.println("\nJogador encontrado:" + player);
                    } else {
                        System.out.println("Jogador não encontrado.");
                    }
                    break;

                case 2:
                    playerMethods.getTop10ByRounds();
                    break;

                case 3:
                    playerMethods.getTop10BySucess();
                    break;

                case 4:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        } while (option != 4);

        scanner.close();
    }
}
