import java.util.ArrayList;

public class Switch implements Conexao, ProtocoloARPImplementacao {

    private ArrayList<PortaSwitch> portas = new ArrayList();
    private ArrayList<RegistroMAC> tabelaMAC = new ArrayList();


    public Switch(ArrayList<PortaSwitch> portas) {
        this.portas = portas;
    }

    @Override
    public boolean verificarEnderecoMAC(String enderecoMAC, Host host) {

        boolean souEu = false;

         for (PortaSwitch porta : this.portas){

            if(porta.getHost() != host){
                if(porta.getHost().verificarEnderecoMAC(enderecoMAC, porta.getHost())){
                    souEu = true;
                    break;
                }
            }

        }

        return souEu;
   
    }

    @Override
    public void broadcast(Pacote pacote) {
        
        System.out.println("Realizando Broadcast...");

        for(PortaSwitch porta: this.portas){

            if(!porta.getHost().verificarEnderecoMAC(pacote.getEnderecoMACOrigem(), this)){

                Host host = porta.getHost();
                host.receberPacote(pacote);

            }
        }

    }

    @Override
    public void receberPacote(Pacote pacote) {

        gravarNaTabelaMac(pacote);

        if(pacote.getenderecoMACDestino().equals(ProtocoloARP.broadcastMAC)){
            broadcast(pacote);
        }else{
            enviarPacote(pacote);
        }
        
    }

    public void gravarNaTabelaMac(Pacote pacote){

        for (PortaSwitch porta : this.portas){
            if(porta.getHost().verificarEnderecoMAC(pacote.getEnderecoMACOrigem(), this)){
                RegistroMAC registroMAC = new RegistroMAC(pacote.getEnderecoMACOrigem(), porta);
                this.tabelaMAC.add(registroMAC);
                break;
            }
        }

    }

    @Override
    public void enviarPacote(Pacote pacote) {

        for(RegistroMAC registroMAC : this.tabelaMAC){
            if(registroMAC.getEnderecoMAC().equals(pacote.getenderecoMACDestino())){
                registroMAC.getPorta().getHost().receberPacote(pacote);
                break;
            }
        }
        
    }

    public void conectarHost(Host host, Integer numeroPorta){

        for(PortaSwitch porta: this.portas){
            if (porta.getNumeroIdentificacao() == numeroPorta){
                porta.setHost(host);
                break;
            }
        }

    }

    /**
     * @return ArrayList<PortaSwitch> return the portas
     */
    public ArrayList<PortaSwitch> getPortas() {
        return portas;
    }

    /**
     * @param portas the portas to set
     */
    public void setPortas(ArrayList<PortaSwitch> portas) {
        this.portas = portas;
    }

    /**
     * @return ArrayList<RegistroMAC> return the tabelaMAC
     */
    public ArrayList<RegistroMAC> getTabelaMAC() {
        return tabelaMAC;
    }

    /**
     * @param tabelaMAC the tabelaMAC to set
     */
    public void setTabelaMAC(ArrayList<RegistroMAC> tabelaMAC) {
        this.tabelaMAC = tabelaMAC;
    }

}
