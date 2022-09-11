import java.util.ArrayList;

public class ProtocoloARP {

    public static String broadcastMAC = "ff:ff:ff:ff:ff:ff";
    public static String payloadSucesso = "Pacote recebido com Sucesso";
    private ArrayList<RegistroARP> tabelaARP = new ArrayList();

    /**
     * @return ArrayList<RegistroARP> return the tabelaARP
     */
    public ArrayList<RegistroARP> getTabelaARP() {
        return tabelaARP;
    }

    /**
     * @param tabelaARP the tabelaARP to set
     */
    public void setTabelaARP(ArrayList<RegistroARP> tabelaARP) {
        this.tabelaARP = tabelaARP;
    }

}
