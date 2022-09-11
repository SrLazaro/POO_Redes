import java.util.ArrayList;

public class Computador implements Host{
    
    private String nome;
    private String ip;
    private String enderecoMAC;
    private ProtocoloARP protocolo;
    private Conexao conexao;


    public Computador(String nome, String ip, String enderecoMac) {
        this.nome = nome;
        this.ip = ip;
        this.enderecoMAC = enderecoMac;
        this.protocolo = new ProtocoloARP();
    }

    public void enviar_mensagem(String mensagem, String ipDestino) {

        if (this.ip.equals(ipDestino)){
            System.out.println("Erro: Ip de Destino igual o de Origem!");
        }else{

        System.out.println("Enviando mensagem...");

        String enderecoMACDestino = resgatarEnderecoMacDestino(ipDestino);

        Pacote pacote = new Pacote( this.getIp(), this.getEnderecoMac(), ipDestino, enderecoMACDestino, mensagem);

        enviarPacote(pacote);

        }

    }

    public String resgatarEnderecoMacDestino(String ipDestino){

        System.out.println("Buscando Endereço MAC de Destino...");

        String enderecoMACDestino = null;

        enderecoMACDestino = resgatarPorTabelaMac(ipDestino);
        if (enderecoMACDestino == null){
            enderecoMACDestino = resgatarPorConexao(ipDestino);
        }

        return enderecoMACDestino;

    }

    public String resgatarPorTabelaMac(String ipDestino){

        String enderecoMACDestino = null;

        System.out.println("Buscando Endereço MAC de Destino por Tabela MAC...");

        ArrayList<RegistroARP> tabelaARP = new ArrayList();

        tabelaARP = this.protocolo.getTabelaARP();

        if(!tabelaARP.isEmpty()){
            for(RegistroARP registroARP : tabelaARP ){
                if (registroARP.getIp().equals(ipDestino)){
                    enderecoMACDestino = registroARP.getEnderecoMAC();
                    break;
                }
            }
        }

        return enderecoMACDestino; 

    }

    public String resgatarPorConexao(String ipDestino){

        System.out.println("Buscando Endereço MAC de Destino por Conexão...");

        Pacote pacote_envio = new Pacote( this.getIp(), this.getEnderecoMac(), ipDestino, ProtocoloARP.broadcastMAC, "Protocolo ARP");

        this.conexao.receberPacote(pacote_envio);
        
        return this.resgatarPorTabelaMac(ipDestino); 

    }

    @Override
    public boolean verificarEnderecoMAC(String enderecoMAC){

        boolean souEu = false;

        if (this.enderecoMAC.equals(enderecoMAC)){
            souEu = true;
        }

        return souEu;

    }

    public void enviarPacote(Pacote pacote){
        this.conexao.receberPacote(pacote);
    }

    public void receberPacote(Pacote pacote){

        gravarNaTabelaARP(pacote.getEnderecoMACOrigem(), pacote.getIpOrigem());

        Mensagem.pacoteRecebido(this.nome, pacote);

        if(!pacote.getPayload().equals(ProtocoloARP.payloadSucesso)){

            Pacote pacote_confirmacao = new Pacote(this.ip, this.enderecoMAC, pacote.getIpOrigem(), pacote.getEnderecoMACOrigem(), ProtocoloARP.payloadSucesso
            );

            enviarPacote(pacote_confirmacao);

        }

    }

    private void gravarNaTabelaARP(String enderecoMac, String ip){

        RegistroARP registroARP = new RegistroARP(enderecoMac, ip);
        this.protocolo.getTabelaARP().add(registroARP);

    }

    public String getEnderecoMac() {
        return enderecoMAC;
    }

    public void setEnderecoMac(String enderecoMac) {
        this.enderecoMAC = enderecoMac;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * @return String return the enderecoMAC
     */
    public String getEnderecoMAC() {
        return enderecoMAC;
    }

    /**
     * @param enderecoMAC the enderecoMAC to set
     */
    public void setEnderecoMAC(String enderecoMAC) {
        this.enderecoMAC = enderecoMAC;
    }

    /**
     * @return ProtocoloARP return the protocolo
     */
    public ProtocoloARP getProtocolo() {
        return protocolo;
    }

    /**
     * @param protocolo the protocolo to set
     */
    public void setProtocolo(ProtocoloARP protocolo) {
        this.protocolo = protocolo;
    }

    /**
     * @return Conexao return the conexao
     */
    public Conexao getConexao() {
        return conexao;
    }

    /**
     * @param conexao the conexao to set
     */
    public void setConexao(Conexao conexao) {
        this.conexao = conexao;
    }

    /**
     * @return String return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

}
