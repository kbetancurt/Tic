package um.edu.uy;


import lombok.Getter;
import um.edu.uy.business.entities.Vuelo;


@Getter

public final class Session {


    private static Session instance = null;
    private String user;
    private String role;
    private String  airport;
    private long airline;
    private Vuelo vuelo;

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

    public void setVuelo(Vuelo vuelo) {
        this.vuelo = vuelo;
    }

    public void reset() {
        this.user = null;
        this.role = null;
        this.airport = null;
        this.airline = -1;
        this.vuelo = null;
    }



}
