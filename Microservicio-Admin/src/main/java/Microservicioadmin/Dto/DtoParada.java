package Microservicioadmin.Dto;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DtoParada {
	
	private int id;

    private String latitud;

    private String longitud;

    private int monopatines;


    public DtoParada(int id, String latitud, String longitud, int monopatines) {
        this.id = id;
        this.latitud = latitud;
        this.longitud = longitud;
        this.monopatines = monopatines;
    }

    public int getId() {
        return id;
    }

    public String getLatitud() {
        return latitud;
    }

    public String getLongitud() {
        return longitud;
    }

	public int getMonopatines() {
		return monopatines;
	}

	public void setMonopatines(int monopatines) {
		this.monopatines = monopatines;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}

	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}

}
