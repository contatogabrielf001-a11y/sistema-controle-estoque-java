
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ArrayList<Produto> produto = new ArrayList<>();
        carregarProdutos(produto);

        int opcao = 0;
        String nome = "";
        double preco = 0;
        int quantidade = 0;

        do {

            int semEstoque = 0;
            int estoqueBaixo = 0;
            int precisaReposicao = 0;
            double valorTotalEstoque = 0;

            for(int i = 0; i < produto.size(); i++){
               
                valorTotalEstoque += produto.get(i).preco
                     *produto.get(i).quantidade;

                if(produto.get(i).quantidade ==0){
                    semEstoque++;
                } else if (produto.get(i).quantidade < 5){
                    estoqueBaixo++;
                }

                if(produto.get(i).quantidade <= 2){
                    precisaReposicao++;
                }
            }

            System.out.println();
            System.out.println("==========RESUMO DO ESTOQUE=========");
            System.out.println("Total de produtos: " +produto.size());
            System.out.println("Produtos sem estoque: " + semEstoque);
            System.out.println("Produtos que precisam de reposicao: " + precisaReposicao);
            System.out.println("Produtos com estoque baixo : " + estoqueBaixo);
            System.out.printf(
                Locale.forLanguageTag("pt-BR"),
                "Valor total do estoque:  R$ %.2f%n",
                valorTotalEstoque
            );
            System.out.println("=====================================");

            System.out.println();
            System.out.println("=========================================");
            System.out.println("      SISTEMA DE CONTROLE DE ESTOQUE");
            System.out.println("=========================================");
            System.out.println("|                                       |");
            System.out.println("|   [1] Cadastrar produto               |");
            System.out.println("|   [2] Consultar produtos              |");
            System.out.println("|   [3] Sair do sistema                 |");
            System.out.println("|   [4] Pesquisar produto               |");
            System.out.println("|   [5] Registrar venda                 |");
            System.out.println("|   [6] Excluir produto                 |");
            System.out.println("|   [7] Editar produto                  |");
            System.out.println("|   [8] Listar produtos para reposicão  |");
            System.out.println("|   [9] Historico de vendas             |");
            System.out.println("|                                       |");
            System.out.println("=========================================");
            System.out.println("Escolha uma opção:  ");


            if(scanner.hasNextInt()){
                opcao = scanner.nextInt();
            } else {
                System.out.println("Entrada invalida! Digite um numero.");
                scanner.nextLine();
                continue;
            }

            switch (opcao) {

          case 1:
    scanner.nextLine();

    
         System.out.println("Digite o nome do produto:");
           nome = scanner.nextLine();

    while (nome.trim().isEmpty()) {
        System.out.println("O nome nao pode ficar vazio!");
        System.out.println("Digite o nome do produto:");
        nome = scanner.nextLine();
    }

    // Pedir e validar o preco
    System.out.println("Digite o preco do produto:");

    while (true) {
        while (!scanner.hasNextDouble()) {
            System.out.println("Preco invalido! Digite um numero:");
            scanner.nextLine();
        }

        preco = scanner.nextDouble();

        if (preco > 0) {
            break;
        }

        System.out.println("O preco deve ser maior que zero. Digite novamente:");
    }

    if (preco > 1000) {
        System.out.println("ATENCAO! Preco acima do esperado.");
    }

    // Pedir e validar a quantidade
    System.out.println("Digite a quantidade do produto:");

    while (!scanner.hasNextInt()) {
        System.out.println("Quantidade invalida! Digite um numero inteiro:");
        scanner.nextLine();
    }

    quantidade = scanner.nextInt();

    while (quantidade < 0) {
        System.out.println("A quantidade nao pode ser negativa. Digite novamente:");

        while (!scanner.hasNextInt()) {
            System.out.println("Quantidade invalida! Digite um numero inteiro:");
            scanner.nextLine();
        }

        quantidade = scanner.nextInt();
    }

    boolean produtoJaExiste = false;

    for(int i = 0; i < produto.size(); i++){
        if(produto.get(i).nome.equalsIgnoreCase(nome.trim())){
            produtoJaExiste = true;
            break;
        }
    }

    if(produtoJaExiste){
        System.out.println("Esse produto ja existe no sistema!");
    } else{
        produto.add(new Produto(nome.trim(), preco, quantidade));

        salvarProdutos(produto);

        System.out.println("Produto cadastrado e salvo com sucesso!");

    }

    if(quantidade ==0){
        System.out.println("Produto sem estoque.");
    } else if (quantidade > 30){
        System.out.println("Quantidade indisponivel para o pedido.");
    } else if (quantidade < 5){
        System.out.println("ATENÇÃO! Produto com estoque baixo.");
    } else {
        System.out.println("Produto com estoque.");
    }

    System.out.println("Produto: " + nome);
    System.out.println("Preco: R$ " + preco);
    System.out.println("Quantidade em estoque: " + quantidade);

    break;

          case 2:
                    if (produto.isEmpty()) {
                        System.out.println("Nenhum produto cadastrado.");
                    } else {
                        System.out.println("===== PRODUTOS CADASTRADOS =====");
                        for(int i = 0; i <produto.size(); i++){
                         System.out.println("========PRODUTO " +(i + 1) + "====");
                         System.out.println("Nome : " + produto.get(i).nome);
                         System.out.println("Preço: R$" + produto.get(i).preco);
                         System.out.println("Quantidade: " + produto.get(i).quantidade);

                        }
                    }

                    break;

          case 3:
                    System.out.println("Saindo do sistema...");
                    break;
                
          case 4:
                   scanner.nextLine();

                 System.out.println("Digite o nome do produto que deseja pesquisar:");
                 String busca = scanner.nextLine();

            boolean encontrado = false;

    for (int i = 0; i < produto.size(); i++) {
        if (produto.get(i).nome.toLowerCase().contains(busca.toLowerCase())) {
                System.out.println("Produto encontrado!");
                System.out.println("Nome: " + produto.get(i).nome);
                System.out.println("Preço: R$ " + produto.get(i).preco);
                System.out.println("Quantidade: " + produto.get(i).quantidade);

            encontrado = true;
        }
    }

    if (!encontrado) {
        System.out.println("Produto não encontrado.");
    }

    break;

          case 5:
            scanner.nextLine();

            System.out.println("Digite o nome do produto:");
            String nomeBusca = scanner.nextLine();

            boolean produtoEncontrado = false;

            for(int i = 0; i <produto.size(); i++){
                if(produto.get(i).nome.equalsIgnoreCase(nomeBusca)){
                    produtoEncontrado = true;

                   System.out.println("Produto encontrado: " + produto.get(i).nome);
                   System.out.println("Estoque atual: " + produto.get(i).quantidade);

                   System.out.println("Quantas unidades foram vendidas?");

                   while(!scanner.hasNextInt()){
                    System.out.println("Entrada invalida! Digite um numero inteiro:" );
                    scanner.nextLine();

                   }
                   
                   int vendidas = scanner.nextInt();

                   if(vendidas <=0) {
                   System.out.println("A quantidade vendida deve ser maior que zero.");    
                   } else if(vendidas > produto.get(i).quantidade){
                    System.out.println("Estoque insuficiente para essa venda.");
                   } else {
                   boolean vendaSalva = registrarVenda(
                   produto.get(i).nome,
                   vendidas,
                   produto.get(i).preco
             );

                  if (vendaSalva) {
                  produto.get(i).quantidade -= vendidas;
                  salvarProdutos(produto);

                  System.out.println("Venda registrada com sucesso!");
                  System.out.println(
                  "Estoque atualizado: " + produto.get(i).quantidade
             
             );
                } else {
                  System.out.println(
                 "Venda cancelada. O estoque nao foi alterado."
              );
                }


                   }

                    break;
                }
            }

            if(!produtoEncontrado){
                System.out.println("Produto não encontrado.");
            }

          break;

          case 6:
            scanner.nextLine();

            System.out.println("Digite o nome do produto que deseja excluir:");
            String nomeExcluir = scanner.nextLine();


            for(int i = 0; i < produto.size(); i++){
                if (produto.get(i).nome.equalsIgnoreCase(nomeExcluir)){

                   System.out.println("Produto encontrado: " + produto.get(i).nome);
                   System.out.println("Tem certeza que deseja excluir este produto?");
                   System.out.println("1 - Sim");
                   System.out.println("2 - Não");

                   int confirmacao;

                   while(!scanner.hasNextInt()){
                    System.out.println("Opcao invalida! Digite 1 para Sim ou 2 para Não:");
                    scanner.nextLine();

                   }

                confirmacao = scanner.nextInt();

                while(confirmacao != 1 && confirmacao !=2){
                    System.out.println("Opcao invalida! Digite 1 para Sim ou 2 para Não:");

                    while(!scanner.hasNextInt()){

                        System.out.println("Opção invalida! Digite 1 para sim ou 2 para Não: ");
                        scanner.nextLine();

                    }

                    confirmacao = scanner.nextInt();
                }
                
                if(confirmacao ==1){
                    produto.remove(i);
                   

                    salvarProdutos(produto);

                    System.out.println("Produto excluido com sucesso");

                
                } else {
                   
                    System.out.println("Exclusão cancelada. O produto foi mantido. ");
                }
                break;
            }

           
            }
         
         break;

         case 7:
            scanner.nextLine();

            System.out.println("Digite o nome do produto que deseja editar:");
            String nomeEditar = scanner.nextLine();

            boolean produtoEditado = false;

            for(int i = 0; i < produto.size(); i++){
                if(produto.get(i).nome.equalsIgnoreCase(nomeEditar)){

                    produtoEditado = true;

                    System.out.println("Produto encontrado! ");
                    System.out.println("Nome atual: " + produto.get(i).nome);
                    System.out.println("Preço atual: R$ " + produto.get(i).preco);

                    System.out.println("Digite o novo nome do produto:");
                    String novoNome = scanner.nextLine();

                    while(novoNome.trim().isEmpty()){
                        System.out.println("O nome não pode ficar vazio!");
                        System.out.println("Digite o novo nome do produto:");
                        novoNome = scanner.nextLine();

                    }


                    System.out.println("Digite o novo preço:");

                    double novoPreco = 0;


                    while(true){

                        while(!scanner.hasNextDouble()){
                            System.out.println("Preço invalido! Digite um numero:");
                            scanner.nextLine();

                        }

                        novoPreco = scanner.nextDouble();
                        
                        if (novoPreco > 0) {
                            produto.get(i).nome = novoNome;
                            produto.get(i).preco = novoPreco;

                            salvarProdutos(produto);

                            System.out.println("Produto atualizado com sucesso!");
                            System.out.println("Novo nome: " + produto.get(i).nome);
                            System.out.println("Novo preco: R$" + produto.get(i).preco);
                            System.out.println("Estoque mantido: " + produto.get(i).quantidade);
                            
                            break;

                        }
                        
                        System.out.println("O preco deve ser maior que zero. Digite Novamente:");

                    }


                }
            }

            if(!produtoEditado){
                System.out.println("Produto não encontrado.");
            }

            break;

              case 8:
                boolean  encontrouReposicao = false;
                
                System.out.println("======PRODUTOS PARA REPOSIÇÃO======");

                for(int i = 0; i < produto.size(); i++){
                    if(produto.get(i).quantidade <= 2){

                        System.out.println("Produto: " + produto.get(i).nome);
                        System.out.println("Estoque atual: " + produto.get(i).quantidade);
                        System.out.println("-------------------------------------------");

                        encontrouReposicao = true;

                    }
                }

                if(!encontrouReposicao){
                    System.out.println("Nenhum produto precisa de reposição.");

                }
                     break;

                default:
                    System.out.println("Opção inválida.");
                    break;

              case 9:
    File arquivoVendas = new File("vendas.txt");

    if (!arquivoVendas.exists()) {
        System.out.println("Nenhuma venda registrada.");
    } else {
        try (BufferedReader leitor =
                 new BufferedReader(new FileReader(arquivoVendas))) {

            String linha;
            boolean encontrouVenda = false;

            System.out.println("======= HISTÓRICO DE VENDAS =======");

            while ((linha = leitor.readLine()) != null) {
                String[] dados = linha.split(";");

                if (dados.length == 4) {
                    System.out.println("Produto: " + dados[0]);
                    System.out.println("Quantidade vendida: " + dados[1]);
                    System.out.println("Preço unitário: R$ " + dados[2]);
                    System.out.println("Valor total: R$ " + dados[3]);
                    System.out.println("----------------------------------");

                    encontrouVenda = true;
                }
            }

            if (!encontrouVenda) {
                System.out.println("Nenhuma venda registrada.");
            }

        } catch (IOException e) {
            System.out.println("Erro ao ler o histórico: " + e.getMessage());
        }
    }

    break;
            }

        } while (opcao != 3);

      scanner.close();
          
    }

   public static boolean salvarProdutos(ArrayList<Produto> produto) {
    try (FileWriter escritor = new FileWriter("produtos.txt")) {

        for (int i = 0; i < produto.size(); i++) {
            Produto p = produto.get(i);

            escritor.write(
                p.nome + ";" + p.preco + ";" + p.quantidade
                + System.lineSeparator()
            );
        }

        System.out.println("Produtos salvos com sucesso!");
        return true;

    } catch (IOException e) {
        System.out.println("Erro ao salvar os produtos: " + e.getMessage());
        return false;
    }
}
    public static void carregarProdutos(ArrayList<Produto> produto) {
    File arquivo = new File("produtos.txt");

    if (!arquivo.exists()) {
        return;
    }

    try (BufferedReader leitor = new BufferedReader(new FileReader(arquivo))) {
        String linha;

        while ((linha = leitor.readLine()) != null) {
            String[] dados = linha.split(";", -1);

            if (dados.length == 3 && !dados[0].trim().isEmpty()) {
                String nome = dados[0];
                double preco = Double.parseDouble(dados[1]);
                int quantidade = Integer.parseInt(dados[2]);

                produto.add(new Produto(nome, preco, quantidade));
            }
        }

        System.out.println("Produtos carregados com sucesso!");

    } catch (IOException e) {
        System.out.println("Erro ao carregar os produtos.");
    } catch (NumberFormatException e) {
        System.out.println("Erro: existem dados inválidos no arquivo.");
    } 
}
    
    public static boolean registrarVenda(String nome, int quantidade, double preco) {
    try (FileWriter escritor = new FileWriter("vendas.txt", true)) {
        double valorTotal = quantidade * preco;

        escritor.write(
            nome + ";" + quantidade + ";" + preco + ";" + valorTotal
            + System.lineSeparator()
        );

        System.out.println("Venda registrada no historico!");
        return true;

    } catch (IOException e) {
        System.out.println("Erro ao registrar a venda: " + e.getMessage());
        return false;
    }
}


}

