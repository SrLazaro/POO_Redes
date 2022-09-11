public class Mensagem {

    public static void linha(){
        System.out.println("=============================");
    }

    public static void pacoteRecebido(String nome, Pacote pacote){

        linha();
        System.out.println("Computador: " + nome);
        System.out.println("Pacote recebido com sucesso!");
        System.out.println("Ip de Origem: " + pacote.getIpOrigem());
        System.out.println("Endereço MAC de Origem: " + pacote.getEnderecoMACOrigem());
        System.out.println("Ip de Destino: " + pacote.getIpDestino());
        System.out.println("Endereço MAC de Destino: " + pacote.getenderecoMACDestino());
        System.out.println("Payload:" + pacote.getPayload());
        linha();

    }

    
    
}
