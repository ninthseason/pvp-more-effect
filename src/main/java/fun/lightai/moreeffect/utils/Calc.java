package fun.lightai.moreeffect.utils;

import net.minecraft.util.math.Vec3d;

public class Calc {
    public static double distanceSquare(Vec3d pos1, Vec3d pos2) {
        return (pos1.x - pos2.x) * (pos1.x - pos2.x) +
                (pos1.y - pos2.y) * (pos1.y - pos2.y) +
                (pos1.z - pos2.z) * (pos1.z - pos2.z);
    }
}
