package model.entities;


import javax.json.bind.annotation.JsonbDateFormat;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
public class Status {

    private Long id;
    private String status;
    private LocalDateTime statusChangeTimestamp;
    private transient Alarm alarmByAlarmId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "status", nullable = false, length = 45)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "statusChangeTimestamp", nullable = false)
    @JsonbDateFormat("yyyy-MM-dd'T'HH:mm:ss")
    public LocalDateTime getStatusChangeTimestamp() {
        return statusChangeTimestamp;
    }

    public void setStatusChangeTimestamp(LocalDateTime statusChangeTimestamp) {
        this.statusChangeTimestamp = statusChangeTimestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Status status1 = (Status) o;

        if (id != null ? !id.equals(status1.id) : status1.id != null) return false;
        if (status != null ? !status.equals(status1.status) : status1.status != null) return false;
        if (statusChangeTimestamp != null ? !statusChangeTimestamp.equals(status1.statusChangeTimestamp) : status1.statusChangeTimestamp != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (statusChangeTimestamp != null ? statusChangeTimestamp.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "Alarm_id", referencedColumnName = "id", nullable = false)
    @JsonbTransient
    public Alarm getAlarmByAlarmId() {
        return alarmByAlarmId;
    }

    public void setAlarmByAlarmId(Alarm alarmByAlarmId) {
        this.alarmByAlarmId = alarmByAlarmId;
    }
}
