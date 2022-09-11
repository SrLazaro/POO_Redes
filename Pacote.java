public class Pacote {

    private String ipOrigem;
    private String enderecoMACOrigem;
    private String ipDestino;
    private String enderecoMACDestino;
    private String payload;

    
    public Pacote(String ipOrigem, String enderecoMACOrigem, String ipDestino, String enderecoMACDestino, String payload) {
        this.ipOrigem = ipOrigem;
        this.enderecoMACOrigem = enderecoMACOrigem;
        this.ipDestino = ipDestino;
        this.enderecoMACDestino = enderecoMACDestino;
        this.payload = payload;
    }


    /**
     * @return String return the ipOrigem
     */
    public String getIpOrigem() {
        return ipOrigem;
    }

    /**
     * @param ipOrigem the ipOrigem to set
     */
    public void setIpOrigem(String ipOrigem) {
        this.ipOrigem = ipOrigem;
    }

    /**
     * @return String return the enderecoMACOrigem
     */
    public String getEnderecoMACOrigem() {
        return enderecoMACOrigem;
    }

    /**
     * @param enderecoMACOrigem the enderecoMACOrigem to set
     */
    public void setEnderecoMACOrigem(String enderecoMACOrigem) {
        this.enderecoMACOrigem = enderecoMACOrigem;
    }

    /**
     * @return String return the ipDestino
     */
    public String getIpDestino() {
        return ipDestino;
    }

    /**
     * @param ipDestino the ipDestino to set
     */
    public void setIpDestino(String ipDestino) {
        this.ipDestino = ipDestino;
    }

    /**
     * @return String return the enderecoMACDestino
     */
    public String getenderecoMACDestino() {
        return enderecoMACDestino;
    }

    /**
     * @param enderecoMACDestino the enderecoMACDestino to set
     */
    public void setenderecoMACDestino(String enderecoMACDestino) {
        this.enderecoMACDestino = enderecoMACDestino;
    }

    /**
     * @return String return the payload
     */
    public String getPayload() {
        return payload;
    }

    /**
     * @param payload the payload to set
     */
    public void setPayload(String payload) {
        this.payload = payload;
    }

}
