package de.ingoschindler.wild.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.math.BigDecimal;

/******************************************************************************
 * Compilation: javac Location.java Execution: java Location
 *
 * Immutable data type for a named location: name and (latitude, longitude).
 *
 * % java LocationTest 172.367 miles from PRINCETON_NJ (40.366633, 74.640832) to
 * ITHACA_NY (42.443087, 76.488707)
 *
 ******************************************************************************/

@Embeddable
public class Location implements Serializable {
    private static final long serialVersionUID = -4868085980882684433L;

    @Column(precision = 8, scale = 4)
    private BigDecimal longitude;

    @Column(precision = 8, scale = 4)
    private BigDecimal latitude;

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

}