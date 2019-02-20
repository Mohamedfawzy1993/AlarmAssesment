package model.dto;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Status {

    private Long id;
    private String status;
    private Timestamp statusChangeTimestamp;
    private Alarm alarmByAlarmId;

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
    public Timestamp getStatusChangeTimestamp() {
        return statusChangeTimestamp;
    }

    public void setStatusChangeTimestamp(Timestamp statusChangeTimestamp) {
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
    public Alarm getAlarmByAlarmId() {
        return alarmByAlarmId;
    }

    public void setAlarmByAlarmId(Alarm alarmByAlarmId) {
        this.alarmByAlarmId = alarmByAlarmId;
    }
}
