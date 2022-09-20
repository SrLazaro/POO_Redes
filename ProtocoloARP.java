import java.util.HashSet;
import java.util.Set;

public class ProtocoloARP {

    public static String broadcastMAC = "ff:ff:ff:ff:ff:ff";
    public static String payloadSucesso = "Pacote recebido com Sucesso";
    private Set<RegistroARP> tabelaARP = new HashSet();

    public Set<RegistroARP> getTabelaARP() {
        return tabelaARP;
    }

    public void setTabelaARP(Set<RegistroARP> tabelaARP) {
        this.tabelaARP = tabelaARP;
    }
}
