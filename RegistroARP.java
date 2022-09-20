import java.util.Objects;

public class RegistroARP {
    
    private String enderecoMAC;
    private String ip;

    public RegistroARP(String enderecoMAC, String ip) {
        this.enderecoMAC = enderecoMAC;
        this.ip = ip;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RegistroARP)) return false;
        RegistroARP that = (RegistroARP) o;
        return getEnderecoMAC().equals(that.getEnderecoMAC()) && getIp().equals(that.getIp());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEnderecoMAC(), getIp());
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
