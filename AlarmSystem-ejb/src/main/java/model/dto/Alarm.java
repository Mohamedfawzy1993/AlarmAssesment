package model.dto;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Alarm {
    private Long id;
    private String alarmId;
    private Timestamp eventTime;
    private String severity;
    private String description;
    private Timestamp ceaseTime;
    private String siteId;
    private Integer isActive;
    private Set<Comment> commentsById;
    private Set<Status> statusesById;

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
    @Column(name = "alarmID", nullable = false, length = 45)
    public String getAlarmId() {
        return alarmId;
    }

    public void setAlarmId(String alarmId) {
        this.alarmId = alarmId;
    }

    @Basic
    @Column(name = "eventTime", nullable = false)
    public Timestamp getEventTime() {
        return eventTime;
    }

    public void setEventTime(Timestamp eventTime) {
        this.eventTime = eventTime;
    }

    @Basic
    @Column(name = "severity", nullable = false, length = 1)
    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    @Basic
    @Column(name = "description", nullable = true, length = -1)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "ceaseTime", nullable = true)
    public Timestamp getCeaseTime() {
        return ceaseTime;
    }

    public void setCeaseTime(Timestamp ceaseTime) {
        this.ceaseTime = ceaseTime;
    }

    @Basic
    @Column(name = "siteID", nullable = false, length = 45)
    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    @Basic
    @Column(name = "isActive", nullable = false)
    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Alarm alarm = (Alarm) o;

        if (id != null ? !id.equals(alarm.id) : alarm.id != null) return false;
        if (alarmId != null ? !alarmId.equals(alarm.alarmId) : alarm.alarmId != null) return false;
        if (eventTime != null ? !eventTime.equals(alarm.eventTime) : alarm.eventTime != null) return false;
        if (severity != null ? !severity.equals(alarm.severity) : alarm.severity != null) return false;
        if (description != null ? !description.equals(alarm.description) : alarm.description != null) return false;
        if (ceaseTime != null ? !ceaseTime.equals(alarm.ceaseTime) : alarm.ceaseTime != null) return false;
        if (siteId != null ? !siteId.equals(alarm.siteId) : alarm.siteId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (alarmId != null ? alarmId.hashCode() : 0);
        result = 31 * result + (eventTime != null ? eventTime.hashCode() : 0);
        result = 31 * result + (severity != null ? severity.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (ceaseTime != null ? ceaseTime.hashCode() : 0);
        result = 31 * result + (siteId != null ? siteId.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "alarmByAlarmId"  , fetch = FetchType.EAGER , cascade = CascadeType.ALL )
    public Set<Comment> getCommentsById() {
        return commentsById;
    }

    public void setCommentsById(Set<Comment> commentsById) {
        this.commentsById = commentsById;
    }

    @OneToMany(mappedBy = "alarmByAlarmId" , fetch = FetchType.EAGER , cascade = CascadeType.ALL )
    public Set<Status> getStatusesById() {
        return statusesById;
    }

    public void setStatusesById(Set<Status> statusesById) {
        this.statusesById = statusesById;
    }

    public void addStatusesByID(Status status)
    {
        if(this.statusesById == null)
            this.statusesById = new HashSet<>();
        this.statusesById.add(status);
    }
}
