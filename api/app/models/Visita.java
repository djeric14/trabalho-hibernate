package models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
@SequenceGenerator(name="SEQUENCE", sequenceName="visita_id_seq")
@Table(name = "visita")
public class Visita implements Serializable {

    private static final long serialVersionUID = -5672269537992623726L;

    @Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQUENCE")
    @Column(name = "id_visita")
    @Basic(optional = false)
    private Integer id;

    @ManyToOne(fetch=FetchType.LAZY)
    @Basic(optional = false)
    private Agenda agenda;

    @Column(name = "latitude")
    @Basic(optional = false)
    private Double latitude;

    @Column(name = "Longitude")
    @Basic(optional = false)
    private Double Longitude;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "hora_inicio")
    @Basic(optional = false)
    protected Date horaInicio;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "hora_fim")
    @Basic(optional = false)
    protected Date horaFim;

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

    public Agenda getAgenda() {
        return agenda;
    }

    public void setAgenda(Agenda agenda) {
        this.agenda = agenda;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return Longitude;
    }

    public void setLongitude(Double longitude) {
        Longitude = longitude;
    }

    public Date getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Date getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(Date horaFim) {
        this.horaFim = horaFim;
    }
}