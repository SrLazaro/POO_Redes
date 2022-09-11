public class RegistroMAC {
    
    private String enderecoMAC;
    private PortaSwitch porta;


     public RegistroMAC(String enderecoMAC, PortaSwitch porta) {
        this.enderecoMAC = enderecoMAC;
        this.porta = porta;
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
     * @return PortaSwitch return the porta
     */
    public PortaSwitch getPorta() {
        return porta;
    }

    /**
     * @param porta the porta to set
     */
    public void setPorta(PortaSwitch porta) {
        this.porta = porta;
    }

}
