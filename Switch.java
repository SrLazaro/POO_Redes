import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Switch implements Conexao, ProtocoloARPImplementacao {

    private ArrayList<PortaSwitch> portas = new ArrayList();
    private Set<RegistroMAC> tabelaMAC = new HashSet<>();


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

    public Set<RegistroMAC> getTabelaMAC() {
        return tabelaMAC;
    }

    public void setTabelaMAC(Set<RegistroMAC> tabelaMAC) {
        this.tabelaMAC = tabelaMAC;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Switch)) return false;
        Switch aSwitch = (Switch) o;
        return getPortas().equals(aSwitch.getPortas()) && getTabelaMAC().equals(aSwitch.getTabelaMAC());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPortas(), getTabelaMAC());
    }
}
