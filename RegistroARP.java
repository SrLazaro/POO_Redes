public class RegistroARP {
    
    private String enderecoMAC;
    private String ip;

    public RegistroARP(String enderecoMAC, String ip) {
        this.enderecoMAC = enderecoMAC;
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
     * @return String return the ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * @param ip the ip to set
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

}
