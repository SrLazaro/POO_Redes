import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VerificadorIp {

    public static List<EnderecoRede> listaEnderecoRede = new ArrayList<>();

    public static void main(String args[]) {

        int opcao = 6;

        while (opcao != 0) {
            System.out.println("---------OPCOES--------------");
            System.out.println("1 - DESCOBRIR PRIMEIRO E ULTIMO IP");
            System.out.println("2 - ENCONTRAR SUBREDES");
            System.out.println("3 - VERIFICAR SE IP PERTENCE A ENDERECO DE REDE");
            System.out.println("4 - VERIFICAR LISTA DE REDE PERTENCE AO IP");
            System.out.println("0 - SAIR");
            System.out.println("digite sua opcao: ");

            Scanner in = new Scanner(System.in);
            opcao = Integer.parseInt(in.nextLine());


            if (opcao == 1) {
                System.out.println("Insira o IP: ");
                String ip = in.nextLine();

                System.out.print("Insira o CIDR " + ip + "/");
                int cidr = Integer.parseInt(in.nextLine());

                getPrimeiroUltimoIp(ip, cidr);
            }
            if (opcao == 2) {
                System.out.println("Insira o IP: ");
                String ip = in.nextLine();

                System.out.print("Insira o CIDR " + ip + "/");
                int cidr = Integer.parseInt(in.nextLine());

                System.out.println("Insira o valor de N: ");
                int n = Integer.parseInt(in.nextLine());

                indicarSubRedes(ip, n, cidr);
            }
            if (opcao == 3) {
                System.out.println("Insira o IP: ");
                String ip = in.nextLine();

                System.out.println("Insira o endereco de rede: ");
                String rede = in.nextLine();

                System.out.print("Insira o CIDR " + rede + "/");
                int cidr = Integer.parseInt(in.nextLine());

                boolean pertence = verificaRedePertenceIp(ip, rede, cidr);

                if (pertence) {
                    System.out.println("Pertence!");
                } else {
                    System.out.println("Nao pertence");
                }
            }

            if (opcao == 4) {
                listaEnderecoRede = new ArrayList<>();
                ;

                while (true) {
                    System.out.println("Insira o Endereco de rede: ");
                    String ip = in.nextLine();

                    System.out.println("Insira o cidr: ");
                    int cidr = Integer.parseInt(in.nextLine());

                    EnderecoRede enderecoRede = new EnderecoRede(ip, cidr);
                    listaEnderecoRede.add(enderecoRede);

                    System.out.println("Deseja Adicionar outra? ");
                    System.out.println("1 - Sim | 2 - Nao ");
                    int continua = Integer.parseInt(in.nextLine());

                    if (continua == 2) {
                        break;
                    }

                }

                System.out.println("Insira o Endereco de ip: ");
                String ip = in.nextLine();

                //verificarListaRedes(ip);


            }

            if (opcao == 0) {
                break;
            }
        }
    }

    public static boolean verificarListaRedes(String ip, List<RegistroRotas> enderecoRedeList) {
        boolean resultado = false;

        List<RegistroRotas> contemIp = new ArrayList<>();

        RegistroRotas enderecoRedeEspecifico = null;

        for (RegistroRotas registroRotas : enderecoRedeList) {
            boolean pertence = verificaRedePertenceIp(ip, registroRotas.getEnderecoRede().getIp(),
                    registroRotas.getEnderecoRede().getCidr());
            if (pertence) {
                contemIp.add(registroRotas);
            }
        }

        for (RegistroRotas contido : contemIp) {
            if (enderecoRedeEspecifico == null) {
                enderecoRedeEspecifico = contido;
            } else {
                if (enderecoRedeEspecifico.getEnderecoRede().getCidr() < contido.getEnderecoRede().getCidr()) {
                    enderecoRedeEspecifico = contido;
                }
            }
        }

        if (enderecoRedeEspecifico != null){
            resultado = true;
        }

        return resultado;
    }

    private static boolean verificaRedePertenceIp(String ip, String rede, int cidr) {
        int ipPossiveis = gerarIpPossiveis(cidr);

        boolean pertence = false;

        for (int qnt = 1; qnt <= ipPossiveis; qnt++) {
            String[] nums = rede.split("\\.");
            int i = (Integer.parseInt(nums[0]) << 24 | Integer.parseInt(nums[2]) << 8
                    | Integer.parseInt(nums[1]) << 16 | Integer.parseInt(nums[3])) + qnt;

            if ((byte) i == -1) i++;


            String ipDaRede = String.format("%d.%d.%d.%d", i >>> 24 & 0xFF, i >> 16 & 0xFF,
                    i >> 8 & 0xFF, i >> 0 & 0xFF);

            if (ip.equals(ipDaRede)) {
                pertence = true;
                break;
            }
        }

        return pertence;
    }

    private static void indicarSubRedes(String ip, int n, int cidr) {
        double resultado = log(2, Integer.parseInt(String.valueOf(n)));

        double qtdBit = (int) Math.ceil(resultado);

        double subRedes = Math.pow(2, qtdBit);

        double novoCidr = cidr + qtdBit;

        String ipNovo = "";

        for (double subRede = 1; subRede <= subRedes; subRede++) {
            if (subRede == 1) {
                ipNovo = ip;
            } else {
                ipNovo = getIpIncrementado(ipNovo, gerarIpPossiveis((int) novoCidr));
            }
            System.out.println(ipNovo + "/" + (int) novoCidr);
        }
    }

    private static double log(double base, double valor) {
        return Math.log(valor) / Math.log(base);
    }


    public static void getPrimeiroUltimoIp(String ip, int cidr) {
        int ip_possivel = gerarIpPossiveis(cidr);

        System.out.println("ultimo ip: " + getIpIncrementado(ip, ip_possivel));

        System.out.println("primeiro ip: " + getIpIncrementado(ip, 1));

    }

    private static String getIpIncrementado(String ip, int ip_possivel) {
        String[] nums = ip.split("\\.");
        int i = (Integer.parseInt(nums[0]) << 24 | Integer.parseInt(nums[2]) << 8
                | Integer.parseInt(nums[1]) << 16 | Integer.parseInt(nums[3])) + ip_possivel;

        if ((byte) i == -1) i++;

        return String.format("%d.%d.%d.%d", i >>> 24 & 0xFF, i >> 16 & 0xFF,
                i >> 8 & 0xFF, i >> 0 & 0xFF);
    }

    private static int gerarIpPossiveis(int cidr) {
        int potencia = 32 - cidr;

        int ip_possivel = (int) Math.pow(2, potencia);

        return ip_possivel;
    }


}
