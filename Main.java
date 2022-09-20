import java.util.ArrayList;

public class Main {

    private static Computador computador1;
    private static Computador computador2;
    private static String IP_COMPUTADOR_1 = "198.168.1.1";
    private static String IP_COMPUTADOR_2 = "192.168.1.2";

    private static Switch switch1;
    private static Switch switch2;

    public static void main(String[] args) {

        criar_switchs();
        criar_computadores();
        conectar_componentes();

        computador1.enviar_mensagem("Bora jogar?", IP_COMPUTADOR_2);
        computador2.enviar_mensagem("Sim!!!", IP_COMPUTADOR_1);

        System.out.println("Tá funcionando");

    }

    public static void criar_computadores() {

        computador1 = new Computador("Matheus", IP_COMPUTADOR_1, "H1");
        computador2 = new Computador("Adamor",IP_COMPUTADOR_2, "H2");

    }

    public static void criar_switchs() {

        criar_switch1();
        criar_switch2();
    }

    public static void criar_switch1() {

        PortaSwitch porta1 = new PortaSwitch(1, "PORTA:1:1");
        PortaSwitch porta2 = new PortaSwitch(2, "PORTA:1:2");

        ArrayList<PortaSwitch> portas = new ArrayList();
        portas.add(porta1);
        portas.add(porta2);

        switch1 = new Switch(portas);

    }

    public static void criar_switch2() {

        PortaSwitch porta1 = new PortaSwitch(1, "PORTA:2:1");
        PortaSwitch porta2 = new PortaSwitch(2, "PORTA:2:2");

        ArrayList<PortaSwitch> portas = new ArrayList();
        portas.add(porta1);
        portas.add(porta2);

        switch2 = new Switch(portas);

    }

    public static void conectar_componentes() {

        switch1.conectarHost(computador1, 1);
        switch1.conectarHost(switch2, 2);

        switch2.conectarHost(computador2, 1);
        switch2.conectarHost(switch1, 2);

        computador1.setConexao(switch1);
        computador2.setConexao(switch2);

    }

}