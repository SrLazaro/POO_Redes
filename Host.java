import java.util.ArrayList;

public interface Host {
    
    public boolean verificarEnderecoMAC(String enderecoMAC, Host host);
    public void receberPacote(Pacote pacote);
    public void enviarPacote(Pacote pacote);

}
