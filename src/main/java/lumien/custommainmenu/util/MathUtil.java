/*
 * Decompiled with CFR 0.148.
 *
 * Could not load the following classes:
 *  javax.vecmath.Vector2f
 */
package lumien.custommainmenu.util;

import javax.vecmath.Vector2f;

public class MathUtil {
    public static boolean isPointInPolygon(Vector2f point, Vector2f... polygonPoints) {
        for (int i = 0; i < polygonPoints.length; ++i) {
            Vector2f p1 = polygonPoints[i];
            Vector2f p2 = polygonPoints[i + 1 < polygonPoints.length ? i + 1 : 0];
            float a = -(p2.y - p1.y);
            float b = p2.x - p1.x;
            float c = -(a * p1.x + b * p1.y);
            float d = a * point.x + b * point.y + c;
            if (!(d < 0.0f)) continue;
            return false;
        }
        return true;
    }
}
