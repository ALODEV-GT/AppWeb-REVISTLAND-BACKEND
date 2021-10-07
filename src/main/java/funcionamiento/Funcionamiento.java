package funcionamiento;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class Funcionamiento {
    protected HttpServletRequest request;
    protected HttpServletResponse response;
    
    protected Funcionamiento(HttpServletRequest request, HttpServletResponse response){
        this.request = request;
        this.response = response;
    }
    
    public abstract void distribuirPost();
    
    public abstract void distribuirGet(); 
    
}
