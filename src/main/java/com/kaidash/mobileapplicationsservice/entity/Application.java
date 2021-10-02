package com.kaidash.mobileapplicationsservice.entity;

import com.kaidash.mobileapplicationsservice.validation.ContentRate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "applications")
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Pattern(regexp = "^\\S+.+\\S+$",message = "Application name shouln't start with whitespace character")
    @NotNull(message = "Application name is required")
    @Size(min = 1, max = 128, message = "Application name is required")
    @Column(name = "app_name")
    private String name;

    @Pattern(regexp = "^([0-9])\\.([0-9])\\.([0-9])$", message = "Must follow semantic versioning - e.g. 0.1.1 or 1.2.5")
    @NotNull(message = "Application version is required")
    @Column(name = "version")
    private String version;

    @ContentRate(value = "3 7 12 16 18", message = "Value for content rate should be one of the following: 3, 7, 12, 16, 18")
    @NotNull(message = "Application content rate is required")
    @Column(name = "content_rate")
    private int contentRate;

    public Application() {}

    public Application(String name, String version, int contentRate) {
        this.name = name;
        this.version = version;
        this.contentRate = contentRate;
    }

    public Application(int id, String name, String version, int contentRate) {
        this.id = id;
        this.name = name;
        this.version = version;
        this.contentRate = contentRate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getContentRate() {
        return contentRate;
    }

    public void setContentRate(int contentRate) {
        this.contentRate = contentRate;
    }

    @Override
    public String toString() {
        return "Application{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", version='" + version + '\'' +
                ", contentRate=" + contentRate +
                '}';
    }
}
