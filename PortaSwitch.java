public class PortaSwitch {
    
    private Integer numeroIdentificacao;
    private String enderecoMAC;
    private Host host;

    public PortaSwitch(Integer numeroIdentificacao, String enderecoMAC) {
        this.numeroIdentificacao = numeroIdentificacao;
        this.enderecoMAC = enderecoMAC;
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
     * @return Host return the host
     */
    public Host getHost() {
        return host;
    }

    /**
     * @param host the host to set
     */
    public void setHost(Host host) {
        this.host = host;
    }


    /**
     * @return Integer return the numeroIdentificacao
     */
    public Integer getNumeroIdentificacao() {
        return numeroIdentificacao;
    }

    /**
     * @param numeroIdentificacao the numeroIdentificacao to set
     */
    public void setNumeroIdentificacao(Integer numeroIdentificacao) {
        this.numeroIdentificacao = numeroIdentificacao;
    }

}
