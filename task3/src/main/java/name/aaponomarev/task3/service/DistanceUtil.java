package name.aaponomarev.task3.service;

import name.aaponomarev.task3.domain.branch.Branch;

public class DistanceUtil {

    final static int RADIUS = 6371000;

    private final double lat;
    private final double lon;

    public DistanceUtil(double lat, double lon) {
        this.lat = Math.toRadians(lat);
        this.lon = Math.toRadians(lon);
    }

    public long distance(Branch branch) {
        double rLat = Math.toRadians(branch.getLat());
        double rLon = Math.toRadians(branch.getLon());
        return Math.round(
                2 * RADIUS * Math.asin(Math.sqrt(
                sqrSin((rLat - this.lat)/2) +
                       Math.cos(rLat)*Math.cos(this.lat)
                               * sqrSin((rLon - this.lon)/2)
        )));
    }

    private Double sqrSin(Double d) {
        return Math.pow(Math.sin(d), 2);
    }
}
