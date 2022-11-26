import java.util.ArrayList;
import java.util.List;

public class Roteador extends Switch {

    private List<RegistroRotas> tabelaRota = new ArrayList<>();

    public Roteador(ArrayList<PortaSwitch> portas) {
        super(portas);
    }

    @Override
    public void receberPacote(Pacote pacote) {
        if(verificarIpRota(pacote.getIpDestino())){

        }
    }

    private boolean verificarIpRota(String ipDestino) {
        boolean resultado = false;
        return VerificadorIp.verificarListaRedes(ipDestino,tabelaRota);
    }

}
