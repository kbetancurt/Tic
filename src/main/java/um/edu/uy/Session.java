package um.edu.uy;


import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import um.edu.uy.business.AirlaneMgr;
import um.edu.uy.persistence.AirlaneRepository;

@Getter

public final class Session {


    private static Session instance = null;
    private String user;
    private String role;
    private String airport;
    private long airline;

    private Session() {
    }

    public static Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }

    public void setUser(String user) {
        this.user = user;

    }

    public void setAirline(long airline) {
        this.airline = airline;
    }



    public void setRole(String role) {
        this.role = role;
    }

    public void setAirport(String airport) {
        this.airport = airport;
    }

    public void destroy() {
        this.user = null;
        this.role = null;
        this.airport = null;
    }


}
