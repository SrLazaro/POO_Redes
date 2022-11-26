public class EnderecoRede {
    private String ip;
    private int cidr;

    public EnderecoRede(String ip, int cidr) {
        this.ip = ip;
        this.cidr = cidr;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getCidr() {
        return cidr;
    }

    public void setCidr(int cidr) {
        this.cidr = cidr;
    }
}
