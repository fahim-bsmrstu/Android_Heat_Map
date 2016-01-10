package com.example.klayton.need;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Klayton on 11-Jan-16.
 */
public class SphericalMercatorProjection {

    final double mWorldWidth;

    public SphericalMercatorProjection(final double worldWidth) {
        mWorldWidth = worldWidth;
    }

    @SuppressWarnings("deprecation")
    public Point toPoint(final LatLng latLng) {
        final double x = latLng.longitude / 360 + .5;
        final double siny = Math.sin(Math.toRadians(latLng.latitude));
        final double y = 0.5 * Math.log((1 + siny) / (1 - siny)) / -(2 * Math.PI) + .5;

        return new Point(x * mWorldWidth, y * mWorldWidth);
    }

    public LatLng toLatLng(com.example.klayton.need.Point point) {
        final double x = point.x / mWorldWidth - 0.5;
        final double lng = x * 360;

        double y = .5 - (point.y / mWorldWidth);
        final double lat = 90 - Math.toDegrees(Math.atan(Math.exp(-y * 2 * Math.PI)) * 2);

        return new LatLng(lat, lng);
    }

}